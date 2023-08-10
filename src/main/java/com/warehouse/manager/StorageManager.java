package com.warehouse.manager;

import com.warehouse.dao.StorageDao;
import com.warehouse.dto.StorageDTO;
import com.warehouse.entity.DiscardedProduct;
import com.warehouse.entity.Product;
import com.warehouse.entity.Storage;
import com.warehouse.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class StorageManager {

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private DiscardedProductManager discardedProductManager;

    @Autowired
    private Helper helper;

    public List<Storage> getStorages() {
        return storageDao.getList();
    }

    public Storage getById(int id) {
        return storageDao.getById(id, Storage.class);
    }

    public void delete(Storage storage) {
        storageDao.delete(storage);
    }

    public void update(Storage storage) {
        storageDao.update(storage);
    }

    public void save(Storage storage) {
        List<Storage> storageList = getStorages();
        boolean unique = true;

        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getProduct().equals(storage.getProduct())) {
                storageList.get(i).setQuantity(storageList.get(i).getQuantity() + storage.getQuantity());
                update(storageList.get(i));
                unique = false;
            }
        }
        if (unique) {
            storageDao.save(storage);
        }
    }

    public List<Product> getStorageProducts() {
        List<Product> storageProducts = new ArrayList<>();
        for (int i = 0; i < getStorages().size(); i++) {
            storageProducts.add(getStorages().get(i).getProduct());
        }
        return storageProducts;
    }

    public Storage getByProductId(int productId) {
        return storageDao.getByProductID(productId);
    }

    public void discardProduct(StorageDTO storageDTO) {
        Storage storage = getById(storageDTO.getStorageId());
        boolean unique = true;

        for (int i = 0; i < discardedProductManager.getDiscardedProducts().size(); i++) {
            DiscardedProduct discardedProduct = discardedProductManager.getDiscardedProducts().get(i);

            if (discardedProduct.getProduct().equals(storage.getProduct())) {
                unique = false;
                discardedProduct.setQuantity(discardedProduct.getQuantity() + storageDTO.getQuantity());
                discardedProductManager.update(discardedProduct);
            }
        }
        if (unique) {
            DiscardedProduct discardedProduct = new DiscardedProduct();
            discardedProduct.setProduct(storage.getProduct());
            discardedProduct.setQuantity(storageDTO.getQuantity());
            discardedProductManager.saveDiscardedProduct(discardedProduct);
        }
        if (storage.getQuantity() == storageDTO.getQuantity()) {
            delete(storage);
            log.info("Товар '" + storage.getProduct().getName() + "' закончился на складе!");
        } else {
            storage.setQuantity(storage.getQuantity() - storageDTO.getQuantity());
            update(storage);
            if (storage.getQuantity() < 20) {
                log.info("Товар '" + storage.getProduct().getName() + "' в количестве " + storage.getQuantity() + " штук, " +
                        "необходимо заказать еще!");
            }
        }
    }

    public void returnProduct(StorageDTO storageDTO) {
        DiscardedProduct discardedProduct = discardedProductManager.getById(storageDTO.getStorageId());
        boolean unique = true;

        for (int i = 0; i < storageDao.getList().size(); i++) {
            Storage storage = storageDao.getList().get(i);

            if (storage.getProduct().equals(discardedProduct.getProduct())) {
                unique = false;
                storage.setQuantity(storage.getQuantity() + storageDTO.getQuantity());
                storageDao.update(storage);
            }
        }
        if (unique) {
            Storage storage = new Storage();
            storage.setProduct(discardedProduct.getProduct());
            storage.setQuantity(storageDTO.getQuantity());
            storageDao.save(storage);
        }
        if (discardedProduct.getQuantity() == storageDTO.getQuantity()) {
            discardedProductManager.delete(discardedProduct);
        } else {
            discardedProduct.setQuantity(discardedProduct.getQuantity() - storageDTO.getQuantity());
            discardedProductManager.update(discardedProduct);
        }
    }

    public List<String> getStorageLog() {
        File file = new File("myApp.log");
        ArrayList<String> logs = new ArrayList<>();
        String line;
        BufferedReader bufferedReader = null;

        try {
            Reader reader = new FileReader(file);
            try (reader) {
                bufferedReader = new BufferedReader(reader);
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    if (line.contains("c.w.manager.ProductOrderManager") || line.contains("c.warehouse.manager.StorageManager")) {
                        try {
                            logs.add(helper.logParse(line));
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException exception) {
                        log.error(exception.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            log.error(exception.getMessage());
        }
        return logs;
    }

    public int getTotalQuantity() {
        List<Storage> storageProducts = getStorages();
        int totalQuantity = 0;

        for (Storage storageProduct : storageProducts) {
            totalQuantity += storageProduct.getQuantity();
        }
        return totalQuantity;
    }

    public void writeToExcelSheet() throws IOException {
        String[] row_heading = {"№", "Код", "Наименование", "Категория", "Количество"};

        XSSFWorkbook workbook = new XSSFWorkbook();

        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        CellStyle innerStyle = workbook.createCellStyle();
        innerStyle.setBorderTop(BorderStyle.THIN);
        innerStyle.setBorderBottom(BorderStyle.THIN);
        innerStyle.setBorderLeft(BorderStyle.THIN);
        innerStyle.setBorderRight(BorderStyle.THIN);

        XSSFSheet stockSheet = workbook.createSheet("Остатки на складе");
        Row titleRow = stockSheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Остатки по складу на " + helper.formatDate(new Date()));
        titleCell.setCellStyle(headerStyle);
        stockSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, row_heading.length - 1));

        Row stockHeaderRow = stockSheet.createRow(1);
        for (int i = 0; i < row_heading.length; i++) {
            Cell cell = stockHeaderRow.createCell(i);
            cell.setCellValue(row_heading[i]);
            cell.setCellStyle(headerStyle);
        }

        for (int i = 0; i < getStorages().size(); i++) {
            Row dataRow = stockSheet.createRow(i + 2);
            dataRow.createCell(0).setCellValue(i + 1);
            dataRow.createCell(1).setCellValue(getStorages().get(i).getProduct().getCode());
            dataRow.createCell(2).setCellValue(getStorages().get(i).getProduct().getName());
            dataRow.createCell(3).setCellValue(getStorages().get(i).getProduct().getProductType().getCategory());
            dataRow.createCell(4).setCellValue(getStorages().get(i).getQuantity());

            for (int j = 0; j < row_heading.length; j++) {
                dataRow.getCell(j).setCellStyle(innerStyle);
            }
        }

        for (int i = 0; i < row_heading.length; i++) {
            stockSheet.autoSizeColumn(i);
        }

        XSSFSheet discardedSheet = workbook.createSheet("Списанные товары");
        Row discardedTitleRow = discardedSheet.createRow(0);
        Cell discardedTitleCell = discardedTitleRow.createCell(0);
        discardedTitleCell.setCellValue("Отчет о списанных товарах на " + helper.formatDate(new Date()));
        discardedTitleCell.setCellStyle(headerStyle);
        discardedSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, row_heading.length - 1));

        Row discardedHeaderRow = discardedSheet.createRow(1);
        for (int i = 0; i < row_heading.length; i++) {
            Cell cell = discardedHeaderRow.createCell(i);
            cell.setCellValue(row_heading[i]);
            cell.setCellStyle(headerStyle);
        }
        for (int i = 0; i < discardedProductManager.getDiscardedProducts().size(); i++) {
            Row dataRow = discardedSheet.createRow(i + 2);
            dataRow.createCell(0).setCellValue(i + 1);
            dataRow.createCell(1).setCellValue(discardedProductManager.getDiscardedProducts().get(i).getProduct().getCode());
            dataRow.createCell(2).setCellValue(discardedProductManager.getDiscardedProducts().get(i).getProduct().getName());
            dataRow.createCell(3).setCellValue(discardedProductManager.getDiscardedProducts().get(i).getProduct().getProductType().getCategory());
            dataRow.createCell(4).setCellValue(discardedProductManager.getDiscardedProducts().get(i).getQuantity());

            for (int j = 0; j < row_heading.length; j++) {
                dataRow.getCell(j).setCellStyle(innerStyle);
            }
        }
        for (int i = 0; i < row_heading.length; i++) {
            discardedSheet.autoSizeColumn(i);
        }
        FileOutputStream out;

        File file = new File("C:\\Users\\123\\Desktop\\ОТЧЕТЫ\\Склад\\ОТЧЕТ_СКЛАД_" + helper.formatDate(new Date()) + ".xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
    }
}



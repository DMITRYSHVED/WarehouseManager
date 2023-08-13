package com.warehouse.manager;

import com.warehouse.dao.OrderStatusDao;
import com.warehouse.dao.ProductOrderDao;
import com.warehouse.dao.ProductOrderMapDao;
import com.warehouse.dto.ProductOrderDTO;
import com.warehouse.dto.ProductOrderMapDTO;
import com.warehouse.entity.OrderStatus;
import com.warehouse.entity.ProductOrder;
import com.warehouse.entity.ProductOrderMap;
import com.warehouse.entity.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductOrderManager {

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductOrderMapDao productOrderMapDao;

    @Autowired
    private OrderStatusDao orderStatusDao;

    @Autowired
    private ProductManager productManager;

    @Autowired
    private StorageManager storageManager;

    private List<ProductOrderMapDTO> productList = new ArrayList<>();

    public List<ProductOrder> getProductOrders() {
        return productOrderDao.getList();
    }

    public ProductOrder getById(int id) {
        return productOrderDao.findById(id, ProductOrder.class);
    }

    public void saveOrder(ProductOrderDTO productOrderDTO) {
        ProductOrder productOrder = new ProductOrder();
        ProductOrderMap productOrderMap = new ProductOrderMap();
        OrderStatus orderStatus = orderStatusDao.findById(1, OrderStatus.class);

        productOrder.setDate(productOrderDTO.getDate());
        productOrder.setClientName(productOrderDTO.getClientName());
        productOrder.setClientContact(productOrderDTO.getClientContact());
        productOrder.setClientAddress(productOrderDTO.getClientAddress());
        productOrder.setOrderStatus(orderStatus);
        productOrderDao.save(productOrder);
        for (int i = 0; i < productList.size(); i++) {
            productOrderMap.setProductOrder(productOrder);
            productOrderMap.setProduct(productManager.getById(productList.get(i).getProductId()));
            productOrderMap.setQuantity(productList.get(i).getQuantity());
            productOrderMapDao.save(productOrderMap);
        }
        productList.clear();
    }

    public void setUpProductList(ProductOrderMapDTO productOrderMapDTO) {
        boolean unique = true;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == productOrderMapDTO.getProductId()) {
                productList.get(i).setQuantity(productList.get(i).getQuantity() + productOrderMapDTO.getQuantity());
                unique = false;
            }
        }
        if (unique) {
            productList.add(productOrderMapDTO);
        }
    }

    public List<String> getProductNameList() {
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            productNames.add(productManager.getById(productList.get(i).getProductId()).getName() + " / " +
                    productList.get(i).getQuantity() + " шт.");
        }
        return productNames;
    }

    public void deleteProductOrder(ProductOrder productOrder) {
        List<ProductOrderMap> productOrderMapList = productOrderMapDao.getList();
        for (int i = 0; i < productOrderMapList.size(); i++) {
            if (productOrderMapList.get(i).getProductOrder().equals(productOrder)) {
                productOrderMapDao.delete(productOrderMapList.get(i));
            }
        }
        productOrderMapDao.delete(productOrder);
    }

    public void processOrder(ProductOrder productOrder) {
        List<ProductOrderMap> productOrderMapList = productOrderMapDao.getList();
        for (int i = 0; i < productOrderMapList.size(); i++) {
            if (productOrderMapList.get(i).getProductOrder().equals(productOrder)) {
                Storage storage = storageManager.getByProductId(productOrderMapList.get(i).getProduct().getId());
                int newQuantity = storage.getQuantity() - productOrderMapList.get(i).getQuantity();
                if (newQuantity == 0) {
                    storageManager.delete(storage);
                    log.info("Товар '" + storage.getProduct().getName() + "' закончился на складе!");
                } else {
                    storage.setQuantity(newQuantity);
                    storage.setProduct(productOrderMapList.get(i).getProduct());
                    storageManager.update(storage);
                    if (newQuantity < 20) {
                        log.info("Товар '" + storage.getProduct().getName() + "' в количестве " + newQuantity + " штук, " +
                                "необходимо заказать еще!");
                    }
                }
            }
        }
        productOrder.setOrderStatus(orderStatusDao.findById(2, OrderStatus.class));
        updateOrder(productOrder);
    }

    public void updateOrder(ProductOrder order) {
        productOrderDao.update(order);
    }

    public List<ProductOrderMapDTO> getProductList() {
        return productList;
    }

    public List<ProductOrderMap> getOrderedProducts() {
        List<ProductOrderMap> orderedProducts = new ArrayList<>();

        for (int i = 0; i < productOrderMapDao.getList().size(); i++) {
            if (getById(productOrderMapDao.getList().get(i).getProductOrder().getId()).getOrderStatus().getId() == 1) {
                orderedProducts.add(productOrderMapDao.getList().get(i));
            }
        }
        return orderedProducts;
    }

    public List<ProductOrderMap> getShippedProducts() {
        List<ProductOrderMap> shippedProducts = new ArrayList<>();

        for (int i = 0; i < productOrderMapDao.getList().size(); i++) {
            ProductOrderMap productOrderMap = productOrderMapDao.getList().get(i);
            ProductOrder productOrder = productOrderMap.getProductOrder();
            if (productOrder.getOrderStatus().getId() == 2) {
                boolean unique = true;
                for (int j = 0; j < shippedProducts.size(); j++) {
                    if (shippedProducts.get(j).getProduct().getId() == productOrderMap.getProduct().getId()) {
                        shippedProducts.get(j).setQuantity(shippedProducts.get(j).getQuantity() +
                                productOrderMap.getQuantity());
                        unique = false;
                        break;
                    }
                }
                if (unique) {
                    shippedProducts.add(productOrderMap);
                }
            }
        }
        return shippedProducts;
    }

    public void deleteOrderProducts() {
        productList.clear();
    }

    public List<String> getDeliveryProductLIst(ProductOrder productOrder) {
        List<String> orderProductList = new ArrayList<>();

        for (int i = 0; i < productOrderMapDao.getList().size(); i++) {
            ProductOrderMap productOrderMap = productOrderMapDao.getList().get(i);
            if (productOrderMap.getProductOrder().equals(productOrder)) {
                orderProductList.add(productOrderMap.getProduct().getName() + " / " + productOrderMap.getQuantity() +
                        " шт.");
            }
        }
        return orderProductList;
    }

}

package com.warehouse.manager;

import com.warehouse.dao.DeliveryDao;
import com.warehouse.dao.DeliveryProductMapDao;
import com.warehouse.dao.DeliveryStatusDao;
import com.warehouse.dto.DeliveryDTO;
import com.warehouse.dto.DeliveryProductMapDTO;
import com.warehouse.entity.Delivery;
import com.warehouse.entity.DeliveryProductMap;
import com.warehouse.entity.DeliveryStatus;
import com.warehouse.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryManager {

    @Autowired
    private DeliveryDao deliveryDao;

    @Autowired
    private DeliveryProductMapDao deliveryProductMapDao;

    @Autowired
    private ProductManager productManager;

    @Autowired
    private ProviderManager providerManager;

    @Autowired
    private DeliveryStatusDao deliveryStatusDao;

    @Autowired
    private StorageManager storageManager;

    private List<DeliveryProductMapDTO> productList = new ArrayList<>();


    public void saveDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        DeliveryProductMap deliveryProductMap = new DeliveryProductMap();
        DeliveryStatus deliveryStatus = deliveryStatusDao.findById(1, DeliveryStatus.class);//класс константы

        delivery.setDate(deliveryDTO.getDate());
        delivery.setProvider(providerManager.getById(deliveryDTO.getProviderId()));
        delivery.setDeliveryStatus(deliveryStatus);
        deliveryDao.save(delivery);
        for (int i = 0; i < productList.size(); i++) {
            deliveryProductMap.setDelivery(delivery);
            deliveryProductMap.setProduct(productManager.getById(productList.get(i).getProductId()));
            deliveryProductMap.setQuantity(productList.get(i).getQuantity());
            deliveryProductMapDao.save(deliveryProductMap);
        }
        productList.clear();
    }


    public Delivery getById(int id) {
        return deliveryDao.findById(id, Delivery.class);
    }

    public void updateDelivery(Delivery delivery) {
        deliveryDao.update(delivery);
    }

    public void setUpProductList(DeliveryProductMapDTO deliveryProductMapDTO) {
        DeliveryProductMap deliveryProductMap = new DeliveryProductMap();

        deliveryProductMap.setQuantity(deliveryProductMapDTO.getQuantity());
        deliveryProductMap.setProduct(productManager.getById(deliveryProductMapDTO.getProductId()));
        boolean unique = true;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductId() == deliveryProductMapDTO.getProductId()) {
                productList.get(i).setQuantity(productList.get(i).getQuantity() + deliveryProductMapDTO.getQuantity());
                unique = false;
            }
        }
        if (unique) {
            productList.add(deliveryProductMapDTO);
        }
    }

    public List<String> getProductList() {
        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            productNames.add(productManager.getById(productList.get(i).getProductId()).getName() + "  /  " +
                    productList.get(i).getQuantity() + " шт.");
        }
        return productNames;
    }

    public List<Delivery> getDeliveryList() {
        return deliveryDao.getList();
    }

    public void delete(Delivery delivery) {
        List<DeliveryProductMap> deliveryProductMapList = deliveryProductMapDao.getList();
        for (int i = 0; i < deliveryProductMapList.size(); i++) {
            if (deliveryProductMapList.get(i).getDelivery().equals(delivery)) {
                deliveryProductMapDao.delete(deliveryProductMapList.get(i));
            }
        }
        deliveryDao.delete(delivery);
    }

    public void takeDelivery(Delivery delivery) {
        delivery.setDeliveryStatus(deliveryStatusDao.findById(2, DeliveryStatus.class));
        List<DeliveryProductMap> deliveryProductMapList = deliveryProductMapDao.getList();
        for (int i = 0; i < deliveryProductMapList.size(); i++) {
            if (deliveryProductMapList.get(i).getDelivery().equals(delivery)) {
                Storage storage = new Storage();
                storage.setProduct(deliveryProductMapList.get(i).getProduct());
                storage.setQuantity(deliveryProductMapList.get(i).getQuantity());
                storageManager.save(storage);
            }
        }
        updateDelivery(delivery);
    }

    public void deleteProductList() {
        productList.clear();
    }

    public List<String> getDeliveryProductLIst(Delivery delivery) {
        List<String> deliveryProductList = new ArrayList<>();

        for (int i = 0; i < deliveryProductMapDao.getList().size(); i++) {
            DeliveryProductMap deliveryProductMap = deliveryProductMapDao.getList().get(i);
            if (deliveryProductMap.getDelivery().equals(delivery)) {
                deliveryProductList.add(deliveryProductMap.getProduct().getName() + " / " + deliveryProductMap.getQuantity() + " шт.");
            }
        }
        return deliveryProductList;
    }
}

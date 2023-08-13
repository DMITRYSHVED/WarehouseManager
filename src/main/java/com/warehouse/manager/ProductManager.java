package com.warehouse.manager;

import com.warehouse.dao.DeliveryProductMapDao;
import com.warehouse.dao.ProductDao;
import com.warehouse.dao.ProductOrderMapDao;
import com.warehouse.dto.ProductDTO;
import com.warehouse.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductTypeManager productTypeManager;

    @Autowired
    private StorageManager storageManager;

    @Autowired
    private DiscardedProductManager discardedProductManager;

    @Autowired
    private DeliveryProductMapDao deliveryProductMapDao;

    @Autowired
    private ProductOrderMapDao productOrderMapDao;


    public Product getById(int id) {
        return productDao.findById(id, Product.class);
    }

    public List<Product> getProducts() {
        return productDao.getList();
    }

    public void updateProduct(ProductDTO productDTO) {
        Product product = getById(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setProductType(productTypeManager.getById(productDTO.getProductType()));

        System.out.println(product);

        productDao.update(product);
    }

    public void save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setProductType(productTypeManager.getById(productDTO.getProductType()));
        productDao.save(product);
    }

    public boolean productIsUseless(int id) {
        boolean useless = true;

        for (int i = 0; i < storageManager.getStorages().size(); i++) {
            if (storageManager.getStorages().get(i).getProduct().getId() == id) {
                useless = false;
            }
        }
        for (int i = 0; i < discardedProductManager.getDiscardedProducts().size(); i++) {
            if (discardedProductManager.getDiscardedProducts().get(i).getProduct().getId() == id) {
                useless = false;
            }
        }
        for (int i = 0; i < deliveryProductMapDao.getList().size(); i++) {
            if (deliveryProductMapDao.getList().get(i).getProduct().getId() == id) {
                useless = false;
            }
        }
        for (int i = 0; i < productOrderMapDao.getList().size(); i++) {
            if (productOrderMapDao.getList().get(i).getProduct().getId() == id) {
                useless = false;
            }
        }
        return useless;
    }

    public void deleteProduct(Product product) {
        productDao.delete(product);
    }
}

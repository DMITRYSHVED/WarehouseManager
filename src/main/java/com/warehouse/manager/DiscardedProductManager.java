package com.warehouse.manager;

import com.warehouse.dao.DiscardedProductDao;
import com.warehouse.entity.DiscardedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscardedProductManager {

    @Autowired
    private DiscardedProductDao discardedProductDao;

    public List<DiscardedProduct> getDiscardedProducts() {
        return discardedProductDao.getList();
    }

    public void saveDiscardedProduct(DiscardedProduct discardedProduct) {
        discardedProductDao.save(discardedProduct);
    }

    public void update(DiscardedProduct discardedProduct) {
        discardedProductDao.update(discardedProduct);
    }

    public void delete(DiscardedProduct discardedProduct) {
        discardedProductDao.delete(discardedProduct);
    }

    public DiscardedProduct getById(int id) {
        return discardedProductDao.getById(id, DiscardedProduct.class);
    }
}

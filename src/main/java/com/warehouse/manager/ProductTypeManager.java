package com.warehouse.manager;

import com.warehouse.dao.ProductTypeDao;
import com.warehouse.entity.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeManager {

    @Autowired
    ProductTypeDao productTypeDao;

    public List<ProductType> getTypes() {
        return productTypeDao.getList();
    }

    public ProductType getById(int id) {
        return productTypeDao.getById(id, ProductType.class);
    }
}

package com.warehouse.manager;

import com.warehouse.dao.DeliveryDao;
import com.warehouse.dao.DeliveryProductMapDao;
import com.warehouse.dao.ProviderDao;
import com.warehouse.entity.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderManager {

    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private DeliveryProductMapDao deliveryProductMapDao;

    @Autowired
    private DeliveryDao deliveryDao;

    public List<Provider> getProviders() {
        return providerDao.getList();
    }

    public Provider getById(int id) {
        return providerDao.findById(id, Provider.class);
    }

    public void updateProvider(Provider provider) {
        providerDao.update(provider);
    }

    public void saveProvider(Provider provider) {
        provider.setActive(true);
        providerDao.save(provider);
    }

    public void deleteProvider(Provider provider) {
        for (int i = 0; i < deliveryDao.getList().size(); i++) {
            if (deliveryDao.getList().get(i).getProvider().equals(provider)) {
                for (int j = 0; j < deliveryProductMapDao.getList().size(); j++) {
                    if (deliveryProductMapDao.getList().get(i).getDelivery().equals(deliveryDao.getList().get(i))) {
                        deliveryProductMapDao.delete(deliveryProductMapDao.getList().get(i));
                    }
                }
                deliveryDao.delete(deliveryDao.getList().get(i));
            }
        }
        providerDao.delete(provider);
    }

    public boolean providerIsActive(Provider provider) {
        boolean isActive = false;

        for (int i = 0; i < deliveryDao.getList().size(); i++) {
            if (deliveryDao.getList().get(i).getProvider().equals(provider) && deliveryDao.getList().get(i).getDeliveryStatus().getId() == 1) {
                isActive = true;
            }
        }
        return isActive;
    }
}

package com.A.GA.Service;

import com.A.GA.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    public int getMaStore(int idUserAdmin) {
        return storeRepository.getMaStore(idUserAdmin);
    }
}

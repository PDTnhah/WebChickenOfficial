package com.A.GA.Repository;

import com.A.GA.Model.Store;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
@Controller
public class StoreRepository {
    public static List<Store> tableStore = new ArrayList<>();

    public StoreRepository() {
        tableStore.add(new Store(1,1,"Shop Chicken"));
        tableStore.add(new Store(2,4,"Shop Mouse"));
        tableStore.add(new Store(3,1,"Shop Mouse1"));
    }

    public int getMaStore(int idUserAdmin) {
        for (Store store : tableStore){
            if (store.getIdUserAdmin() == idUserAdmin){
                return store.getMaStore();

            }
        }
        System.out.println("không có cửa hàng như trên");
        return 0;
    }
}

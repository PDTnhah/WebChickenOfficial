package com.A.GA.Service;

import com.A.GA.Model.ComBo;
import com.A.GA.Repository.ComBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComBoAdminService {
    @Autowired
    private ComBoRepository comBoRepository;
    public void addComBo(String nameComBo, int price, String category, String describe, String file, int maStore) {
        comBoRepository.addComBo(nameComBo,price,category,describe,file,maStore);
    }

    public List<ComBo> getAllComBoAdmin(int maStore) {
        return comBoRepository.getAllComBoAdmin(maStore);
    }

    public List<ComBo> searchBoxHomeAdmin(String searchBox, int maStore) {
        return comBoRepository.searchBoxHomeAdmin(searchBox,maStore);
    }
}

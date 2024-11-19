package com.A.GA.Service;

import com.A.GA.Repository.ComBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ComBoAdminService {
    @Autowired
    private ComBoRepository comBoRepository;
    public void addComBo(String nameComBo, int price, String category, String describe, String file) {
        comBoRepository.addComBo(nameComBo,price,category,describe,file);
    }
}

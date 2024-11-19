package com.A.GA.Repository;

import com.A.GA.Model.ComBo;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComBoRepository {
    public static final List<ComBo> tableComBo = new ArrayList<>();

    public List<ComBo> getAllComBo() {
        return tableComBo;
    }

    public void addComBo(String nameComBo, int price, String category, String describe, String file) {
        ComBo newComBo= new ComBo(nameComBo,price,file,describe,category);
    }
}

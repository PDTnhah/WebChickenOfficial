package com.A.GA.Service;

import com.A.GA.Model.ComBo;
import com.A.GA.Repository.ComBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComBoUserService {
    @Autowired
    private ComBoRepository comBoRepository;
    public List<ComBo> getAllComBo() {
        return comBoRepository.getAllComBo();
    }

    public List<ComBo> searchBoxComBo(String searchBox) {
        return comBoRepository.searchBoxComBo(searchBox);
    }
}

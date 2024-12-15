package com.A.GA.Service;

import com.A.GA.Model.ComBo;
import com.A.GA.Model.ProductChicken;
import com.A.GA.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductChicken getById(int id) {
        return productRepository.getByIdProduct(id);
    }

    public List<ProductChicken> getOrderProduct() {
        return productRepository.getOrderProduct();
    }public List<ComBo> getOrderProductComBo() {
        return productRepository.getOrderProductComBo();
    }


    public void addOrderProduct(int id) {
        if (!ProductRepository.tableProduct.isEmpty()) {
            productRepository.addOrderProduct(id);
        }

    }
    public void addOrderProductComBo(int maComBo) {
        productRepository.addOrderProductComBo(maComBo);
    }
    public List<ProductChicken> getProduct() {
        if (!ProductRepository.tableProduct.isEmpty()) {
            List<ProductChicken> productChickens= productRepository.getProduct();
            return productChickens != null ? productChickens : new ArrayList<>();
        }
        return null;
    }

    public void deleteOrder(int id) {
        productRepository.deleteOrder(id);
    }public void deleteOrderComBo(int maComBo) {
        productRepository.deleteOrderComBo(maComBo);
    }


    public List<ProductChicken> searchBox(String searchBox) {
        return productRepository.listSearchBox(searchBox);
    }

    public double  sumProduct() {
        return productRepository.sumProduct();
    }


    // ảnh công phượng
    public String getImage() {
        return productRepository.loadImage();
    }


    public List<ProductChicken> getALLProductAdmin(int maStore) {
        return productRepository.getALLProductAdmin(maStore);
    }

    public List<ProductChicken> searchBoxHomeAdmin(String searchBox, int maStore) {
        return productRepository.searchBoxHomeAdmin(searchBox,maStore);
    }
}

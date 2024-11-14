package com.project.shopapp.components;

import com.project.shopapp.models.Category;
import io.lettuce.core.dynamic.annotation.CommandNaming;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@KafkaListener(id ="groupA", topics = {"get-all-categories", "insert-a-category"})
public class MyKafkaListnener {
    @KafkaHandler
    public void listenCategory(Category category){
        System.out.println("Received" + category);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object){
        System.out.println("Received" + object);
    }

    @KafkaHandler
    public  void listenerListCategories(List<Category> categories) {
        System.out.println("Received " + categories);
    }
}

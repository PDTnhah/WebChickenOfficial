package com.A.GA.Service;

import com.A.GA.Model.History;
import com.A.GA.Repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    public List<History> getAllHistory() {
        if (HistoryRepository.tableHistory.isEmpty()){
            return historyRepository.getAllHistory();
        }
        return null;
    }
}

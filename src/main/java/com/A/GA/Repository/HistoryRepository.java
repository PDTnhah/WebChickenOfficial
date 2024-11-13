package com.A.GA.Repository;

import com.A.GA.Model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    public static final List<History> tableHistory = new ArrayList<>();
    public List<History> getAllHistory() {
        return tableHistory;
    }
}

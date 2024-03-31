package com.aca.cafemanagementsystem.service;

import com.aca.cafemanagementsystem.model.Table;

import java.util.List;

public interface TableService {
    Table createTable(Table table);
    Table deleteTable(Table table);
    List<Table> getAllTables();
    Table getTableById(Long tableId);
}

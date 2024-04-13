package com.aca.cafemanagementsystem.service;

import com.aca.cafemanagementsystem.dto.TableDto;
import com.aca.cafemanagementsystem.model.DiningTable ;

import java.util.List;

public interface TableService {

    List<DiningTable > getAllTables();

    DiningTable  getTableByNumber(int tableNumber);
}

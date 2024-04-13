package com.aca.cafemanagementsystem.rest;

import com.aca.cafemanagementsystem.dto.TableDto;
import com.aca.cafemanagementsystem.model.DiningTable;
import com.aca.cafemanagementsystem.serviceimpl.TableServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {
    private final TableServiceImpl tableService;

    public TableController(TableServiceImpl tableService) {
        this.tableService = tableService;

    }
    @GetMapping
    public List<DiningTable> getAllTables() {
        return tableService.getAllTables();
    }
    @GetMapping("/{tableNumber}")
    public DiningTable getTableByNumber(@PathVariable int tableNumber){
        return tableService.getTableByNumber(tableNumber);
    }
}

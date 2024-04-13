package com.aca.cafemanagementsystem.serviceimpl;

import com.aca.cafemanagementsystem.dao.TableRepository;
import com.aca.cafemanagementsystem.dto.TableDto;
import com.aca.cafemanagementsystem.model.DiningTable ;
import com.aca.cafemanagementsystem.service.TableService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<DiningTable > getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public DiningTable  getTableByNumber(int tableNumber) {
        return tableRepository.findByTableNumber(tableNumber);
    }
}

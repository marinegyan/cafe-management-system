package com.aca.cafemanagementsystem.service;

import com.aca.cafemanagementsystem.dto.TableDto;
import com.aca.cafemanagementsystem.dto.WaiterDto;
import com.aca.cafemanagementsystem.exceptions.UnauthorizedAccessException;
import com.aca.cafemanagementsystem.model.DiningTable ;
import com.aca.cafemanagementsystem.model.User;

public interface ManagerService {
    DiningTable  createTable(TableDto dto) ;
    void deleteTable(Long id);
    User addWaiter(WaiterDto waiterDto);
}

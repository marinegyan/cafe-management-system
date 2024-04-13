package com.aca.cafemanagementsystem.serviceimpl;

import com.aca.cafemanagementsystem.dao.TableRepository;
import com.aca.cafemanagementsystem.dao.UserRepository;
import com.aca.cafemanagementsystem.dto.TableDto;
import com.aca.cafemanagementsystem.dto.WaiterDto;
import com.aca.cafemanagementsystem.model.Role;
import com.aca.cafemanagementsystem.model.DiningTable;
import com.aca.cafemanagementsystem.model.User;
import com.aca.cafemanagementsystem.service.ManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final UserRepository userRepository;
    private final TableRepository tableRepository;

    public ManagerServiceImpl(UserRepository userRepository, TableRepository tableRepository) {
        this.userRepository = userRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public DiningTable createTable(TableDto dto) {
        DiningTable table = new DiningTable();
        table.setOccupied(dto.isOccupied());
        table.setCapacity(dto.getCapacity());
        table.setTableNumber(dto.getTableNumber());
        tableRepository.save(table);
        return table;
    }

    @Override
    public void deleteTable(Long id) {
        DiningTable table = new DiningTable();
        DiningTable existingTable = tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));
        tableRepository.delete(existingTable);
    }

    @Override
    public User addWaiter(WaiterDto waiterDto) {
        User waiter = new User();
        waiter.setName(waiterDto.getName());
        waiter.setStatus(waiterDto.getStatus());
        waiter.setEmail(waiterDto.getEmail());
        waiter.setRole(Role.WAITER);
        return userRepository.save(waiter);
    }
}

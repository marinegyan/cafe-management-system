package com.aca.cafemanagementsystem.rest;

import com.aca.cafemanagementsystem.dto.TableDto;
import com.aca.cafemanagementsystem.dto.WaiterDto;
import com.aca.cafemanagementsystem.model.DiningTable;
import com.aca.cafemanagementsystem.model.Role;
import com.aca.cafemanagementsystem.model.User;
import com.aca.cafemanagementsystem.service.ManagerService;
import com.aca.cafemanagementsystem.serviceimpl.ManagerServiceImpl;
import com.aca.cafemanagementsystem.serviceimpl.TableServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerServiceImpl managerService;

    public ManagerController(ManagerServiceImpl managerService) {
        this.managerService = managerService;
    }


    @PostMapping()
    @Secured(Role.MANAGER)
    public DiningTable createTable(@RequestBody TableDto tableDto){
        return managerService.createTable(tableDto);
    }

    @DeleteMapping("/{id}")
    @Secured(Role.MANAGER)
    public void deleteTable(@PathVariable Long id) {
        managerService.deleteTable(id);
    }
    @PostMapping("/waiters")
    @Secured(Role.MANAGER)
    public User addWaiter(@RequestBody WaiterDto waiterDto) {
        return managerService.addWaiter(waiterDto);
    }

}

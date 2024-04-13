package com.aca.cafemanagementsystem.dao;

import com.aca.cafemanagementsystem.model.Orders;
import com.aca.cafemanagementsystem.model.DiningTable ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders> findByStatus(String status);
    @Query("SELECT o FROM Orders o WHERE o.table.tableNumber = :tableNumber")
    List<Orders> findByTable(int  tableNumber);

}

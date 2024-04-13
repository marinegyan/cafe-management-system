package com.aca.cafemanagementsystem.dao;


import com.aca.cafemanagementsystem.model.DiningTable ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<DiningTable ,Long> {

    DiningTable  findByTableNumber(int tableNumber);
    List<DiningTable> findByCapacity(int capacity);
    List<DiningTable> findDiningTableByOccupiedIsFalse();

}

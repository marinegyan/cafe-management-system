package com.aca.cafemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "occupied")
    private boolean occupied;

    @OneToMany(mappedBy = "table")
    private List<Order> orders = new ArrayList<>();
}


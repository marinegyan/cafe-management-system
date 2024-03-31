package com.aca.cafemanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    Table table;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private User user;

}

package com.aca.cafemanagementsystem.dto;

import com.aca.cafemanagementsystem.model.DiningTable ;
import com.aca.cafemanagementsystem.model.User;
import lombok.Data;

@Data

public class OrderDto {
    private String status;
    private Long id;
    private DiningTable table;
}

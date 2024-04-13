package com.aca.cafemanagementsystem.dto;

import lombok.Data;

@Data
public class TableDto {
    Long userId;
    Long tableId;
    int tableNumber;
    int capacity;
    boolean isOccupied;
}

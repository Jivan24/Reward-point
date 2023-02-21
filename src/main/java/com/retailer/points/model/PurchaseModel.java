package com.retailer.points.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {

    @NotNull(message = "User id is mandatory")
    private Long userId;

    @NotNull(message = "Item name is mandatory")
    private String itemName;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cant be less than zero")
    private Double price;

}

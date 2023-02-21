package com.retailer.points.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointsResponse {

    private Long userId;
    private Map<String, Double> points;
    private Double total;

}

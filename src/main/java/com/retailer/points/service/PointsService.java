package com.retailer.points.service;


import com.retailer.points.model.PointsResponse;
import com.retailer.points.exception.PurchaseNotFoundException;

import java.util.List;

public interface PointsService {

    PointsResponse getPoints(Long userId) throws PurchaseNotFoundException;

    List<PointsResponse> getPointsForAllCustomers() throws PurchaseNotFoundException;

}

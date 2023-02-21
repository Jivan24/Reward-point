package com.retailer.points.service.impl;

import com.retailer.points.entity.Purchase;
import com.retailer.points.exception.PurchaseNotFoundException;
import com.retailer.points.model.PointsResponse;
import com.retailer.points.repository.PurchaseRepository;
import com.retailer.points.service.PointsService;
import com.retailer.points.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PointsServiceImpl implements PointsService {

    @Autowired
    private PurchaseRepository repository;


    @Override
    public PointsResponse getPoints(Long userId) throws PurchaseNotFoundException {
        List<Purchase> purchases = repository.findByUserIdAndPurchaseDateGreaterThanEqual(userId, DateUtil.getStartDate(3));
        if (purchases.isEmpty()) {
            throw new PurchaseNotFoundException("No records are present for user : " + userId);
        }
        Map<String, Double> points = calculatePoints(purchases);

        return PointsResponse.builder()
                .userId(userId)
                .total(points.values().stream().reduce(0.0, Double::sum))
                .points(points)
                .build();
    }

    private Map<String, Double> calculatePoints(List<Purchase> purchases) {
        Map<String, Double> map = new HashMap<>();
        for (Purchase purchase : purchases) {
            map.merge(DateUtil.responseDateFormat.format(purchase.getPurchaseDate()),
                    calculatePoint(purchase), Double::sum);
        }
        return map;
    }

    @Override
    public List<PointsResponse> getPointsForAllCustomers() throws PurchaseNotFoundException {
        List<Purchase> purchases = repository.findByPurchaseDateGreaterThanEqual(DateUtil.getStartDate(-3));
        if (purchases.isEmpty()) {
            throw new PurchaseNotFoundException("Please purchase items for earning points");
        }
        Map<Long, List<Purchase>> userPurchases = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getUserId));

        return userPurchases.entrySet().stream().map(entry -> {
            Map<String, Double> pointsMap = calculatePoints(entry.getValue());
            return PointsResponse.builder()
                    .userId(entry.getKey())
                    .total(pointsMap.values().stream().reduce(0.0, Double::sum))
                    .points(pointsMap)
                    .build();
        }).collect(Collectors.toList());
    }

    private Double calculatePoint(Purchase purchase) {
        double start = purchase.getPrice() - 50.0;
        if (start > 0) {
            double value = start + Math.max(0, (start - 50));
            log.debug("Point for price " + purchase.getPrice() + " is " + value);
            return value;
        }

        return 0.0;
    }

}

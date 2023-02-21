package com.retailer.points.service.impl;

import com.retailer.points.entity.Purchase;
import com.retailer.points.model.PurchaseModel;
import com.retailer.points.repository.PurchaseRepository;
import com.retailer.points.service.PurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase add(PurchaseModel dto) {
        return purchaseRepository.save(map(dto));
    }

    private Purchase map(PurchaseModel dto) {
        return Purchase.builder()
                .item(dto.getItemName())
                .price(dto.getPrice())
                .userId(dto.getUserId())
                .purchaseDate(new Date())
                .build();
    }
}

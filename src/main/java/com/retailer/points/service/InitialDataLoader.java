package com.retailer.points.service;

import com.retailer.points.entity.Purchase;
import com.retailer.points.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InitialDataLoader {

    private PurchaseRepository purchaseRepository;

    @PostConstruct
    public void loadData() {
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(getProduct(257L,90.0, 2023, 2, 18));
        purchases.add(getProduct(257L,130.0, 2023, 1, 2));
        purchases.add(getProduct(257L,10.0, 2022, 12, 8));
        purchases.add(getProduct(257L,80.0, 2023, 1, 16));
        purchases.add(getProduct(257L,120.0, 2022, 12, 19));
        purchases.add(getProduct(257L,70.0, 2023, 2, 11));
        purchases.add(getProduct(257L,30.0, 2023, 1, 23));
        purchases.add(getProduct(257L,90.0, 2023, 2, 7));
        purchases.add(getProduct(257L,140.0, 2023, 2, 12));
        purchases.add(getProduct(257L,50.0, 2023, 1, 30));
        purchases.add(getProduct(257L,110.0, 2022, 12, 9));

        // Loading data for second user

        purchases.add(getProduct(258L,90.0, 2023, 2, 7));
        purchases.add(getProduct(258L,140.0, 2023, 2, 12));
        purchases.add(getProduct(258L,50.0, 2023, 1, 30));
        purchases.add(getProduct(258L,110.0, 2022, 12, 9));
        purchaseRepository.saveAll(purchases);
    }

    public static Purchase getProduct(Long userId, double price, int year, int month, int dayOfMonth) {
        return Purchase.builder().userId(userId).price(price).purchaseDate(Date.from(LocalDate.of(year, month, dayOfMonth).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())).item("Product Name").build();
    }

}

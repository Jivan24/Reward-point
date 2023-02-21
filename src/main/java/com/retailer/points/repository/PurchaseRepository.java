package com.retailer.points.repository;

import com.retailer.points.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {

    List<Purchase> findByUserIdAndPurchaseDateGreaterThanEqual(Long customerId, Date date);

    List<Purchase> findByPurchaseDateGreaterThanEqual(Date date);


}

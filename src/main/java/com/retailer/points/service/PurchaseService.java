package com.retailer.points.service;

import com.retailer.points.entity.Purchase;
import com.retailer.points.model.PurchaseModel;

public interface PurchaseService {

    Purchase add(PurchaseModel purchaseModel);

}

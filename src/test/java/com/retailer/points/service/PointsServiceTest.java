package com.retailer.points.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retailer.points.entity.Purchase;
import com.retailer.points.exception.PurchaseNotFoundException;
import com.retailer.points.model.PointsResponse;
import com.retailer.points.repository.PurchaseRepository;
import com.retailer.points.service.impl.PointsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.retailer.points.service.InitialDataLoader.getProduct;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PointsServiceTest {

    @InjectMocks
    private PointsService rewardService = new PointsServiceImpl();

    @Mock
    private PurchaseRepository purchaseRepository;

    @Test
    public void testWithRewards() throws PurchaseNotFoundException, JsonProcessingException {
        Mockito.when(purchaseRepository.findByUserIdAndPurchaseDateGreaterThanEqual(any(),any())).thenReturn(loadData());
        String expectedResponse = "{\"userId\":257,\"points\":{\"Feb-2023\":230.0,\"Dec-2022\":160.0,\"Jan-2023\":140.0},\"total\":530.0}";
        PointsResponse response = rewardService.getPoints(257L);
        Assertions.assertEquals(530, response.getTotal());
        Assertions.assertEquals(expectedResponse, new ObjectMapper().writeValueAsString(response));
    }

    @Test
    public void testTransactionWithNoRewards() throws PurchaseNotFoundException, JsonProcessingException {
        Mockito.when(purchaseRepository.findByUserIdAndPurchaseDateGreaterThanEqual(any(),any())).thenReturn(mockAmountsLessThan50());
        String expectedResponse = "{\"userId\":1,\"points\":{\"Feb-2023\":0.0,\"Dec-2022\":0.0,\"Jan-2023\":0.0},\"total\":0.0}";
        PointsResponse response = rewardService.getPoints(1L);
        Assertions.assertEquals(0, response.getTotal());
        Assertions.assertEquals(expectedResponse, new ObjectMapper().writeValueAsString(response));
    }

    public List<Purchase> loadData() {
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
        return purchases;
    }

    public List<Purchase> mockAmountsLessThan50() {
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(getProduct(258L,50.0, 2023, 2, 7));
        purchases.add(getProduct(258L,40.0, 2023, 2, 12));
        purchases.add(getProduct(258L,30.0, 2023, 1, 30));
        purchases.add(getProduct(258L,10.0, 2022, 12, 9));
        return purchases;
    }

}

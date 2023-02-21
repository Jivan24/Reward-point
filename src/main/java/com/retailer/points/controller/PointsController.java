package com.retailer.points.controller;

import com.retailer.points.exception.PurchaseNotFoundException;
import com.retailer.points.model.PointsResponse;
import com.retailer.points.service.PointsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/points")
public class PointsController {

    @Autowired
    private PointsService service;

    @GetMapping("/{userId}")
    public ResponseEntity<PointsResponse> get(@PathVariable("userId") Long userId) throws PurchaseNotFoundException {
        return new ResponseEntity<>(service.getPoints(userId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<PointsResponse>> get() throws PurchaseNotFoundException {
        return new ResponseEntity<>(service.getPointsForAllCustomers(), HttpStatus.OK);
    }

}

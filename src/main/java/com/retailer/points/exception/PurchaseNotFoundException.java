package com.retailer.points.exception;

public class PurchaseNotFoundException extends Exception {

    private String message;

    public PurchaseNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

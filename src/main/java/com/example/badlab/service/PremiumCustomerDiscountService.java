package com.example.badlab.service;

import org.springframework.stereotype.Service;

@Service
public class PremiumCustomerDiscountService {

    public double calculateDiscount(int age, double amount) {

        double discount = 0;

        if (age > 60) {
            discount = 10;
        }

        if (age > 70) {
            discount = 20;
        }

        if (amount > 1000) {
            discount += 5;
        }

        if (amount > 5000) {
            discount += 10;
        }

        return discount;
    }
}
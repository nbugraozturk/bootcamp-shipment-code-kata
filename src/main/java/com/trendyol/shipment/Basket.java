package com.trendyol.shipment;

import java.util.Arrays;
import java.util.List;


public class Basket {
    private static final int SHIPMENT_SIZE_THRESHOLD = 3;
    private List<Product> products;

    public ShipmentSize getShipmentSize() {
        long smallProductsAmountInBasket = countSmallProductsInBasket();
        if (isShipmentSizeThresholdMet(smallProductsAmountInBasket)) return ShipmentSize.MEDIUM;

        long mediumProductsAmountInBasket = countMediumProductsInBasket();
        if (isShipmentSizeThresholdMet(mediumProductsAmountInBasket)) return ShipmentSize.LARGE;

        long largeProductsAmountInBasket = countLargeProductsInBasket();
        if (isShipmentSizeThresholdMet(largeProductsAmountInBasket)) return ShipmentSize.X_LARGE;

        return getBiggestProductsSize();
    }

    public ShipmentSize getBiggestProductsSize() {
        int biggestIndex = 0;
        for(Product product: products) {
            int currentIndex = Arrays.asList(ShipmentSize.values()).indexOf(product.getSize());
            if(currentIndex > biggestIndex) {
                biggestIndex = currentIndex;
            }
        }
        return ShipmentSize.values()[biggestIndex];
    }

    private long countSmallProductsInBasket() {
        return products.stream().filter((product -> product.getSize() == ShipmentSize.SMALL)).count();
    }

    private long countMediumProductsInBasket() {
        return products.stream().filter((product -> product.getSize() == ShipmentSize.MEDIUM)).count();

    }

    private long countLargeProductsInBasket() {
        return products.stream().filter((product -> product.getSize() == ShipmentSize.LARGE)).count();

    }

    private boolean isShipmentSizeThresholdMet(long amount) {
        return amount >= SHIPMENT_SIZE_THRESHOLD;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

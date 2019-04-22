package ie.demo.service.impl;

import ie.util.BillingStrategy;

public class BillingStrategyContext {

    private BillingStrategy strategy;

    public BillingStrategyContext(BillingStrategy strategy){
        this.strategy = strategy;
    }

    public float executeStrategy(float basePrice){
        return strategy.getActPrice(basePrice);
    }
}

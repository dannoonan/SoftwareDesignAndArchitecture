package ie.util;

public class NormalStrategy implements BillingStrategy {

    public float getActPrice(float basePrice) {
        return basePrice;
    }
}

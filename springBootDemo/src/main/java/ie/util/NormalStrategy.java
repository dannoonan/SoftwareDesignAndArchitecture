package ie.util;

public class NormalStrategy implements BillingStrategy {

    @Override
    public float getActPrice(float basePrice) {
        return basePrice;
    }
}

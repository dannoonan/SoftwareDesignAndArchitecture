package ie.util;

public class PremiumStrategy implements BillingStrategy {

    public float getActPrice(float basePrice) {
        return basePrice / 2;
    }
}

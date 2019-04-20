package ie.util;

public class PremiumStrategy implements BillingStrategy {

    @Override
    public float getActPrice(float basePrice) {
        return basePrice / 2;
    }
}

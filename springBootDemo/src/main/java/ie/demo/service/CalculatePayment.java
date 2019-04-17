package ie.demo.service;

public abstract class CalculatePayment {

	protected CalculatePayment nextPaymentMethod;
	private static float finalPay;
	
	public void setNextPaymentMethod(CalculatePayment next) {
		this.nextPaymentMethod = next;
	}
	
	public float getFinalPay() {
		return finalPay;
	}
	
	protected void setFinalPay(float finalPay) {
		CalculatePayment.finalPay = finalPay;
	}
	
	public abstract void calculatePaidAmount(float minutes);	
}

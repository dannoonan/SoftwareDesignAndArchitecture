package ie.demo.domain;

import java.util.Date;

public class Order {

    private int orderId;
    private int bikeId;
    private int userId;
    private int paidStatus;
    private float moneyConsumed;
    private Date orderTime;
    
    public Order() {
    	
    }

    public int getOrderId() { return this.orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getBikeId() { return this.bikeId; }
    public void setBikeId(int bikeId) { this.bikeId = bikeId; }
    public int getUserId() { return this.userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getPaidStatus() { return this.paidStatus; }
    public void setPaidStatus(int paidStatus) { this.paidStatus = paidStatus; }
    public float getMoneyConsumed() { return this.moneyConsumed; }
    public void setMoneyConsumed(float moneyConsumed) { this.moneyConsumed = moneyConsumed; }
    public Date getOrderTime() { return this.orderTime; }
    public void setOrderTime(Date orderTime) { this.orderTime = orderTime; }

}

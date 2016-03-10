package in.co.mn.panda.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by manuMohan on 10/03/2016.
 */
public class Job {
    @SerializedName("status")
    private String status;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("distance")
    private double distance;

    @SerializedName("job_date")
    private String jobDate;

    @SerializedName("extras")
    private String extras;

    @SerializedName("order_duration")
    private int orderDuration;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("order_time")
    private String orderTime;

    @SerializedName("payment_method")
    private String paymentMethod;

    @SerializedName("price")
    private double price;

    @SerializedName("recurrency")
    private int recurrency;

    @SerializedName("job_city")
    private String jobCity;

    @SerializedName("job_latitude")
    private double jobLatitude;

    @SerializedName("job_longitude")
    private double jobLongitude;

    @SerializedName("job_postalcode")
    private String jobPostalCode;

    @SerializedName("job_street")
    private String jobStreet;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public int getOrderDuration() {
        return orderDuration;
    }

    public void setOrderDuration(int orderDuration) {
        this.orderDuration = orderDuration;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRecurrency() {
        return recurrency;
    }

    public void setRecurrency(int recurrency) {
        this.recurrency = recurrency;
    }

    public String getJobCity() {
        return jobCity;
    }

    public void setJobCity(String jobCity) {
        this.jobCity = jobCity;
    }

    public double getJobLatitude() {
        return jobLatitude;
    }

    public void setJobLatitude(double jobLatitude) {
        this.jobLatitude = jobLatitude;
    }

    public double getJobLongitude() {
        return jobLongitude;
    }

    public void setJobLongitude(double jobLongitude) {
        this.jobLongitude = jobLongitude;
    }

    public String getJobPostalCode() {
        return jobPostalCode;
    }

    public void setJobPostalCode(String jobPostalCode) {
        this.jobPostalCode = jobPostalCode;
    }

    public String getJobStreet() {
        return jobStreet;
    }

    public void setJobStreet(String jobStreet) {
        this.jobStreet = jobStreet;
    }
}

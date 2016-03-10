package in.co.mn.panda.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by manuMohan on 10/03/2016.
 */
public class Job implements Parcelable{
    @SerializedName("status")
    private String status;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("distance")
    private String distance;

    @SerializedName("job_date")
    private String jobDate;

    @SerializedName("extras")
    private String extras;

    @SerializedName("order_duration")
    private double orderDuration;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("order_time")
    private String orderTime;

    @SerializedName("payment_method")
    private String paymentMethod;

    @SerializedName("price")
    private String price;

    @SerializedName("recurrency")
    private int recurrency;

    @SerializedName("job_city")
    private String jobCity;

    @SerializedName("job_latitude")
    private String jobLatitude;

    @SerializedName("job_longitude")
    private String jobLongitude;

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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
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

    public double getOrderDuration() {
        return orderDuration;
    }

    public void setOrderDuration(double orderDuration) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getJobLatitude() {
        return jobLatitude;
    }

    public void setJobLatitude(String jobLatitude) {
        this.jobLatitude = jobLatitude;
    }

    public String getJobLongitude() {
        return jobLongitude;
    }

    public void setJobLongitude(String jobLongitude) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.customerName);
        dest.writeString(this.distance);
        dest.writeString(this.jobDate);
        dest.writeString(this.extras);
        dest.writeDouble(this.orderDuration);
        dest.writeString(this.orderId);
        dest.writeString(this.orderTime);
        dest.writeString(this.paymentMethod);
        dest.writeString(this.price);
        dest.writeInt(this.recurrency);
        dest.writeString(this.jobCity);
        dest.writeString(this.jobLatitude);
        dest.writeString(this.jobLongitude);
        dest.writeString(this.jobPostalCode);
        dest.writeString(this.jobStreet);
    }

    public Job() {
    }

    protected Job(Parcel in) {
        this.status = in.readString();
        this.customerName = in.readString();
        this.distance = in.readString();
        this.jobDate = in.readString();
        this.extras = in.readString();
        this.orderDuration = in.readDouble();
        this.orderId = in.readString();
        this.orderTime = in.readString();
        this.paymentMethod = in.readString();
        this.price = in.readString();
        this.recurrency = in.readInt();
        this.jobCity = in.readString();
        this.jobLatitude = in.readString();
        this.jobLongitude = in.readString();
        this.jobPostalCode = in.readString();
        this.jobStreet = in.readString();
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        public Job createFromParcel(Parcel source) {
            return new Job(source);
        }

        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
}

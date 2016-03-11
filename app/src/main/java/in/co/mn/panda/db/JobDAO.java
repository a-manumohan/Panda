package in.co.mn.panda.db;

import android.os.Parcel;
import android.os.Parcelable;

import in.co.mn.panda.model.Job;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by manuMohan on 11/03/2016.
 */
public class JobDAO extends RealmObject implements Parcelable{
    private String status;

    private String customerName;

    private String distance;

    private String jobDate;

    private String extras;

    private double orderDuration;

    @Required
    @PrimaryKey
    private String orderId;

    private String orderTime;

    private String paymentMethod;

    private String price;

    private int recurrency;

    private String jobCity;

    private String jobLatitude;

    private String jobLongitude;

    private String jobPostalCode;

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


    public static JobDAO fromJob(Job job) {
        JobDAO jobDAO = new JobDAO();
        jobDAO.setCustomerName(job.getCustomerName());
        jobDAO.setDistance(job.getDistance());
        jobDAO.setExtras(job.getExtras());
        jobDAO.setJobCity(job.getJobCity());
        jobDAO.setJobDate(job.getJobDate());
        jobDAO.setJobLatitude(job.getJobLatitude());
        jobDAO.setJobLongitude(job.getJobLongitude());
        jobDAO.setJobPostalCode(job.getJobPostalCode());
        jobDAO.setJobStreet(job.getJobStreet());
        jobDAO.setOrderDuration(job.getOrderDuration());
        jobDAO.setOrderId(job.getOrderId());
        jobDAO.setPaymentMethod(job.getPaymentMethod());
        jobDAO.setPrice(job.getPrice());
        jobDAO.setStatus(job.getStatus());
        jobDAO.setRecurrency(job.getRecurrency());
        jobDAO.setOrderTime(job.getOrderTime());
        return jobDAO;
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

    public JobDAO() {
    }

    protected JobDAO(Parcel in) {
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

    public static final Creator<JobDAO> CREATOR = new Creator<JobDAO>() {
        public JobDAO createFromParcel(Parcel source) {
            return new JobDAO(source);
        }

        public JobDAO[] newArray(int size) {
            return new JobDAO[size];
        }
    };
}

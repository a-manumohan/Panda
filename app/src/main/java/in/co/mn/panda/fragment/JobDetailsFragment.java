package in.co.mn.panda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import butterknife.Bind;
import in.co.mn.panda.R;
import in.co.mn.panda.db.JobDAO;
import in.co.mn.panda.util.Utils;

public class JobDetailsFragment extends BaseFragment {
    private static final String ARG_JOB = "arg_job";
    private static final String TAG = JobDetailsFragment.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.title)
    TextView mJobTitleTextView;
    @Bind(R.id.job_status)
    TextView mJobStatusTextView;
    @Bind(R.id.customer_name)
    TextView mCustomerNameTextView;
    @Bind(R.id.job_date)
    TextView mJobDateTextView;
    @Bind(R.id.order_time)
    TextView mOrderTimeTextView;
    @Bind(R.id.order_duration)
    TextView mOrderDurationTextView;
    @Bind(R.id.order_price)
    TextView mOrderPriceTextView;
    @Bind(R.id.payment_method)
    TextView mPaymentMethodTextView;
    @Bind(R.id.job_city)
    TextView mJobCityTextView;
    @Bind(R.id.distance)
    TextView mDisatnceTextView;
    @Bind(R.id.street)
    TextView mStreetTextView;
    @Bind(R.id.postal_code)
    TextView mPostalCodeTextView;
    @Bind(R.id.recurrence)
    TextView mRecurrenceTextView;
    @Bind(R.id.extras_label)
    TextView mExtrasLabelTextView;
    @Bind(R.id.extras)
    TextView mExtrasTextView;

    private JobDAO mJob;

    public JobDetailsFragment() {
        // Required empty public constructor
    }

    public static JobDetailsFragment newInstance(JobDAO job) {
        JobDetailsFragment fragment = new JobDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_JOB, job);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mJob = getArguments().getParcelable(ARG_JOB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(v -> getFragmentManager().popBackStack());
        if (mJob == null) {
            return;
        }
        String jobTitle = mJob.getCustomerName() + " - " + mJob.getOrderId();
        mJobTitleTextView.setText(jobTitle);
        mJobStatusTextView.setText(mJob.getStatus());
        mCustomerNameTextView.setText(mJob.getCustomerName());
        mOrderTimeTextView.setText(mJob.getOrderTime());
        mOrderDurationTextView.setText(mJob.getOrderDuration());
        mOrderPriceTextView.setText(mJob.getPrice());
        mPaymentMethodTextView.setText(mJob.getPaymentMethod());
        mJobCityTextView.setText(mJob.getJobCity());
        mStreetTextView.setText(mJob.getJobStreet());
        mPostalCodeTextView.setText(mJob.getJobPostalCode());
        mRecurrenceTextView.setText(Utils.getRecurrenceString(getContext(), mJob.getRecurrency()));
        if (TextUtils.isEmpty(mJob.getExtras())) {
            mExtrasLabelTextView.setVisibility(View.GONE);
        } else {
            mExtrasLabelTextView.setVisibility(View.VISIBLE);
            mExtrasTextView.setText(mJob.getExtras());
        }

        try {
            String date = Utils.getFormattedDateString(getContext(), mJob.getJobDate());
            mJobDateTextView.setText(date);
        } catch (NumberFormatException e) {
            Log.e(TAG, "invalid date " + mJob.getJobDate());
        }

        String distanceString;
        try {
            double distance = Double.parseDouble(mJob.getDistance());
            distanceString = String.format(Locale.getDefault(), "%.2fKm", distance);
        } catch (NumberFormatException e) {
            distanceString = "0Km";
        }

        mDisatnceTextView.setText(distanceString);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setJob(JobDAO job) {
        mJob = job;
        updateViews();
    }

    private void updateViews() {

    }
}

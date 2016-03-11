package in.co.mn.panda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import in.co.mn.panda.R;
import in.co.mn.panda.db.JobDAO;

public class JobDetailsFragment extends BaseFragment {
    private static final String ARG_JOB = "arg_job";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.title)
    TextView mJobTitleTextView;

    private JobDAO mJob;

    private OnFragmentInteractionListener mListener;

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
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setJob(JobDAO job) {
        mJob = job;
        updateViews();
    }

    private void updateViews() {

    }

    public interface OnFragmentInteractionListener {
    }
}

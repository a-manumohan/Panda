package in.co.mn.panda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.mn.panda.R;
import in.co.mn.panda.db.JobDAO;

public class JobDetailsFragment extends BaseFragment {
    private static final String ARG_JOB = "arg_job";

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

    public interface OnFragmentInteractionListener {
    }
}

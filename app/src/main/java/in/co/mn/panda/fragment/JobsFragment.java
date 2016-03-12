package in.co.mn.panda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.co.mn.panda.PandaApplication;
import in.co.mn.panda.R;
import in.co.mn.panda.adapter.JobsAdapter;
import in.co.mn.panda.db.JobDAO;


public class JobsFragment extends BaseFragment {
    private static final String ARG_JOBS = "arg_jobs";

    @Bind(R.id.jobs)
    RecyclerView mJobsRecyclerView;

    @Bind(R.id.empty_view)
    TextView mEmptyView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private JobsAdapter mJobsAdapter;

    private OnFragmentInteractionListener mListener;

    private List<JobDAO> mJobs;

    public JobsFragment() {
        // Required empty public constructor
    }

    public static JobsFragment newInstance(ArrayList<JobDAO> jobs) {
        JobsFragment jobsFragment = new JobsFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(ARG_JOBS, jobs);
        jobsFragment.setArguments(arguments);
        return jobsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PandaApplication) getActivity().getApplication()).getPandaComponent().inject(this);
        if (getArguments() != null) {
            mJobs = getArguments().getParcelableArrayList(ARG_JOBS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void init() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> mListener.refreshJobs());
        mJobsAdapter = new JobsAdapter(job -> mListener.jobSelected(job));
        mJobsAdapter.setJobs(mJobs);
        mJobsRecyclerView.setAdapter(mJobsAdapter);
        mJobsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateEmptyView();
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

    public void setJobs(List<JobDAO> jobs) {
        mJobs = jobs;
        updateViews(jobs);
    }

    public void setRefreshing(boolean refreshing){
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(refreshing));
    }

    private void updateViews(List<JobDAO> jobs) {
        if (mJobsAdapter != null) {
            mJobsAdapter.setJobs(jobs);
            mJobsAdapter.notifyDataSetChanged();
            updateEmptyView();
        }
    }

    private void updateEmptyView() {
        if (mJobs == null || mJobs.size() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
        }else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    public interface OnFragmentInteractionListener {
        void jobSelected(JobDAO jobDAO);
        void refreshJobs();
    }
}

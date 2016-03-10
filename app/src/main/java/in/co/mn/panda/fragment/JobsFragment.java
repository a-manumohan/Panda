package in.co.mn.panda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import in.co.mn.panda.PandaApplication;
import in.co.mn.panda.R;
import in.co.mn.panda.adapter.JobsAdapter;
import in.co.mn.panda.model.Job;
import in.co.mn.panda.network.NetworkManager;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class JobsFragment extends BaseFragment {

    @Bind(R.id.jobs)
    RecyclerView mJobsRecyclerView;

    @Inject
    NetworkManager mNetworkManager;

    private JobsAdapter mJobsAdapter;

    private OnFragmentInteractionListener mListener;

    private Subscription mJobsSubscription;

    public JobsFragment() {
        // Required empty public constructor
    }

    public static JobsFragment newInstance() {
        return new JobsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PandaApplication)getActivity().getApplication()).getPandaComponent().inject(this);
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
        fetchJobs();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mJobsSubscription != null) {
            mJobsSubscription.unsubscribe();
        }
    }

    private void init() {
        mJobsAdapter = new JobsAdapter();
        mJobsRecyclerView.setAdapter(mJobsAdapter);
        mJobsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void fetchJobs() {
        mJobsSubscription = mNetworkManager.getJobs()
                                           .subscribeOn(Schedulers.newThread())
                                           .observeOn(AndroidSchedulers.mainThread())
                                           .subscribe(
                                                   jobs -> {
                                                       persistJobs(jobs);
                                                       updateViews();
                                                   },
                                                   this::showRetryDialog,
                                                   () -> {
                                                   }
                                           );

    }

    private void showRetryDialog(Throwable throwable) {

    }

    private void updateViews() {

    }

    private void persistJobs(List<Job> jobs) {

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

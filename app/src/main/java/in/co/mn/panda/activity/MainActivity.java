package in.co.mn.panda.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.co.mn.panda.PandaApplication;
import in.co.mn.panda.R;
import in.co.mn.panda.db.DbManager;
import in.co.mn.panda.db.JobDAO;
import in.co.mn.panda.fragment.JobsFragment;
import in.co.mn.panda.network.NetworkManager;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements JobsFragment.OnFragmentInteractionListener {
    private static final String TAG_JOBS_FRAGMENT = "jobs_fragment";
    private static final String TAG_JOB_DETAILS_FRAGMENT = "job_details_fragment";

    private Subscription mJobsSubscription;
    @Inject
    NetworkManager mNetworkManager;
    @Inject
    DbManager mDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((PandaApplication) getApplication()).getPandaComponent().inject(this);
        showJobsFragment(mDbManager.queryAllJobs());
        fetchJobs();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mJobsSubscription != null) {
            mJobsSubscription.unsubscribe();
        }
    }

    private void fetchJobs() {
        mJobsSubscription = mNetworkManager.getJobs()
                                           .subscribeOn(Schedulers.newThread())
                                           .observeOn(AndroidSchedulers.mainThread())
                                           .subscribe(
                                                   jobs -> {
                                                       mDbManager.persist(jobs);
                                                       updateViews( mDbManager.queryAllJobs());
                                                   },
                                                   this::showRetryDialog,
                                                   () -> {
                                                   }
                                           );

    }

    private void showRetryDialog(Throwable throwable) {

    }

    private void updateViews(List<JobDAO> jobs) {
        showJobsFragment(jobs);
    }

    @Override
    public void jobSelected(JobDAO jobDAO) {

    }

    public void showJobsFragment(List<JobDAO> jobs) {
        JobsFragment jobsFragment = (JobsFragment) getSupportFragmentManager().findFragmentByTag(TAG_JOBS_FRAGMENT);
        if (jobsFragment == null) {
            jobsFragment = JobsFragment.newInstance((ArrayList<JobDAO>) jobs);
        } else {
            jobsFragment.setJobs(jobs);
        }
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.container, jobsFragment, TAG_JOBS_FRAGMENT)
                                   .commit();
    }

    public void showJobDetailsFragment(JobDAO job) {

    }
}

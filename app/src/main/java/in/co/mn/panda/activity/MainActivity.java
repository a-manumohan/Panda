package in.co.mn.panda.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import in.co.mn.panda.PandaApplication;
import in.co.mn.panda.R;
import in.co.mn.panda.db.DbManager;
import in.co.mn.panda.db.JobDAO;
import in.co.mn.panda.fragment.JobDetailsFragment;
import in.co.mn.panda.fragment.JobsFragment;
import in.co.mn.panda.network.NetworkManager;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements
        JobsFragment.OnFragmentInteractionListener {
    private static final String TAG_JOBS_FRAGMENT = "jobs_fragment";
    private static final String TAG_JOB_DETAILS_FRAGMENT = "job_details_fragment";

    private static final String ARG_JOBS = "arg_jobs";

    private Subscription mJobsSubscription;
    @Inject
    NetworkManager mNetworkManager;
    @Inject
    DbManager mDbManager;

    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    private AlertDialog mAlertDialog;

    private ArrayList<JobDAO> mJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((PandaApplication) getApplication()).getPandaComponent().inject(this);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(ARG_JOBS)) {
                mJobs = savedInstanceState.getParcelableArrayList(ARG_JOBS);
            }
        }
        if (mJobs == null) {
            showJobsFragment(mDbManager.queryAllJobs());
            fetchJobs();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mJobsSubscription != null) {
            mJobsSubscription.unsubscribe();
        }
    }

    private void fetchJobs() {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        mProgressBar.setIndeterminate(true);
        mJobsSubscription = mNetworkManager.getJobs()
                                           .subscribeOn(Schedulers.newThread())
                                           .observeOn(AndroidSchedulers.mainThread())
                                           .map(jobs -> {
                                               mDbManager.persist(jobs);
                                               return mDbManager.queryAllJobs();
                                           })
                                           .subscribe(
                                                   jobs -> {
                                                       mJobs = (ArrayList<JobDAO>) jobs;
                                                       updateViews(mJobs);
                                                   },
                                                   throwable -> {
                                                       mProgressBar.setVisibility(View.GONE);
                                                       setJobsFragmentRefreshing(false);
                                                       showRetryDialog(throwable);
                                                   },
                                                   () -> {
                                                       mProgressBar.setVisibility(View.GONE);
                                                       setJobsFragmentRefreshing(false);
                                                   }
                                           );

    }

    private void showRetryDialog(Throwable throwable) {
        mAlertDialog = new AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.retry_request_title))
                .setMessage(getString(R.string.retry_request_message))
                .setPositiveButton(getString(R.string.action_retry), (dialog, which) -> {
                    fetchJobs();
                })
                .setNegativeButton(getString(R.string.action_cancel), (dialog, which) -> {

                })
                .show();
    }

    private void updateViews(List<JobDAO> jobs) {
        showJobsFragment(jobs);
    }

    private void setJobsFragmentRefreshing(boolean refreshing) {
        JobsFragment jobsFragment = (JobsFragment) getSupportFragmentManager().findFragmentByTag(TAG_JOBS_FRAGMENT);
        if (jobsFragment == null) {
            return;
        }
        jobsFragment.setRefreshing(refreshing);
    }

    @Override
    public void jobSelected(JobDAO job) {
        showJobDetailsFragment(job);
    }

    @Override
    public void refreshJobs() {
        fetchJobs();
    }

    public void showJobsFragment(List<JobDAO> jobs) {
        JobsFragment jobsFragment = (JobsFragment) getSupportFragmentManager().findFragmentByTag(TAG_JOBS_FRAGMENT);
        if (jobsFragment == null) {
            jobsFragment = JobsFragment.newInstance((ArrayList<JobDAO>) jobs);
            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.container, jobsFragment, TAG_JOBS_FRAGMENT)
                                       .commit();
        } else {
            jobsFragment.setJobs(jobs);
        }

    }

    public void showJobDetailsFragment(JobDAO job) {
        JobDetailsFragment jobDetailsFragment = (JobDetailsFragment) getSupportFragmentManager().findFragmentByTag(TAG_JOB_DETAILS_FRAGMENT);
        if (jobDetailsFragment == null) {
            jobDetailsFragment = JobDetailsFragment.newInstance(job);
            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.container, jobDetailsFragment, TAG_JOB_DETAILS_FRAGMENT)
                                       .addToBackStack(null)
                                       .commit();
        } else {
            jobDetailsFragment.setJob(job);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARG_JOBS, mJobs);
    }
}

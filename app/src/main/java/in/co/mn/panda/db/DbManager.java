package in.co.mn.panda.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import in.co.mn.panda.model.Job;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by manuMohan on 11/03/2016.
 */
@Singleton
public class DbManager {
    private Realm mRealm;
    private Context mContext;

    public DbManager(Realm realm, Context context) {
        mRealm = realm;
        mContext = context;
    }

    public void persist(Job job) {
        mRealm.beginTransaction();
        JobDAO jobDAO = JobDAO.fromJob(job);
        mRealm.commitTransaction();
    }

    public void persist(List<Job> jobs) {
        mRealm.beginTransaction();
        for (Job job : jobs) {
            JobDAO jobDAO = JobDAO.fromJob(job);
        }
        mRealm.commitTransaction();
    }

    public List<JobDAO> queryAllJobs() {
        RealmResults<JobDAO> realmResults = mRealm.where(JobDAO.class)
                                                  .findAll();
        ArrayList<JobDAO> jobDAOs = new ArrayList<>();
        for (JobDAO jobDAO : realmResults) {
            jobDAOs.add(jobDAO);
        }
        return jobDAOs;
    }

    public JobDAO queryJob(String orderId) {
        return mRealm.where(JobDAO.class).equalTo("orderId", orderId).findFirst();
    }
}

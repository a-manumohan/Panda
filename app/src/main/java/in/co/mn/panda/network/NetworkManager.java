package in.co.mn.panda.network;

import android.content.Context;

import java.util.List;

import javax.inject.Singleton;

import in.co.mn.panda.db.DbManager;
import in.co.mn.panda.db.JobDAO;
import rx.Observable;

/**
 * Created by manuMohan on 10/03/2016.
 */
@Singleton
public class NetworkManager {
    private Context mContext;
    private PandaService mPandaService;
    private DbManager mDbManager;

    public NetworkManager(Context context, PandaService pandaService, DbManager dbManager) {
        mContext = context;
        mPandaService = pandaService;
        mDbManager = dbManager;
    }

    public Observable<List<JobDAO>> getJobs() {
        return mPandaService.getjobs().map(jobs -> {
            mDbManager.persist(jobs);
            return mDbManager.queryAllJobs();
        });
    }
}

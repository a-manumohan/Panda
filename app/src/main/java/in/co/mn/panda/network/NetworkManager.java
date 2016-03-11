package in.co.mn.panda.network;

import android.content.Context;

import java.util.List;

import javax.inject.Singleton;

import in.co.mn.panda.model.Job;
import rx.Observable;

/**
 * Created by manuMohan on 10/03/2016.
 */
@Singleton
public class NetworkManager {
    private Context mContext;
    private PandaService mPandaService;

    public NetworkManager(Context context, PandaService pandaService) {
        mContext = context;
        mPandaService = pandaService;
    }

    public Observable<List<Job>> getJobs() {
        return mPandaService.getjobs();
    }
}

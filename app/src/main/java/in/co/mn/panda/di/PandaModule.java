package in.co.mn.panda.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.co.mn.panda.Constants;
import in.co.mn.panda.network.NetworkManager;
import in.co.mn.panda.network.PandaService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manuMohan on 10/03/2016.
 */
@Module
public class PandaModule {
    private Context mContext;

    public PandaModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    public PandaService providePandaService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(PandaService.class);
    }

    @Provides
    @Singleton
    public NetworkManager provideNetworkManager(Context context, PandaService pandaService) {
        return new NetworkManager(context, pandaService);
    }
}

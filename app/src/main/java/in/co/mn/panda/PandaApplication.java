package in.co.mn.panda;

import android.app.Application;

import in.co.mn.panda.di.DaggerpandaComponent;
import in.co.mn.panda.di.PandaComponent;
import in.co.mn.panda.di.PandaModule;

/**
 * Created by manuMohan on 10/03/2016.
 */
public class PandaApplication extends Application {

    private PandaComponent pandaComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        pandaComponent = DaggerpandaComponent
                .builder()
                .pandaModule(new PandaModule())
                .build();
    }

    public PandaComponent getPandaComponent() {
        return pandaComponent;
    }
}

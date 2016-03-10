package in.co.mn.panda.di;

import javax.inject.Singleton;

import dagger.Component;
import in.co.mn.panda.activity.MainActivity;
import in.co.mn.panda.fragment.JobDetailsFragment;
import in.co.mn.panda.fragment.JobsFragment;

/**
 * Created by manuMohan on 10/03/2016.
 */
@Component(modules = PandaModule.class)
@Singleton
public interface PandaComponent {
    void inject(MainActivity activity);

    void inject(JobsFragment fragment);

    void inject(JobDetailsFragment fragment);
}

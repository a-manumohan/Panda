package in.co.mn.panda.network;

import java.util.List;

import in.co.mn.panda.model.Job;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by manuMohan on 10/03/2016.
 */
public interface PandaService {

    @GET("/jobs")
    Observable<List<Job>> getjobs();

}

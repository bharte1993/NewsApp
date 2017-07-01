package exercise.packagecom.headlines.network.headlines;

import exercise.packagecom.headlines.Model.HeadlinesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abhijeet on 8/6/17.
 */

public interface ApiInterface {

    @GET("v1/articles")
    Call<HeadlinesResponse> getData(
            @Query("source") String source,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );
}

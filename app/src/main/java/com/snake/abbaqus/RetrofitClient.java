package com.snake.abbaqus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitClient {
    @GET("subreddits/popular.json")
        //List<GithubRepo> is return type    @Path("user")String user is the parameter we will pass
    Call<Data> reposForUser();

}

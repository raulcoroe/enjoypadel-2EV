package com.svalero.enjoypadel.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.svalero.enjoypadel.domain.Center;
import com.svalero.enjoypadel.domain.Match;
import com.svalero.enjoypadel.domain.Player;

public interface EnjoyPadelApiInterface {

    @GET("players")
    Call<List<Player>> getPlayers();

    @GET("players?availability=true")
    Call<List<Player>> getAvailablePlayers();

    @GET("player/{id}")
    Call<Player> findPlayerById(@Path("id") long id);

    @POST("players")
    Call<Player> addPlayer(@Body Player newPlayer);

    @DELETE("player/{id}")
    Call<Void> deletePlayer(@Path("id") long id);

    @PUT("player/{id}")
    Call<Player> modifyPlayer(@Path("id") long id, @Body Player player);


    @GET("centers")
    Call<List<Center>> getCenters();

    @GET("center/{id}")
    Call<Center> findCenterById(@Path("id") long id);

    @POST("centers")
    Call<Center> addCenter(@Body Center center);

    @DELETE("center/{id}")
    Call<Void> deleteCenter(@Path("id") long id);

    @PUT("center/{id}")
    Call<Center> modifyCenter(@Path("id") long id, @Body Center center);

    @GET("matches")
    Call<List<Match>> getMatches();

    @GET("match/{id}")
    Call<Match> findMatchById(@Path("id") long id);

    @POST("matches")
    Call<Match> addMatch(@Body Match match);

    @DELETE("match/{id}")
    Call<Void> deleteMatch(@Path("id") long id);

    @PUT("match/{id}")
    Call<Match> modifyMatch(@Path("id") long id, @Body Match match);

}

package com.jarylchng.reactivemongoexample.grizzlyjersey;

import com.jarylchng.reactivemongoexample.common.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("async")
public class ResourceReactive {
    @GET
    @Path("{userID}")
    @Produces(MediaType.TEXT_HTML)
    public CompletionStage<String> getIt(@PathParam("userID") String userID) {
        CompletableFuture<String> future = new CompletableFuture<>();

        User.ReactiveDAO.findByID(userID)
                .doOnSuccess(user -> future.complete(user.toString()))
                .doOnError(e -> future.complete(e.getMessage()))
                .subscribe();

        return future;
    }

    @GET
    @Path("{userID}/{name}/{phoneNumber}")
    @Produces(MediaType.TEXT_HTML)
    public CompletionStage<String> newUser(@PathParam("userID") String userID,
                                           @PathParam("name") String name,
                                           @PathParam("phoneNumber") String phoneNumber) {
        CompletableFuture<String> future = new CompletableFuture<>();

        User user = new User(userID, name, phoneNumber);

        User.ReactiveDAO.insert(user)
                .doOnSuccess(success -> future.complete(success.toString()))
                .doOnError(e -> future.complete(e.getMessage()))
                .subscribe();

        return future;
    }
}

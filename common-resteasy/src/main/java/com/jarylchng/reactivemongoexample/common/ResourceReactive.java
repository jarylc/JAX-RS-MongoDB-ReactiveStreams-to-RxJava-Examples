package com.jarylchng.reactivemongoexample.common;

import com.mongodb.reactivestreams.client.Success;
import io.reactivex.Single;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("async")
public class ResourceReactive {
    @GET
    @Path("{userID}")
    @Produces(MediaType.TEXT_PLAIN)
    public Single<String> getIt(@PathParam("userID") String userID) {
        return User.ReactiveDAO.findByID(userID)
                .map(User::toString)
                .onErrorReturn(Throwable::getMessage);
    }

    @GET
    @Path("{userID}/{name}/{phoneNumber}")
    @Produces(MediaType.TEXT_PLAIN)
    public Single<String> newUser(@PathParam("userID") String userID,
                                  @PathParam("name") String name,
                                  @PathParam("phoneNumber") String phoneNumber) {
        User user = new User(userID, name, phoneNumber);

        return User.ReactiveDAO.insert(user)
                .map(Success::toString)
                .onErrorReturn(Throwable::getMessage);
    }
}

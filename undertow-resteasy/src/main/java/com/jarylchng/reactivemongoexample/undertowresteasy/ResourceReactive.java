package com.jarylchng.reactivemongoexample.undertowresteasy;

import com.jarylchng.reactivemongoexample.common.User;
import com.mongodb.reactivestreams.client.Success;
import io.reactivex.Single;
import org.jboss.resteasy.annotations.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("async")
public class ResourceReactive {
    @GET
    @Path("{userID}")
    @Stream
    public Single<String> getIt(@PathParam("userID") String userID) {
        return User.ReactiveDAO.findByID(userID)
                .map(User::toString)
                .onErrorReturn(Throwable::getMessage);
    }

    @GET
    @Path("{userID}/{name}/{phoneNumber}")
    @Stream
    public Single<String> newUser(@PathParam("userID") String userID,
                                  @PathParam("name") String name,
                                  @PathParam("phoneNumber") String phoneNumber) {
        User user = new User(userID, name, phoneNumber);

        return User.ReactiveDAO.insert(user)
                .map(Success::toString)
                .onErrorReturn(Throwable::getMessage);
    }
}

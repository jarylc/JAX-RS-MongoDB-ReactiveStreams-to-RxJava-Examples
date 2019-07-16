package com.jarylchng.reactivemongoexample.common;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("sync")
public class ResourceSynced {
    @GET
    @Path("{userID}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("userID") String userID) {
        User user = User.SyncedDAO.findByID(userID);

        if (user == null)
            return "Not found.";

        return user.toString();
    }

    @GET
    @Path("{userID}/{name}/{phoneNumber}")
    @Produces(MediaType.TEXT_PLAIN)
    public String newUser(@PathParam("userID") String userID,
                          @PathParam("name") String name,
                          @PathParam("phoneNumber") String phoneNumber) {
        User user = new User(userID, name, phoneNumber);

        try {
            User.SyncedDAO.insert(user);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "SUCCESS";
    }
}

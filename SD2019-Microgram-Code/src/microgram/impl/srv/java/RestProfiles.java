package microgram.impl.srv.java;

import java.util.List;
import java.util.Set;
import javax.ws.rs.*;

import microgram.api.Profile;

@Path("/profiles")
public interface RestProfiles {
    String PATH = "/profiles";

    @GET
    @Path("/{userId}")
    @Produces({"application/json"})
    Profile getProfile(@PathParam("userId") String var1);

    @POST
    @Consumes({"application/json"})
    void createProfile(Profile var1);

    @DELETE
    @Path("/{userId}")
    void deleteProfile(@PathParam("userId") String var1);

    @GET
    @Produces({"application/json"})
    List<Profile> search(@QueryParam("query") String var1);

    @PUT
    @Path("/{userId1}/following/{userId2}")
    @Consumes({"application/json"})
    void follow(@PathParam("userId1") String var1, @PathParam("userId2") String var2, boolean var3);

    @GET
    @Path("/{userId1}/following/{userId2}")
    boolean isFollowing(@PathParam("userId1") String var1, @PathParam("userId2") String var2);

    @GET
    @Path("/{userId}/following")
    @Produces({"application/json"})
    Set<String> getFollowing(@PathParam("userId") String var1);
}

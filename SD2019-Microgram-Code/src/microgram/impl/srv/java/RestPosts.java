package microgram.impl.srv.java;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import microgram.api.Post;

@Path("/posts")
public interface RestPosts {
    String PATH = "/posts";

    @GET
    @Path("/{postId}")
    @Produces({"application/json"})
    Post getPost(@PathParam("postId") String var1);

    @DELETE
    @Path("/{postId}")
    void deletePost(@PathParam("postId") String var1);

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    String createPost(Post var1);

    @GET
    @Path("/{postId}/likes/{userId}")
    boolean isLiked(@PathParam("postId") String var1, @PathParam("userId") String var2);

    @PUT
    @Path("/{postId}/likes/{userId}")
    @Consumes({"application/json"})
    void like(@PathParam("postId") String var1, @PathParam("userId") String var2, boolean var3);

    @GET
    @Path("/from/{userId}")
    @Produces({"application/json"})
    List<String> getPosts(@PathParam("userId") String var1);

    @GET
    @Path("/feed/{userId}")
    @Produces({"application/json"})
    List<String> getFeed(@PathParam("userId") String var1);

    @DELETE
    @Path("/remove/{userId}")
    void removeUser(@PathParam("userId") String var1);
}

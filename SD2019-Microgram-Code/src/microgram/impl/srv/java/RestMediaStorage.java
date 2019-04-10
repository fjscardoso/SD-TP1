package microgram.impl.srv.java;

import microgram.api.java.Result;

import javax.ws.rs.*;

@Path("/media")
public interface RestMediaStorage {
    String PATH = "/media";

    @POST
    @Produces({"application/json"})
    @Consumes({"application/octet-stream"})
    String upload(byte[] var1);

    @GET
    @Path("/{id}")
    @Produces({"application/octet-stream"})
    byte[] download(@PathParam("id") String var1);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") String var1);

}


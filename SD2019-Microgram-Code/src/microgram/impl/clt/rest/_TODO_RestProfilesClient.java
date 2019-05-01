package microgram.impl.clt.rest;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import microgram.api.Profile;
import microgram.impl.srv.java.Profiles;
import microgram.api.java.Result;
import microgram.api.rest.RestProfiles;

//TODO Make this class concrete
public class _TODO_RestProfilesClient extends RestClient implements Profiles {

	public _TODO_RestProfilesClient(URI serverUri) {
		super(serverUri, RestProfiles.PATH);
	}

	@Override
	public Result<Profile> getProfile(String userId) {
		Response r = target.path("/" + userId)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		
		return super.responseContents(r, Status.OK, new GenericType<Profile>() {});
	}

	@Override
	public Result<Void> createProfile(Profile profile) {
		Response r = target
				.request()
				.post( Entity.entity( profile, MediaType.APPLICATION_JSON));

		return super.responseContents(r, Status.OK, new GenericType<Void>(){});
	}

	@Override
	public Result<Void> deleteProfile(String userId) {
		Response r = target.path("/" + userId)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.delete();
		return super.responseContents(r, Status.OK, new GenericType<Void>(){});
	}


	//perguntar ao anciaes queryParam(s)
	@Override
	public Result<List<Profile>> search(String s) {
		Response r = target
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();

		return super.responseContents(r, Status.OK, new GenericType<List<Profile>>() {});
	}

	@Override
	public Result<Void> follow(String s, String s1, boolean b) {
		return null;
	}

	@Override
	public Result<Boolean> isFollowing(String s, String s1) {
		return null;
	}

	@Override
	public Result<Set<String>> getFollowing(String userId) {
		Response r = target
				.path("/" + userId + "/following")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();

		return super.responseContents(r, Status.OK, new GenericType<Set<String>>() {});
	}


}

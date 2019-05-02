package microgram.impl.clt.java;

import microgram.api.Profile;
import microgram.impl.srv.java.Profiles;
import microgram.api.java.Result;

import java.util.List;
import java.util.Set;

public class _TODO_RetryProfilesClient extends RetryClient implements Profiles {

	final Profiles impl;

	public _TODO_RetryProfilesClient( Profiles impl ) {
		this.impl = impl;	
	}
	
	@Override
	public Result<Profile> getProfile(String userId) {
		return reTry( () -> impl.getProfile(userId));
	}

	@Override
	public Result<Void> createProfile(Profile profile) {
		return reTry( () -> impl.createProfile(profile));
	}

	@Override
	public Result<Void> deleteProfile(String userId) {
		return reTry( () -> impl.deleteProfile(userId));
	}

	@Override
	public Result<List<Profile>> search(String s) {
		return reTry( () -> impl.search(s));
	}

	@Override
	public Result<Void> follow(String userId1, String userId2, boolean b) {
		return reTry( () -> impl.follow(userId1, userId2, b));
	}

	@Override
	public Result<Boolean> isFollowing(String userId1, String userId2) {
		return reTry( () -> impl.isFollowing(userId1, userId2));
	}

	@Override
	public Result<Set<String>> getFollowing(String userId) {
		return reTry( () -> impl.getFollowing(userId));
	}
}

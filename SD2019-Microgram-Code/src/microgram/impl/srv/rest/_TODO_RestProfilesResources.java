package microgram.impl.srv.rest;


import java.net.URI;
import java.util.List;

import microgram.api.Profile;
import microgram.api.java.Profiles;
import microgram.api.rest.RestProfiles;
import microgram.impl.srv.java.JavaProfiles;

//Make this class concrete.
public class _TODO_RestProfilesResources extends RestResource implements RestProfiles {

	final Profiles impl;
	
	public _TODO_RestProfilesResources(String serverUri) {
		this.impl = new JavaProfiles();
	}
	
	@Override
	public synchronized Profile getProfile(String userId) {
		return super.resultOrThrow( impl.getProfile(userId));
	}

	@Override
	public synchronized void createProfile(Profile profile) {
		super.resultOrThrow(impl.createProfile(profile));
	}

	@Override
	public synchronized List<Profile> search(String s) {
		return super.resultOrThrow(impl.search(s));
	}

	@Override
	public synchronized void follow(String userId1, String userId2, boolean b) {
		super.resultOrThrow(impl.follow(userId1, userId2, b));
	}

	@Override
	public synchronized boolean isFollowing(String userId1, String userId2) {
		return super.resultOrThrow(impl.isFollowing(userId1, userId2));
	}


}

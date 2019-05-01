package microgram.impl.srv.java;

import static microgram.api.java.Result.error;
import static microgram.api.java.Result.ok;
import static microgram.api.java.Result.ErrorCode.CONFLICT;
import static microgram.api.java.Result.ErrorCode.NOT_FOUND;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import discovery.Discovery;
import microgram.api.Profile;
import microgram.api.java.Result;
import microgram.api.java.Result.ErrorCode;
import microgram.impl.clt.rest._TODO_RestPostsClient;
import microgram.impl.clt.rest._TODO_RestProfilesClient;
import microgram.impl.srv.rest.RestResource;

public class JavaProfiles extends RestResource implements microgram.impl.srv.java.Profiles {

	protected Map<String, Profile> users = new HashMap<>();
	protected Map<String, Set<String>> followers = new HashMap<>();
	protected Map<String, Set<String>> following = new HashMap<>();

	protected _TODO_RestPostsClient client;

	public JavaProfiles(URI uri){
		this.client = new _TODO_RestPostsClient(uri);

	}

	@Override
	public Result<Profile> getProfile(String userId) {
		Profile res = users.get( userId );
		if( res == null )
			return error(NOT_FOUND);

		res.setFollowers( followers.get(userId).size() );
		res.setFollowing( following.get(userId).size() );

//		_TODO_RestPostsClient client = new _TODO_RestPostsClient(Discovery.findUrisOf("Microgram-Posts",1)[0]);
		if(this.client.getPosts(userId).isOK())
		res.setPosts(this.client.getPosts(userId).value().size());
		else
			res.setPosts(0);

		return ok(res);
	}

	@Override
	public Result<Void> createProfile(Profile profile) {
		Profile res = users.putIfAbsent( profile.getUserId(), profile );
		if( res != null )
			return error(CONFLICT);
		
		followers.put( profile.getUserId(), new HashSet<>());
		following.put( profile.getUserId(), new HashSet<>());
		return ok();
	}

	//rever
	@Override
	public Result<Void> deleteProfile(String userId) {

		Profile res = users.get( userId );
		if( res == null )
			return error(NOT_FOUND);

		users.remove(userId);

		Iterator<String> it = following.get(userId).iterator();
		while(it.hasNext()) {
			String n = it.next();
			followers.get(n).remove(userId);
		}

		following.remove(userId);
		followers.remove(userId);


		try {
//			_TODO_RestPostsClient client = new _TODO_RestPostsClient(Discovery.findUrisOf("Microgram-Posts",1)[0]);
			if(this.client.removeUser(userId).isOK())
				this.client.removeUser(userId);

			return ok();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ok();
	}
	
	@Override
	public Result<List<Profile>> search(String prefix) {
		return ok(users.values().stream()
				.filter( p -> p.getUserId().startsWith( prefix ) )
				.collect( Collectors.toList()));
	}

	@Override
	public Result<Void> follow(String userId1, String userId2, boolean isFollowing) {		
		Set<String> s1 = following.get( userId1 );
		Set<String> s2 = followers.get( userId2 );
		
		if( s1 == null || s2 == null)
			return error(NOT_FOUND);
		
		if( isFollowing ) {
			boolean added1 = s1.add(userId2 ), added2 = s2.add( userId1 );
			if( ! added1 || ! added2 )
				return error(CONFLICT);		
		} else {
			boolean removed1 = s1.remove(userId2), removed2 = s2.remove( userId1);
			if( ! removed1 || ! removed2 )
				return error(NOT_FOUND);					
		}
		return ok();
	}

	@Override
	public Result<Boolean> isFollowing(String userId1, String userId2) {

		Set<String> s1 = following.get( userId1 );
		Set<String> s2 = followers.get( userId2 );
		
		if( s1 == null || s2 == null)
			return error(NOT_FOUND);
		else
			return ok(s1.contains( userId2 ) && s2.contains( userId1 ));
	}

	@Override
	public Result<Set<String>> getFollowing(String userId) {
		Set<String> res = following.get(userId);
		if (res != null)
			return ok(res);
		else
			return error( NOT_FOUND );
	}
}

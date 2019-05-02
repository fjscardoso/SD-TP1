package microgram.impl.srv.java;

import static microgram.api.java.Result.error;
import static microgram.api.java.Result.ok;
import static microgram.api.java.Result.ErrorCode.CONFLICT;
import static microgram.api.java.Result.ErrorCode.NOT_FOUND;

import discovery.Discovery;
import microgram.impl.clt.rest._TODO_RestProfilesClient;

import java.net.URI;
import java.util.*;

import microgram.api.Post;
import microgram.api.java.Result;
import utils.Hash;

public class JavaPosts implements Posts {

	protected Map<String, Post> posts = new HashMap<>();
	protected Map<String, Set<String>> likes = new HashMap<>();
	protected Map<String, Set<String>> userPosts = new HashMap<>();

	@Override
	public Result<Post> getPost(String postId) {
		Post res = posts.get(postId);
		if (res != null)
			return ok(res);
		else
			return error(NOT_FOUND);
	}

	@Override
	public Result<Void> deletePost(String postId) {
		Post res = posts.get(postId);
		if (res == null)
			return error(NOT_FOUND);

		posts.remove(postId);
		likes.remove(postId);
		userPosts.get(res.getOwnerId()).remove(postId);

		return ok();
	}

	@Override
	public Result<String> createPost(Post post) {
		String postId = Hash.of(post.getOwnerId(), post.getMediaUrl());
		if (posts.putIfAbsent(postId, post) == null) {

			likes.put(postId, new HashSet<>());

			Set<String> posts = userPosts.get(post.getOwnerId());
			if (posts == null)
				userPosts.put(post.getOwnerId(), posts = new LinkedHashSet<>());

			posts.add(postId);

//			_TODO_RestProfilesClient client = new _TODO_RestProfilesClient(Discovery.findUrisOf("Microgram-Profiles",1)[0]);
//			System.out.println("cliente userId: " + client.getProfile(post.getOwnerId()).value().getUserId());
		}
		return ok(postId);
	}

	@Override
	public Result<Void> like(String postId, String userId, boolean isLiked) {
		
		Set<String> res = likes.get(postId);
		if (res == null)
			return error( NOT_FOUND );

		if (isLiked) {
			if (!res.add(userId))
				return error( CONFLICT );
		} else {
			if (!res.remove(userId))
				return error( NOT_FOUND );
		}

		getPost(postId).value().setLikes(res.size());
		return ok();
	}

	@Override
	public Result<Boolean> isLiked(String postId, String userId) {
		Set<String> res = likes.get(postId);
		
		if (res != null)
			return ok(res.contains(userId));
		else
			return error( NOT_FOUND );
	}

	@Override
	public Result<List<String>> getPosts(String userId) {
		Set<String> res = userPosts.get(userId);
		if (res != null)
			return ok(new ArrayList<>(res));
		else
			return error( NOT_FOUND );
	}
	
	
	@Override
	public Result<List<String>> getFeed(String userId) {

		try {
			_TODO_RestProfilesClient client = new _TODO_RestProfilesClient(Discovery.findUrisOf("Microgram-Profiles",1)[0]);
			if(!client.getFollowing(userId).isOK())
				return error(NOT_FOUND);
			Result<Set<String>> set = client.getFollowing(userId);
			Set<String> set2 = new HashSet<>();
			Iterator<String> iter = set.value().iterator();
			while(iter.hasNext()){
				set2.addAll(userPosts.get(iter.next()));
			}
			return ok(new ArrayList<>(set2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error(NOT_FOUND);

	}

	public Result<Void> removeUser(String userId){

		if (userPosts.get(userId) == null)
			return ok();

		Iterator<String> iter = userPosts.get(userId).iterator();
		while(iter.hasNext()){
			String next = iter.next();
			posts.remove(next);
			likes.remove(next);
		}

		userPosts.remove(userId);

		return ok();
	}

}

package microgram.impl.clt.java;

import microgram.api.Post;
import microgram.impl.srv.java.Posts;
import microgram.api.java.Result;

import java.util.List;

public class _TODO_RetryPostsClient extends RetryClient implements Posts {

	final Posts impl;
	
	public _TODO_RetryPostsClient( Posts impl ) {
		this.impl = impl;
	}

	@Override
	public Result<Post> getPost(String postId) {
		return reTry( () -> impl.getPost(postId));
	}

	@Override
	public Result<String> createPost(Post post) {
		return reTry( () -> impl.createPost(post));
	}

	@Override
	public Result<Void> deletePost(String postId) {
		return reTry( () -> impl.deletePost(postId));
	}

	@Override
	public Result<Void> like(String postId, String userId, boolean b) {
		return reTry( () -> impl.like(postId, userId, b));
	}

	@Override
	public Result<Boolean> isLiked(String postId, String userId) {
		return reTry( () -> impl.isLiked(postId, userId));
	}

	@Override
	public Result<List<String>> getPosts(String userId) {
		return reTry( () -> impl.getPosts(userId));
	}

	@Override
	public Result<List<String>> getFeed(String userId) {
		return reTry( () -> impl.getFeed(userId));
	}

	@Override
	public Result<Void> removeUser(String userId) {
		return reTry( () -> impl.removeUser(userId));
	}

}

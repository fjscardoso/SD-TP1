package microgram.impl.srv.rest;

import java.net.URI;
import java.util.List;

import microgram.api.Post;
import microgram.api.java.Posts;
import microgram.api.rest.RestPosts;
import microgram.impl.srv.java.JavaPosts;

// Make this class concrete.
public class _TODO_RestPostsResources extends RestResource implements RestPosts {

	final Posts impl;
		
	public _TODO_RestPostsResources(String serverUri) {
		this.impl = new JavaPosts();
	}
	
	@Override
	public synchronized Post getPost(String postId) {
		return super.resultOrThrow(impl.getPost(postId));
	}

	@Override
	public synchronized void deletePost(String postId) {
		super.resultOrThrow(impl.deletePost(postId));
	}

	@Override
	public synchronized String createPost(Post post){
		return super.resultOrThrow(impl.createPost(post));
	}

	@Override
	public synchronized boolean isLiked(String postId, String userId) {
		return super.resultOrThrow(impl.isLiked(postId, userId));
	}

	@Override
	public synchronized void like(String postId, String userId, boolean b) {
		super.resultOrThrow(impl.like(postId, userId, b));
	}

	@Override
	public synchronized List<String> getPosts(String userId) {
		return super.resultOrThrow(impl.getPosts(userId));
	}

	@Override
	public synchronized List<String> getFeed(String userId) {
		return super.resultOrThrow(impl.getFeed(userId));
	}

}

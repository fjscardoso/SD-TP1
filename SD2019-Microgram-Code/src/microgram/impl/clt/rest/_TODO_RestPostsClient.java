package microgram.impl.clt.rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import microgram.api.Post;
import microgram.impl.srv.java.Posts;
import microgram.api.java.Result;
import microgram.impl.srv.java.RestPosts;

//TODO Make this class concrete
public class _TODO_RestPostsClient extends RestClient implements Posts {

	public _TODO_RestPostsClient(URI serverUri) {
		super(serverUri, RestPosts.PATH);
	}


	@Override
	public Result<Post> getPost(String postId) {
		Response r = target
				.path("/" + postId)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		return super.responseContents(r, Status.OK, new GenericType<Post>(){});
	}

	public Result<String> createPost(Post post) {
		Response r = target
				.request()
				.post( Entity.entity( post, MediaType.APPLICATION_JSON));
		
		return super.responseContents(r, Status.OK, new GenericType<String>(){});	
	}

	@Override
	public Result<Void> deletePost(String s) {
		Response r = target.path("/" + s)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.delete();
		return super.responseContents(r, Status.OK, new GenericType<Void>(){});
	}

	@Override
	public Result<Void> like(String postId, String userId, boolean b) {
		return null;
	}

	@Override
	public Result<Boolean> isLiked(String postId, String userId) {
		Response r = target
				.path("/" + postId + "/likes/" + userId)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		return super.responseContents(r, Status.OK, new GenericType<Boolean>(){});
	}

	@Override
	public Result<List<String>> getPosts(String userId) {
		Response r = target
				.path("/from/" + userId)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		return super.responseContents(r, Status.OK, new GenericType<List<String>>(){});
	}

	@Override
	public Result<List<String>> getFeed(String userId) {
		Response r = target
				.path("/feed/" + userId)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		return super.responseContents(r, Status.OK, new GenericType<List<String>>(){});
	}

	@Override
	public Result<Void> removeUser(String userId) {
		Response r = target.path("/remove/" + userId)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.delete();
		return super.responseContents(r, Status.OK, new GenericType<Void>(){});
	}
}

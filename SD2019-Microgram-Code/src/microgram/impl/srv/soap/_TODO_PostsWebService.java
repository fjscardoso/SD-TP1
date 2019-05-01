package microgram.impl.srv.soap;

import microgram.api.Post;
import microgram.impl.srv.java.Posts;
import microgram.api.soap.MicrogramException;
import microgram.api.soap.SoapPosts;
import microgram.impl.srv.java.JavaPosts;

import java.util.List;

//Make this class concrete.
public class _TODO_PostsWebService extends SoapService implements SoapPosts {

	final Posts impl;
	
	protected _TODO_PostsWebService() {
		this.impl = new JavaPosts();
	}

	@Override
	public Post getPost( String postId ) throws MicrogramException {
		return super.resultOrThrow( impl.getPost(postId));
	}

	@Override
	public void deletePost(String postId) throws MicrogramException {
		super.resultOrThrow( impl.deletePost(postId));
	}

	@Override
	public String createPost(Post post) throws MicrogramException {
		return null;
	}

	@Override
	public boolean isLiked(String s, String s1) throws MicrogramException {
		return false;
	}

	@Override
	public void like(String s, String s1, boolean b) throws MicrogramException {

	}

	@Override
	public List<String> getPosts(String s) throws MicrogramException {
		return null;
	}

	@Override
	public List<String> getFeed(String s) throws MicrogramException {
		return null;
	}

}

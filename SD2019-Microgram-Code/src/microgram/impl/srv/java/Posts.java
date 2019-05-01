package microgram.impl.srv.java;

import java.util.List;
import microgram.api.Post;
import microgram.api.java.Result;

public interface Posts {
    Result<Post> getPost(String var1);

    Result<String> createPost(Post var1);

    Result<Void> deletePost(String var1);

    Result<Void> like(String var1, String var2, boolean var3);

    Result<Boolean> isLiked(String var1, String var2);

    Result<List<String>> getPosts(String var1);

    Result<List<String>> getFeed(String var1);

    Result<Void> removeUser(String userId);
}


package microgram.impl.srv.java;

import java.util.List;
import java.util.Set;

import microgram.api.Profile;
import microgram.api.java.Result;

public interface Profiles {
    Result<Profile> getProfile(String var1);

    Result<Void> createProfile(Profile var1);

    Result<Void> deleteProfile(String var1);

    Result<List<Profile>> search(String var1);

    Result<Void> follow(String var1, String var2, boolean var3);

    Result<Boolean> isFollowing(String var1, String var2);

    Result<Set<String>> getFollowing(String userId);
}

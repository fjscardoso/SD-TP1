package microgram.impl.srv.java;

import microgram.api.java.Result;

public interface Media {
    Result<String> upload(byte[] bytes);

    Result<byte[]> download(String id);

    void delete(String id);

}

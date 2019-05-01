package microgram.impl.srv.soap;


import microgram.api.Profile;
import microgram.impl.srv.java.Profiles;
import microgram.api.soap.MicrogramException;
import microgram.api.soap.SoapProfiles;
import microgram.impl.srv.java.JavaProfiles;

import java.net.URI;

//Make this class concrete.
public abstract class _TODO_ProfilesWebService extends SoapService implements SoapProfiles {

	final Profiles impl;
	
	protected _TODO_ProfilesWebService(URI uri) {
		this.impl = new JavaProfiles(uri);
	}
	
	@Override
	public Profile getProfile( String userId ) throws MicrogramException {
		return super.resultOrThrow( impl.getProfile(userId));
	}
	
}

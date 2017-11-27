package com.gupta.shanu.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.gupta.shanu.messenger.database.DatabaseClass;
import com.gupta.shanu.messenger.model.Profile;

public class ProfileService {
	private Map<String,Profile> profiles;
	
	public ProfileService(){
		profiles = DatabaseClass.getProfiles();
		profiles.put("ShanuGupta", new Profile(1L,"ShanuGupta", "Shanu", "Gupta"));
	}
	
	public List<Profile> getProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile p){
		p.setId(profiles.size()+1);
		profiles.put(p.getProfileName(), p);
		return p;
	}
	
	public Profile updateProfile(Profile p){
		if(p.getProfileName().isEmpty()) return null;
		profiles.put(p.getProfileName(),p);
		return p;
	}
	
	public Profile deleteProfile(String profileName){
		return profiles.remove(profileName);
	}
}

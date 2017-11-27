package com.gupta.shanu.messenger.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gupta.shanu.messenger.model.Profile;
import com.gupta.shanu.messenger.service.ProfileService;

@Path("/profiles")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

	ProfileService service = new ProfileService();

	@GET
	public List<Profile> getProfiles() {
		return service.getProfiles();
	}

	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return service.getProfile(profileName);
	}

	@POST
	public Profile addProfile(Profile p) {
		return service.addProfile(p);
	}

	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile p) {
		p.setProfileName(profileName);
		return service.updateProfile(p);
	}

	@DELETE
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName) {
		return service.deleteProfile(profileName);
	}
}

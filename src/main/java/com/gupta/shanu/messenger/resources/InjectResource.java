package com.gupta.shanu.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectResource {

	@GET
	@Path("annotations")
	public String getParamsByAnnotations(@MatrixParam("param") String matParam, @HeaderParam("authId") String token,
			@CookieParam("name") String cookie){
		return "Matrix Param: " + matParam + " Header Param: " + token + " Cookie Param: " + cookie;
	}
	
	@GET
	@Path("context")
	public String getParamsByContext(@Context UriInfo uriInfo){
		String path = uriInfo.getAbsolutePath().toString();
		String queryParams = uriInfo.getQueryParameters().toString();
		return "Path: " + path + " QueryParams: " + queryParams;
	}
}

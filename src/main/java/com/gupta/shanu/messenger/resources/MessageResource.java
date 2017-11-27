package com.gupta.shanu.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.gupta.shanu.messenger.model.Message;
import com.gupta.shanu.messenger.resources.beans.MessageFilterBean;
import com.gupta.shanu.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService service = new MessageService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("JSON method gets called");
		if (filterBean.getYear() > 0) {
			return service.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return service.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return service.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessages(@BeanParam MessageFilterBean filterBean) {
		System.out.println("XML method gets called");
		if (filterBean.getYear() > 0) {
			return service.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return service.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return service.getAllMessages();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message =  service.getMessage(id);
		//String uri = uriInfo.getAbsolutePath().toString();
		message.addLink(getUriBySelf(message, uriInfo), "self");
		message.addLink(getUriByProfile(message, uriInfo), "profile");
		message.addLink(getUriByComment(message, uriInfo), "comment");
		return message;
	}

	private String getUriByComment(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getComments")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build().toString();
	}

	private String getUriByProfile(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
	}

	private String getUriBySelf(Message m, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(String.valueOf(m.getId())).build().toString();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(@Context UriInfo uriInfo, Message m) {
		Message newMessage = service.addMessage(m);
		String messageId = String.valueOf(m.getId());
		URI location = uriInfo.getAbsolutePathBuilder().path(messageId).build();
		return Response.created(location).entity(newMessage).build(); 
	}

	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message m) {
		m.setId(id);
		return service.updateMessage(m);
	}

	@DELETE
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("messageId") long id) {
		return service.deteteMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getComments(){
		return new CommentResource();
	}
}
Developed a RESTful web service called MessangerApp using jersey framework. Like any other messenger application, it has also three major
entities i.e, profiles, messages and comments. A profile of any user will have properties like profileId and profile name, a message sent 
by any user will have properties like messageId, message content and profileId, similarly a comment done by any user over any message have
properties like commentId, comment and profileId.

I have created an in-memory database class having profile, message and comment as database entities or POJOs and provided the RESTful 
endpoints for supporting all CRUD operations over these entities.

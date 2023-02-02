# Skynet Project 3 Backend
Spring Boot Application
### SkyArt - Taking Over Your Imagination
Welcome to SkyArt, the premier place to share and discuss about A.I. art! 

### General Information
This is a Java application utilizing the Spring framework. Dependencies were mangaged with Maven and all required dependencies were initialized in Spring Boot and can be found within the pom.xml file

We also have a Dockerfile to utilize the docker platform and allow for containerization of our application, requiring the Docker application to run the subsequent container

### Endpoints
*All endpoints require use of JSON

###### Authentication

POST /auth/login
	- Returns JSON of user object if login is successful.
	- Returns JSON of an exception if login is unsuccessful.

POST /auth/logout
	- Requires nothing
	- Returns nothing

POST /auth/register
	- Requires user's First Name, Last Name, Email, and Password
	- Returns JSON of the created user
  
POST /auth/user
	- Takes in HTTP session and returns the user
  
POST /auth/logout
	- Requires nothing
	- Returns nothing
  
 POST /auth/logout
	- Requires nothing
	- Returns nothing

###### Post

GET /post
	- Returns all posts of people you follow
  
PUT /post
	- Publish a post
  
GET /post/all
	- Returns all posts
  
  PUT /post/like
	- Like a post
  
  DELETE /post/delete-post
	- Delete your post
  
 ###### Comment
  
PUT /comment
	- Publish a comment
 
 POST /comment
	- Gets all comments for a post
  
  DELETE /comment
	- Delete a comment
  
   ###### Profile
   
 GET /profile
	- Get profile information
  
   PATCH profile/update
	- Edit profile
  
   ###### Followers
   
 GET /users
	- Get all users
  
   GET /users/following
	- Get all users you are following
  
   GET /users/followers
	- Get all users that follow you
  
   PUT /users/follow
	- Follow a user
  
###Set Up
To allow for use of this backend, edit the allowedOrigins within the WebMVC configuration found in the com.revature package to include the url of the host you want to use, and to use the cloud host utilize the url already found within the application

To utilize your own database, change the data source within the application.yml file

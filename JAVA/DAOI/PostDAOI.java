package CaseStudy.DAOI;

import CaseStudy.entities.Post;
import CaseStudy.entities.Users;

public interface PostDAOI {
	

	//a method to "upload"/persist the post to the post table 
	int upload(Post newpost, int id);




	//method to create a new entry in the likes table
	int addlikes(Post post, Users user);

}

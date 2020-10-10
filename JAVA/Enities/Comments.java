package CaseStudy.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comments {
	
	
	//primary key
	@Id @GeneratedValue
	private int id;
	
	
	//the post the comment was made on
	
	@ManyToOne
	private Post post;
	
	
	//the user who made the comment
	@ManyToOne
	private Users user;
	
	
	
	//the comment made by the user
	private String comment;
	
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}








	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Post getPost_id() {
		return post;
	}




	public void setPost_id(Post post_id) {
		this.post = post_id;
	}




	public Users getUser_id() {
		return user;
	}




	public void setUser_id(Users user_id) {
		this.user= user_id;
	}



 
	public String getComment() {
		return comment;
	}




	public void setComment(String comment) {
		this.comment = comment;
	}




	@Override
	public String toString() {
		return "Comments [id=" + id + ", post_id=" + post + ", user_id=" + user + ", comment=" + comment + "]";
	}

}

package CaseStudy.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class Likes {
	
	
	
	//just meant to server as a primary key for the likes table
	@Id
	@GeneratedValue
	private int id;
	
	
	//represents the post the like is associated with
	private Post post;
	//represents the user the like is associated with
	
	private Users likes;

	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Likes(Post post, Users user, int id ){
		super();
		this.post = post;
		this.likes = user;
		this.id = id;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Likes other = (Likes) obj;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		return true;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Users getUser() {
		return likes;
	}

	public void setUser(Users user) {
		this.likes = user;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Likes [post=" + post + ", user=" + likes + "]";
	}
	
	
}

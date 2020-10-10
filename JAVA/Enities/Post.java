package CaseStudy.entities;

import java.lang.annotation.Target;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Post {
	
	
	//the primary id for a post entry
	@Id
	@GeneratedValue
	private int id;
	
	
	//the user who created the entry
	private Users users;
	
	private String title;
	private String description;
	
	//stores the file name of the image
	private String file;
	
	//stores the catergory the image belongs to
	private String tag;
	
	
	
	
	//legacy field that is no longer needed
	//implemented a new solution of the likes feature in the Likes.java entity
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Likes")
	private List<Users> likes;
	
	
	
	

	public Post(int id, Users user, String title, String description, String file, String tag,
			List<Users> likes) {
		super();
		this.id = id;
		this.users = user;
		this.title = title;
		this.description = description;
		this.file = file;
		this.tag = tag;
		this.likes = likes;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	


	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + users + ", title=" + title + ", description=" + description
				+ ", loaction=" + file + ", tag=" + tag + ", likes=" + likes + "]";
	}
	
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Post other = (Post) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id; 
	}

	public Users getUser_id() {
		return users;
	}

	public void setUser_id(Users user_id) {
		this.users = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Users> getLikes() {
		return likes;
	}

	public void setLikes(List<Users> likes) {
		this.likes = likes;
	}
	
	
}
	
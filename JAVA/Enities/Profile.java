package CaseStudy.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Profile {
	
	
	@OneToOne
	@Id
	private Users user;
	
	private String profileImage;
	
	private String description;
	
	
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profile(Users user, String profileImage, String description) {
		super();
		this.user = user;
		this.profileImage = profileImage;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Profile [user=" + user + ", profileImage=" + profileImage + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((profileImage == null) ? 0 : profileImage.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Profile other = (Profile) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (profileImage == null) {
			if (other.profileImage != null)
				return false;
		} else if (!profileImage.equals(other.profileImage))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	

}

package CaseStudy.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class users_users {
	@Id
	private Users users;
	private Users following;

	
	public users_users(Users user, Users following) {
		super();
		this.users = user;
		this.following = following;
	}
	public users_users() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "users_users [user=" + users + ", following=" + following + "]";
	}
	public Users getUser() {
		return users;
	}
	public void setUser(Users user) {
		this.users = user;
	}
	public Users getFollowing() {
		return following;
	}
	public void setFollowing(Users following) {
		this.following = following;
	}
	

}

package CaseStudy.DAOI;

import CaseStudy.entities.Users;

public interface UsersDAO {
	
	
	//method to add a new user to the service
	
	
	//validate the user credentials when signing
	public boolean validate(String name, String pass);
	
	
	//get user information
	public Users returnuser(String name);

	boolean resigtar(String name, String password, String email);

}

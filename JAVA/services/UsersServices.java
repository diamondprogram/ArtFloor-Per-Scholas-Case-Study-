package CaseStudy.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import CaseStudy.DAOI.UsersDAO;
import CaseStudy.entities.Post;
import CaseStudy.entities.Profile;
import CaseStudy.entities.Users;
import CaseStudy.entities.users_users;


public class UsersServices implements UsersDAO {
	private static String Persistenceunitname = "CaseStudy";
	
	
	//method to return a user object where id provided matches a user id
	public Users getuser(int id) {
		
		//create a connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		//create a user object and set it to null
		Users user = null;
		
		try {
			//find a user object in the database were the id matches
			user = em.find(Users.class, id);
			
		}catch(Exception e) {
			//print out error trace if an error occurs
			e.printStackTrace();
		}finally {
			
			//close the connection
			em.close();
			emf.close();
		}
		
		//return the user
		return user;
	}
	
	//gets the number of likes the user had received among all his posts
	public long getlikes(Users user) {
		//create a connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		
		//set a long object to 0, this will be our counter
		long count = 0;
		//create a list of post objects to store the results
		List<Post> lp = new ArrayList<>();
		try{
			//get all the post objects that were stored in the likes table
			Query q =  em.createQuery("Select o.post from Likes o");
			
			//store them in the list object
			lp = q.getResultList();
			
			//iterate through the list
			for(Post post : lp) {
				//if the user id in the post object is equal to the id of the user provided 
				if(post.getUser_id().getId() == user.getId()) {
					//then increase the count by 1
					count +=1;
				}
				else {
					//else move on to the next iteration of the loop
					continue;
				}
			}
		}catch(Exception e) {//prints out any error that occurs
			e.printStackTrace();
		}finally {//close a connection
			em.close();
			emf.close();
		}
	
		return count;//return count
	}
	
	
	//method to get all of the post made by a user
	public List<Post> getuserpost( Users user){
		
		//create a connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		//a list of post objects to store the results
		List<Post> lp = new ArrayList<>();
		
		try{
			//get all post where the user field equals the user provided 
			Query q =  em.createQuery("Select o from Post o WHERE o.user =:users");
			
			//set the parameter to the user object provided
			q.setParameter("users", user);
			//store the results
			lp = q.getResultList();
		}catch(Exception e) {//if any error print out the error trace
			e.printStackTrace();
		}finally {//close the connection
			em.close();
			emf.close();
		}
		
		
		return lp;//return the list
		
	}
	
	
	
	//method to add a user to the database/service
	@Override
	public boolean resigtar(String name, String password,String email) {//gets the username and password provided 
		boolean joined = false;//check if the user was successfully added
		
		//connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		//create a user object and set the name and password
		Users newuser = new Users();
		newuser.setUsername(name);
		newuser.setPassword(password);
		newuser.setEmail(email);
		Profile userProfile = new Profile();
		userProfile.setUser(newuser);
		//set the date to the method today()
		newuser.setDate(today());
		try {
			//begin the transaction
			em.getTransaction().begin();
			
			//persist the data to the database. the id will be auto generated
			em.persist(newuser);
			
			em.persist(userProfile);
			
			//commit the changes
			em.getTransaction().commit();
			
			//set joined to true 
			joined  = true;
			
			//lets us know if the user was added
			System.out.print("New user was added.");
		}catch(Exception e) {
			//if something went wrong joined is false and print out a message
			joined = false;
			System.out.print("Something went wrong.");
		}finally {//close connection
			em.close();
			emf.close();
		}
		return joined;//return the boolean
	}
	
	//a simple method to get the date of the day the user joined
	public static String today() {
		
		Date date = new Date();//create an instance of the date object
		
		int month = date.getMonth() + 1;//gets the months between 0-11, so adding 1 will gives us 1-12
		int day = date.getDate();//get date will gives us numeric date of the day between 1-31
		int year = date.getYear() +1900; //will give us the year
		
		String joindate = month+"/"+day+"/"+year; //Concatenate the 3 ints into a string in mm/dd/yyyy format
		return joindate;//return the date
		
	}

	@Override
	public boolean validate(String name, String pass) {//check if the user login credentials are valid
		boolean isvalid = false;//set a boolen to false, this will tell the controller if the credentials are good
		Users existinguser = null;//set a user object to null
		
		//connect
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try{
			
			Query q =  em.createQuery("SELECT COUNT(o) from Users o WHERE o.username =:names");
			//set the parameter "names" equal to the one provided
			q.setParameter("names", name);
			long check = (long) q.getSingleResult();
			System.out.println(check);
			if(check == 0) {
				return false;
			}
			
			//look for a user where the user name matches the one provided
			Query q2 =  em.createQuery("SELECT o from Users o WHERE o.username =:names");
			//set the parameter "names" equal to the one provided
			q2.setParameter("names", name);
			//set the user object equal to the results
			existinguser = (Users) q2.getSingleResult();
			//if the rseult is null return false
			if(existinguser == null) {
				//this will prevent the user from getting access to the site
				return false;
				
			}
			//else if name is not null, check if the password match 
			else if(pass.equals(existinguser.getPassword())) {
				isvalid = true;//if so the set isvalid to true
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {//close connection
			em.close();
			emf.close();
		}
		// TODO Auto-generated method stub
		return isvalid;//return the boolean
	}

	
	
	//get user by string name very similar to getuser method but looks for user by username instead
	@Override
	public Users returnuser(String name) {
		// TODO Auto-generated method stub
		Users existinguser = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		try{
			Query q =  em.createQuery("SELECT o from Users o WHERE o.username =:names");
			q.setParameter("names", name);
			existinguser = (Users) q.getSingleResult();
			if(existinguser == null) {
				
				return null;
				
			}
			
		}catch(Exception e) {
			
		}finally {
			em.close();
			emf.close();
		}
		return  existinguser;
	}
	
	
	
	public boolean removeUser(int id) {
		boolean removed = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		try{
			
			Users user = em.find(Users.class, id);
			em.getTransaction().begin();
				em.remove(user);
			em.getTransaction().commit();
			removed= true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		
		return removed;
	}
	
	
	
	
	@SuppressWarnings("null")
	public int follow(Users user, int followingid) {
		
		int res = 0;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		List<users_users> follow = null;
		
		try {
			
			Users follows =  em.find(Users.class, followingid);
			Query q1 = em.createQuery("SELECT COUNT(o) from users_users o WHERE o.users= :userid ");
			q1.setParameter("userid", user);
			Long count = (Long) q1.getSingleResult();
			
			System.out.println(follows);
			
			Query q2 = em.createQuery("SELECT o from users_users o WHERE o.users = :userid ");
			q2.setParameter("userid", user);
			follow = q2.getResultList();
			users_users following = new users_users(user,follows);
			
			if(user.getId() == follows.getId()) {
				return 0;
			}
			
			
			if(count == 0) {
				
			
				em.getTransaction().begin();
				
				em.persist(following);

				em.getTransaction().commit();
				
				res = 1;
				
			}
			else if(count > 0) {
				for (users_users scs : follow) {
					if(scs.getFollowing().getId() == followingid) {
						
						res = 0;
						return res;
					}
					
				}
				em.getTransaction().begin();
				
				em.persist(following);

				em.getTransaction().commit();
				res = 1;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		return res;
	}
	
	

}

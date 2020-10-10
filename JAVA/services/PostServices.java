package CaseStudy.Services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import CaseStudy.DAOI.PostDAOI;
import CaseStudy.entities.Comments;
import CaseStudy.entities.Likes;
import CaseStudy.entities.Post;
import CaseStudy.entities.Users;

public class PostServices implements PostDAOI{
	private static String Persistenceunitname = "CaseStudy";
	
	
	//method to get all post with a keyword in the title
	public List<Post> getallbykeywordpost(String keyword){
		//connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		//list object to store the results
		List<Post> lp = new ArrayList<>();
		try{
			//get all post where the title contains the keyword any where in the title
			Query q =  em.createQuery("Select o from Post o WHERE o.title LIKE CONCAT('%',:keyword,'%')");
			
			//set the parameter "keyword" to the string provided
			q.setParameter("keyword", keyword);
			
			//store the result in the list
			lp = q.getResultList();
		}catch(Exception e) {
			e.printStackTrace();//if error print the trace
		}finally {//close the connection
			em.close();
			emf.close();
		}
		return lp;//return the list 
	}
	
	
	//get all recent by tagged category 
	public List<Post> getTagpostRecent(String keyword){
		//connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		//new list object
		List<Post> lp = new ArrayList<>();
		
		try{
			//get all tags were tag is equal to the string provided
			Query q =  em.createQuery("Select o from Post o WHERE o.tag = :tag ORDER BY o.id DESC");
			//set the parameter "tag" to the string provided
			q.setParameter("tag", keyword);
			//limit the results to the 15 objects
			lp = q.setMaxResults(15).getResultList();
		}catch(Exception e) {
			e.printStackTrace();//print trace if error
		}finally {//close connection
			em.close();
			emf.close();
		}
		return lp;//return list
	}
	
	
	
	
	//get all post related to a tag
	public List<Post> getAllPostByTags(String keyword){
		//connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		//new list object to hold the result and to be returned
		List<Post> lp = new ArrayList<>();
		
		try{
			
			//get all tags were tag is equal to the string provided
			Query q =  em.createQuery("Select o from Post o WHERE o.tag = :tag");
			
			//set the parameter "tag" to the string provided
			q.setParameter("tag", keyword);
			
			//set the list to the result of the query
			lp = q.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {//close connection
			em.close();
			emf.close();
		}
		return lp;
	}
	
	
	
	//creates an entry in the comments table
	public int setcomments(int post_id, int user_id, String com){
		
		int result = 0;//int result to be return, tells us whether or not the comment was made
		
		//create an instance of the comment object
		Comments comment = new Comments();
		
		//connection
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try {
			//get both post and user who's ids matches the ones provided
			Users user = (Users) em.find(Users.class, user_id);
			Post post = (Post) em.find(Post.class, post_id);
			
			//set the fields of the comment object
			comment.setComment(com);
			comment.setPost_id(post);
			comment.setUser_id(user);
			
			//begin the transaction
			em.getTransaction().begin();
			
			//persist the data
			em.persist(comment);
			
			//commit the changes
			em.getTransaction().commit();
			//set res = 1
			result = 1; 
		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}finally {
			em.close();
			emf.close();
		}
		return result;//return the result
	}
	
	
	
	
	//get the information on a post by its id
	public Post getpostbyid(int id) {
		
		//create a post object
		Post post = new Post();
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		
		try {
			//set it to the result of the entity find where the id match
			post = (Post) em.find(Post.class, id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return post;//return the post object
	}
	
	
	
	
	
	public List<Comments> getcomments(Post post_id){
		
		//list of comments to store the result of the query and to be returned
		List<Comments> lc = new ArrayList<>();
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		
		try {
			//look for all comments in the table where the post field equals to the post object provided
			Query q = em.createQuery("SELECT o FROM Comments o WHERE o.post = :id");
			//set the parameter "id" to post_id
			q.setParameter("id", post_id);
			
			//set lc to the result
			lc = q.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("Something went wrong.");
			
		}finally {
			em.close();
			emf.close();  
		}
		return lc;//return the the list object
	}
	
	
	
	
	//gets all recent post regardless of the tags
	public List<Post> getallrecentpost(){
		//list of post to store the result of the query and to be returned
		List<Post> lp = new ArrayList<>();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try {
			//get all recent post order by id
			String statement = "SELECT o FROM Post o Order By o.id DESC";
			Query q =em.createQuery(statement);
			
			//limit the result to the first 15
			lp = q.setMaxResults(15).getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return lp;//return the list
	}
	
	
	
	
	//simple method to add an like to the image
	@Override
	public int addlikes(Post post, Users user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		//create an instance of the like object
		Likes likes = new Likes();
		LikesServices  ls = new LikesServices();
		//set the user and post to the ones provided
		likes.setPost(post);
		likes.setUser(user);
		Likes checklike = new Likes();
		
		try {
			//a query to look for an entry in the table
			Query q = em.createQuery("Select COUNT(o) from Likes o WHERE o.post = :posts AND o.likes = :user");
			
			//set the parameters to the ones provided
			q.setParameter("user", user);
			q.setParameter("posts", post);

			
			//set the result to a long 
			long like = (long) q.getSingleResult();
			
			//if the long is 1 or more return 0
			if(like >= 1) {//this prevents a user from liking and image more than once
				Query q2 = em.createQuery("Select o from Likes o WHERE o.post = :posts AND o.likes = :user");
				q2.setParameter("user", user);
				q2.setParameter("posts", post);
				checklike = (Likes) q2.getSingleResult();
				ls.likesremove(checklike.getId());
				return 0;
			}
			else {//else if no entry exist create one
				em.getTransaction().begin();
					em.persist(likes);//Persist the data
				em.getTransaction().commit();//commit the changes
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
	
	//gets all like for a post
	public long getlikes(Post post){
		long likes = 0;//this will be our counter
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		try {
			//count all entries where post field equals to the post object provided
			Query q = em.createQuery("Select COUNT(o) from Likes o WHERE o.post = :posts");
			//set "posts" to the post object
			q.setParameter("posts", post);
			//set the result to the long 
			likes = (long) q.getSingleResult();
			System.out.println(likes);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return likes;
		
	}
	
	
	
	
	//"upload"/persist the post to the post table
	@Override
	public int upload(Post newpost,int id) {
		int i = 0;//checker to see if it was successful
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try {
			//find the user who's id equals to the one provided
			Users user = em.find(Users.class, id);
			//set that user as the user field in the post object
			newpost.setUser_id(user);
			//begin the transaction
			em.getTransaction().begin();
			em.persist(newpost);//persist the data
			em.getTransaction().commit();//commit the changes
			i =1;//set i to 1
		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			em.close();
			emf.close();
		}
		return i; 
	}
	
	
	
	public boolean isuser(int poster_id, int session_id) {
		boolean isTheUser = false;
		if(poster_id == session_id) {
			isTheUser = true;
		}
		else {
			isTheUser = false;
		}
		
		return isTheUser;
	}
	
	
	public boolean deletePost(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		boolean deleted = false;
		
		try {
			Post post = em.find(Post.class, id);
			em.getTransaction().begin();
			em.remove(post);
			em.getTransaction().commit();
			deleted = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		
		return deleted;
	}
	
	
	//method to upload the image file to the server
	public String imgupload(HttpServletRequest request) {
		//file name is set to null this will be sent back to be set to the post object filename field
		String filename = null;
		//create a instances of ServletFileUpload 
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
	
		try {
			//create an list of file items  and set is to the request
			//this will get any and all files the suer submitted and put them into a list
			List<FileItem> files = sf.parseRequest(request);
			
			//just to see if it getting the files properly (just for testing)
			System.out.println(files);
			
			//iterate through the list of files
			for(FileItem file : files) {
				if(!file.isFormField()) {//checks whether or not the item provided was a file
					
					
					//"writes" the file to the designated file path on the server
					file.write(new File("C:/Users/umoum/Documents/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/Artfloor/resources/uploadimgs" ,  FilenameUtils.getName(file.getName())));
					//gets the file name and stores it in the string
					filename = FilenameUtils.getName(file.getName());
					
					
				}
				else {//if it is not a file, skip over it
					continue;
				}
				
			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return filename;//returns the string
	}

}

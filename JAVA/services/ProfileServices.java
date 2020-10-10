package CaseStudy.Services;

import java.io.File;
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

import CaseStudy.entities.Post;
import CaseStudy.entities.Profile;
import CaseStudy.entities.Users;

public class ProfileServices {
	private static String Persistenceunitname = "CaseStudy";
	
	
	public boolean hasProfileImage(int id) {
		boolean hasImage = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try {
			Users user = em.find(Users.class, id);
			Query q1 = em.createQuery("SELECT o.profileImage from Profile o where o.user =:users");
			q1.setParameter("users", user);
			
			String result = (String) q1.getSingleResult();
			
			if(result == null) {
				return false;
			}
			else if (result !=null){
				hasImage=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		return hasImage;
	}
	
	
	
	public boolean hasDescription(Users user) {
		boolean istrue = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try {
			
			Query q1 = em.createQuery("SELECT o.description from Profile o where o.user =:users");
			q1.setParameter("users", user);
			
			String result = (String) q1.getSingleResult();
			
			if(result == null) {
				return false;
			}
			else if (result !=null){
				istrue=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		
		return istrue;
	}
	
	public void setDescription(String des, int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try {
			//Users user = em.find(Users.class, id);
			Profile userProfile = em.find(Profile.class,id);
			
			userProfile.setDescription(des);
			
			em.getTransaction().begin();
			em.merge(userProfile);
			em.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		
	}
	
	public String getDescription(Users user) {
		String description = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try {
			
			Query q1 = em.createQuery("SELECT o.description from Profile o where o.user =:users");
			q1.setParameter("users", user);
			
			description = (String) q1.getSingleResult();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			emf.close();
		}
		
		
		return description;
	}
	
	
	
	
	//method to upload the image file to the server
		public void updateProfileImage(HttpServletRequest request, int id) {
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
						file.write(new File("C:/Users/umoum/Documents/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/Artfloor/resources/profileimages" ,  FilenameUtils.getName(file.getName())));
						//gets the file name and stores it in the string
						filename = FilenameUtils.getName(file.getName());
						
						
					}
					else {//if it is not a file, skip over it
						continue;
					}
					
				}
				
				upload(id,filename);

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
		}
		
		public String getProflieImage( int id) {
			String fileName = null;
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
			EntityManager em = emf.createEntityManager();
			
			
			try {
				//find the user who's id equals to the one provided
				Profile user = em.find(Profile.class, id);
				
				
				fileName = user.getProfileImage();
			}catch(Exception e) {
				e.printStackTrace();

			}finally {
				em.close();
				emf.close();
			}
			return fileName;
			
		}
	
	
		
		
		public  static int upload(int id,String imageName) {
			int i = 0;//checker to see if it was successful
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
			EntityManager em = emf.createEntityManager();
			
			try {
				//find the user who's id equals to the one provided
				Profile user = em.find(Profile.class, id);
				//set that user as the user field in the post object
				
				if(user.getProfileImage() !=null) {
					File file = new File("C:/Users/umoum/Documents/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/Artfloor/resources/profileimages/"+user.getProfileImage());
					if(file.exists()) {
						file.delete();
					}
				}
				
				
				user.setProfileImage(imageName);
				//begin the transaction
				em.getTransaction().begin();
				em.merge(user);
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
	
	

}

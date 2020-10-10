package CaseStudy.Services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import CaseStudy.entities.Comments;
import CaseStudy.entities.Likes;
import CaseStudy.entities.Post;
import CaseStudy.entities.Users;

public class LikesServices {
	private static String Persistenceunitname = "CaseStudy";
	
	
	public List<Post> likedPost(int id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		List<Post> posts = null;
		
		
		try{
			
			Users user = em.find(Users.class, id);
			
			Query count = em.createQuery("SELECT COUNT(o) from Likes o WHERE o.likes =:id");
			count.setParameter("id", user);
			long c = (long) count.getSingleResult();
			
			if(c <=0) {
				return null;
			}
			
			
			Query q = em.createQuery("SELECT o.post from Likes o WHERE o.likes =:id");
			q.setParameter("id", user);
			
			posts = q.getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();//if error print the trace
		}finally {//close the connection
			em.close();
			emf.close();
		}
		
		return posts;
	}
	
	
	public void likesremove(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try{
			Likes comment = em.find(Likes.class, id);
			
			em.getTransaction().begin();
			em.remove(comment);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();//if error print the trace
		}finally {//close the connection
			em.close();
			emf.close();
		}
		
	}

}

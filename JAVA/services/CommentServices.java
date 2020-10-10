package CaseStudy.Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import CaseStudy.entities.Comments;

public class CommentServices {
	private static String Persistenceunitname = "CaseStudy";
	
	public void deletecomment(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
		EntityManager em = emf.createEntityManager();
		
		try{
			Comments comment = em.find(Comments.class, id);
			
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

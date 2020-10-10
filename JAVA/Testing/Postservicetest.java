package CaseStudy.testing;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CaseStudy.Services.CommentServices;
import CaseStudy.Services.PostServices;
import CaseStudy.Services.UsersServices;
import CaseStudy.entities.Comments;
import CaseStudy.entities.Post;
import CaseStudy.entities.Users;

public class Postservicetest {
	private static String Persistenceunitname = "CaseStudy";
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
//
//	@Test
//	public void testsetcomments() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
//		EntityManager em = emf.createEntityManager();
//		Users user = (Users) em.find(Users.class, 1151);
//		Post post = (Post) em.find(Post.class, 1551);
//		
//		String com = "Agumon SHINNNKKAAAAA!";
//		
//		PostServices ps = new PostServices();
//		
//		int res = ps.setcomments(post.getId(), user.getId(), com);
//		
//		assertEquals(1, res);
//		
//	}
//	@Test
//	public void testgetcomments() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
//		EntityManager em = emf.createEntityManager();
//		
//		Post post = (Post) em.find(Post.class, 1551);
//		
//		String com = "Agumon SHINNNKKAAAAA!";
//		
//		PostServices ps = new PostServices();
//		
//		List<Comments> res = ps.getcomments(post);
//		
//		System.out.println(res.get(0).getComment());
//		assertNotNull(res.get(0).getComment());
//		
//	} 
//	@Test
//	public void testaddlikes() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
//		EntityManager em = emf.createEntityManager();
//		
//		Post post = (Post) em.find(Post.class, 1551);
//		
//		Users user = (Users) em.find(Users.class,1251);
//		
//		
//		PostServices ps = new PostServices();
//		
//		int res = ps.addlikes(post, user);
//		
//		assertEquals(1, res);
//		
//	}
//	@Test
//	public void testgetlikes() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
//		EntityManager em = emf.createEntityManager();
//		
//		Post post = (Post) em.find(Post.class, 1551);
//		
//		Users user = (Users) em.find(Users.class, 1851);
//		
//		
//		PostServices ps = new PostServices();
//		
//		long ll = ps.getlikes(post);
//		
//		assertEquals(0, 0);
//		
//	} 
//	@Test
//	public void testgetbykeyword() {
//		
//		
//		PostServices ps = new PostServices();
//		
//		List<Post> ll = ps.getAllPostByTags("#Digital");
//		
//		System.out.println(ll.size());
//		
//		assertEquals(0, 0);
//		
//	} 
//	@Test
//	public void testgetTagedpost() {
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
//		EntityManager em = emf.createEntityManager();
//
//		PostServices ps = new PostServices();
//		
//		List<Post> ll = ps.getTagpostRecent("#Digital");
//		
//		System.out.println(ll.size());
//		
//		assertEquals(0, 0);
//		
//	} 
//	@Test
//	public void testgetuserpost() {
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
//		EntityManager em = emf.createEntityManager();
//		Users user = (Users) em.find(Users.class, 1251);
//		UsersServices us = new UsersServices();
//		PostServices ps = new PostServices();
//		
//		long l = us.getlikes(user);
//		
//		System.out.print(l);
//		assertEquals(0, 0);
//		
//	} 
//	@Test 
//	public void Testdeleteion() {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Persistenceunitname);
//		EntityManager em = emf.createEntityManager();
//		Post post = em.find(Post.class, 2156);
//		
//		PostServices ps = new PostServices();
//		
//		 boolean test = ps.deletePost(post.getId());
//		 assertEquals(true, test);
//	}





}

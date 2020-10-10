package CaseStudy.MainController;

import java.io.File;
import java.util.List;
import java.util.Map;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import CaseStudy.Services.CommentServices;
import CaseStudy.Services.LikesServices;
import CaseStudy.Services.PostServices;
import CaseStudy.Services.ProfileServices;
import CaseStudy.Services.UsersServices;
import CaseStudy.entities.Comments;
import CaseStudy.entities.Post;
import CaseStudy.entities.Users;


@Controller
@SessionAttributes("userLogin")
public class MainController {
	
	//creates the model attribute when we create a session
	@ModelAttribute("userLogin")
	public Users setUpUser() {
		return new Users();
	} 
	
//	@RequestMapping("/")
//	public String DefaultHandler() {
//		return"home";
//	}
	
	
	
	
	//when ever we call the home page 
	@RequestMapping("/home")
	public ModelAndView homeHandler(@SessionAttribute("userLogin") Users userSession ) {//we pass the session as a parameter
		//we create an instance of a model and view
		ModelAndView mav = new ModelAndView(); 
		
		//we then create a string, and postservices object
		String viewName = null;
		
		PostServices ps = new PostServices();
		
		LikesServices ls = new LikesServices();
		
		
		System.out.println(ls.likedPost(userSession.getId()));
		//we create a set of list objects holding the different category of post objects
		List<Post> rlp=ps.getallrecentpost();
		List<Post> dlp=ps.getTagpostRecent("#Digital");
		List<Post> alp=ps.getTagpostRecent("#Action");
		List<Post> elp=ps.getTagpostRecent("#Epic");
		List<Post> clp=ps.getTagpostRecent("#Cute");
		List<Post> slp=ps.getTagpostRecent("#Sci-Fy");
		List<Post> flp=ps.getTagpostRecent("#Fantasy");
		List<Post> mlp=ps.getTagpostRecent("#Modern");
		List<Post> nlp=ps.getTagpostRecent("#Noir");
		
		//if the session is  null meaning the user wasn't logined send them back to the login
		if(userSession.getUsername() == null) { 
			System.out.println("userSession");
			
			//set the viewname to login
			viewName = "login";
			
		}else if(userSession.getUsername() != null) {//else if session is not null
			try {
				//create mav objects to hold the list objects
				mav.addObject("Likedpost", ls.likedPost(userSession.getId()));
				mav.addObject("userLogin", userSession);
				mav.addObject("recent",rlp);
				mav.addObject("userLogin", userSession);
				mav.addObject("recent",rlp);
				mav.addObject("digital", dlp);
				mav.addObject("action", alp);
				mav.addObject("epic", elp);
				mav.addObject("cute", clp);
				mav.addObject("scifi", slp);
				mav.addObject("fantasy", flp);
				mav.addObject("modern", mlp);
				mav.addObject("noir", nlp);
				
				//set the view name to the home page
				viewName ="home";
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}  
		mav.setViewName(viewName);
		return mav;//Return the mav
	}
	
	
	
	
	
	//the index page is the login page
	@RequestMapping(value={"/","login"})
	public String DefaultHandler() {
		return"login";
	}
	

	
	//brings users to the upload page to begin uploading their image process
	@RequestMapping("/upload")
	public String uploadHandler() {
		return"upload";
	}
	
	
	//sends users to the join/signup page when wanting to join the service
	@RequestMapping("/join")
	public String joinHandler() {
		return"join";
	}
	
	
	
	
	//when on the login page and a new user submitts their information
	@RequestMapping("/adduser")
	public ModelAndView addUserHandler(@ModelAttribute Users user) {
		String message = null;//message is set to null
		String viewName = "message"; //view name is set to message.jsp
		
		//create an instance of modelandview and user services
		UsersServices us = new UsersServices();
		ModelAndView mav = new ModelAndView();
		
		//if the resigtration is successful
		if(us.resigtar(user.getUsername(), user.getPassword(), user.getEmail())) {
			message = "Congarts, your account was created.";//message is set to a congrats message
			mav.addObject("loginmess", "inline");//loginmessaeg is made viewable
			mav.addObject("homenmess", "none");//home message is made invisble 
			mav.addObject("User", user);//we add the user object to a mav object
		}else {//if the process didn't work
			 message = "An error had Occurred";//message is sent to an error message
			 mav.addObject("loginmess", "inline");//loginmessaeg is made viewable
			 mav.addObject("homenmess", "none");//home message is made invisble 
		}
		mav.addObject("message", message );//set the message mav object to the message string
		mav.setViewName(viewName);
		
		return mav; //return the mav
	}
	
	@RequestMapping("/follow/{id}")
	public String followHanlder(@SessionAttribute("userLogin") Users userSession,@PathVariable("id") int id) {
		UsersServices us = new UsersServices();
		int userid= userSession.getId();
		System.out.println(userid);
		System.out.println(id);
		us.follow(userSession,id);
		return "redirect:/home";
	}
	
	
	//when a user want to view their account page
	@RequestMapping("/account/{id}")
	public ModelAndView yourAccountHandler(@SessionAttribute("userLogin") Users userSession,@PathVariable("id") int id) {
		String viewName = "account";//view name is set to account.jsp
		
		//create an instance of modelandview and user services
		UsersServices us = new UsersServices();
		ModelAndView mav = new ModelAndView();
		PostServices ps = new PostServices();
		
		ProfileServices pros = new ProfileServices(); 
		boolean isTheUser = ps.isuser(id, userSession.getId());
		Users account = new Users();
		account = us.getuser(id);
		
		boolean haspfpImage = pros.hasProfileImage(id);
		
		
		if(haspfpImage) {
			String filename = pros.getProflieImage(id);
			mav.addObject("pfp", filename);
			
		}else {
			mav.addObject("pfp", "no-profile.jpg");
		}
		
		
		
		//we call the getlikes method and set it to a long likes
		long likes = us.getlikes(account);
		if(pros.hasDescription(account)) {
			mav.addObject("description", pros.getDescription(account));
			
		}else {
			mav.addObject("description", "No description");
		}
		//create a list of post created by this user
		List<Post> userpost = us.getuserpost(account);
		
		//add them all to a mav object
		mav.addObject("userLogin", account);
		
		mav.addObject("likes",likes);
		mav.addObject("userposts", userpost);
		mav.addObject("isUser", isTheUser);
		mav.setViewName(viewName);
		
		return mav; //return the mav
	}
	
	
	
	
	
	
	@RequestMapping("/deleteAccount")
	public String removeAccountHandler(@SessionAttribute("userLogin") Users userSession) {
		UsersServices us = new UsersServices();
		int id = userSession.getId();
		boolean removed = us.removeUser(id);
		System.out.println(removed);
		return "join";
	}
	
	@RequestMapping("/updateDescription/{id}")
	public String updateDescriptionHandler(HttpSession session,@PathVariable("id") int id, @RequestParam("des") String des) {
		ProfileServices profile =  new ProfileServices();
		System.out.println(id);
		profile.setDescription(des, id);
		ModelAndView mav = new ModelAndView();
		session.setAttribute("id", id);
		return "redirect:/account/"+id;
		//response.sendRedirect(request.getContextPath()+"/account/"+ session.getAttribute("id"));
	}
	
	
	
	
	//when the user begins the upload process
	@RequestMapping("/uploadfile")
	public ModelAndView uploadfileHandler(HttpSession session,HttpServletRequest request, HttpServletResponse response,@RequestParam("descript") String description) {
		//create an instance of modelandview and post services
		PostServices ps = new PostServices();
		ModelAndView mav = new ModelAndView();
		
		//this we get the description, tag and title field and set them to their respected string objects
		String tag = request.getParameter("tag");
		String title = request.getParameter("title");
		
		//this was just for testing purposes to see if the data matched what was entered
		System.out.println("*******************************");
		System.out.println(title);
		System.out.println(description);
		System.out.println(tag);
		
		//create a new post object
		Post post = new Post();
		//wet set the title,description, and tag
		post.setTitle(title);
		post.setDescription(description);
		post.setTag(tag);
		
		//we set the view name to uploadinfo which is the second helf of the upload process
		mav.setViewName("uploadinfo");
		
		
		
		//we add the post as a session attribute
		session.setAttribute("myPost", post);
		return mav;
	}
	
	
	
	//finish uppload will complete the upload process 
	@RequestMapping("/finishupload")
	public ModelAndView uploadpart2Handler(HttpServletRequest request, HttpServletResponse response,@SessionAttribute("userLogin") Users userSession, HttpSession session) {
		
		//create an instance of modelandview and a string message
		ModelAndView mav = new ModelAndView();
		String message = null;
		//set the view name to message
		mav.setViewName("message");
		
		
		//we craete an instance of a post object and set it to the session attribute myPost
		Post myPost = new Post();
		myPost = (Post) session.getAttribute("myPost");
		
		//we then call the post services class and call imgupload to upload the image to the server
		PostServices ps = new PostServices();
		
		//and set the string filename to the result of the method
		String filename = ps.imgupload(request);
		System.out.println(filename);
		
		//we then set the user and filename field
		myPost.setUser_id(userSession);
		myPost.setFile(filename+"");
		
		//when then persist it to the database
		int res = ps.upload(myPost,userSession.getId());
		
		if(res ==1) {//if res = 1
			message = ("Post was successfully uploaded");//image uploades successful;
			mav.addObject("loginmess", "none"); //set login messge to none
			mav.addObject("homenmess", "inline");//make home message visible
			
		}else {
			message = ("An error had occured, try changing name of file and reupload");//an error occurred
			mav.addObject("loginmess", "none");//set login messge to none
			mav.addObject("homenmess", "inline");//make home message visible
			
		}
		mav.addObject("message",message);

		
		
		return mav;//return the mav
	}
	@RequestMapping("/deletePost/{id}")
	public ModelAndView deleteImageHandler(@PathVariable("id") int id) {
		String message="";
		ModelAndView mav = new ModelAndView();
		Post post = new Post();
		PostServices ps = new PostServices();  
		mav.setViewName("message");
		boolean isdelete = ps.deletePost(id);
		if(isdelete) {
			message = "The post was deleted";
			mav.addObject("message",message);
			mav.addObject("homenmess", "inline");
			mav.addObject("loginmess", "none");
		}else {
			message = "post was not delted";
			mav.addObject("message",message);
			mav.addObject("homenmess", "inline");
			mav.addObject("loginmess", "none");
		}
		return mav;
	}
	
	
	//when someone want to view an image
	@RequestMapping("/img/{id}")
	public ModelAndView PostimgHandler(@PathVariable("id") int id,@SessionAttribute("userLogin") Users userSession) {//we pass the image id as an argument
		
		ModelAndView mav = new ModelAndView();
		
		//we set the view name to img.jsp
		mav.setViewName("img");  
		
		//we create an instance of post and post services
		Post post = new Post();
		PostServices ps = new PostServices();  
		
		//we the post by its id
		post = ps.getpostbyid(id);
		
		//we then get all the like for the post
		Long likes = ps.getlikes(post);
		boolean isTheUser = ps.isuser(post.getUser_id().getId(), userSession.getId());
		//we then get all of the comments for the post
		List<Comments> lc = ps.getcomments(post);
		
		//we set them to mav object
		mav.addObject("selectedpost", post);
		mav.addObject("postcomments", lc);
		mav.addObject("likes", likes);
		
		mav.addObject("isUser", isTheUser);
		return mav;//we then return the mav
		
	}
	
	@RequestMapping("updatepfp/{id}")
	public String profileImageHandler(@PathVariable("id") int id,HttpServletRequest request, HttpServletResponse response) {
		ProfileServices profile = new ProfileServices();
		profile.updateProfileImage(request, id);
		return "redirect:/account/"+id;
	}
	
	
	//when someone presses on the like button
	@RequestMapping("/imgl/{id}")
	public String LikeHandler(@PathVariable("id") int id ,@SessionAttribute("userLogin") Users userSession,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("delete");
		
		
		//we create an instance of post and post services
		Post post = new Post();
		
		PostServices ps = new PostServices();
		post = ps.getpostbyid(id);
		//we call the addlikes to add the new entry in the likes table 
		long newlike = ps.addlikes(post, userSession);
		
		
		return "redirect:/img/"+id;
		
	}
	
	
	//similar or the all like request handler
	//this one adds new comments to a post
	@RequestMapping("/imga/{id}")
	public String addcommentHandler(@PathVariable("id") int id,@SessionAttribute("userLogin") Users userSession, @RequestParam("comment") String com,HttpSession session) {
		ModelAndView mav = new ModelAndView();  
	
		mav.setViewName("delete");
		session.setAttribute("postid",id);
		PostServices ps = new PostServices();
		
		//wee call setcomment method to add the new comment to the comments table
		ps.setcomments(id, userSession.getId(), com);

		return "redirect:/img/"+id;
	}
	
	
	@RequestMapping("/deletec")
	public String deleteCommentHandler(@RequestParam("photoid") int post_id,HttpSession session,@RequestParam("comment") int cid) {
		ModelAndView mav = new ModelAndView();
		CommentServices cs = new CommentServices();
		cs.deletecomment(cid);
		mav.setViewName("delete");
		session.setAttribute("postid", post_id);
		return "redirect:/img/"+post_id;
	}
	
	
	
	
	
	
	//when a user submit their credentials on the login page call the validation handler
	@RequestMapping("/valid")
	public ModelAndView valdidateUserHandler(@ModelAttribute Users user, @SessionAttribute("userLogin") Users userSession) {
		
		//create an instance of modelandvie along with user services and post services 
		ModelAndView mav = new ModelAndView();
		UsersServices us = new UsersServices();
		

		String viewName = null;
		

		if(us.validate(user.getUsername(), user.getPassword())) {//if the username and password are valid
			try {
				//set user session to the user
				userSession = us.returnuser(user.getUsername());
				
				System.out.println(userSession);
				
				//create mav objects to hold the list objects
				mav.addObject("userLogin", userSession);

				System.out.print("sucess");
			
			}catch(Exception e) {
				e.printStackTrace();//if error print stack trace
			}
			
		}else {
			//System.out.print("failed");
			System.out.println(userSession);
			
		}
		if(userSession == null) {//if userSession is null, meaning the credentials were bad
			System.out.println("userSession");
			viewName = "login";//view name is set to login sending them back to the login
			
		}else if(userSession.getUsername() != null) {
			viewName = "loginsuccess"; //if not null then set it to home 
		}
		mav.setViewName(viewName);
		return mav;
		
	}
	//when a user wants to serach for a set of images containing a keyword in the title
	@RequestMapping("/search")
	public ModelAndView searchHandler(@SessionAttribute("userLogin") Users userSession,HttpServletRequest request) {
		//create an instance of modelandvie along with post services 
		ModelAndView mav = new ModelAndView(); 
		
		PostServices ps = new PostServices();
		//view name is set to serach.jsp
		String viewName = "search";
		//set get the parameter to a string
		String serach = request.getParameter("search");
		//pass that string in getkeyowrd method and store the result in a list of post
		List<Post> lp = ps.getallbykeywordpost(serach);
		
		//we then store the list in an mav object and return the mav
		mav.setViewName(viewName);
		mav.addObject("word", serach);
		mav.addObject("keyword", lp);
		return mav;
	}
	
	//when a user presses on a tag button 
	//it will call the method to getAllPostByTags store them in a list
	//send the list in a mav object to the search page
	@RequestMapping("/tags")
	public ModelAndView searchtagHandler( @SessionAttribute("userLogin") Users userSession,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(); 
		String viewName = "search";
		PostServices ps = new PostServices();
		String tags = request.getParameter("search");
		List<Post> lp = ps.getAllPostByTags(tags);
		System.out.println(lp.size());
		mav.setViewName(viewName);
		mav.addObject("word", tags);
		mav.addObject("keyword", lp);
		return mav;
	}
	
	//calls the sign-out page
	@RequestMapping("/signout")
	public String signoutHandler(@SessionAttribute("userLogin") Users userSession) {
		return"signout";
		
	}

}

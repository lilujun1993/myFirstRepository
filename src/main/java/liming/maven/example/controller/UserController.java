package liming.maven.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import liming.maven.example.model.UserInfo;
import liming.maven.example.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request){
		
		List<UserInfo> findAll = userService.findAll();
		
		request.setAttribute("userList", findAll);
		System.out.println("获取全部用户信息成功！！");
		return "/allUser";
	}
	
	/**
	 * 璺宠浆鍒版坊鍔犵敤鎴风晫闈?
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request){
		
		return "/addUser";
	}
	/**
	 * 娣诲姞鐢ㄦ埛骞堕噸瀹氬悜
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	public String addUser(UserInfo userinfo,HttpServletRequest request){
		userService.save(userinfo);
		System.out.println("用户信息增加成功！！");
		return "redirect:/user/getAllUser.do";
	}
	
	/**
	 *缂栬緫鐢ㄦ埛
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(UserInfo userinfo,HttpServletRequest request){
		
		
		if(userService.update(userinfo)){
			userinfo = userService.findById(userinfo.getId());
			request.setAttribute("user", userinfo);
			System.out.println("用户信息更新成功！！");
			return "redirect:/user/getAllUser.do";
		}else{
			return "/error";
		}
	}
	/**
	 * 鏍规嵁id鏌ヨ鍗曚釜鐢ㄦ埛
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public String getUser(int id,HttpServletRequest request){
		
		request.setAttribute("user", userService.findById(id));
		System.out.println("用户信息获取成功by ID");
		return "/editUser";
	}
	/**
	 * 鍒犻櫎鐢ㄦ埛
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delUser")
	public void delUser(int id,HttpServletRequest request,HttpServletResponse response){
		String result = "{\"result\":\"error\"}";
		
		if(userService.delete(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

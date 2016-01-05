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
		System.out.println("��ȡȫ���û���Ϣ�ɹ�����");
		return "/allUser";
	}
	
	/**
	 * 跳转到添加用户界�?
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request){
		
		return "/addUser";
	}
	/**
	 * 添加用户并重定向
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	public String addUser(UserInfo userinfo,HttpServletRequest request){
		userService.save(userinfo);
		System.out.println("�û���Ϣ���ӳɹ�����");
		return "redirect:/user/getAllUser.do";
	}
	
	/**
	 *编辑用户
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(UserInfo userinfo,HttpServletRequest request){
		
		
		if(userService.update(userinfo)){
			userinfo = userService.findById(userinfo.getId());
			request.setAttribute("user", userinfo);
			System.out.println("�û���Ϣ���³ɹ�����");
			return "redirect:/user/getAllUser.do";
		}else{
			return "/error";
		}
	}
	/**
	 * 根据id查询单个用户
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public String getUser(int id,HttpServletRequest request){
		
		request.setAttribute("user", userService.findById(id));
		System.out.println("�û���Ϣ��ȡ�ɹ�by ID");
		return "/editUser";
	}
	/**
	 * 删除用户
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

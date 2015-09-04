package net.bunnyland.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bunnyland.dao.UserInfoDao;
import net.bunnyland.dto.UserInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@Autowired
	UserInfoDao userInfoDao;
	
	//����� ���� ����ȭ �� �ҷ�����
	//�ش� �޼��� �ȿ� ���� ����̽� ID�� �ҷ����� ����� �߰��� �ָ� �ȴ�.
	@RequestMapping(value = "/user.do")
	public String user(Model model, UserInfoDto dto, HttpServletRequest request, HttpSession session) {
		
		List<UserInfoDto> userList = userInfoDao.selectUser();
		model.addAttribute("userList", userList);
		
		return "managePage";
	}
	
}

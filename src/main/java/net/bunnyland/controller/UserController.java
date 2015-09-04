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
	
	//사용자 정보 동기화 및 불러오기
	//해당 메서드 안에 사용다 디바이스 ID를 불러오는 기능을 추가해 주면 된다.
	@RequestMapping(value = "/user.do")
	public String user(Model model, UserInfoDto dto, HttpServletRequest request, HttpSession session) {
		
		List<UserInfoDto> userList = userInfoDao.selectUser();
		model.addAttribute("userList", userList);
		
		return "managePage";
	}
	
}

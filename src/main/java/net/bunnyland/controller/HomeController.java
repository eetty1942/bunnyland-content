package net.bunnyland.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bunnyland.dao.ManagerInfoDao;
import net.bunnyland.dto.ManagerInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	/**
	 * @Autowired를 이용하여 쿼리에 접근하는 매서드럴 자동으로 연결시켜주고 임의의 이름을 지어주어 사용하기 쉽게끔 하는 기능을 합니다.
	 */
	@Autowired
	ManagerInfoDao managerDao;	
	
	//최초 서비스 접근되는 index 페이지 (로그인 화면)
	// 보완 필요 : 로그인 이후에는 접근되지 않도록 해야한다.
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpSession session, ManagerInfoDto managerDto) {
		
		return "login";
			
	}
	
	/**
	 * 로그인이 처리되는 영역이다. id, pw가  디비에 있는 정보와 일치하는지 확인할 뒤 경과를 출력한다.
	 * 
	 * @param request
	 * @param dto
	 * @return
	 */
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, ManagerInfoDto dto) {

		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		
		ManagerInfoDto managerDto = managerDao.selectManager(dto);
		
		if(request.getParameter("id").equals(managerDto.getId()) && request.getParameter("password").equals(managerDto.getPassword())) {
			
			request.getSession().setAttribute("manager", managerDto);
			
			return "redirect:/content.do";
		} else {
			return "redirect:/index.do";
		}
		
	}
	
	//로그아웃 처리
	// 세션을 무효화 함으로서 유지하던 세션을 없에고 로그아웃이 가능하게 해준다.
	 @RequestMapping("/logout.do")
	 public String logout(HttpSession session) {
		 
		 session.invalidate();
		 
		 return "redirect:/index.do";
	 }
}

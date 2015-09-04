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
	 * @Autowired�� �̿��Ͽ� ������ �����ϴ� �ż��已 �ڵ����� ��������ְ� ������ �̸��� �����־� ����ϱ� ���Բ� �ϴ� ����� �մϴ�.
	 */
	@Autowired
	ManagerInfoDao managerDao;	
	
	//���� ���� ���ٵǴ� index ������ (�α��� ȭ��)
	// ���� �ʿ� : �α��� ���Ŀ��� ���ٵ��� �ʵ��� �ؾ��Ѵ�.
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpSession session, ManagerInfoDto managerDto) {
		
		return "login";
			
	}
	
	/**
	 * �α����� ó���Ǵ� �����̴�. id, pw��  ��� �ִ� ������ ��ġ�ϴ��� Ȯ���� �� ����� ����Ѵ�.
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
	
	//�α׾ƿ� ó��
	// ������ ��ȿȭ �����μ� �����ϴ� ������ ������ �α׾ƿ��� �����ϰ� ���ش�.
	 @RequestMapping("/logout.do")
	 public String logout(HttpSession session) {
		 
		 session.invalidate();
		 
		 return "redirect:/index.do";
	 }
}

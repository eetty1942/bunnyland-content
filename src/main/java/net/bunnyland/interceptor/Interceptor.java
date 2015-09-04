package net.bunnyland.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bunnyland.dto.ManagerInfoDto;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//controller �̺�Ʈ ȣ�� ��
		HttpSession session = request.getSession();
		ManagerInfoDto managerDto = (ManagerInfoDto) session.getAttribute("manager");
		try {
			if(managerDto == null ) {
				response.sendRedirect("index.do");
				
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//controller ȣ�� �� view page ��� ��
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//controller + view ������ ��� ��� ��
		super.afterCompletion(request, response, handler, ex);
	}	

}

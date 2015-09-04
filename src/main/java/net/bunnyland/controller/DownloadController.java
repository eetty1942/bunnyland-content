package net.bunnyland.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController implements ApplicationContextAware{

	
    private WebApplicationContext context = null;

	// �ٿ�ε� ��� 
	@RequestMapping("/filedownload.do")
	public ModelAndView download(HttpServletRequest request) throws Exception {


		/**���ε� ��ο� �ִ� ���� �� ��ġ�ϴ� ���ϸ��� ã�� �ٿ�ε尡���� �������� �����Ѵ�
		 * �ʿ信 ���� ��� ���� �����մϴ�.
		 */
		File downloadFile = new File("/volume1/cds/bunnyland-content/WEB-INF/upload/" + request.getParameter("fileName"));
		return new ModelAndView("downloadView", "downloadFile", downloadFile);
		
	}
	
   private File getFile(HttpServletRequest request) {
           String path = context.getServletContext().getRealPath("/upLoad/" + request.getParameter("fileName"));
           return new File(path);
   }

  @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
           this.context = (WebApplicationContext) applicationContext;
   }
}

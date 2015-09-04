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

	// 다운로드 기능 
	@RequestMapping("/filedownload.do")
	public ModelAndView download(HttpServletRequest request) throws Exception {


		/**업로드 경로에 있는 파일 중 일치하는 파일명을 찾아 다운로드가능한 컨텐츠를 제공한다
		 * 필요에 따라 경로 수정 가능합니다.
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

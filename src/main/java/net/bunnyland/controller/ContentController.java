package net.bunnyland.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bunnyland.dao.ContentDao;
import net.bunnyland.dao.ContentFileDao;
import net.bunnyland.dao.PushLogDao;
import net.bunnyland.dto.ContentDto;
import net.bunnyland.dto.ContentFileDto;
import net.bunnyland.dto.PushLogDto;
import net.bunnyland.dao.ManagerInfoDao;
import net.bunnyland.dto.ManagerInfoDto;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ContentController {

	//업로드할 파일 경로
	private String uploadPath = "/volume1/cds/bunnyland-content/WEB-INF/upload";

	@Autowired
	ManagerInfoDao manageDao;
	@Autowired  
	ContentDao contentDao;
	@Autowired
	ContentFileDao fileDao;
	@Autowired
	PushLogDao pushDao;
	
	//관리자 페이지 접근
	@RequestMapping(value = "/content.do")
	public String content(Model model, ManagerInfoDto dto, HttpServletRequest request, HttpSession session) {
		
		//세션을 이용하여 관리자 정보를 받아오는 부분
		dto = (ManagerInfoDto) session.getAttribute("manager");
		ManagerInfoDto managerInfo = manageDao.selectManager(dto);
		model.addAttribute("managerInfo", managerInfo);
		
		// 컨텐츠 목록 불러오기
		List<ContentDto> contentList = contentDao.selectContent();
		model.addAttribute("contentList", contentList);

		//푸시 내역 불러오기 - 신규
		List<PushLogDto> newList = pushDao.selectNewContent();
		model.addAttribute("newList", newList);
		
		//푸시 내역 불러오기 - 이벤트
		List<PushLogDto> eventList = pushDao.selectEventContent();
		model.addAttribute("eventList", eventList);
		
		//푸시 내역 불러오기 -프로모션 
		List<PushLogDto> promotionList = pushDao.selectPromotionContent();
		model.addAttribute("promotionList", promotionList);

		//관리자 뷰페이지 리턴
		return "index";
	}
	
	//컨텐츠 및 파일 목록 json처리 
	//리턴되는 값은 model 각각의 이름에 맞는데이터가 표시되어지게끔 되어있습니다.
	@RequestMapping(value="/content_list_a.do", method = RequestMethod.GET)
	public String contentData(Model model) {
		
		List<ContentDto> contentList = new ArrayList<ContentDto>();
		contentList = contentDao.selectContent();
		
		List<ContentFileDto> fileList = new ArrayList<ContentFileDto>();
		fileList = fileDao.selectContentFileList();
		
		//컨텐츠에 대한 JSON 처리
		JSONArray jsonArray_content = new JSONArray();
		model.addAttribute("contentlist", contentList);
		model.addAttribute("content_jsonList", jsonArray_content.fromObject(contentList));
		
		//컨텐츠 파일에 대한 JSON 처리
		JSONArray jsonArray_file = new JSONArray();
		model.addAttribute("filelist", fileList);
		model.addAttribute("file_jsonList", jsonArray_file.fromObject(fileList));
		
		return "jsonList";
	}
	

	//컨텐츠 상세
	@RequestMapping(value = "/contentdetail.do")
	public String contentDetail( Model model, int idx, HttpServletRequest request, HttpSession session) {
		
		//content 내용 
		ContentDto contentDetail = contentDao.selectContentDetail(idx);
		model.addAttribute("contentDetail", contentDetail);
		
		//해당 content에 엮여있는 파일목록 및 상세
		List<ContentFileDto> fileDetail = fileDao.selectContentFile(idx);
		model.addAttribute("fileDetail", fileDetail);
 
		return "contentDetail";
	}
	
	//컨텐츠  수정 폼 test
		@RequestMapping(value = "/testmod.do", method = RequestMethod.GET)
		public String testMod(ContentDto dto, Model model) {

			ContentDto contentDto = new ContentDto();
			ManagerInfoDto managerDto = new ManagerInfoDto();
			ContentFileDto fileDto = new ContentFileDto();
			
			contentDto.setManagerInfo_managerNumber(1);
			ContentDto contentDetail = contentDao.selectContentDetail(contentDto.getContentNumber());
			model.addAttribute("contentDetail", contentDetail);
			List<ContentFileDto> fileDetail = fileDao.selectContentFile(contentDto.getContentNumber());
			model.addAttribute("fileDetail", fileDetail);
					
			return "contentModifyForm";
		}
	
	// 이미지 하나씩 불러오는 방법
	@RequestMapping(value="/thumbnailImage.do", method = RequestMethod.GET) 
	public void thumbnailImage(Model model, @RequestParam("contentNumber") int contentNum, HttpServletRequest req, HttpServletResponse res) throws IOException {

		
		InputStream is = null;
		res.setContentType("image/jpeg");
		ContentDto contentDto = contentDao.selectContentDetail(contentNum);
		is = new ByteArrayInputStream(contentDto.getThumbnail());
		
		ServletOutputStream os = res.getOutputStream();
		
		int binaryRead;
		
		while((binaryRead = is.read()) != -1){
			os.write(binaryRead);
		}
	}
	
	//컨텐츠 수정 - 보완 필요할 수도 있습니다.
	@RequestMapping(value = "/contentUpdate.do", method = RequestMethod.POST)
	public String contentUpdate(@RequestParam("f") MultipartFile[] multipartFile, HttpServletRequest request, 
			@RequestParam("title") String title, @RequestParam("thumb") MultipartFile multiImage, @RequestParam("description") String description, ContentDto dto, Model model) throws IOException {
		
		ContentDto contentDto = new ContentDto();
		ManagerInfoDto managerDto = new ManagerInfoDto();
		ContentFileDto fileDto = new ContentFileDto();
		contentDto.setContentNumber(6);
		contentDto.setManagerInfo_managerNumber(1);
		ContentDto contentDetail = contentDao.selectContentDetail(dto.getContentNumber());
		model.addAttribute("contentDetail", contentDetail);
		List<ContentFileDto> fileDetail = fileDao.selectContentFile(dto.getContentNumber());
		model.addAttribute("fileDetail", fileDetail);
		
        MultipartFile[] multi = multipartFile;
        
        if (multipartFile != null) {
        	
        	
        	byte[] imagebytes = multiImage.getBytes();
        	File imageFile = new File(uploadPath, multiImage.getOriginalFilename());
			FileCopyUtils.copy(imagebytes, imageFile);

			contentDto.setContentName(title);
			contentDto.setDescription(description);
			contentDto.setThumbnail(imagebytes);
	
			contentDto.setManagerInfo_managerNumber(1);
			

			//DB 수정
			contentDao.updateContent(contentDto);
			
			int idx = contentDao.selectContentNum();
			
            for (int i = 0; i < multi.length; i++) {
               /* // 파일 중복명 처리
                String genId = UUID.randomUUID().toString(); 
                // 본래 파일명
                String originalfileName = mf.get(i).getOriginalFilename(); 
                 
                String saveFileName = genId + "." + getExtension(originalfileName);
                // 저장되는 파일 이름
                */ 
            	
            	byte[] bytes = multi[i].getBytes();
            	File file = new File(uploadPath, multi[i].getOriginalFilename());
    			FileCopyUtils.copy(bytes, file);
    			
    			fileDto.setFileName(multi[i].getOriginalFilename());
    			fileDto.setFilePath(file.getAbsolutePath());
    			fileDto.setFileType(multi[i].getContentType());
    			fileDto.setFileSize((int) multi[i].getSize());
    			fileDto.setContent_contentNumber(idx);
    			//db 파일저장
    			fileDao.updateContentFile(fileDto);
    			
            }
            return ("redirect:content.do");
        }
		return "redirect:/contentUpdate.do";
	}
	
	//컨텐츠 삭제
	@RequestMapping(value = "/deleteContent.do")
	public String deleteContent(int contentNum, Model model, ContentDto dto, HttpServletRequest request, HttpSession session) {
		
		List<ContentFileDto> fileDto =fileDao.selectContentFile(contentNum);
		model.addAttribute("fileDto", fileDto);

		for(int i=0; i<fileDto.size();i++) {

			String fileName = fileDto.get(i).getFileName();
			File file = new File(uploadPath, fileName);
			
			if(file.exists()) {
				file.delete();
				
			} 
		}
		fileDao.deleteContentFile(contentNum);
    	
		contentDao.deleteContent(contentNum);
		
		return "redirect:/content.do";
	}
	
	//파일 업로드 - 컨텐츠 등록 및 파일 처리	 
	@RequestMapping(value = "/registContent.do", method = RequestMethod.POST)
	public String test(@RequestParam("f") MultipartFile[] multipartFile, HttpServletRequest request, 
			@RequestParam("title") String title, @RequestParam("manager") int managernum, @RequestParam("thumb") MultipartFile multiImage, @RequestParam("description") String description, Model model) throws IOException, InterruptedException {
		
 
        // 넘어온 파일을 리스트로 저장
        MultipartFile[] multi = multipartFile;
        
        if (multipartFile != null) {
        	ContentDto contentDto = new ContentDto();
        	ManagerInfoDto managerDto = new ManagerInfoDto();
        	ContentFileDto fileDto = new ContentFileDto();
        	
        	byte[] imagebytes = multiImage.getBytes();
        	File imageFile = new File(uploadPath, multiImage.getOriginalFilename());
			FileCopyUtils.copy(imagebytes, imageFile);

			contentDto.setContentName(title);
			contentDto.setDescription(description);
			contentDto.setThumbnail(imagebytes);

			contentDto.setManagerInfo_managerNumber(managernum);
			

			//DB입력
			
			contentDao.insertContent(contentDto);
			int idx = contentDao.selectContentNum();
			
            for (int i = 0; i < multi.length; i++) {
                /*// 파일 중복명 처리
                String genId = UUID.randomUUID().toString(); 
                // 본래 파일명
                String originalfileName = mf.get(i).getOriginalFilename(); 
                 
                String saveFileName = genId + "." + getExtension(originalfileName);
                // 저장되는 파일 이름*/
            	
            	//파일명 중복 처리 샘플이며 사용자 기호에 맞게 수정하시면 됩니다. 위의 경우 등록일을 추가하는 방식으로 되는 방법입니다. 
            	
            	String originalFileName = multi[i].getOriginalFilename();
        		String contentType = multi[i].getContentType();
        		int fileSize = (int) multi[i].getSize();
    			String upPath = uploadPath+"/"+originalFileName;

        		
        		InputStream is = null;
        		FileOutputStream out = null;
        		
            	is = multi[i].getInputStream();

                //해당 경로에 폴더가 존재하는지 여부 검증, 없다면 만든다.
        		File dir = new File(uploadPath);   
                if (!dir.exists()) {
                	dir.mkdirs();
                }

                out = new FileOutputStream(upPath);
                
    			FileCopyUtils.copy(is, out);
    			
    			//db 파일저장
				fileDto.setFileName(originalFileName);
				fileDto.setFileSize(fileSize);
				fileDto.setFileType(contentType);
				fileDto.setFilePath(uploadPath);
				fileDto.setContent_contentNumber(idx);
				
    			fileDao.insertContentFile(fileDto);
    			
    			is.close();
    			out.close();
    			
            }
            return ("redirect:content.do");
        }
        return ("redirect:formtest.do");

	}
	
}

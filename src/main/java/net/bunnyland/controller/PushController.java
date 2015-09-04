package net.bunnyland.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import net.bunnyland.dao.PushLogDao;
import net.bunnyland.dao.UserInfoDao;
import net.bunnyland.dto.PushLogDto;
import net.bunnyland.dto.UserInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Controller
public class PushController {

	// GCM의 API 키를 넣어준다.
	private static final String API = "";
	
	private static final Sender sender = new Sender(API);
	private static final String ENC = "UTF-8";
	Result result = null;
	
	@Autowired
	PushLogDao pushDao;
	@Autowired
	static
	UserInfoDao userDao;
	
	
	//푸시알림 내용 작성 신규
	@RequestMapping(value = "/pushNew.do",  method = RequestMethod.POST)
	public static boolean pushNew(PushLogDto dto) {
		
		// 여기에 리스트 형태로 사용자의 디바이스 아이디를 불러온 뒤 아래의 부시알림 로직을 순회처리하여 전송해주면 됩니다.
		List<UserInfoDto> userList = userDao.selectUser(); // 사용자 디바이스 정보 불러오기

		//전송되는 메시지, 디바이스 아이디
		String message = dto.getPushDescription();
		//List<String> deviceId = 
		
		PushLogDto pushDto = new PushLogDto();
		pushDto.setPushKind(dto.getPushKind());
		pushDto.setManagerInfo_managerNumber(dto.getManagerInfo_managerNumber());
		pushDto.setPushDescription(message);
	
		try {
			message = URLEncoder.encode(message, ENC);
			final Message.Builder messageBuilder = new Message.Builder();
			messageBuilder.addData("msg", message);
			//메세지 전송 부분
			for(int i=0; i<userList.size(); i++) {
				String deviceId = userList.get(i).getDevice();
						final com.google.android.gcm.server.Result result = sender.send(messageBuilder.build(), deviceId, 5);
						final String messageId = result.getMessageId();
						
						return (messageId != null);
			}
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return false;
	}
		
	//푸시알림 내용 작성 이벤트 프로모션
	@RequestMapping(value = "/pushEvent.do", method = RequestMethod.POST)
	public static boolean pushEvent(PushLogDto dto) {
		
		// 여기에 리스트 형태로 사용자의 디바이스 아이디를 불러온 뒤 아래의 부시알림 로직을 순회처리하여 전송해주면 됩니다.
		
		//전송되는 메시지, 디바이스 아이디, url
		String message = dto.getPushDescription();
		String deviceId = "";
		String url = dto.getUrl();
		
		PushLogDto pushDto = new PushLogDto();
		pushDto.setPushKind(dto.getPushKind());
		pushDto.setUrl(url);
		pushDto.setManagerInfo_managerNumber(dto.getManagerInfo_managerNumber());
		pushDto.setPushDescription(message);
		
		try {
			message = URLEncoder.encode(message, ENC);
			final Message.Builder messageBuilder = new Message.Builder();
			messageBuilder.addData("url", url);
			messageBuilder.addData("msg", message);
			//메세지 전송 부분
			final com.google.android.gcm.server.Result result = sender.send(messageBuilder.build(), deviceId, 5);
			final String messageId = result.getMessageId();
			
			return (messageId != null);
		
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}


	//푸시 내역 관리
	@RequestMapping(value="/pushlog.do")
	public String pushLogList() {
		return "";
	}
	
}

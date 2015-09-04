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

	// GCM�� API Ű�� �־��ش�.
	private static final String API = "";
	
	private static final Sender sender = new Sender(API);
	private static final String ENC = "UTF-8";
	Result result = null;
	
	@Autowired
	PushLogDao pushDao;
	@Autowired
	static
	UserInfoDao userDao;
	
	
	//Ǫ�þ˸� ���� �ۼ� �ű�
	@RequestMapping(value = "/pushNew.do",  method = RequestMethod.POST)
	public static boolean pushNew(PushLogDto dto) {
		
		// ���⿡ ����Ʈ ���·� ������� ����̽� ���̵� �ҷ��� �� �Ʒ��� �νþ˸� ������ ��ȸó���Ͽ� �������ָ� �˴ϴ�.
		List<UserInfoDto> userList = userDao.selectUser(); // ����� ����̽� ���� �ҷ�����

		//���۵Ǵ� �޽���, ����̽� ���̵�
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
			//�޼��� ���� �κ�
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
		
	//Ǫ�þ˸� ���� �ۼ� �̺�Ʈ ���θ��
	@RequestMapping(value = "/pushEvent.do", method = RequestMethod.POST)
	public static boolean pushEvent(PushLogDto dto) {
		
		// ���⿡ ����Ʈ ���·� ������� ����̽� ���̵� �ҷ��� �� �Ʒ��� �νþ˸� ������ ��ȸó���Ͽ� �������ָ� �˴ϴ�.
		
		//���۵Ǵ� �޽���, ����̽� ���̵�, url
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
			//�޼��� ���� �κ�
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


	//Ǫ�� ���� ����
	@RequestMapping(value="/pushlog.do")
	public String pushLogList() {
		return "";
	}
	
}

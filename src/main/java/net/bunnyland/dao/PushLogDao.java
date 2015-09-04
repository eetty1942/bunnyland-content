package net.bunnyland.dao;

import java.util.List;

import net.bunnyland.dto.PushLogDto;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PushLogDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	//푸시 로그 불러오기
	public List<PushLogDto> selectContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectPushLog");
	}
	
	// 신규 푸시 로그 불러오기
	public List<PushLogDto> selectNewContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectNewPushLog");
	}
			
	//이벤트 푸시 로그 불러오기
	public List<PushLogDto> selectEventContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectEventPushLog");
	}
			
	//프로모션 푸시 로그 불러오기
	public List<PushLogDto> selectPromotionContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectPromotionPushLog");
	}
			
	// 푸시로그 추가
	public int insertPushLog(PushLogDto dto){
		return sqlSessionTemplate.insert("pushLogSql.insertPushLog", dto);
	}
	// 푸시로그 삭제
	public int deleteContent(int logNum){
		return sqlSessionTemplate.delete("pushLogSql.deletePushLog", logNum);
	}
}

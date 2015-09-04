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
	
	//Ǫ�� �α� �ҷ�����
	public List<PushLogDto> selectContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectPushLog");
	}
	
	// �ű� Ǫ�� �α� �ҷ�����
	public List<PushLogDto> selectNewContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectNewPushLog");
	}
			
	//�̺�Ʈ Ǫ�� �α� �ҷ�����
	public List<PushLogDto> selectEventContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectEventPushLog");
	}
			
	//���θ�� Ǫ�� �α� �ҷ�����
	public List<PushLogDto> selectPromotionContent(){
		return sqlSessionTemplate.selectList("pushLogSql.selectPromotionPushLog");
	}
			
	// Ǫ�÷α� �߰�
	public int insertPushLog(PushLogDto dto){
		return sqlSessionTemplate.insert("pushLogSql.insertPushLog", dto);
	}
	// Ǫ�÷α� ����
	public int deleteContent(int logNum){
		return sqlSessionTemplate.delete("pushLogSql.deletePushLog", logNum);
	}
}

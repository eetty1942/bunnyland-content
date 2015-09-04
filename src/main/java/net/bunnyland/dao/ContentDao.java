package net.bunnyland.dao;

import java.util.List;

import net.bunnyland.dto.ContentDto;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	//ÄÁÅÙÃ÷ ¸®½ºÆ® ºÒ·¯¿À±â
	public List<ContentDto> selectContent(){
		return sqlSessionTemplate.selectList("contentSql.selectContent");
	}
	//ÄÁÅÙÃ÷ »ó¼¼ ºÒ·¯¿À±â
	public ContentDto selectContentDetail(int contentNumber){
		return sqlSessionTemplate.selectOne("contentSql.selectContentDetail", contentNumber);
	}
	//ÄÁÅÙÃ÷ µî·Ï½Ã ¹øÈ£ ºÒ·¯¿À±â
	public int selectContentNum(){
		return sqlSessionTemplate.selectOne("contentSql.selectContentNum");
	}
/*	//ÄÁÅÙÃ÷ ½æ³×ÀÏ ÀÌ¹ÌÁö ºÒ·¯¿À±â
	public ContentDto selectContentImage(int contentNumber){
		return sqlSessionTemplate.selectOne("contentSql.selectContentImage", contentNumber);
	}*/
	// ÄÁÅÙÃ÷ Ãß°¡
	public int insertContent(ContentDto dto){
		return sqlSessionTemplate.insert("contentSql.insertContent", dto);
	}
	// ÄÁÅÙÃ÷ ¼öÁ¤
	public int updateContent(ContentDto dto){
		return sqlSessionTemplate.update("contentSql.updateContent", dto);
	}
	// ÄÁÅÙÃ÷ »èÁ¦
	public int deleteContent(int contentNum){
		return sqlSessionTemplate.delete("contentSql.deleteContent", contentNum);
	}
}

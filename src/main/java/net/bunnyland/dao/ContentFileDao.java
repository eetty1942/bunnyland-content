package net.bunnyland.dao;

import java.util.List;

import net.bunnyland.dto.ContentFileDto;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentFileDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	//ÄÁÅÙÃ÷ ¸®½ºÆ® ºÒ·¯¿À±â
	public List<ContentFileDto> selectContentFile(int contentNumber){
		return sqlSessionTemplate.selectList("contentFileSql.selectContentFile", contentNumber);
	}
	
	//ÄÁÅÙÃ÷ json ¸®½ºÆ® ºÒ·¯¿À±â
		public List<ContentFileDto> selectContentFileList(){
			return sqlSessionTemplate.selectList("contentFileSql.selectContentFileList");
		}
	//ÄÁÅÙÃ÷ »ó¼¼ ºÒ·¯¿À±â
	public ContentFileDto selectContentDetail(int fileNumber){
		return sqlSessionTemplate.selectOne("contentFileSql.selectContentFileDetail", fileNumber);
	}
	//ÄÁÅÙÃ÷ ½æ³×ÀÏ ÀÌ¹ÌÁö ºÒ·¯¿À±â
		public ContentFileDto selectThumbnail(String fileType){
			return sqlSessionTemplate.selectOne("contentFileSql.selectThumbnail", fileType);
		}
	
	// ÄÁÅÙÃ÷ Ãß°¡
	public int insertContentFile(ContentFileDto dto){
		return sqlSessionTemplate.insert("contentFileSql.insertContentFile", dto);
	}
	// ÄÁÅÙÃ÷ ¼öÁ¤
	public int updateContentFile(ContentFileDto dto){
		return sqlSessionTemplate.update("contentFileSql.updateContentFile", dto);
	}
	// ÄÁÅÙÃ÷ »èÁ¦
	public int deleteContentFile(int contentNum){
		return sqlSessionTemplate.delete("contentFileSql.deleteContentFile", contentNum);
	}
}

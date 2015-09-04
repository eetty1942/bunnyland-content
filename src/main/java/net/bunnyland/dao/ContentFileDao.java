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

	//������ ����Ʈ �ҷ�����
	public List<ContentFileDto> selectContentFile(int contentNumber){
		return sqlSessionTemplate.selectList("contentFileSql.selectContentFile", contentNumber);
	}
	
	//������ json ����Ʈ �ҷ�����
		public List<ContentFileDto> selectContentFileList(){
			return sqlSessionTemplate.selectList("contentFileSql.selectContentFileList");
		}
	//������ �� �ҷ�����
	public ContentFileDto selectContentDetail(int fileNumber){
		return sqlSessionTemplate.selectOne("contentFileSql.selectContentFileDetail", fileNumber);
	}
	//������ ����� �̹��� �ҷ�����
		public ContentFileDto selectThumbnail(String fileType){
			return sqlSessionTemplate.selectOne("contentFileSql.selectThumbnail", fileType);
		}
	
	// ������ �߰�
	public int insertContentFile(ContentFileDto dto){
		return sqlSessionTemplate.insert("contentFileSql.insertContentFile", dto);
	}
	// ������ ����
	public int updateContentFile(ContentFileDto dto){
		return sqlSessionTemplate.update("contentFileSql.updateContentFile", dto);
	}
	// ������ ����
	public int deleteContentFile(int contentNum){
		return sqlSessionTemplate.delete("contentFileSql.deleteContentFile", contentNum);
	}
}

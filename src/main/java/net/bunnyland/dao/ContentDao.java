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
	
	//������ ����Ʈ �ҷ�����
	public List<ContentDto> selectContent(){
		return sqlSessionTemplate.selectList("contentSql.selectContent");
	}
	//������ �� �ҷ�����
	public ContentDto selectContentDetail(int contentNumber){
		return sqlSessionTemplate.selectOne("contentSql.selectContentDetail", contentNumber);
	}
	//������ ��Ͻ� ��ȣ �ҷ�����
	public int selectContentNum(){
		return sqlSessionTemplate.selectOne("contentSql.selectContentNum");
	}
/*	//������ ����� �̹��� �ҷ�����
	public ContentDto selectContentImage(int contentNumber){
		return sqlSessionTemplate.selectOne("contentSql.selectContentImage", contentNumber);
	}*/
	// ������ �߰�
	public int insertContent(ContentDto dto){
		return sqlSessionTemplate.insert("contentSql.insertContent", dto);
	}
	// ������ ����
	public int updateContent(ContentDto dto){
		return sqlSessionTemplate.update("contentSql.updateContent", dto);
	}
	// ������ ����
	public int deleteContent(int contentNum){
		return sqlSessionTemplate.delete("contentSql.deleteContent", contentNum);
	}
}

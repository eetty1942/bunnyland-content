package net.bunnyland.dao;

import java.util.List;

import net.bunnyland.dto.ManagerInfoDto;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	//������ ���� �ҷ�����
	public ManagerInfoDto selectManager(ManagerInfoDto dto){
		return sqlSessionTemplate.selectOne("managerInfoSql.selectManager", dto);
	}
	
	// ȸ�� ���� �߰�
	public int insertManager(ManagerInfoDto dto){
		return sqlSessionTemplate.insert("memberInfoSql.insertManager", dto);
	}
	
	// ������ ���� ����
	public int updateManager(ManagerInfoDto dto){
		return sqlSessionTemplate.update("managerInfoSql.updateManager", dto);
	}
	
	// ������ ���� ����
	public int deleteManager(int managerNum){
		return sqlSessionTemplate.delete("managerInfoSql.deleteManager", managerNum);
	}
		
}

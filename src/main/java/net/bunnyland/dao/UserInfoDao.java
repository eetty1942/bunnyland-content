package net.bunnyland.dao;

import java.util.List;

import net.bunnyland.dto.UserInfoDto;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	//����� ���� �ٷ�����
	public List<UserInfoDto> selectUser(){
		return sqlSessionTemplate.selectList("userInfoSql.selectUser");
	}
	
	//����� �߰�
	public int insertUser(UserInfoDto dto){
		return sqlSessionTemplate.insert("contentSql.insertUser", dto);
	}
	
	//����� ���� ����
	public int updateUser(UserInfoDto dto){
		return sqlSessionTemplate.update("userInfoSql.updateUser", dto);
	}

	//���� ����
	public int deleteContent(int deviceNum){
		return sqlSessionTemplate.delete("userInfoSql.deleteUser", deviceNum);
	}
}

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
	
	//사용자 정보 줄러오가
	public List<UserInfoDto> selectUser(){
		return sqlSessionTemplate.selectList("userInfoSql.selectUser");
	}
	
	//사용자 추가
	public int insertUser(UserInfoDto dto){
		return sqlSessionTemplate.insert("contentSql.insertUser", dto);
	}
	
	//사용자 정보 수정
	public int updateUser(UserInfoDto dto){
		return sqlSessionTemplate.update("userInfoSql.updateUser", dto);
	}

	//정보 삭제
	public int deleteContent(int deviceNum){
		return sqlSessionTemplate.delete("userInfoSql.deleteUser", deviceNum);
	}
}

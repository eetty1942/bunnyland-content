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
	
	//관리자 정보 불러오기
	public ManagerInfoDto selectManager(ManagerInfoDto dto){
		return sqlSessionTemplate.selectOne("managerInfoSql.selectManager", dto);
	}
	
	// 회원 정보 추가
	public int insertManager(ManagerInfoDto dto){
		return sqlSessionTemplate.insert("memberInfoSql.insertManager", dto);
	}
	
	// 관리자 정보 수정
	public int updateManager(ManagerInfoDto dto){
		return sqlSessionTemplate.update("managerInfoSql.updateManager", dto);
	}
	
	// 관리자 정보 삭제
	public int deleteManager(int managerNum){
		return sqlSessionTemplate.delete("managerInfoSql.deleteManager", managerNum);
	}
		
}

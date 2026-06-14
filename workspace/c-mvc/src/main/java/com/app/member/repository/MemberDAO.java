package com.app.member.repository;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.app.member.domain.MemberVO;
import com.app.mybatis.config.MyBatisConfig;

public class MemberDAO {
	public SqlSession sqlSession;
	
	public MemberDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
//														true 면 자동으로커밋. 일단은 true 로하자 나중에 배우고 우리가 조절하자
	}
//	여기까지는 변하지않는 코드
	
	
//	회원가입
	public void insert(MemberVO memberVO) {
//		매퍼.xml의 namespace.id 임
		sqlSession.insert("member.insert", memberVO);
	}
	
//	로그인
	public Optional<MemberVO> selectByMemberEmailAndMemberPassword(MemberVO memberVO) {
		return Optional.ofNullable((MemberVO) sqlSession.selectOne("member.selectByMemberEmailAndMemberPassword", memberVO));
	}
}

















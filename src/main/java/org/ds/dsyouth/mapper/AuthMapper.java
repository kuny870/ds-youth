package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.Address;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {

	// 권한
	List<Auth> selectAuthList();
	int selectAuthCnt(int id);	// 권한의 cnt 구하기
	int selectAuthExecCnt();	// 국장 권한의 cnt 구하기
	List<User> selectUserListByAuth();	// 회원권한
	List<User> selectUserListByUser();	// 회원관리
	boolean deleteAuth(Auth auth);	// 권한 초기화
	boolean deleteAuthExec();	// 국장 권한 초기화
	
	boolean insertUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(User user);	// 회원 탈퇴(강퇴)
	boolean restoreUser(User user);	// 회원 복구
	User selectUserByLoginId(User user);
//	Master selectMasterByLoginId(Master master);
//	Partner selectPartnerByLoginId(Partner partner);
//	User selectUserById(User user);
	
	boolean insertSessionId(User user);
	boolean deleteSessionId(User user);
//	boolean deleteSessionIdMaster(Master master);
//	boolean deleteSessionIdPartner(Partner partner);
	User selectSessionId(String sessionId);
	
//	boolean updateEmailToken(User user);
//	boolean updateEmailAuth(User user);
//	boolean updateRePwTime(User user);
//	boolean updateDelTime(User user);
//	boolean updateUserPw(User user);
//	boolean updateBusinessApply(User user);

	// 주소 관리
	boolean insertAddress(Address address);
	List<Address> selectAddressList(User user);
	Address selectAddress(Address address);
	boolean updateAddress(Address address);
	boolean deleteAddress(Address address);
	
}

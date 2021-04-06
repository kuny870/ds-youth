package org.ds.dsyouth.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.ds.dsyouth.exception.IdDuplicatedException;
import org.ds.dsyouth.exception.IdNoMatchException;
import org.ds.dsyouth.exception.PasswordNoMatchException;
import org.ds.dsyouth.model.Address;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.User;
import org.ds.dsyouth.model.UserKeepLogin;

public interface AuthService {

	boolean registUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException;
	boolean modifyUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	boolean modifyUserPwReset(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;	// 임시 비밀번호로 변경하기
	boolean removeUser(User user);	// 회원 강퇴
	boolean restoreUser(User user);	// 회원 복구
	User getUserById(User user);	// id 값으로 user 객체 불러오기
	User getUserByLoginId(User user);	// loginId 값으로 user 객체 불러오기
//	Master getMasterByLoginId(Master master);	// loginId 값으로 master 객체 불러오기
//	Partner getPartnerByLoginId(Partner partner);	// loginId 값으로 partner 객체 불러오기
	
	void getUserCheck(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException;
//	void getMasterCheck(Master master) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException;
//	void getPartnerCheck(Partner partner) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException;
	boolean keepLogin(UserKeepLogin ukl);
	boolean removeSessionId(UserKeepLogin ukl);
//	boolean removeSerssionIdMaster(Master master);
//	boolean removeSerssionIdPartner(Partner partner);
	User getSessionId(String sessionId);
	
//	void sendMail(User user) throws Exception;
//	boolean modifyUserEmailAuth(User user);
//	void sendMailForFindPw(User user) throws Exception;
//	boolean modifyUserPw(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
//	void sendMailForWithdraw(User user) throws Exception;
//	boolean modifyBusinessApply(User user);
	
	// 주소 관리
	boolean registAddress(Address address);
	List<Address> getAddressList(User user);
	Address getAddress(Address address);
	boolean modifyAddress(Address address);
	boolean removeAddress(Address address);
	
	int getAuthCnt(int id);		// 권한의 cnt 구하기
	int getAuthExecCnt();		// 국장 권한의 cnt 구하기
	
	List<User> getUserListByAuth(int id);	// 회원권한
	List<User> getUserListByUser();	// 회원관리
	boolean removeAuth(Auth auth);	// 권한 초기화
	boolean removeAuthExec();	// 국장 권한 초기화
	
	Auth getAuthById(int id);
	
}

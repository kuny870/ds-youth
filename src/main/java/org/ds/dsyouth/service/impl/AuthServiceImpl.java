package org.ds.dsyouth.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.ds.dsyouth.exception.IdDuplicatedException;
import org.ds.dsyouth.exception.IdNoMatchException;
import org.ds.dsyouth.exception.PasswordNoMatchException;
import org.ds.dsyouth.mapper.AuthMapper;
import org.ds.dsyouth.model.Address;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.User;
import org.ds.dsyouth.model.UserKeepLogin;
import org.ds.dsyouth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	public static final int PAGESIZE = 10; // 게시판 하단에 보이게 될 페이지 개수 ==> (1 2 3 다음) 이런식으로, 글 게시판이든 사진 게시판이든 똑같이 10개로 고정
	
	@Autowired
	private AuthMapper authMapper;


	/**
	 * 회원가입
	 */
	@Override
	public boolean registUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException {
		
		User userConfirm = authMapper.selectUserByLoginId(user);
				
		if(userConfirm != null) {
			throw new IdDuplicatedException();
		}
		
		boolean result = false;
		
        // SHA-256 MessageDigest의 생성
        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
        // 문자열 바이트로 메시지 다이제스트를 갱신
        mdSHA256.update(user.getLoginPw().getBytes("UTF-8"));
        // 해시 계산 반환값은 바이트 배열
        byte[] sha256Hash = mdSHA256.digest();
        // 바이트배열을 16진수 문자열로 변환하여 표시
        StringBuilder hexSHA256hash = new StringBuilder();
        for(byte b : sha256Hash) {
            String hexString = String.format("%02x", b);
            hexSHA256hash.append(hexString);
        }
        
        user.setLoginPw(hexSHA256hash.toString());

        result = authMapper.insertUser(user);
		
		return result;
	}

	
	/**
	 * 로그인 시 회원정보 체크 - 일반 사용자
	 */
	@Override
	public void getUserCheck(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException {
		
		User userConfirm = authMapper.selectUserByLoginId(user);
		
		checkId(userConfirm);			// id 유무 check
		checkPw(user, userConfirm);		// pw 일치 check
//		checkEmailAuth(userConfirm);	// email 인증 check
	}

	public User getUserById(User user) {
		return null;
	}

	/**
	 * ID 있는지 체크
	 * @param user
	 * @throws IdNoMatchException
	 * @throws PasswordNoMatchException
	 */
	public void checkId(User user) throws IdNoMatchException, PasswordNoMatchException {
		User userConfirm = authMapper.selectUserByLoginId(user);
		
		if(userConfirm == null) {
			throw new IdNoMatchException();
		}
	}
	

	/**
	 * PW 일치하는지 체크
	 * @param user
	 * @param userConfirm
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws PasswordNoMatchException
	 */
	public void checkPw(User user, User userConfirm) throws NoSuchAlgorithmException, UnsupportedEncodingException, PasswordNoMatchException {
		
		// SHA-256 MessageDigest의 생성
        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
        // 문자열 바이트로 메시지 다이제스트를 갱신
        mdSHA256.update(user.getLoginPw().getBytes("UTF-8"));
        // 해시 계산 반환값은 바이트 배열
        byte[] sha256Hash = mdSHA256.digest();
        // 바이트배열을 16진수 문자열로 변환하여 표시
        StringBuilder hexSHA256hash = new StringBuilder();
        for(byte b : sha256Hash) {
            String hexString = String.format("%02x", b);
            hexSHA256hash.append(hexString);
        }
        
        user.setLoginPw(hexSHA256hash.toString());
        
    	if(! user.getLoginPw().equals(userConfirm.getLoginPw())) {
    		throw new PasswordNoMatchException();
    	}
	}

	@Override
	public User getUserByLoginId(User user) {
		return authMapper.selectUserByLoginId(user);
	}
	@Override
	public boolean keepLogin(UserKeepLogin ukl) {
		return authMapper.insertKeepLogin(ukl);
	}
	@Override
	public boolean removeSessionId(UserKeepLogin ukl) {
		return authMapper.deleteSessionId(ukl);
	}
	@Override
	public boolean registAddress(Address address) {
		return authMapper.insertAddress(address);
	}
	@Override
	public boolean modifyAddress(Address address) {
		return authMapper.updateAddress(address);
	}
	@Override
	public boolean removeAddress(Address address) {
		return authMapper.deleteAddress(address);
	}

	@Override
	public Address getAddress(Address address) {
		return authMapper.selectAddress(address);
	}

	@Override
	public List<Address> getAddressList(User user) {
		return authMapper.selectAddressList(user);
	}

	/**
	 * 회원 기본정보 수정
	 */
	@Override
	public boolean modifyUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		boolean result = false;
		
		if(user.getLoginPw() != null) {
			
			// SHA-256 MessageDigest의 생성
	        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
	        // 문자열 바이트로 메시지 다이제스트를 갱신
	        mdSHA256.update(user.getLoginPw().getBytes("UTF-8"));
	        // 해시 계산 반환값은 바이트 배열
	        byte[] sha256Hash = mdSHA256.digest();
	        // 바이트배열을 16진수 문자열로 변환하여 표시
	        StringBuilder hexSHA256hash = new StringBuilder();
	        for(byte b : sha256Hash) {
	            String hexString = String.format("%02x", b);
	            hexSHA256hash.append(hexString);
	        }
	        
	        user.setLoginPw(hexSHA256hash.toString());
	        
		}
		
        result = authMapper.updateUser(user);
		
		return result;
	}

	
	/**
	 * 임시 비밀번호로 변경
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public boolean modifyUserPwReset(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		user.setLoginPw("0000");
		
		// SHA-256 MessageDigest의 생성
        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
        // 문자열 바이트로 메시지 다이제스트를 갱신
        mdSHA256.update(user.getLoginPw().getBytes("UTF-8"));
        // 해시 계산 반환값은 바이트 배열
        byte[] sha256Hash = mdSHA256.digest();
        // 바이트배열을 16진수 문자열로 변환하여 표시
        StringBuilder hexSHA256hash = new StringBuilder();
        for(byte b : sha256Hash) {
            String hexString = String.format("%02x", b);
            hexSHA256hash.append(hexString);
        }
	        
        user.setLoginPw(hexSHA256hash.toString());
        
		return authMapper.updateUser(user);
	}
	
	
	/**
	 * 회원 탈퇴(강퇴)
	 */
	@Override
	public boolean removeUser(User user) {
		boolean result = authMapper.deleteUser(user);
		if(result) {
			UserKeepLogin ukl = new UserKeepLogin();
			ukl.setLoginId(user.getLoginId());
			authMapper.deleteAllSessionId(ukl);
		}
		return result;
	}
	
	
	/**
	 * 회원 복구
	 */
	@Override
	public boolean restoreUser(User user) {
		return authMapper.restoreUser(user);
	}


	@Override
	public User getSessionId(String sessionId) {
		UserKeepLogin ukl = authMapper.selectSessionId(sessionId);
		User user = null;
		if(ukl != null) {
			user = new User();
			user.setLoginId(ukl.getLoginId());
			user = authMapper.selectUserByLoginId(user);
		}
		return user;
	}


	/**
	 * 권한의 cnt 구하기
	 */
	@Override
	public int getAuthCnt(int id) {
		return authMapper.selectAuthCnt(id);
	}

	/**
	 * 권한별 User list
	 */
	@Override
	public List<User> getUserListByAuth(int id) {
		return authMapper.selectUserListByAuth(id);
	}
	
	/**
	 * 회원 관리 User list
	 */
	@Override
	public List<User> getUserListByUser() {
		return authMapper.selectUserListByUser();
	}


	/**
	 * 권한 초기화
	 */
	@Override
	public boolean removeAuth(Auth auth) {
		return authMapper.deleteAuth(auth);
	}

	/**
	 * 국장 권한의 cnt 구하기
	 */
	@Override
	public int getAuthExecCnt() {
		return authMapper.selectAuthExecCnt();
	}

	/**
	 * 국장 권한 초기화
	 */
	@Override
	public boolean removeAuthExec() {
		return authMapper.deleteAuthExec();
	}


	@Override
	public Auth getAuthById(int id) {
		return authMapper.selectAuthById(id);
	}

}

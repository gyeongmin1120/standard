package standard.auth.service;

import java.util.List;
import java.util.Map;

public interface AuthService {
	
	/**
	 * 로그인
	 * @param userVO
	 * @return
	 */
	public Map<String, String> login(UserVO userVO);
	
	/**
	 * 로그인
	 * @param userVO
	 * @return
	 */
	public Map<String, String> signUp(UserVO userVO);
	/**
	 * 로그아웃
	 * @param 
	 * @return 
	 */
	public Map<String, String> logout();
	
	/**
	 * 사용자 정보 가져오기
	 */
	public UserVO getUserSession();
	
	/**
	 * 사용자 email 중복확인
	 */
	public Map<String, String> checkUserEmail(String email);
}

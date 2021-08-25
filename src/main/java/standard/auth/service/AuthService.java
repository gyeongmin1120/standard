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
}

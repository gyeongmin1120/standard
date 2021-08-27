package standard.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
//import egovframework.rte.fdl.cryptography.EgovPasswordEncoder;
import standard.auth.mapper.AuthMapper;
import standard.auth.service.AuthService;
import standard.auth.service.UserVO;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthMapper authMapper;

//	@Autowired
//	private EgovPasswordEncoder egovPasswordEncoder;
	
	
	/**
	 * 사용자 email 중복 확인
	 */
	public Map<String, String> checkUserEmail(String email) {
		Map<String, String> resultMap = new HashMap<>();
		
		if(authMapper.checkUserEmail(email) > 0) {
			resultMap.put("type", "impossible");
			resultMap.put("message", "중복된 이메일이 존재합니다.");
		}else {
			resultMap.put("type","possible");
			resultMap.put("message", "사용가능한 이메일입니다.");
		}
		return resultMap;
	}
	
	
	/**
	 * 회원가입
	 * @param userVO
	 * @return
	 */
	public Map<String, String> signUp(UserVO userVO) {
		Map<String, String> resultMap = new HashMap<>();

		if(authMapper.checkUserEmail(userVO.getEmail()) > 0 ) {
			resultMap.put("type","fail");
			resultMap.put("message", "중복된 이메일입니다.");
		}else {
			authMapper.insertUser(userVO);
			resultMap.put("type","success");
			resultMap.put("message","회원가입 되었습니다.");
		}
	
		return resultMap;
	}
	
	/**
	 * 로그인
	 * @param userVO
	 * @return
	 */
	public Map<String, String> login(UserVO userVO) {
		Map<String, String> resultMap = new HashMap<>();

		UserVO user = authMapper.selectUserByEmail(userVO.getEmail());

		if(user == null) { 
			resultMap.put("type", "fail"); 
			resultMap.put("message","존재하지 않는 이메일 입니다."); 
		}else {
			if(user.getPassword().equals(userVO.getPassword())) { 
				//egovPasswordEncoder.encryptPassword(userVO.getPassword())
				ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
				HttpSession httpSession = servletRequestAttributes.getRequest().getSession();
				httpSession.setAttribute("userVO", user); 
				resultMap.put("type", "success"); 
			}else { 
				resultMap.put("type", "fail"); 
				resultMap.put("message","비밀번호가 일치하지 않습니다."); 
			} 
		}
		return resultMap;
	}
	
	/**
	 * 로그아웃
	 * @param 
	 * @return
	 */
	public Map<String, String> logout(){
		Map<String, String> resultMap = new HashMap<>();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttributes.getRequest().getSession();
		
		if(httpSession.getAttribute("userVO") != null) {
			httpSession.invalidate();
			resultMap.put("type", "success"); 
			resultMap.put("message", "로그아웃 되었습니다."); 
		}else {
			resultMap.put("type", "fail"); 
			resultMap.put("message", "로그인을 해주십시오."); 
		}
		return resultMap;
	}
	
	/**
	 * 세션 정보 가져오기
	 */
	public UserVO getUserSession() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttributes.getRequest().getSession();
		
		return (UserVO)httpSession.getAttribute("userVO");
	}
}

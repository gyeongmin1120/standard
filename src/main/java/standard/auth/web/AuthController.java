package standard.auth.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import standard.auth.service.UserVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import standard.auth.service.AuthService;
import standard.program.service.ProgramSearchVO;
import standard.program.service.ProgramService;
import standard.program.service.ProgramVO;

@RequestMapping("/auth")
@Controller
public class AuthController {
	private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	private final String JSP_DIR = "standard/auth/";
	
	private PaginationInfo paginationInfo = new PaginationInfo();
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private EgovPropertyService propertiesService;
	
	
	/**
	 * 사용자 email 중복 체크
	 */
	@GetMapping("/checkUserEmail.do")
	public ModelAndView checkUserEmail(String email) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("result", authService.checkUserEmail(email));
		modelAndView.setViewName("jsonView");
		
		return modelAndView;
	}
	/**
	 * 회원가입 화면으로 이동
	 * @return
	 */
	@GetMapping("/signUp.do")
	public String signUp() {
		return JSP_DIR + "signUp";
	}
	/**
	 * 회원가입 화면으로 이동
	 * @return
	 */
	@PostMapping("/signUp.do")
	public ModelAndView signUp(UserVO userVO) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("result", authService.signUp(userVO));
		modelAndView.setViewName("jsonView");
		
		return modelAndView;
	}
	
	/**
	 * 로그인 화면으로 이동
	 * @return
	 */
	@GetMapping("/login.do")
	public String login() {
		return JSP_DIR + "login";
	}
	/**
	 * 로그인 
	 * @param userVO
	 * @return
	 */
	@PostMapping("/login.do")
	public ModelAndView login(UserVO userVO) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("result", authService.login(userVO));
		modelAndView.setViewName("jsonView");
		
		return modelAndView;
	}
	
	/**
	 * 로그아웃 
	 * @param userVO
	 * @return
	 */
	@PostMapping("/logout.do")
	public ModelAndView logout(UserVO userVO) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("result", authService.logout());
		modelAndView.setViewName("jsonView");
		
		return modelAndView;
	}
	
}

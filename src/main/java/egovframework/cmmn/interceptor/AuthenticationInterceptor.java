package egovframework.cmmn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import standard.auth.service.UserVO;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = (HttpSession)request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		if(userVO == null) {
			/*
			 * ModelAndView modelAndView = new ModelAndView("redirect:/auth/login.do");
			 * throw new ModelAndViewDefiningException(modelAndView);
			 */
			response.sendRedirect(request.getContextPath()+"/auth/login.do");
			return false;
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}

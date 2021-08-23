package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
  
import egovframework.com.cmm.EgovBrowserUtil; 
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO; 
import egovframework.com.cmm.util.EgovBasicLogger;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일      	수정자           수정내용
 *  ------------   --------    ---------------------------
 *   2009.03.25  	이삼섭          최초 생성
 *   2014.02.24		이기하          IE11 브라우저 한글 파일 다운로드시 에러 수정
 *   2018.08.28		신용호          Safari, Chrome, Firefox, Opera 한글파일 다운로드 처리 수정 (macOS에서 확장자 exe붙는 문제 처리)
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovFileDownloadController {

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	/**
	 * 브라우저 구분 얻기.
	 *
	 * @param request
	 * @return
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	    * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
	    *
	    * @param commandMap
	    * @param response
	    * @throws Exception
	    */
	   @RequestMapping(value = "/cmm/fms/FileDown.do")
	   public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

	      //전송한 파일 정보 받아옴
	      String atchFileId = (String) commandMap.get("atchFileId");
	      String fileSn = (String) commandMap.get("fileSn");
	      
//	      Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//	      isAuthenticated = true;
//	      if (isAuthenticated) {

	         FileVO fileVO = new FileVO();
	         fileVO.setAtchFileId(atchFileId);
	         fileVO.setFileSn(fileSn);
	         FileVO fvo = fileService.selectFileInf(fileVO); //아이디와 순번 기준으로 파일 select
	         
	         String sss = "gif|hwp|doc|";

	         File uFile = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
	         long fSize = uFile.length(); //파일의 크기를 체크!

//	         if (fSize > 0) {
	         //mimetype = 메시지 컨텐트 형식을 정의하기 위한 인터넷 표준
	            String mimetype = "application/x-msdownload";//파일 확장자 (dll)
	            
	            String userAgent = request.getHeader("User-Agent");// 사용자정보
	            HashMap<String,String> result = EgovBrowserUtil.getBrowser(userAgent);
	            
	            if ( !EgovBrowserUtil.MSIE.equals(result.get(EgovBrowserUtil.TYPEKEY)) ) {
	               mimetype = "application/x-stuff"; //파일 확장자(sit)
	            }
	            /** 인코딩해서 다운로드 형식으로 변경*/
	            String contentDisposition = EgovBrowserUtil.getDisposition(fvo.getOrignlFileNm(),userAgent,"UTF-8"); //인코딩해서 다운로드 형식으로 변경
	            //response.setBufferSize(fSize);   // OutOfMemeory 발생
	            response.setContentType(mimetype);
	            //response.setHeader("Content-Disposition", "attachment; filename=\"" + contentDisposition + "\"");
	            response.setHeader("Content-Disposition", contentDisposition);
	            response.setContentLengthLong(fSize); //파일 사이즈값을 넣음

	            /*
	             * FileCopyUtils.copy(in, response.getOutputStream());
	             * in.close();
	             * response.getOutputStream().flush();
	             * response.getOutputStream().close();
	             */
	            BufferedInputStream in = null;
	            BufferedOutputStream out = null;

	            try {
	               in = new BufferedInputStream(new FileInputStream(uFile)); //FileInputStream 데이터를 읽어올 파일을 지정
	               out = new BufferedOutputStream(response.getOutputStream()); //파일을 쓰기위한것

	               FileCopyUtils.copy(in, out);//다운로드 하기 위해서 기존의 파일을 복사해서 보여줌
	                                    //https://m.blog.naver.com/gaeean/221739006045
	               out.flush();            //프로그램이 멈춘게 아니라 진행중인 상황임을 강제적으로 뿌려주는(중요함)  부분
	            } catch (IOException ex) {
	               // 다음 Exception 무시 처리
	               // Connection reset by peer: socket write error
	               EgovBasicLogger.ignore("IO Exception", ex);
	            } finally {
	               EgovResourceCloseHelper.close(in, out);
	            } 

//	         } else {
//	            response.setContentType("application/x-msdownload");
	//
//	            PrintWriter printwriter = response.getWriter();
//	            
//	            printwriter.println("<html>");
//	            printwriter.println("<br><br><br><h2>Could not get file name:<br>" + fvo.getOrignlFileNm() + "</h2>");
//	            printwriter.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
//	            printwriter.println("<br><br><br>&copy; webAccess");
//	            printwriter.println("</html>");
//	            
//	            printwriter.flush();
//	            printwriter.close();
//	         }
//	      }
	   }
}


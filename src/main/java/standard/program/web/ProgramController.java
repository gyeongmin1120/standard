package standard.program.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import standard.auth.service.UserVO;
import standard.program.service.ProgramSearchVO;
import standard.program.service.ProgramService;
import standard.program.service.ProgramVO;

@RequestMapping("/program")
@Controller
public class ProgramController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProgramController.class);
	private final String JSP_DIR = "standard/program/";
	
	private PaginationInfo paginationInfo = new PaginationInfo();
	
	@Autowired
	private ProgramService programService;
	
	@Autowired
	private EgovPropertyService propertiesService;
	
	/**
	 * 샘플 프로그램 리스트 화면 조회
	 * @param programSearchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.do")
	public String list(ProgramSearchVO programSearchVO, ModelMap model) {
		//@SessionAttribute("userVO") UserVO user
		
		setPage(programSearchVO);
		
		paginationInfo.setTotalRecordCount(programService.selectProgramListTotalCount(programSearchVO));
		model.addAttribute("programList", programService.selectProgramList(programSearchVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return JSP_DIR + "list.tiles";
	}
	
	/**
	 * 샘플 프로그램 상세 화면 조회
	 * @param id
	 * @param model
	 * @param programSearchVO
	 * @return
	 */
	@GetMapping("/detail.do")
	public String detail(Long id, ModelMap model, ProgramSearchVO programSearchVO) {
		model.addAttribute("program", programService.selectProgram(id));
		
		return JSP_DIR + "detail.tiles";
	}
	
	/**
	 * 샘플 프로그램 등록 화면 조회
	 * @param programSearchVO
	 * @param model
	 * @return
	 */
	@GetMapping("/insert.do")
	public String insert(ProgramSearchVO programSearchVO, ModelMap model) {
		model.addAttribute("programList", programService.selectProgramList(programSearchVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return JSP_DIR + "register.tiles";
	}
	
	/**
	 * 샘플 프로그램 수정 화면 조회
	 * @param id
	 * @param model
	 * @param programSearchVO
	 * @return
	 */
	@GetMapping("/update.do")
	public String update(Long id, ModelMap model, ProgramSearchVO programSearchVO) {
		model.addAttribute("program", programService.selectProgram(id));
		
		return JSP_DIR + "register.tiles";
	}
	
	/**
	 * 샘플 프로그램 등록/수정
	 * @param programVO
	 * @param programSearchVO
	 * @param model
	 * @return
	 */
	@PostMapping("/register.do")
	public String register(ProgramVO programVO, ProgramSearchVO programSearchVO, ModelMap model) {
		
		//로그인 정보
		//UserVO userVO = (UserVO)session.getAttribute("userVO");
		//UserVO user = authMapper.selectUserByEmail(userVO.getEmail());
		
		if(programVO.getId() == null) {
			programService.insertProgram(programVO);
		} else {
			programService.updateProgram(programVO);
		}
		
		setPage(programSearchVO);
		
		paginationInfo.setTotalRecordCount(programService.selectProgramListTotalCount(programSearchVO));
		
		model.addAttribute("programList", programService.selectProgramList(programSearchVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return JSP_DIR + "list.tiles";
	}
	
	/**
	 * 샘플 프로그램 삭제
	 * @param id
	 * @param model
	 * @param programSearchVO
	 * @return
	 */
	@PostMapping("/delete.do")
	public String delete(Long id, ModelMap model, ProgramSearchVO programSearchVO) {
		
		programService.deleteProgram(id);
		model.addAttribute("result", "sucess");
		
		setPage(programSearchVO);
		
		paginationInfo.setTotalRecordCount(programService.selectProgramListTotalCount(programSearchVO));
		
		model.addAttribute("programList", programService.selectProgramList(programSearchVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return JSP_DIR + "list.tiles";
	}
	
	public void setPage(ProgramSearchVO programSearchVO){
		programSearchVO.setPageSize(propertiesService.getInt("pageSize"));
		
		paginationInfo.setCurrentPageNo(programSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(programSearchVO.getPageUnit());
		paginationInfo.setPageSize(programSearchVO.getPageSize());

		programSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		programSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		programSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	}
}

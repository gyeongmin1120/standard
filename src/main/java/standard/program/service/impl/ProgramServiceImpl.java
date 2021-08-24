package standard.program.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import standard.program.mapper.ProgramMapper;
import standard.program.service.ProgramSearchVO;
import standard.program.service.ProgramService;
import standard.program.service.ProgramVO;

@Service
public class ProgramServiceImpl implements ProgramService {
	
	@Resource(name = "programMapper")
	private ProgramMapper programMapper;
	
	/**
	 * 샘플 프로그램 리스트 조회
	 */
	public List<ProgramVO> selectProgramList(ProgramSearchVO programSearchVO) {
		return programMapper.selectProgramList(programSearchVO);
	}
	
	/**
	 * 샘플 프로그램 리스트 토탈 카운트
	 */
	public int selectProgramListTotalCount(ProgramSearchVO programSearchVO) {
		return programMapper.selectProgramListTotalCount(programSearchVO);
	}
	
	/**
	 * 단일 샘플 프로그램 조회
	 */
	public ProgramVO selectProgram(Long id) {
		return programMapper.selectProgram(id);
	}
	
	/**
	 * 샘플 프로그램 등록
	 */
	public void insertProgram(ProgramVO programVO) {
		programMapper.insertProgram(programVO);
	}
	
	/**
	 * 샘플 프로그램 수정
	 */
	public void updateProgram(ProgramVO programVO) {
		programMapper.updateProgram(programVO);
	}
	
	/**
	 * 샘플 프로그램 삭제
	 */
	public void deleteProgram(Long id) {
		programMapper.deleteProgram(id);
	}
}

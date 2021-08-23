package standard.program.mapper;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import standard.program.service.ProgramSearchVO;
import standard.program.service.ProgramVO;

@Mapper("programMapper")
public interface ProgramMapper {
	
	/**
	 * 샘플 프로그램 리스트 조회
	 * @param programSearchVO
	 * @return
	 */
	public List<ProgramVO> selectProgramList(ProgramSearchVO programSearchVO);
	
	/**
	 * 샘플 프로그램 리스트 토탈 카운트
	 * @param programSearchVO
	 * @return
	 */
	public int selectProgramListTotalCount(ProgramSearchVO programSearchVO);
	
	/**
	 * 단일 샘플 프로그램 조회
	 * @param programSearchVO
	 * @return
	 */
	public ProgramVO selectProgram(Long id);
	
	/**
	 * 샘플 프로그램 등록
	 * @param programVO
	 */
	public void insertProgram(ProgramVO programVO);
	
	/**
	 * 샘플 프로그램 수정
	 * @param programVO
	 */
	public void updateProgram(ProgramVO programVO);
	
	/**
	 * 샘플 프로그램 삭제
	 * @param programVO
	 */
	public void deleteProgram(Long id);
}

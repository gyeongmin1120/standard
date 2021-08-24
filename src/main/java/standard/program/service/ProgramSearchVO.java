package standard.program.service;

import egovframework.cmmn.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramSearchVO extends ComDefaultVO {
	private Long searchId;			// 조회 아이디
	private String searchTitle;		// 조회 제목
	private String searchOption;	// 조회 조건
	private String searchContent;	// 조회 내용

}

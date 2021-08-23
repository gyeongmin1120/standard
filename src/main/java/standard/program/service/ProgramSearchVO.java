package standard.program.service;

import egovframework.cmmn.ComDefaultVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramSearchVO extends ComDefaultVO {
	private Long searchId;			// 조회 아이디
	private String searchTitle;		// 조회 제목

	public Long getSearchId(){
		return searchId;
	}
	public void setSearchId(Long searchId){
		this.searchId = searchId;
	}
	public String getSearchTitle(){
		return searchTitle;
	}
	public void setSearchTitle(String searchTitle){
		this.searchTitle = searchTitle;
	}

}

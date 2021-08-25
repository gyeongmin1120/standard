package standard.auth.mapper;

import java.util.List;

import standard.auth.service.UserVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import standard.program.service.ProgramSearchVO;
import standard.program.service.ProgramVO;

@Mapper("authMapper")
public interface AuthMapper {
	public UserVO selectUserByEmail(String email);
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	
	<body id="page-top"  class="sb-nav-fixed">
		<main>
			<div class="container-fluid">
				<div class="card mt-2 mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i>
						<c:if test="${empty program.id}">
							프로그램 등록
						</c:if>
						<c:if test="${!empty program.id}">
							프로그램 수정
						</c:if>
						
					</div>
					<div class="card-body">
						<!--등록/수정 form 시작 -->
						<form id="registrationForm" name="registrationForm" method="post">
							<table id="registrationTable" class="management-table table" >
								<colgroup>
									<col width="30%"/>
									<col width="70%"/>
								</colgroup>
								<tbody>
									<input type="hidden" class="form-control" id="id" name="id" value="${program.id}" >
									<tr>
										<td><label for="title" class="form-label">ProgramTitle</label></td>
										<td><input type="text" class="form-control" id="title" name="title" value="${program.title}" ></td>
									</tr>
								</tbody>
							</table>
							<!--검색조건 및 페이징 정보 -->
							<input type="hidden" id="searchOption" name="searchOption" value="${programSearchVO.searchOption}"/>
							<input type="hidden" id="searchContent" name="searchContent" value="${programSearchVO.searchContent}"/>
							<input type="hidden" id="pageIndex" name="pageIndex" value="${programSearchVO.pageIndex}"/>
						</form>
					</div>
					<!--등록/수정에 따른 버튼  -->
					<div class="crud-btn-group">
						<c:if test="${empty program.id}">
							<button class="btn btn-dark" type="button" onclick="fncRegister();">등록</button>
						</c:if>
						<c:if test="${!empty program.id}">
							<button class="btn btn-dark" type="button" onclick="fncUpdate(${program.id});">수정</button>
						</c:if>
						<button class="btn btn-dark"  onclick="fncList();">목록</button>
					</div>
				</div>
			</div>
		</main>
		<script type="text/javascript">
			//등록 함수
			function fncRegister(){
				if(confirm("등록하시겠습니까?")){
					
					let form = document.registrationForm;
					form.method = "post";
					form.action = '<c:url value="/program/register.do"/>';
					form.submit();
				}
			}
			//수정 함수
			function fncUpdate(no) {
				
				if(confirm("수정하시겠습니까?")){
				
					let form = document.registrationForm;
					form.method = "post";
					form.action = '<c:url value="/program/register.do"/>';
					form.submit();
				}
			}
			//목록 이동 함수
			function fncList() {
				let form = document.registrationForm;
				form.method = 'get';
				form.action = '<c:url value="/program/list.do"/>';
				form.submit();
			}
			
		</script>
	</body>
</html>

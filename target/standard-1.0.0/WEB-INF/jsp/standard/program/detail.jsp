<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	
	<body id="page-top">
		<main>
			<div class="container-fluid">
				<div class="card mt-2 mb-4">
					<div class="card-header">
						<i class="fas fa-table me-1"></i>
						STANDARD 상세
					</div>
					<div class="card-body" >
						<!--list table 시작 -->
						<table class="table table-hover mt-5">
							<colgroup>
								<col width="30%"/>
								<col width="70%"/>
							</colgroup>
							
							<tbody>
								<tr>
									<td><label for="id" class="form-label">ProgramId</label></td>
									<td><input type="text" class="form-control" id="id" name="id" value="${program.id}" readonly></td>
								</tr>
								<tr>
									<td><label for="title" class="form-label">ProgramTitle</label></td>
									<td><input type="text" class="form-control" id="title" name="title" value="${program.title}" readonly></td>
								</tr>
							</tbody>
						</table>
						<!--list table 끝 -->
						<!--목록 버튼  -->
						<div class="d-flex justify-content-end">
							<button class="btn btn-dark" onclick="fncList();">목록</button>
						</div>
					</div>
				</div>
				<!--목록 화면 이동에 필요한 검색조건, 페이징 정보  -->
				<form name="linkForm" id="linkForm">
					<input type="hidden" id="id" name="id"/>
					<input type="hidden" id="searchOption" name="searchOption" value="${programSearchVO.searchOption}"/>
					<input type="hidden" id="searchContent" name="searchContent" value="${programSearchVO.searchContent}"/>
					<input type="hidden" id="pageIndex" name="pageIndex" value="${programSearchVO.pageIndex}"/>
				</form>
				
			</div>
		</main>
		<script>
			//목록으로 이동하는 함수
			function fncList() {
				let form = document.linkForm;
				form.method = 'get';
				form.action = '<c:url value="/program/list.do"/>';
				form.submit();
			}
		</script>
		
	</body>

</html>

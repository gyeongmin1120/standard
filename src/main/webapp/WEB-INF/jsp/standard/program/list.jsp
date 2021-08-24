<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
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
						STANDARD
					</div>
					<div class="card-body" >
						<!--검색용 search form 시작  -->
						<form id="search" name="search" action='<c:url value="/program/list.do"/>'>
							<table class="table">
								<colgroup>
									<col width="10%" />
									<col width="80%" />
									<col width="10%" />
								</colgroup>
								<tr>
									<td>
										<select id="searchOption" name="searchOption" class="form-select form-select-sm mb-3" aria-label=".form-select-sm example">
											<option value="searchId" <c:if test="${programSearchVO.searchOption == 'searchId'}">selected='selected'</c:if> >ID</option>
											<option value="searchTitle" <c:if test="${programSearchVO.searchOption == 'searchTitle'}">selected='selected'</c:if> >TITLE</option>
										</select>
									</td>
									<td>
										<input type="text" class="form-control form-control-sm" name="searchContent" value="${programSearchVO.searchContent}">
									</td>
									<td>
										<button type="submit" class="btn btn-dark btn-sm" style="width: 80px;">검색</button>
									</td>
								</tr>
							</table>
						</form>
						<!--검색용 search form 끝  -->
						<!--list table 시작 -->
						<table class="table table-hover mt-5">
							<colgroup>
								<col width="10%"/>
								<col width="60%"/>
								<col width="15%"/>
								<col width="15%"/>
							</colgroup> 
							<thead>
								<tr>
									<td>ID</td>
									<td>TITLE</td>
									<td>UPDATE</td>
									<td>DELETE</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${programList}" var="program">
									<tr>
										<td onclick="fncDetail(<c:out value="${program.id}"/>)"><c:out value="${program.id}" /></td>
										<td onclick="fncDetail(<c:out value="${program.id}"/>)"><c:out value="${program.title}" /></td>
										<td><button type="button" class="btn btn-success btn-sm" onclick="fncUpdate(event, <c:out value="${program.id}"/>)">UPDATE</button></td>
										<td><button type="button" class="btn btn-danger btn-sm" onclick="fncDelete(<c:out value="${program.id}"/>)">DELETE</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!--list table 끝 -->
						<!--등록 버튼  -->
						<div class="d-flex justify-content-end">
							<button class="btn btn-dark" onclick="fncInsert();">등록</button>
						</div>
						
						<!--페이징-->
						<div class="pagination-container w-100 mt-4">
							<nav aria-label="Page navigation example">
								<ul class="pagination">
									<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="page"/>
								</ul>
							</nav>
						</div>
					</div>
				</div>
				<!--등록화면 이동에 필요한 검색조건, 페이징 정보  -->
				<form name="linkForm" id="linkForm">
					<input type="hidden" id="id" name="id"/>
					<input type="hidden" id="searchOption" name="searchOption" value="${programSearchVO.searchOption}"/>
					<input type="hidden" id="searchContent" name="searchContent" value="${programSearchVO.searchContent}"/>
					<input type="hidden" id="pageIndex" name="pageIndex" value="${programSearchVO.pageIndex}"/>
				</form>
				
			</div>
		</main>
		<script>
		
			function page(pageNo){
				/* document.search.action= '<c:url value="/program/list.do"/>?pageIndex='+pageNo;
				document.search.submit(); */
				
				document.getElementById('pageIndex').value = pageNo;
				
				let data = {
					'pageIndex' : pageNo,
					'searchOption': $('#searchOption').val(),
					'searchContent': $('#searchContent').val()
				};
				
				$.ajax({
					url: '<c:url value="/program/list.do"/>',
					method: "get",
					dataType: "html",
					data : data
				}).done(function(html) {
					$("#page-top").html(html)
				}).fail(function( jqXHR, textStatus, errorThrown ) {
					alert("Error!", "Request failed: " + errorThrown, "error");
				});
			}
			function fncInsert() {
				let form = document.linkForm;
				form.method = 'get';
				form.action = '<c:url value="/program/insert.do"/>';
				form.submit();
			}
			function fncDetail(id) {
				document.getElementById('id').value = id;
				
				let form = document.linkForm;
				form.method = 'get';
				form.action = '<c:url value="/program/detail.do"/>';
				form.submit();
			}
		
			function fncUpdate(event, id) {
				event.stopPropagation();
				
				document.getElementById('id').value = id;
				
				let form = document.linkForm;
				form.method = 'get';
				form.action = '<c:url value="/program/update.do"/>';
				form.submit();
			}
			
			function fncDelete(id) {
				document.getElementById('id').value = id;
				
				let form = document.linkForm;
				form.method = 'post';
				form.action = '<c:url value="/program/delete.do"/>';
				form.submit();
			}
		
		</script>
	</body>
</html>

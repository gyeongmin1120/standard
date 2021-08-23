<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Program</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
<style>
	html, body {
		width: 100%;
		height: 100%;
		margin: 0;
		padding: 0;
	}
	
	tr td {
		text-align: center;
	}
	
	.search-container {
		width: 100%;
		display: flex;
		justify-content: flex-end;
	}
	
	.table-container {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.pagination-container { 
		display: flex;
		justify-content: center;
	}
</style>
</head>
<body>
	<div class="table-container">
		<div class="col-sm-6">
			<form name = "search" id = "search" method="post" action="<c:url value="/program/list.do"/>">
				<table class="table table-bordered">
					<colgroup>
						<col width="10%"/>
						<col width="90%"/>
					</colgroup>
					<tr>
						<td>title</td>
						<td><input type="text" class="form-control form-control-sm" name="searchTitle" value="${programSearchVO.searchTitle}" style="width: 570px;"></td>
					</tr>
				</table>
				<div class="search-container">
					<button type="submit" class="btn btn-primary">검색</button>
				</div>
			</form>
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
			<div class="pagination-container w-100 mt-4">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="page"/>
					</ul>
				</nav>
			</div>
			<div class="d-grid">
				<a href="<c:url value="/program/insert.do"/>" class="btn btn-primary mt-5">등록</a>
			</div>
		</div>
	</div>
	<form name="linkForm">
		<input type="hidden" id="id" name="id"/>
	</form>
	<script>
	
		function page(pageNo){
			document.search.action= '<c:url value="/program/list.do"/>?pageIndex='+pageNo;
			document.search.submit();
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
		
		//TEST BRANCH
	</script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="layoutSidenav_nav">
	<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
		<div class="sb-sidenav-menu">
			<div class="nav">
				<%-- <c:forEach items="${menuList}" var="menu" varStatus="status">
					<!--첫번째 행이 아니고  -->
					<c:if test="${status.index != 0}">
						<!--앞에 메뉴 레벨이 2이면서 자신의 메뉴 레벨이 1이면  -->
						<c:if test="${ (menuList[status.index - 1].level == 2 && menu.level == 1) || (menu.level != 0 && menu.level == 1 && menuList[status.index + 1].level == 2) }">
							</nav>
							</div>
						</c:if>
					</c:if>
					
					<!--루트가 아니면서 레벨이 1이면서 다음거의 레벨값이 2이면-->
					<c:if test="${menu.level != 0 && menu.level == 1 && menuList[status.index + 1].level == 2 }">
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#M<c:out value="${menu.menuNo }"/>" aria-expanded="false" aria-controls="M<c:out value="${menu.menuNo }"/>">
							<div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
							<c:out value="${menu.menuName }"/>
							<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
						</a>
						<div class="collapse" id="M<c:out value="${menu.menuNo }"/>" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
						<nav class="sb-sidenav-menu-nested nav">
					</c:if>
					
					<!--루트가 아니면서 레벨이 1이면서 다음거의 레벨값이 2가 아니면  -->
					<c:if test="${menu.level != 0 && menu.level == 1 && menuList[status.index + 1].level != 2}">
						<a class="nav-link" href="<c:url value="${menu.menuUrl}"/>">
							<div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
							<c:out value="${menu.menuName}"/>
						</a>
					</c:if>
					
					<!--루트가 아니면서 레벨이 2이면 -->
					<c:if test="${menu.level != 0 && menu.level != 1 && menuList[status.index - 1].level <= menu.level }">
						<a class="nav-link" href="<c:url value="${menu.menuUrl}"/>"><c:out value="${menu.menuName}"/></a>
					</c:if>
					
					<!--마지막이면  -->
					<c:if test="${status.last && menuList[status.index - 1].level != 1}">
						</nav>
						</div>
					</c:if>
					
				</c:forEach> --%>
				<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#M" aria-expanded="false" aria-controls="M">
					<div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
					TEST
					<div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
				</a>
				
			</div>
		</div>
	</nav>
</div>
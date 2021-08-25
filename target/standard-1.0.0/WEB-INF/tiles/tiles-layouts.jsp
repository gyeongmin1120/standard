<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles"  prefix="tiles"%>
<!DOCTYPE html>
<html>
	<head>
		<%-- <div id="header"><tiles:insertAttribute name="header"/></div> --%>
		<tiles:insertAttribute name="header"/>
	</head>
	<body>
		<div id="wrap">
			<div id="nav"><tiles:insertAttribute name="nav"/></div>
			<div id="layoutSidenav">
				<tiles:insertAttribute name="left"/>
				<div id="layoutSidenav_content">
					<tiles:insertAttribute name="content"/>
					<tiles:insertAttribute name="footer"/>
				</div>
			</div>
			
			
		</div>
		<tiles:insertAttribute name="script"/>
	</body>
</html>

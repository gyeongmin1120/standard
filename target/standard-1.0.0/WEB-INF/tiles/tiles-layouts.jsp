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
			<div id="left"><tiles:insertAttribute name="left"/></div>
			<div id="content"><tiles:insertAttribute name="content"/></div>
			<div id="footer"><tiles:insertAttribute name="footer"/></div>
		</div>
		<tiles:insertAttribute name="script"/>
	</body>
</html>

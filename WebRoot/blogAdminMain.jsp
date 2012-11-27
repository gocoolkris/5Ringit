<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="cn.com.blogonline.*"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="org.zjy.vo.Blog"/>
<jsp:directive.page import="org.zjy.web.varible.Constants"/>
<%
	Blog blog = (Blog)session.getAttribute(Constants.LOGIN_USER_KEY);
	
	if(blog == null){
		response.sendRedirect("login.jsp");
	}
	else{
%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" content="">
		<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
		<meta name="ProgId" content="FrontPage.Editor.Document">
		<title>博客管理页面</title>
	</head>

	<frameset framespacing="0" border="0" rows="23,*" frameborder="0">
		<frame name="banner" scrolling="no" noresize target="contents" src="adminTop.jsp">
		<frameset cols="200,*">
			<frame name="contents" target="main"  src="adminLeft.jsp">
			<frame name="main" src="adminMain.jsp">
		</frameset>
		<noframes>
			<body>

				<p>
					您的浏览器不支持框架
				</p>

			</body>
		</noframes>
	</frameset>

</html>
<%}%>

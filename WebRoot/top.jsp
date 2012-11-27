<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ page import="java.util.*"%>
<jsp:directive.page import="org.zjy.vo.Blog"/>
<jsp:directive.page import="org.zjy.web.varible.Constants"/>
<%
	Blog blog = (Blog)session.getAttribute(Constants.VISIT_BLOG_KEY);
	if(blog==null){
	  System.out.println("top.jsp的Blog对象为空");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>博客主页</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
		<base target="contents">
	</head>
	<body>
		<table width="750" border="0"  cellpadding="0" cellspacing="0" class="content" style="background:url('images/top_bgline.png') repeat-x; ">
          <tr>
            <td width="174"><img src="images/mypoco_logo.png" width="174" height="75"></td>
            <td width="178"><img src="images/mypoco_1.png" width="178" height="75"></td>
            <td width="199"><img src="images/mypoco_2.png" width="199" height="75"></td>
            <td width="219" align="right" valign="bottom"><span id="top_nav">网站主页 京津专区  留言 杂记</span></td>
          </tr>
        </table>
		<table height="45" cellspacing="0" cellpadding="0" width="750" background="images/bk_11.jpg" border="0">
			<tbody>
				<tr>
					<td align="middle">
						<b><font size="2" color="#5C7FA7"><%=blog.getSubject()%>的Blog，欢迎您！</font></b>
					</td>
				</tr>
				
			</tbody>
		</table>

	</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="cn.com.blogonline.*"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="org.zjy.vo.Sort"/>
<jsp:directive.page import="org.zjy.vo.Blog"/>
<jsp:directive.page import="org.zjy.vo.Links"/>
<jsp:directive.page import="org.zjy.web.varible.Constants"/>

<%
	Blog blog = (Blog)session.getAttribute(Constants.VISIT_BLOG_KEY);
	List sortList = (List)session.getAttribute(Constants.SORT_LIST_KEY);
	Sort sort = null;
	List linkList = (List)session.getAttribute(Constants.LINK_LIST_KEY);
	Links link = null;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>主菜单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
		<base target="main">
	    <style type="text/css">
<!--
.STYLE1 {
	color: #5c7fa7;
	font-weight: bold;
}
.STYLE2 {
	color: #5C7FA7;
	font-weight: bold;
}
-->
        </style>
</head>
	<body>
		<table cellspacing="0" cellpadding="0" width="750" background="images/bk_11.jpg" border="0" style="border-style:none">
			
		</table>

		<table border="1" width="203" bordercolor="#7D9EC3" height="450" style="border-style:none">
			<tr>
				<td width="193" valign="top" height="156">
					<table width="88%" height="189" border="1" bordercolor="#7D9EC3" style="border-style:none">
						<tr>
							<td width="100%">
								<img border="0" src="images/<%=blog.getImage()%>" width="180" height="121" alt="">
							</td>
						</tr>
						<tr>
							<td width="100%">
								<p align="center" class="STYLE2">
								  <%=blog.getSubject()%>的Blog								</p>							</td>
						</tr>
				  </table>
			  </td>
			</tr>
			<tr>
				<td width="193" valign="top" height="73">
					<table border="1" width="141%" bordercolor="#7D9EC3" height="69">
						<tr>
							<td width="100%" height="17">
								<span class="STYLE2">导航栏</span>							</td>
						</tr>
						<tr>
							<td width="100%" height="17">
								<p align="center">
									<a href="blogOperate?sortid=0&amp;pageid=0" target="main">个人首页</a>
								</p>
							</td>
						</tr>
					</table>
			  </td>
			</tr>
			<tr>
				<td width="193" valign="top" height="67">
					<table border="1" width="140%" bordercolor="#7D9EC3">
						<tr>
							<td width="100%">
								<span class="STYLE2">博客分类</span>							</td>
						</tr>

						<tr>
							<td width="100%">
								<p align="center">
									<a href="blogOperate?sortid=0&amp;pageid=0" target="main">所有文章</a>
								</p>
							</td>
						</tr>

						<%if(sortList != null ){

       for(int i = 0;i < sortList.size();i++)
       {  
       		sort = (Sort)sortList.get(i);
      %>


						<tr>
							<td width="100%">
								<p align="center">
									<a href="blogOperate?sortid=<%=sort.getId()%>&amp;pageid=0" target="main"><%=sort.getName()%></a>
								</p>
							</td>
						</tr>

						<%
         }
      }	%>

					</table>
			  </td>
			</tr>
			<tr>
				<td width="193" valign="top" height="73">
					<table border="1" width="140%" bordercolor="#7D9EC3">
						<tr>
							<td width="100%">
								<span class="STYLE1">我的链接</span>							</td>
						</tr>


						<%if(linkList != null ){

       for(int i = 0;i < linkList.size();i++)
       {  
       		link = (Links)linkList.get(i);
      %>



						<tr>
							<td width="100%">
								<p align="center">
									<a href="<%=link.getUrl()%>" target="_blank"><%=link.getName()%></a>
								</p>
							</td>
						</tr>

						<%
         }
      }	%>



					</table>

			  </td>
			</tr>
			<tr>
				<td width="193" valign="top" align="center" height="17">
					<font color="#7D9EC3">网页计数器:<%=blog.getVisitcount()%></font>
			  </td>
			</tr>
	</table>
	</body>
</html>

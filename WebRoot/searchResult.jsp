<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="cn.com.blogonline.*"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="org.zjy.vo.Article"/>
<jsp:directive.page import="org.zjy.web.varible.Constants"/>
<%
	List searchList = (List)session.getAttribute(Constants.ARTICLE_LIST_KEY);
	Article article = null;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>搜索结果信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
	</head>
	<body>
		<table height="45" cellspacing="0" cellpadding="0" width="750" background="images/bk_11.jpg" border="0">
			
		</table>
		<table height="19" cellspacing="0" cellpadding="0" width="750" bgcolor="#5C7FA7" border="0">
			<tr>
				<td width="3"></td>
				<td width="252"></td>
				<td style="PADDING-TOP: 2px" align="middle">
					<p align="center">
						<b><font color="#ffffff">搜索结果显示</font></b>
					</p>
				</td>
				<td width="252"></td>
				<td width="3"></td>
			</tr>
		</table>
		<table height="45" cellspacing="0" cellpadding="0" width="750" background="images/bk_11.jpg" border="0">
			<tbody>
				<tr>
					<td align="middle"></td>
				</tr>
				
			</tbody>
		</table>

		<table border="1" width="750" bordercolor="#FCD447">


			<%if(searchList != null ){
			System.out.println(searchList.size());
       for(int i = 0;i < searchList.size();i++)
       {  
       		article = (Article)searchList.get(i);
      %>


			<tr>
				<td width="99%" valign="top">
					<div>
						文章标题：<a href="openArticle?articleid=<%=article.getId()%>" target="_blank"><%=article.getTitle()%></a>
					</div>
					<div class="systemLHeight180">
						<%=article.getContent()%>
						<div class="author" style="width: 740; height: 19">
							发表时间:
							<%=article.getPubtime()%>
						</div>
					</div>
				</td>
			</tr>


			<%	
         }
      }	%>


		</table>
		<hr style="color:#999999" width="90%">
		<p align="center">
			<font color="#5C7FA7"><b>BlogOnline 版权所有</b></font>
		</p>
	</body>
</html>

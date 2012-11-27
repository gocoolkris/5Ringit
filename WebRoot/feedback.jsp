<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="cn.com.blogonline.*"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="org.zjy.vo.Article"/>
<jsp:directive.page import="org.zjy.vo.FeedBack"/>
<jsp:directive.page import="org.zjy.web.varible.Constants"/>
<%
	Article article = (Article)session.getAttribute(Constants.CUR_ARTICLE_KEY);
	List feedBackList = (List)session.getAttribute(Constants.FEEDBACK_LIST_KEY);
	FeedBack feedBack = null;
	Integer tpage=(Integer)session.getAttribute(Constants.CUR_PAGEID_KEY);
	int pageId=tpage.intValue();
	System.out.println(tpage);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>文章信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
	</head>
	<body>
		<table border="1" width="550" bordercolor="#7D9EC3">
			<tr>
				<td width="100%">
					<p align="center">
						<b><font color="#5C7FA7">文章信息</font></b>
					</p>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<b><font color="#5C7FA7"><%=article.getTitle()%></font></b>
					<p>
						<b><font color="#5C7FA7"><%=article.getContent()%></font></b>
					</p>
					<p>
					</p>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<p align="center">
						<b><font color="#5C7FA7">相关评论</font></b>
					</p>
				</td>
			</tr>

			<%if(feedBackList != null ){

       for(int i = 0;i < feedBackList.size();i++)
       {  
       		feedBack = (FeedBack)feedBackList.get(i);
      %>


			<tr>
				<td width="100%">
					<table border="0" width="100%">
						<tr>
							<td width="100%">
								<b><%=feedBack.getUname()%></b>
							</td>
						</tr>
						<tr>
							<td width="100%">
								<p align="right">
									<b><%=feedBack.getPubtime()%></b>
								</p>
							</td>
						</tr>
						<tr>
							<td width="100%">
								<b><%=feedBack.getContent()%></b>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<%	
         }
      }	%>

			<tr>
				<td width="100%">
					<p align="center">
						| <font color="#999999"> <a href="showFeedback?pageid=<%=pageId-1%>">上页</a></font> | <a href="showFeedback?pageid=<%=pageId+1%>">下页</a> |
					</p>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<b><font color="#5C7FA7">发表评论：</font></b>
					<form name="regForm" onsubmit="" action="writeFeedback" method="post">
						<table cellspacing="2" cellpadding="0" width="100%" border="0">
							<tbody>
								<td width="12%">
									<label class="redfont" for="user">
										您的昵称
									</label>
								</td>
								<td>
									<input class="text" id="uname" name="uname" datatype="Username">
								</td>
								<tr>
									<td>
										内容
									</td>
									<td>
										<textarea rows="6" name="content" cols="50"></textarea>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td>
										<p align="center">
											<input class="button-submit" type="submit" value="提交" name="Submit">
										</p>
									</td>
								</tr>
							</tbody>
						</table>
					</form>

				</td>
			</tr>
		</table>
	</body>
</html>

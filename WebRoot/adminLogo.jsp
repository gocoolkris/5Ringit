<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="cn.com.blogonline.*"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="org.zjy.web.varible.Constants"/>
<%
	Integer modiFlag = (Integer)session.getAttribute(Constants.MODIFY_OK_KEY);
	if (modiFlag!=null){
		out.println("修改成功！");
		session.removeAttribute(Constants.MODIFY_OK_KEY);
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>博客Logo管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
	</head>
	<body>
		<table border="1" width="95%" bordercolor="#7D9EC3">
			<tr>
				<td width="100%">
					<p align="left">
						<b><font color="#5C7FA7">博客Logo管理</font></b>
					</p>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table cellspacing="2" cellpadding="0" width="98%" align="center" border="0">
						<tbody>
							<form enctype="multipart/form-data" method="post" action="saveLogo">

							<tr>
								<td class="redfont" valign="top" height="30">
									博客图片
								</td>
								<td>
									<input type="file" name="image">

								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%" align="center">
					<input class="button-submit" id="save" type="submit" value="确认修改" name="save">
					&nbsp;

				</td>
			</tr>
		</table>
		</form>
	</body>
</html>

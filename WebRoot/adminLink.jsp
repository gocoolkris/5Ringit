<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>Blog友情链接管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
		<script language="javascript" src="images/adminLink.js" type="text/javascript"></script>
		<base target="contents">
	</head>
	<body text="#FCD447" onload="loadRequest() ">

		<table border="1" width="550" height="73" bordercolor="#FCD447">
			<tr>
				<td width="74%" valign="top" height="14">
					<font color="#5C7FA7"><b>Blog友情链接管理</b></font>
				</td>
			</tr>
			<tr>
				<td width="74%" valign="top" height="14">
					<b><font color="#5C7FA7">新链接名称：</font></b>
					<input style="BORDER-RIGHT: #e78d00 1px solid; BORDER-TOP: #e78d00 1px solid; BORDER-LEFT: #e78d00 1px solid; WIDTH: 125px; BORDER-BOTTOM: #e78d00 1px solid; " name="name">
					<b><font color="#5C7FA7">地址：</font></b>
					<input style="BORDER-RIGHT: #e78d00 1px solid; BORDER-TOP: #e78d00 1px solid; BORDER-LEFT: #e78d00 1px solid; WIDTH: 125px; BORDER-BOTTOM: #e78d00 1px solid; " name="url">
					<input class="button-submit" type="submit" value="添加" style="width: 40; height: 20" onclick="addLink() ">
				</td>
			</tr>
			<tr>
				<td width="74%" valign="top" height="14">
				</td>
			</tr>
			<tr>
				<td width="74%" valign="top" height="13">
					<font color="#5C7FA7">
						<table border="1" width="100%" bordercolor="#7D9EC3" align="center">
							<tr>
								<td width="25%" align="center">
									<b>链接名称</b>
								</td>
								<td width="46%" align="center">
									<b>链接地址</b>
								</td>
								<td width="29%" colspan="2" align="center">
									<b>操作</b>
								</td>
							</tr>
							<tbody id="linkList"></tbody>
						</table> <b></b></font>
				</td>
			</tr>
		</table>

	</body>
</html>

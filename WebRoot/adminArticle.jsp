<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title>Blog文章管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
		<script language="javascript" src="images/adminArticle.js" type="text/javascript"></script>
		<base target="contents">
	</head>
	<body text="#FCD447" onload="loadRequest()">

		<table border="1" width="550" height="73" bordercolor="#FCD447">
			<tr>
				<td width="74%" valign="top" height="14">
					<font color="#5C7FA7"><b>Blog文章管理</b></font>
				</td>
			</tr>
			<tr>
				<td width="74%" valign="top" height="14">
					<font color="#5C7FA7"><b>文章分类</b></font>
					<select id="sort" onchange="sortChange()">
					</select>
				</td>
			</tr>

			<tr>
				<td width="74%" valign="top" height="13">
					<font color="#5C7FA7">
						<table border="1" width="100%" bordercolor="#7D9EC3" align="center">
							<tr>
								<td width="25%" align="center">
									<b>文章名称</b>
								</td>
								<td width="25%" align="center">
									<b>发表时间</b>
								</td>
								<td width="50%" colspan="2" align="center">
									<b>操作</b>
								</td>
							</tr>
							<tbody id="articleList"></tbody>
						</table> <b></b></font>
				</td>
			</tr>

		</table>

	</body>
</html>

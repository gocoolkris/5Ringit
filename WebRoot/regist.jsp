<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="StyleSheet" type="text/css" href="images/mystyle.css">
		<script language="javascript" src="images/regist.js" type="text/javascript"></script>
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
						<b><font color="#ffffff">新用户注册</font></b>
					</p>
				</td>
				<td width="252"></td>
				<td width="3"></td>
			</tr>
		</table>
		<table height="45" cellspacing="0" cellpadding="0" width="750" background="images/bk_11.jpg" border="0">
			<tbody>
				<tr>
					<td align="middle">
						<font color="#5C7FA7"><b>请填写您的个人信息！</b></font>
					</td>
				</tr>
				<form action=""></form>
			</tbody>
		</table>

		<table border="1" width="750" bordercolor="#FCD447">
			<tr>
				<td width="99%" valign="top">
					<form name="regForm" onsubmit="return regist()" action="regist" method="post">
						<table cellspacing="2" cellpadding="0" width="100%" border="0">
							<tbody>
								<td width="12%">
									<label class="redfont" for="user">
										您的用户名
									</label>
								</td>
								<td>
									<input onblur="checkUser()" class="text" id="uname" name="uname" msg="用户名不能为空,应为字母、数字、下划线" >
									<span>用户名不能为空,应为字母、数字、下划线</span>
								</td>
								<tr>
									<td>
										<label class="redfont" for="psw">
											您的密码
										</label>
									</td>
									<td>
										<input class="text" id="psw" type="password" name="psw" msg="密码应为6字母、数字、特殊字符"  max="18" min="6">
										<span>密码应为6字母、数字、特殊字符</span>
									</td>
								</tr>
								<tr>
									<td>
										<label class="redfont" for="psw2">
											重复输入密码
										</label>
									</td>
									<td>
										<input class="text" id="psw2" type="password" name="psw2" msg="两次输入的密码不一致" >
										<span></span>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<span class="greenfont" style="FONT-SIZE: 14px">请对您的博客信息进行设置</span>
									</td>
								</tr>
								<tr>
									<td>
										<label class="redfont" for="name">
											您的博客标题
										</label>
									</td>
									<td>
										<input class="text" id="subject" name="subject" msg="博客标题不能为空" datatype="Require">
										&nbsp;&nbsp;<span>给您的博客起个好听的名字</span>
									</td>
								</tr>
								<tr>
									<td>
										<label class="redfont" for="email">
											电子邮件地址
										</label>
									</td>
									<td>
										<input class="text" id="email" type="text" name="email" msg="需符合电子邮件地址的格式" max="18" min="6">
										<span>请按照电子邮件地址的格式填写</span>
									</td>
								</tr>

								<tr>
									<td>
										&nbsp;
									</td>
									<td>
										<input class="button-submit" type="submit" value="注册">
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<hr style="color:#999999" width="90%">
		<p align="center">
			<font color="#5C7FA7"><b>BlogOnline 版权所有</b></font>
		</p>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<jsp:directive.page import="org.zjy.service.BlogService"/>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
      <%
         String id=request.getParameter("id");
         BlogService service=new BlogService();
         service.get
       %>
  </body>
</html>

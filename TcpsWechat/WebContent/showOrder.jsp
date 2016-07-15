<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>  
<%@ page import="java.util.Map"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<% int curPage = 1; 
   int pageSize = 2; 
	%>
<center>
		<form name="loginform" id="loginform" action="add" method="post">
			
				<label style="font-size:23px;">已完成订单：<br>
				</label>
				<table border="1px" cellspacing = "0px" style="border-collapse:collapse;font-size:18px">
  					<tr>a
    					<th width="100">订单号</th>
    					<th width="100">时间</th>
    					<th width="100">金额</th>
  					</tr>
  					<% if(session.getAttribute("okList") != null){
  					  if(request.getParameter("page") != null && Integer.parseInt(request.getParameter("page")) > 0)
  					   {
  						   curPage = Integer.parseInt(request.getParameter("page"));
  					   }
  					  List<?> list = (List<?>)session.getAttribute("okList");
  					   if(list.size()>0){
  					   if((curPage > list.size()/pageSize)){
  						   if(list.size() % 2 != 0)
  							   curPage = list.size()/pageSize+1;
  						   else
  							   curPage = list.size()/pageSize;
  					   }
  					   for(int i = (curPage-1)*pageSize; i < curPage*pageSize && i < list.size(); i++ ){
  					   Map<String,Object> map = (Map<String,Object>) list.get(i);
  					%>
					<tr><td><%=map.get("ORDER_NO") %></td><td><%=map.get("ORDER_TIME") %></td><td><%=map.get("SUM_MONEY") %></td></tr>
					<% }
  					   session.removeAttribute("okList");
  					   }} %>
				</table>
				<br>  
				<input type="button" value="上一页" onclick="location.href='showOrder.jsp?page=<%=curPage-1%>'">  
				 <label>第<%=curPage %>页</label>
				<input type="button" value="下一页" onclick="location.href='showOrder.jsp?page=<%=curPage+1%>'">
				<br>  
				<br>   
				<label style="font-size:23px;">异常订单：<br>
				</label>
				
				<table border="1px" cellspacing = "0px" style="border-collapse:collapse;font-size:18px">
				<tr><th>订单号</th><th>时间</th><th>金额</th></tr>
				<%
				 if(session.getAttribute("unpaidOrder") != null){
					 Map<String,Object> unpaidOrder = (Map<String,Object>) session.getAttribute("unpaidOrder"); 
				 
				%>
					<tr>
    					<th width="100"><%=unpaidOrder.get("ORDER_NO") %></th>
    					<th width="100"><%=unpaidOrder.get("ORDER_TIME") %></th>
    					<th width="100"><%=unpaidOrder.get("SUM_MONEY") %></th> 
  					</tr>
 			    <% 
 			    	session.removeAttribute("unpaidOrder");
				 }
 			    %>
				</table>
		</form>
</center>
</body>
</html>
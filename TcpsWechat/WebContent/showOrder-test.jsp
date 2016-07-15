<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>  
<%@ page import="java.util.Map"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">	
 <link rel="stylesheet" href="/TcpsWechat/weui.min.css"/>
 <script type="text/javascript" src="js/bootstrap.min.js"></script>
 <script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
 <style type="text/css">
 	#hidebg {
			position: absolute;
			left: 0px;
			top: 0px;
			background-color: #000;
			width: 100%; /*宽度设置为100%，这样才能使隐藏背景层覆盖原页面*/
			height: 100%;
			filter: alpha(opacity = 60); /*设置透明度为60%*/
			opacity: 0.6; /*非IE浏览器下设置透明度为60%*/
			display: none;
			z-Index: 2;
		}
 </style>
</head>
<body>
<div class="container">
<% int curPage = 1; 
   int pageSize = 2; 
	%>

				<label style="font-size:23px;">已完成订单：</label>
				<br>
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
					<div class="row" style="background-color:#7D26CD;">
		         	<div class="col-xs-12 col-md-9">
			        <h4>订单号：<%=map.get("ORDER_NO") %></h4>
				    <h4>订单时间：    <%=map.get("ORDER_TIME") %></h4>
				    <h4>订单总金额： <%=map.get("SUM_MONEY") %></h4>
			    	</div>
			    	<div class="col-xs-12 col-md-1">
			        <p style="padding-left:10px; padding-bottom:20px; padding-right:20px; padding-top:20px;"></p> 
			     	</div>
		       		</div>
		       		<br>
					<% }
  					   }} %>
				<br>  
				

				<div class="weui_dialog_ft">
				<input class="weui_btn weui_btn_primary" type="button" value="上一页" onclick="location.href='showOrder-test.jsp?page=<%=curPage-1%>'">  
				</div>
				<br>
				<center>
				 <label style="font-size:23px;">第<%=curPage %>页</label>
				 </center>
				 <br>
				<input  class="weui_btn weui_btn_primary" type="button" value="下一页" onclick="location.href='showOrder-test.jsp?page=<%=curPage+1%>'">
			
				<br>   
				<label style="font-size:23px;">异常订单：<br>
				</label>
				
			
				<%
				 if(session.getAttribute("unpaidOrder") != null){
					 Map<String,Object> unpaidOrder = (Map<String,Object>) session.getAttribute("unpaidOrder"); 
					 session.setAttribute("orderNo",unpaidOrder.get("ORDER_NO"));
					 session.setAttribute("sumMoney",unpaidOrder.get("SUM_MONEY"));
					 session.setAttribute("orderTime", unpaidOrder.get("ORDER_TIME"));
					 double sumMoney = Double.parseDouble(unpaidOrder.get("SUM_MONEY").toString());
					 String product = null;
					 if(sumMoney > 10 && sumMoney < 20){
						 product = "0001";
					 }
					 else if(sumMoney > 20 && sumMoney < 50){
						 product = "0002";
					 }
					 else if(sumMoney > 50 && sumMoney < 100){
						 product = "0003";
					 }
					 else if(sumMoney > 100 && sumMoney < 200){
						 product = "0004";
					 }
					 else if(sumMoney > 200 && sumMoney < 300){
						 product = "0005";
					 }
					 else if(sumMoney > 300){
						 product = "0006";
					 }
					 session.setAttribute("product_id", product);
				 
				%>
					<div class="row" style="background-color:#7D26CD;">
		         	<div class="col-xs-12 col-md-9">
			        <h4>订单号：<%=unpaidOrder.get("ORDER_NO") %></h4>
				    <h4>订单时间：    <%=unpaidOrder.get("ORDER_TIME") %></h4>
				    <h4>订单总金额： <%=unpaidOrder.get("SUM_MONEY") %></h4>
			    	</div>
			    	<div class="col-xs-12 col-md-1">
			        <p style="padding-left:10px; padding-bottom:20px; padding-right:20px; padding-top:20px;"></p> 
			     	</div>
			     	<form id="submitForm" action="pay1.jsp" method="post">
					<input type="submit" onclick="submitForm()" value="去支付">
					</form>
					<form id="submitForm1" action="deleteUnpaid" method="post">
					<input type="submit" onclick="submitForm1()" value="取消该订单">
					</form>
		       		</div>
		       		
		       		<br>
 			    <% 
				 }
 			    %>
</div>

 <div id="hidebg"></div>
</body>

<script type="text/javascript">
	function submitForm(){
		$("#hidebg").css("display","block");
		$("#submitForm").submit();
	}
	function submitForm1(){
		$("#hidebg").css("display","block");
		$("#submitForm1").submit();
	}
</script>
</html>
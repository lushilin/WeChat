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
 <script type="text/javascript" src="/TcpsWechat/jquery-2.1.4.min.js"></script>
 <link rel="stylesheet" href="/TcpsWechat/weui.min.css"/>
 <link rel="stylesheet" href="/TcpsWechat/bootstrap.min.css"/>
 <script type="text/javascript" src="/TcpsWechat/bootstrap.min.js"></script>
 
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
	.head {
		line-height: 44px;
		height: 44px;
		text-align: center;
		font-size: 20px;
		background: #c9302c;
		color: white;
	}
		
 </style>
</head>
<body>
<center>
	<div class="weui_tabbar">
	  <ul class="nav nav-tabs">
	  	<li role="presentation" style="margin-left:32px;">
	    	<a href="#wx" id="paid" role="tab" data-toggle="tab" style="margin-left:40px;margin-left:40px" class="weui_tabbar_item weui_bar_item_on">
	            <div class="weui_tabbar_icon">
	            	<img src="/TcpsWechat/image/icon_nav_button_unpaid.png" alt="">
	            </div>
	            <p class="weui_tabbar_label">已支付</p>
	        </a>
	    </li>
	    <li role="presentation">
		    <a href="#txl" id="unpaid" role="tab" data-toggle="tab" style="margin-left:40px;margin-left:40px"  class="weui_tabbar_item weui_bar_item_on">
	            <div class="weui_tabbar_icon">
	                <img src="/TcpsWechat/image/icon_nav_button_normal.png" alt="">
	            </div>
	            <p class="weui_tabbar_label">未支付</p>
	        </a>
	    </li>
	  </ul>
	</div>

	<div class="tab-content">
	    <div class="tab-pane" id="wx">
		 	<% int curPage = 1; 
			   int pageSize = 2; 
			%>
			
				<header class="head">已完成订单</header>
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
					<div class="row" style="background-color:#FFFFFF;">
			         	<div class="col-xs-12 col-md-9">
					        <h4>订单号：<%=map.get("ORDER_NO") %></h4>
						    <h4>订单时间：    <%=map.get("ORDER_TIME") %></h4>
						    <h4>订单总金额： <%=map.get("SUM_MONEY") %></h4>
				    	</div>
			       	</div>
			       	<br>
					<% }
			  		}
				  } %>
				  	<center>
						<label style="font-size:23px;">第<%=curPage %>页</label>
					</center>
				  	
					<table width="100%" height="25%" border="0">
						<tr>
							<td align="center" valign="middle">
								<table width="95%"  border="0" align="center" cellpadding="10px" cellspacing="20px">
									<tr>
										<td><input class="weui_btn weui_btn_primary" type="button" value="上一页" onclick="location.href='showOrder-test1.jsp?page=<%=curPage-1%>'"></td>
										<td></td>
										<td><input class="weui_btn weui_btn_primary" type="button" value="下一页" onclick="location.href='showOrder-test1.jsp?page=<%=curPage+1%>'"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
		</div>
		<div class="tab-pane" id="txl">
		 	<header class="head">未完成订单</header>
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
					<div class="weui_cells">
						<div class="weui_cell">
							<div class="weui_cell_hd">
								<img
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
									style="width:20px;margin-right:5px;display:block">
							</div>
							<div class="weui_cell_hd">
								<label class="weui_label">订单号</label>
							</div>
							<div class="weui_cell_bd weui_cell_primary">
								<lable><%=unpaidOrder.get("ORDER_NO") %></lable>
							</div>
						</div>
						<div class="weui_cell">
							<div class="weui_cell_hd">
								<img
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
									style="width:20px;margin-right:5px;display:block">
							</div>
							<div class="weui_cell_hd">
								<label class="weui_label">订单时间</label>
							</div>
							<div class="weui_cell_bd weui_cell_primary">
								<lable> <%=unpaidOrder.get("ORDER_TIME") %></lable>
							</div>
						</div>
						<div class="weui_cell">
							<div class="weui_cell_hd">
								<img
									src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
									style="width:20px;margin-right:5px;display:block">
							</div>
							<div class="weui_cell_hd">
								<label class="weui_label">订单总金额</label>
							</div>
							<div class="weui_cell_bd weui_cell_primary">
								<lable> <%=unpaidOrder.get("SUM_MONEY") %></lable>
							</div>
						</div>
					</div>
					<br>
					<table width="100%" height="25%" border="0">
						<tr>
							<td align="center" valign="middle">
								<table width="95%"  border="0" align="center" cellpadding="10px" cellspacing="20px">
									<tr>
										<td>
											<form id="submitForm" action="pay1.jsp" method="post">
												<input type="submit" class="weui_btn weui_btn_primary" onclick="submitForm()" value="去支付">
											</form>
										</td>
										<td>
											<form id="submitForm1" action="deleteUnpaid" method="post">
												<input type="submit" class="weui_btn weui_btn_primary" onclick="submitForm1()" value="取消该订单">
											</form>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
		       	</div>
		       		
		       		<br>
 			    <% 
 				 
				 }
 			    %>
		</div>
</center>
<div id="hidebg"></div>
</body>

<script type="text/javascript">
	$("#paid").click();
	
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
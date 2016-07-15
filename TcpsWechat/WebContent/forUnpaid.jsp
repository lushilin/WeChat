<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link href="grid.css" rel="stylesheet">
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
<label style="font-size:23px;">您有一笔未支付订单，是否要继续支付？</label>
<br>
<div class="row" style="background-color:#7D26CD;">
		         	<div class="col-xs-12 col-md-9">
			        <h4>订单号：<%=session.getAttribute("orderNo") %></h4>
				    <h4>订单时间：    <%=session.getAttribute("orderTime") %></h4>
				    <h4>订单总金额： <%=session.getAttribute("sumMoney") %></h4>
			    	</div>
			    	<div class="col-xs-12 col-md-1">
			        <p style="padding-left:10px; padding-bottom:20px; padding-right:20px; padding-top:20px;"></p> 
			     	</div>
		       		</div>
<br>
<form id="submitForm1" action="pay1.jsp" method="post">
<input class="weui_btn weui_btn_primary" type="submit" value="确定">
</form>
<br>
<form id="submitForm2" action="jump" method="post">
<input class="weui_btn weui_btn_primary" type="submit" onclick="submit2()" value="不,创建新订单">
</form>
</div>
<div id="hidebg"></div>
</body>

<script type="text/javascript">
	function submit1(){
		$("#hidebg").css("display","block");
		$("#submitForm1").submit();
	}

	function submit2(){
		$("#hidebg").css("display","block");
		$("#submitForm2").submit();
	}
</script>
</html>
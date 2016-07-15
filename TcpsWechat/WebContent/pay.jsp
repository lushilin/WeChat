<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/TcpsWechat/weui.min.css"/>
 <script type="text/javascript" src="/TcpsWechat/jquery-3.0.0.min.js"></script>
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
 </style>
</head>
<body>

<div class="container">
<%=session.getAttribute("openid") %>
<br>
<center>
<label style="font-size:23px;">订单支付:</label>
</center>
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

<input class="weui_btn weui_btn_primary" type="button" onclick="callpay()" value="支付">

</div>
<div id="hidebg"></div>
</body>
<script type="text/javascript">
	  	function callpay(){
			$("#hidebg").css("display","block");
	  		var outTradeNo = "<%=session.getAttribute("orderNo") %>";
	  		var productId = "<%=session.getAttribute("product_id") %>";
			 
			$.ajax({
				type : "post",
				data : "out_trade_no=" + outTradeNo + "&product_id="
						+ productId,
				dataType : "text",
				url : "/TcpsWechat/payment",
				success : function(data) {
					if(data.length > 2)
					{
						$("#hidebg").css("display","none");
						var elements = $(data);
						var appId = elements.find("appId").text();
						var timeStamp = elements.find("timeStamp").text();
						var nonceStr = elements.find("nonceStr").text();
						var packages = elements.find("package").text();
						var paySign = elements.find("paySign").text();
						WeixinJSBridge
								.invoke(
										'getBrandWCPayRequest',
										{
											"appId" : appId,
											"timeStamp" : timeStamp,
											"nonceStr" : nonceStr,
											"package" : packages,
											"signType" : "MD5",
											"paySign" : paySign
										},
										function(res) {
											WeixinJSBridge.log(res.err_msg);
											if (res.err_msg == "get_brand_wcpay_request:ok") {
												var openid = "<%=session.getAttribute("openid") %>";
												var orderNo = "<%=session.getAttribute("orderNo") %>";
												url = "http://www.yanwenxiong.cn/TcpsWechat/updateOrder?openid=" + openid;
												url = url + "&orderNo=";
												url = url + orderNo;
												alert(url);
												window.location.href =url;
											} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
												alert("用户取消支付!");
											} else {
												alert("支付失败!");
											}
										});
						}
					}
				,error : function(data) {
					alert(data);
				}
			}); 
		}
</script>
</html>
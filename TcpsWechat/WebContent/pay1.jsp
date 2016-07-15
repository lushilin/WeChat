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
	.head{
			line-height:44px;
			height: 44px;
			text-align: center;
			font-size: 20px;
			background: #c9302c;
			color: white;
		}
 </style>
</head>
<body>
	<header class="head">
		<strong>订单支付</strong>
	</header>
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
				<lable><%=session.getAttribute("orderNo") %></lable>
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
				<lable><%=session.getAttribute("orderTime") %></lable>
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
				<lable><%=session.getAttribute("sumMoney") %></lable>
			</div>
		</div>
	</div>
	<br>
	<div>
		<input type="button" value="确认支付" style="width:88%" onclick="callpay()" class="weui_btn weui_btn_primary">
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
												alert("您已经成功支付！");
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
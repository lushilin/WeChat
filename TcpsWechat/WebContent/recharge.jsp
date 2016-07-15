<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.setAttribute("backTag", "ok");
%>


<html lang="en">
<head>
	<base href=<%=basePath %>>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>IC卡充值</title>
	<link rel="stylesheet" href="/TcpsWechat/weui.min.css"/>
	<script type="text/javascript" src="/TcpsWechat/jquery-3.0.0.min.js"></script>
	<style>
		.head{
			line-height:44px;
			height: 44px;
			text-align: center;
			font-size: 20px;
			background: #c9302c;
			color: white;
		}

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

	<header class="head">
	<strong>IC卡充值</strong>
	</header>
	<table width="100%" height="25%" border="0">
		<tr>
			<td align="center" valign="middle">
				<table width="95%" border="0" align="center" cellpadding="0" cellspacing="10">
					<tr >
						<td><a name="priceLable" class="weui_btn weui_btn_primary price">10元</a></td>
						<td><a name="priceLable" class="weui_btn weui_btn_primary price">20元</a></td>
						<td><a name="priceLable" class="weui_btn weui_btn_primary price">50元</a></td>
					</tr>
					<tr>
						<td><a name="priceLable" class="weui_btn weui_btn_primary price">100元</a></td>
						<td><a name="priceLable" class="weui_btn weui_btn_primary price">200元</a></td>
						<td><a name="priceLable" class="weui_btn weui_btn_primary price">300元</a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<!-- 选择卡片类型-->
	<div class="weui_cells_title" style="font-size:16px"><strong>选择充值卡片</strong></div>
	<div class="select weui_cells weui_cells_radio">
		<label class="weui_cell weui_check_label">
			<div class="card weui_cell_bd weui_cell_primary">
				<p>新的IC卡</p>
			</div>
			<div class="weui_cell_ft">
				<input type="radio" class="weui_check" name="radio1" id="x11">
				<span class="weui_icon_checked"></span>
			</div>
		</label>
		<label class="weui_cell weui_check_label">
			<div class="card weui_cell_bd weui_cell_primary">
				<p>已认证IC卡</p>
			</div>
			<div class="weui_cell_ft">
				<input type="radio" name="radio1" class="weui_check" id="x12" checked="checked">
				<span class="weui_icon_checked"></span>
			</div>
		</label>
	</div>
	<hr/>
	<!-- 根据卡片类型不同显示的内容-->
	<div class="select_content">
		<div class="new_card" style="display:none">
			<div class="weui_cells weui_cells_access">
				<a class="weui_cell" id="chooseCity">
					<div class="weui_cell_bd weui_cell_primary">
						<p>城市</p>
					</div>
					<div id="city" class="weui_cell_ft">未选择</div>
				</a>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd"><label class="weui_label">IC卡号</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="number" pattern="[0-9]*" placeholder="请输入您的IC卡号"/>
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd"><label class="weui_label">持卡人</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" type="number" placeholder="请输入持卡人的姓名"/>
				</div>
			</div>
		</div>

		<div class="identified_card">
			<div class="weui_cell weui_cell_select weui_select_after">
				<div class="weui_cell_hd">
					<label class="weui_label">我的卡片</label>
				</div>
				<% Map<String,Object> user = (Map<String,Object>) request.getAttribute("user"); 
				%>
				<div class="weui_cell_bd weui_cell_primary">
					<select class="weui_select" name="select2" id="select2">
						<% 	if(user != null){
							 if(!user.isEmpty()){
							for(int i=1; i <= Integer.parseInt(user.get("ID_NO").toString()); i++){ %>
						<option value="<%=user.get("IC_ID"+i) %>"><%=user.get("IC_ID"+i) %></option>
						<% }}} %>
					</select>
				</div>
			</div>
		</div>
	</div>
	<hr/>
	<table>
		<tr class="weui_cell">
			<td><label class="weui_label">需付款</label></td>
			<td><label class="weui_label" id="total_price_visible">0 元</label></td>
		</tr>
	</table>
	<div class="myForm">
		<form id="payForm"  action="rechargedo"  method="post" onsubmit="return check();">
			<input type="hidden" id="card_number" name="card_number">
			<input type="hidden" id="total_price" name="total_price">
			<input type="hidden" id="product_id" name="product_id">
			<input type="submit" value="确认" class="weui_btn weui_btn_primary" onclick="submitForm()" style="width:88%">
		</form> 
	</div>
	
	<div id="nullAlert" class="nullAlert" style="display:none">
		<div class="weui_dialog_alert">
	    <div class="weui_mask"></div>
	    <div class="weui_dialog">
	        <div class="weui_dialog_hd"><strong class="weui_dialog_title">IC卡充值</strong></div>
	        <div class="weui_dialog_bd">请选择您要充值的金额</div>
	        <div class="weui_dialog_ft">
	            <a href="recharge.jsp" class="weui_btn_dialog primary">确定</a>
	        </div>
	    </div>
	</div>
</div>
<div id="hidebg"></div>
</body>
<script type="text/javascript">
	$("a#chooseCity").click(function () {
		window.location.href = "/Wechat/pages/city.html";
	});
	
	console.log($("div#city")[0].innerText);

	$("div.card").click(function () {
		var val1 = $(this)[0].innerText.trim();
		if(val1 == "新的IC卡"){
			$("div.new_card").show();
			$("div.identified_card").hide();
		}
		else if(val1 == "已认证IC卡"){
			$("div.identified_card").show();
			$("div.new_card").hide();
		}
	});

	var total_price;
	var product_id;
	//监视那个按钮被按下
	$(document).ready(function(){
		$("a").click(function(){
			var lableList = $("a[name=priceLable]");
			for(var i = 0; i < lableList.length; i++){
				lableList[i].setAttribute("class","weui_btn weui_btn_primary price");
			}

			$(this)[0].setAttribute("class","weui_btn weui_btn_warn price");
			total_price = $(this)[0].innerText;
			total_price = total_price.substr(0,total_price.length-1).trim();

			switch(total_price){
				case "10": product_id = "0001"; break;
				case "20": product_id = "0002"; break;
				case "50": product_id = "0003"; break;
				case "100": product_id = "0004"; break;
				case "200": product_id = "0005"; break;
				case "300": product_id = "0006"; break;
			}
			//TODO 将来这里放置手续费;
			$("input#total_price")[0].value = total_price;
			$("label#total_price_visible")[0].innerText = total_price;
			$("input#product_id")[0].value = product_id;
		});
	});
	
	function check(){
		if($("input#total_price")[0].value !== ""){
			$("input#card_number")[0].value = $("select#select2")[0].value;
			return true;
		}
		else{
			$("div#nullAlert").show();
			$("#hidebg").css("display","none");
			return false;
		}
	}
	
	function submitForm(){
		$("#hidebg").css("display","block");
	}

</script>
</html>

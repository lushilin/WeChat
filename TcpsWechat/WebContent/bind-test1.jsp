<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="/TcpsWechat/weui.min.css" rel="stylesheet" type="text/css">
 <script type="text/javascript" src="/TcpsWechat/bootstrap.min.js"> </script>
 <script type="text/javascript" src="/TcpsWechat/jquery-3.0.0.min.js"> </script>
 <script src="javascripts/bootstrap-select.js"></script>

 <style>
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
	<header class="head">
		<strong>IC卡绑定</strong>
	</header>

<div class="container" style ="padding-top:40px">
	<form class="form-signin" name="loginform" id="loginform" action="add" method="post">
		<div class="weui_cells">
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<img
						src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
						style="width:20px;margin-right:5px;display:block">
				</div>
				<div class="weui_cell_hd">
					<label class="weui_label">openid</label>
				</div>
				<div class="weui_cell_bd weui_cell_primary">
					<lable><%=session.getAttribute("openid") %></lable>
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<img
						src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
						style="width:20px;margin-right:5px;display:block">
				</div>
				<div class="weui_cell_hd"><label class="weui_label">IC卡号</label></div>
				<div class="weui_cell_bd weui_cell_primary">
					<input class="weui_input" id="card_id" name="card_id" type="number" pattern="[0-9]*" placeholder="请输入您的IC卡号"/>
				</div>
			</div>
			<div class="weui_cell">
				<div class="weui_cell_hd">
					<img
						src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII="
						style="width:20px;margin-right:5px;display:block">
				</div>
				<div class="weui_cell_hd">
					<label class="weui_label">城市</label>
				</div>
				<div class="weui_cell_bd">
					<div class="weui_cell weui_cell_select">
	            		<select class="weui_select" name="city"  id="city">
			                <option value="0454">寿阳</option>
			                <option value="01213630">蚌埠</option>
			                <option value="3">北京</option>
			            </select>
        			</div>
				</div>
			</div>
		</div>
		<br>
		<input type="submit" value="确认" click="submitForm()" class="weui_btn weui_btn_primary" style="width:88%">
	</form>
</div> 
 

</body>
<div id="hidebg"></div>
<script	type="text/javascript">
	function submitForm(){
		$("#hidebg").css("display","block");
		$("#loginform").submit();
	}
</script>
</html>
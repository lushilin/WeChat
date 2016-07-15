<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="/TcpsWechat/css/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link href="signin.css" rel="stylesheet">
 <script type="text/javascript" src="/TcpsWechat/js/bootstrap.min.js"> </script>
 <script src="javascripts/prettify.js"></script>
 <script type="text/javascript" src="/TcpsWechat/js/jquery-3.0.0.min.js"> </script>
 <script src="javascripts/bootstrap-select.js"></script>
 <script type="text/javascript">
      window.onload=function(){
      $('.selectpicker').selectpicker(); 
      };
 </script>
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
 </style>
</head>
<body>
 <div class="container" style ="padding-top:40px">
<form class="form-signin" name="loginform" id="loginform" action="add" method="post">

<label  for="city" class="sr-only">请选择城市</label>
<center>
<select class="selectpicker"  data-style="btn-inverse" name="city"  id="city" >
	<option value="0454">寿阳</option>
	<option value="01213630">蚌埠</option>
	</select>
</center>	
<br>
<label for="open_id" class="sr-only">用户的openid </label>
<input  class="form-control" name="open_id" value="<%=session.getAttribute("openid") %>" id="open_id" size="28" type="text" disabled="true" readOnly="true"> 

<label for="card_id" class="sr-only">卡号</label>
<input   class="form-control" name="card_id" value="请输入您的IC卡号" id="card_id" size="20" type="text">
<input class="btn btn-lg btn-primary btn-block"  name="submit" value="绑定" type="submit" click="submitForm()"> 
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
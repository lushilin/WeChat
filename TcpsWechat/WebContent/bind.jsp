<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<center>
<form name="loginform" id="loginform" action="add" method="post">
<p>
<label>请选择城市
<select name="city"  id="city">
	<option value="0454">寿阳</option>
	<option value="01213630">蚌埠</option>
	</select></label>
</p>
<p>
<label >用户的openid<br>
<input name="open_id" value="<%=session.getAttribute("openid") %>" id="open_id" size="28" type="text" disabled="true" readOnly="true"> </label>
</p>
<p>
<label for="user_pass">卡号<br>
<input name="card_id" value="请输入您的IC卡号" id="card_id" size="20" type="text"></label>
</p>
<p>

<input name="submit" value="绑定" type="submit"> 

</form>
</center>
</body>
</html>
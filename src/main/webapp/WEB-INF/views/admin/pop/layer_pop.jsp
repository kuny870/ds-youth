<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<!-- POPUP --> 

<div id="divpop" style="position:absolute;left:50px;top:70px;z-index:200;visibility:hidden;"> 

<table width=230 cellpadding=2 cellspacing=0> 

<tr> 

<td style="border:1px #666666 solid" align=center bgcolor=white>

.<br>

​

<font size="2" color= "red">< 레이버 팝업창 text> <p>

<font size="2" color= "blue"> ***title***<P>

<font size="2" color= "red"> " text line 01"<p>

<font size="2" color= "blue">★ text line 02 ★<P>

<font size="2" color= "red"> ***title2***<p>

<font size="2" color= "blue"><p>★ text line 03 ★ 

<p> <font size="2" color= "red"> " text line 04"<p>

<font size="2" color= "black"> " 카톡문의-ID: cansave"<p>

​

​

​

</td> 

​

​

</tr> 

<tr> 

<form name="notice_form"> 

<td align=right bgcolor=white> 

<input type="checkbox" name="chkbox" value="checkbox">오늘 하루 이 창을 열지 않음 

<a href="javascript:closeWin();"><B>[닫기]</B></a> 

</td> 

</form> 

</tr> 

</table> 

</div> 

​

<script language="javascript"> 

cookiedata = document.cookie; 

if ( cookiedata.indexOf("maindiv=done") < 0 ) { 

document.all['divpop'].style.visibility = "visible"; 

} else { 

document.all['divpop'].style.visibility = "hidden"; 

}





function closeWin() { 

if ( document.notice_form.chkbox.checked ) { 

setCookie( "maindiv", "done" , 1 ); 

}

document.all['divpop'].style.visibility = "hidden";

}

function setCookie( name, value, expiredays ) { 

	var todayDate = new Date(); 

	todayDate.setDate( todayDate.getDate() + expiredays ); 

	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";";

}
	
	
</script> 



</html>
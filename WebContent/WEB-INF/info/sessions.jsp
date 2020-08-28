<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id='stateDatabaseUser' class='programranje.yatospace.server.basic.bean.SessionBean' scope='session'></jsp:useBean>
<br>
ПРЕГЛЕД СЕСИЈА

<br><br>
<form name='sessions_info'>
	<div><font size='2px'>Административна сесија : </font></div>
	<input type='text' name='username' value='${pageContext.session.id}' readonly/>
	<div><font size='2px'>Корисничка сесија : </font>
	<input type='text' name='database' value='${stateDatabaseUser.userProfileSessionId(pageContext.session)}' readonly/></div>
	<br><br>
</form>
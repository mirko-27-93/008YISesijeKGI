<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id='stateDatabaseUser' class='programranje.yatospace.server.basic.bean.SessionBean' scope='session'></jsp:useBean>
<br>
ОДЈАВНА ФОРМА
<br><br>
<form name='logout_form' method='POST' action='${pageContext.request.contextPath}/transitive/Logout.jsp'>
	<div><font size='2px'>Корисничко име : </font></div>
	<input type='text' name='username' value='${stateDatabaseUser.info.userName}' readonly/>
	<div><font size='2px'>Назив базе података : </font>
	<input type='text' name='database' value='${stateDatabaseUser.info.dataBaseSvcName}' readonly/></div>
	<div><font size='2px'>Адреса базе података  : </font></div>
	<input type='text' name='address'  value='${stateDatabaseUser.info.dataBase}' readonly/>
	<br><br>
	<input type='submit' value='Одјава'/>
	<br><br>
</form>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<jsp:useBean id='webPageTrasitiveBean' class='programranje.yatospace.server.basic.bean.WebPageBean' scope='session'></jsp:useBean>
<jsp:setProperty name='webPageTrasitiveBean' property='backPage' value='${pageContext.request.contextPath}/index.jsp'></jsp:setProperty>

<tiles:insertDefinition name="indexPage" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>notice select</title>

<c:import url="../template/boot.jsp" />
</head>
<body>

<c:import url="../template/nav.jsp" />


<div class="container">
<h1>N o t i c e</h1>
<hr>
<h2>S E L E C T</h2>
<hr>

	<div class="form-group">
      <label for="title">title:</label>
      ${noticeVO.title}
    </div>
    
    <div class="form-group">
      <label for="writer">writer:</label>
      ${noticeVO.writer}
    </div>
    
    <div class="form-group">
      <label for="contents">contents:</label>${noticeVO.contents}
    </div>


</div>



</body>
</html>
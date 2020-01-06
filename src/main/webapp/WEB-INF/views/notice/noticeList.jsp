<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp" />

</head>
<body>

<c:import url="../template/nav.jsp" />

notice list

<div class="container">

		<div>
			<form action="./noticeList" id="frm">
				 <input type="hidden" value="1" name="curPage" id="curPage">
			
				<select name="kind">				
					<option id="kT" value="kT">제목</option>
					<option id="kW" value="kW">작성자</option>
					<option id="kC" value="kC">내용</option>
				</select>
			
				<input type="text" name="search" value="${pager.search}">
				
				<button>검색</button>
			</form>
		</div>

	<table class="table table-hover"> 
	<tr>
		<td>num</td>
		<td>title</td>
		<td>writer</td>
		<td>date</td>
		<td>hit</td>
	</tr>
	
	<c:forEach items="${list}" var="lists"> 
    <tr>
    	<td>${lists.num}</td>
    	<td> <a href="./noticeSelect?num=${lists.num}" class="list-group-item">${lists.title}</a></td>
    	<td>${lists.writer}</td>
    	<td>${lists.regDate}</td>
    	<td>${lists.hit}</td>
    </tr>
    
   </c:forEach>

	</table>
	
	
	<div style="width: 100%; margin: 0 auto; text-align: center; padding-top: 10px;">
				<ul class="pagination" style="margin: 0 auto; text-align: center;">
					
					<c:if test="${pager.curBlock gt 1}">
						<li><span id=${pager.startNum - 1} class="list">이전<a href="./noticeList?curPage=${pager.startNum-1}">[이전]</a></span></li>
						<a href="./noticeList?curPage=${pager.startNum-1}">[이전]</a>
					</c:if>
					
					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" step="1" var="i">
						<li><span id="${i}" class="list">${i}</span></li>
						
					</c:forEach>

					<c:if test="${pager.curBlock lt pager.totalBlock}">
						<li><span id="${pager.lastNum + 1}" class="list">다음</span></li>
						<a href="./noticeList?curPage=${pager.lastNum+1}">[다음]</a>
					</c:if>
					
				</ul>
			</div>



<%-- 
<div class="list-group">
<c:forEach items="${List}" var="list"> 
  <a href="#" class="list-group-item"> ${list.num} ${list.title} ${list.writer} ${list.regDate} ${list.hit}</a>
</c:forEach>
</div> --%>

</div>

	<script type="text/javascript">
	 	var kind = '${pager.kind}';
		if (kind == '') {
			kind = "kT";
		}
		
		$("#"+kind).prop("selected", true);
	 
		$(".list").click(function() {
			$("#curPage").val($(this).attr("id"));
			$("#frm").submit();
		});
	</script>

</body>
</html>
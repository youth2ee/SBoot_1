<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice Write</title>

<c:import url="../template/boot.jsp" />
<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

</head>
<body>

<c:import url="../template/nav.jsp" />



<div class="container">
<h1>N o t i c e</h1>
<hr>
<h2>W R I T E</h2>

	<form:form action="./noticeWrite" method="post" modelAttribute="noticeVO" enctype="multipart/form-data">
	<div class="form-group">
      <!-- path(memberVO의 변수명) = name -->
      <label for="title">title:</label>
      <form:input path="title" placeholder="Enter title" class="form-control" id="title" />
     
      <div style="height: 20px;"> <form:errors path="title" cssStyle="color:red; " /> </div>
    </div>
    
    <div class="form-group">
      <label for="writer">writer:</label>
      <form:input path="writer" class="form-control" id="writer" placeholder="Enter writer" />
       <div style="height: 20px;"> <form:errors path="writer" cssStyle="color:red; " /> </div>
    </div>
    
    <div class="form-group">
      <label for="contents">contents:</label>
      <form:textarea path="contents" class="form-control" id="contents" placeholder="Enter contents" />
       <div style="height: 20px;"> <form:errors path="contents" cssStyle="color:red; " /> </div>
    </div>
    
<!--         <div class="form-group">
      <label for="files">File:</label>
      <input type="file" class="form-control" id="files" name="files">
    </div>
     -->
<!--     		<div id="files_d">
			<div class="form-group" title="parent">
      		<label for="files">File:</label>
     		 <input type="file" class="form-control" id="files" name="files">
				<input type="button" class="btn btn-danger del" value="del">
			</div>
		</div>
    
    <input type="button" class="btn btn-success" value="ADD FILE" id="add">
    
    
	<button type="submit" class="btn btn-default">Submit</button>
	
	 -->
	
				 <div class="row" >
		    	<input type="button"  class="btn btn-info col-sm-3" id="add" value="ADD FILE">
		    	<div id="files" class="col-sm-12"></div>
		    </div>
		    
		    <div class="row"></div>
		     <div class="row" style="margin-top: 50px;">
		    <input type="submit" id="write" class="btn btn-danger col-sm-3" value="Write">
		   	</div> 
	
	
	</form:form>



</div>

<script type="text/javascript">


$("#contents").summernote();

//var files = $("#files_d").html();
//var count = 1;
/* var index = 0; //인덱스번호 사용하기 */
/* $("#files_d").empty(); */

/* 	$("#add").click(function() {
		if (count < 5) {
			$("#files_d").append(files);
			count++;
		} else {
			alert("파일추가는 최대 5개까지 가능합니다.")
		}
	});
	
	$("#files_d").on("click", ".del", function() {
			count--;
			$(this).parents(".form-group").remove();				
	});
	 */

	 var count=0;
		
		$("#add").click(function() {
			if(count<5){
				var result ='<div class="input-group col-xs-3"><input type="file" name="files" class="form-control" id="files"><span class="input-group-addon"><i class="glyphicon glyphicon-remove del"></i></span> </div>';
				$("#files").append(result);
				count++;
			}else {
				alert("첨부파일은 최대 5개만 가능합니다.");
			}
		});
		
		$("#files").on("click", ".del", function() {
			$(this).parent().parent().remove();
			//$(this).remove();
			count--;
		});
	





</script>




</body>
</html>
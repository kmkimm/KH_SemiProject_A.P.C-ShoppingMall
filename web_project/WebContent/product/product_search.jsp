<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	html, body{
	
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	
	.search_wrapper {
		min-height: 100vh;
		display: flex;
		flex-direction: column;
		flex: 1;
	}
	
	
	.search_container{
		flex: 1;
		
	}
	
	.Products_list{
		display: grid;
		grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
		align-itmes: center;
	}
	
	
	
	
	/*********************************/
	.apcTitle{
		margin-top: 50px;
		font-size: 63px;
		margin-left: 15px;
		font-weight: bold;
		width: 100%;
		flex: 1;
	
	}
	
	.apcTitle > a{
		color: black;
		text-decoration: none;
	}
	
	.apcTitle > a:hover{
		color: white;
		text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
	}


	/**********************************/
	
	
	
	#btnSearch {
		background-image: url(../images/icon-search-bold.svg);
		position: absolute;
		top: 80px;
		left: 800px;
		width: 48px;
		height: 48px;
		background-size: 48px;
		margin-top: -1px;
		cursor: pointer;
	}
	
	
	.space {
		height: 200px;
		
	}
	
	.product > a {
		text-decoration: none;
		color: black;
	}
	
	
	.description_text {
		text-align: center;
	}
	
	/****************************/
	
	#search_field {
		width: 570px;
		height: 60px;
		position: absolute;
		top: 67px;
		left: 220px;
		text-decoration: none;
		border: none;
		font-size: 50px;
	
	}
	
	
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script type="text/javascript">

	$(function(){
		$('#btnSearch').click(function(){
			var valueById = $('#search_field').val();
				location.href='<%=request.getContextPath() %>/product_search.do?keyword='+valueById;
		});
	});
	
	

</script>
</head>
<body>
	
	
	<div class="search_wrapper">
	
	<jsp:include page="../include/shop_top.jsp"/>
	<jsp:include page="../include/shop_top_right.jsp"/>
	
		<div class="apcTitle">
	
			<a href="<%=request.getContextPath() %>/index.jsp">A.P.C.</a> 
			
			<form action="<%=request.getContextPath() %>/product_search.do">
			
				<input id="search_field" type="search" value="" name="keyword" placeholder="SEARCH" minlength="2" maxlength="128">
			
				<div id="btnSearch"></div>
				
			</form>
		</div>
			
		<div class="search_container"> 
		

			<c:set var="list" value="${productList }"/>
		
			<div class="Products_list">
			
					<c:if test="${!empty list }">
					
						<c:forEach items="${list }" var="dto">	
						
							<div class="product">
								<!-- 20220511 이슬 수정 : product_detail.do로 통일 -->
				    			<a href="<%=request.getContextPath() %>/product_detail.do?num=${dto.getPno() }&color=${dto.getPcolor() }&size=${dto.getPsize() }">
									<img alt="" src="image_products/${dto.getPimage() }" width="100%"> 
									<div class="description">
										<div class="description_text">${dto.getPname() }</div>
										<div class="description_text"><fmt:formatNumber value="${dto.getPrice() }"/>원</div>
									</div>
								</a>
							</div>
							
						</c:forEach>
						
					</c:if>
					
					
			</div> <!-- Products_list end -->
		
				<!--  <div class="space"></div>-->
	
			
		
		</div> <!-- search_container end -->
		
		<jsp:include page="../include/shop_bottom.jsp"/>
		
	</div> <!-- search_wrapper end -->
	
	
</body>
</html>
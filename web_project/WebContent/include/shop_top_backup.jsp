<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">


	

	.seach_text{
		display: none;
	
	}
	
	

	/********** include end **********/


	
	#navr{
		margin: 10px 0px 0px 10px;
		/*display: flex;*/
		position: absolute;
		
	}
	
	#navr_ul{
		height: 20px;
		padding-left: 10px;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		/*justify-content: space-evenly;*/
		padding-left: 0px;
		margin: 0px;
	}
	
	
	
	
	
	#navr_ul > li{
		font-size: 14px;
		font-weight: bold;
		padding: 0 38px 0px 0px;
		list-style-type: none;
		
	}
	
	
	
	/********** 상단 메뉴바 호버 처리 해야됌 *************/

	.dropDown{
		display: flex;
		flex-direction: column;
		background-color: pink;
		padding: 0px;
		display: none;
		
	}
	

	
	.dropDown:hover{
		display: visible;
		background-color: pink;
	}
	
	.navr_ul > li:hover {
		border: 1px solid red;
		color: blue;
	}
	
	
	
	/********** left side end **********/
	
	a {
		text-decoration: none;
		color: black;
	}
	.women{
		
		
	}

	
	
	
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript"></script>

<script type="text/javascript">


	
	$(function (){
		$('#search_icon2').click(function(){
			$(location).attr('href', '<%=request.getContextPath() %>/product/product_search.jsp');
		});
	});
	
	
	


</script>
</head>
<body>
	
	
	<div id="navr">
		<ul id="navr_ul">
			<li class="women">
				<a href="#">Women</a>
				<div>
					<ul class="dropDown">
						<li>New Arrivals</li>
						<li>Permanent</li>
						<li>Dresses</li>
						<li>Outerwear</li>
						<li>Jackets</li>
						<li>Jeans</li>
						<li>Pants</li>
						<li>Skirts, Shors</li>
						<li>T-Shirts, Tops</li>
						<li>Blouses, Shirts</li>
						<li>Knitwear</li>
						<li>Sweatshirts</li>
					</ul>
				</div>
				<div class="dropDown">
					<ul>
						<li>Jessica Ogden</li>
						<li>A.P.C. Sneakers</li>
						<li>Denim</li>
						<li>Lookbook</li>
					</ul>
				</div>
			</li>
			<li>Men</li>
			<li>Denim</li>
			<li>Golf</li>
			<li>Objects</li>
			<li>Surplus</li>
			<li>A.P.C.X Jessica Ogden</li>
			<li>
			
				<div class="search-container">
					<a href="#" alt="Search image">
					
						<img id="search_icon2" alt="Search image" 
						src="<%=request.getContextPath() %>/images/icon-search-bold.svg" width="18px">
						
						<span class="seach_text">search</span>	
					</a>
					
				</div>
				
			</li>
		</ul>
	</div> <!-- navr end -->
	
	

	
	<!--  <script src="script/modal.js"></script>-->

</body>
</html>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Basic Shopping Cart</title>
	<link rel="stylesheet" href="../resources/static/css/style.css" type="text/css" media="all" />
</head>
<body>

<!-- Shell -->
<div class="shell">

	<!-- Main -->
	<div id="main">
		<div class="cl">&nbsp;</div>
		<a href="/"><h1>Basic Shopping Cart</h1></a>
		<br>
		<!-- Sidebar -->
		<div id="sidebar">

			<!-- Categories -->
			<div class="box categories">
				<h2>Top Categories</h2>
				<div class="box-content">
					<ul>
						<c:if test="${not empty categories}">
							<c:forEach var="item" items="${categories}">
								<li><a href="/category/${item.categoryId}">${item.name}</a></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
			<!-- End Categories -->

		</div>
		<!-- End Sidebar -->

		<!-- Content -->
		<div id="content">

			<!-- Products -->
			<div class="products">
				<div class="cl">&nbsp;</div>
				<ul>
					<c:if test="${not empty products}">
						<c:forEach var="item" items="${products}" varStatus="loop">
							<li>
								<a href="/product/${item.productId}">
									<img src="../resources/static/images/${item.categoryId}.jpg" alt="" />
									<div class="product-info">
										<h3>${item.name} - ${item.price} TL</h3>
									</div>
								</a>
							</li>

							<c:if test="${(loop.count mod 3) == 0}">
				</ul><ul>
							</c:if>

						</c:forEach>
					</c:if>
				</ul>
				<div class="cl">&nbsp;</div>
			</div>
			<!-- End Products -->

		</div>
		<!-- End Content -->

		<div class="cl">&nbsp;</div>
	</div>
	<!-- End Main -->

</div>
<!-- End Shell -->

</body>
</html>
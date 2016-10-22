<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Basic Shopping Cart</title>
	<link rel="stylesheet" href="../../resources/static/css/product.css" type="text/css" media="all" />
</head>
<body>

<!-- Shell -->
<div class="shell">

	<!-- Main -->
	<div id="main">
		<div class="cl">&nbsp;</div>
		<center><h1>Basic Shopping Cart</h1></center><br>
		<!-- Content -->
		<div id="content">

			<!-- Products -->
			<div class="product">
				<div class="cl">&nbsp;</div>
				<ul>
					<li>
						<img src="../../resources/static/images/jackets1.jpg" width="390" height="490" alt="" />
					</li>
					<li>
						<br><h1>${product.name}</h1><br>${product.price} TL
						<br><br><br><br><br><br>Quantity:&nbsp;<input class="quantity"/>&nbsp;&nbsp;<input type="button" class="add-to-basket" value="Add to Basket" />
						<br><br>
						<textarea class="detail-area">Product Details:${product.details}
Size:${product.size}
Colour:${product.colour}</textarea>
					</li>
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
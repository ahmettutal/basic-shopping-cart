<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Basic Shopping Cart</title>
	<link rel="stylesheet" href="../resources/static/css/product.css" type="text/css" media="all" />




	<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script>
		jQuery(document).ready(
				function($) {

					$("#basket").click(function(event) {
						var productId = $('#productId').val();
						var count = $('#quantity').val();

						$("#basket").prop("disabled", true);

						$.post("/basket", {
							productId : productId,
							count : count,
						}, function(data) {

							var result = data;
							if (result == "OK")
								alert("Item(s) Added to Basket");
							else
								alert(data);

						}).done(function() {
						}).fail(function(xhr, textStatus, errorThrown) {
							alert("Adding to Basket Failed!");
						}).complete(function() {
							$("#basket").prop("disabled", false);
						});

					});

				});
	</script>


</head>
<body>

<!-- Shell -->
<div class="shell">

	<!-- Main -->
	<div id="main">
		<div class="cl">&nbsp;</div>
		<a href="/category/${product.categoryId}"><h1>Basic Shopping Cart</h1></a><br>
		<!-- Content -->
		<div id="content">

			<!-- Products -->
			<div class="product">
				<div class="cl">&nbsp;</div>

				<input type="hidden" name="productId" id="productId" value="${product.productId}" />

				<ul>
					<li>
						<img src="../resources/static/images/jackets1.jpg" width="390" height="490" alt="" />
					</li>
					<li>
						<br><h1>${product.name}</h1><br>${product.price} TL
						<br><br><br><br><br><br>Quantity:&nbsp;<input class="quantity" id="quantity" value="1"/>&nbsp;&nbsp;
						<input type="button" id="basket" class="add-to-basket" value="Add to Basket" />
						<br><br>
						<textarea class="detail-area">Product Details:${product.detail}

Size:${product.size}

Colour:${product.colour}</textarea>
					</li>
				</ul>
				<span style="float: right; padding-right: 40px;"><h1><a href="/basket">Go To CHECKOUT <img src="../resources/static/images/basket.png" width="39" /></a></h1></span>
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
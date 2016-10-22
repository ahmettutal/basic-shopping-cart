<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Basic Shopping Cart</title>
	<link rel="stylesheet" href="../../resources/static/css/basket.css" type="text/css" media="all" />
	<script src="https://code.jquery.com/jquery-2.2.4.js" charset="utf-8"></script>
</head>
<body>

<div class="shopping-cart">

	<div class="title">
		<a href="/">Basic Shopping Cart</a>
	</div>
	<c:set var="totalCost" value="0" scope="page"/>
	<c:if test="${not empty products}">
		<c:forEach var="item" items="${products}">
			<div class="item">
				<div class="buttons">
					<span class="delete-btn"></span>
				</div>

				<div class="image">
					<img src="../../resources/static/images/${item.categoryId}.jpg" width="100" height="100" />
				</div>

				<div class="description">
					<span>${item.name}</span>
					<span>${item.size} / ${item.colour}</span>
					<span>${item.price}</span>
				</div>

				<div class="quantity">
					<button class="plus-btn" type="button" name="button">
						<img src="../../resources/static/images/plus.svg" />
					</button>
					<input type="text" name="count" id="count" value="${basketMap.get(item.productId).intValue()}">
					<button class="minus-btn" type="button" name="button">
						<img src="../../resources/static/images/minus.svg" alt="" />
					</button>
				</div>

				<div class="total-price">Total: ${item.price * basketMap.get(item.productId).intValue()} TL</div>
				<c:set var="totalCost" value="${totalCost + (item.price * basketMap.get(item.productId).intValue())}" scope="page"/>
			</div>
		</c:forEach>
	</c:if>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${products.size()} items on your basket.<br/><br/>

</div>

<div class="last-line">
	<br/>
	<span style="float:right;padding-right:9px;">Total: ${totalCost} TL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" id="checkout" class="checkout" value="Checkout" /></span><br/><br/>
	<span style="float:right;padding-right:87px;">Tax(%18): ${totalCost * 0.18} TL</span><br/><br/>
</div>


<script type="text/javascript">
	$('.minus-btn').on('click', function(e) {
		e.preventDefault();
		var $this = $(this);
		var $input = $this.closest('div').find('input');
		var value = parseInt($input.val());

		if (value > 1) {
			value = value - 1;
		} else {
			value = 0;
		}

		$input.val(value);

	});

	$('.plus-btn').on('click', function(e) {
		e.preventDefault();
		var $this = $(this);
		var $input = $this.closest('div').find('input');
		var value = parseInt($input.val());

		if (value < 100) {
			value = value + 1;
		} else {
			value = 100;
		}

		$input.val(value);
	});

	$('.delete-btn').on('click', function() {
		$(this).toggleClass('is-active');
	});
</script>

</body>
</html>
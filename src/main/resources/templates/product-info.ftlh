<!DOCTYPE html>
<html>
<head>
    <title>Store</title>
</head>
<body>
<h1> ELECTRONICS STORE </h1><hr>
<h4>Product details</h4>
<b>Product name:</b> ${product.title}
<b>Product description: </b> ${product.description}<br>
<b>Price: </b> ${product.description}<br>
<b>City: </b> ${product.description}<br>
<b>Author: </b> ${product.description}<br>
<hr>
<form action="/product/delete/${product.id}" method ="post">
<input type="submit" value="Delete product">
</form>
</body>
</html>
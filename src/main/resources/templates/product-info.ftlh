<!DOCTYPE html>
<html>
<head>
    <title>Store</title>
</head>
<body>
<h1> ELECTRONICS STORE </h1><hr>
<h4>Product details</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Product name:</b> ${product.title}
<b>Product description: </b> ${product.description}<br>
<b>Price: </b> ${product.price}<br>
<b>City: </b> ${product.city}<br>
<b>Author: </b> ${product.author}<br>
<hr>
<form action="/product/delete/${product.id}" method ="post">
<input type="submit" value="Delete product">
</form>
</body>
</html>
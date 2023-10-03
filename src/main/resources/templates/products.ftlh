<!DOCTYPE html>
<html>
<head>
    <title>STORE</title>
</head>
<body>
<h1> STORE </h1><hr>
<h3>Original electronics...</h3><br>
<form action="/" method="get">
    Search by ad name:
<input type="submit" value="Search">
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} UAH(₴)
        | <a href="/product/${product.id}">Details</a></p>
    </div>
    <#else>
        <h3>No products available</h3>
</#list>
<hr>
<h3>Create new product</h3>
<form action="/product/create" method ="post">
    Ad name: <input type="text" name="title"/><br><br>
    Description of the ad: <input type="text" name="description"/><br><br>
    Price: <input type="number" name="price"/><br><br>
    City: <input type="text" name="city"/><br><br>
    Name: <input type="text" name="author"/><br><br>
    <input type="submit" value="Add product">
</form>
</body>
</html>
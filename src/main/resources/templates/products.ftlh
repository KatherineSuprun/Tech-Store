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
<h3>Create new product</h3>                   // указывает что можно передавать файловый тип также
<form action="/product/create" method ="post" enctype="multipart/form-data">
    Ad name: <input type="text" name="title"/><br><br>
    Description of the ad: <input type="text" name="description"/><br><br>
    Price: <input type="number" name="price"/><br><br>
    City: <input type="text" name="city"/><br><br>
    Name: <input type="text" name="author"/><br><br>
    First photo: <input type="file" name="file1"/><br><br>
    Second photo: <input type="file" name="file2"/><br><br>
    Third photo: <input type="file" name="file3"/><br><br>

    <input type="submit" value="Add product">
</form>
</body>
</html>
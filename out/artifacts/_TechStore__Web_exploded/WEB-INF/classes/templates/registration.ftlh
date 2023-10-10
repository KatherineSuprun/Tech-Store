<!DOCTYPE html>
<html>
<head>
    <title>TECH-STORE</title>
</head>
<body>
<h2>Registration</h2>
<form action="/registration" method="post">
    User name: <input type="text" name="name"><br><br>
    Email: <input type="text" name="email"><br><br>
    Phone number: <input type="text" name="phoneNumber"><br><br>
    Password: <input type="password" name="password"><br><br>
<#-- crsf.token создает уникальный ключ для защиты данных от перехвата, (ctrl + /) -->
    <input type="hidden" name="_csrf" value="${_crsf.token}">
    <input type="submit" value="Sign in"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>
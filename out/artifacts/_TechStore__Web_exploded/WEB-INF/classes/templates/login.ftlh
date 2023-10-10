<!DOCTYPE html>
<html>
<head>
    <title>TECH-STORE</title>
</head>
<body>
<h2>Authorization</h2>
<form action="/login" method="post">
    Email: <input type="email" name="username"><br><br>
    Password: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_crsf.token}">
    <input type="submit" value="Enter"/>
</form>
<a href="/registration">Registration</a>
</body>
</html>
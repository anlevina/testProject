<!DOCTYPE html>
<html>
<head>
    <title>
        Registration Page
    </title>
</head>
<br>
<h3>Registration</h3>
<form action="/registration" method="post" enctype="multipart/form-data">
    Username: <input type="text" name="username"/><br>
    Email: <input type="email" name="email"/><br>
    Password: <input type="password" name="password"/><br><br>
    Image: <input type="file" name="avatar"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Register"/>
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>
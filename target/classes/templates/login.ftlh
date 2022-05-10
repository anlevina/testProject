<!DOCTYPE html>
<html>
<head>
    <title>
        Login Page
    </title>
</head>
<br>
<h3>Authorization</h3>
<form action="/login" method="post">
    Email: <input type="text" name="username"/><br><br>
    Password: <input type="password" name="password"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Login"/>
</form>
<a href="/registration">Register</a>
<h1>or</h1>
<a href="/">Main page</a>
</body>
</html>
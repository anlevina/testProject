<!DOCTYPE html>
<html>
<head>
<title>
    Test
</title>
</head>
<body>
<h1> <a href="/login">Login</a> </h1><br>
<h1> <a href="/registration">Register</a> </h1><br><br>
<#if user.isActive()>
<a href="/user/${user.id}">Profile</a> <br><br>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value  ="Logout"/>
</form>
</#if>
<form action="/" method="get">
Search by name: <input type="text" name="name"/><br>
    <input type="submit" value="Search"/>
</form>
<#if user.isActive()>
<h3>Create something</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Name: <input type="text" name="name"/><br><br>
    Description: <input type="text" name="description"/><br><br>
    Price: <input type="number" name="price"/><br><br>
    Image: <input type="file" name="file"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Create"/>
</form>
</#if>
<#list products as product>
    <div>
        <p>
            ${product.name} | ${product.price} | <a href="/product/${product.id}">More info</a>
        </p>
        <br><br>
        <#if product.filename??>
            <img src="/img/${product.filename}">
        </#if>
    </div>
    <#else>
        <h3>No goods</h3>
</#list>
<br>
</body>
</html>
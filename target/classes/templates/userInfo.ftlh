<!DOCTYPE html>
<html>
<head>
    <title>
        Registration Page
    </title>
</head>
<br>
<h3>Information about ${user.username}</h3>
<#if user.avatarFilename??>
    <img src="/img/${user.avatarFilename}">
</#if> <br><br>
Username: ${user.username} <br><br>
Email: ${user.email} <br><br>
<h3>User's goods</h3>
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
</body>
</html>
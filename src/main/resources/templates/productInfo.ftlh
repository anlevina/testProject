<!DOCTYPE html>
<html>
<head>
    <title>
        Test
    </title>
</head>
<body>
<br>
<h3>About:</h3>
<br>
Name: ${product.name} <br>
Description: ${product.description}<br>
Price: ${product.price} <br><br>
Author: <a href="/user/${product.user.id}">${product.user.username}</a>  <br><br>
<#if product.filename??>
    Image: <img src="/img/${product.filename}"> <br><br>
</#if>
<input type="submit" value="Create"/>
<br>
<br>
<br>
<#--<form action="/product/delete/${product.id}" method="post">-->
<#--    <input type="submit" value="Delete"/>-->
<#--</form>-->
</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>
        Test
    </title>
</head>
<body>
<h1>Administration panel</h1><br><br>
<table>
    <tr>
        <th>More info</th>
        <th>Email</th>
        <th>Active</th>
        <th>Roles</th>
        <th>Ban</th>
        <th>Add role</th>
    </tr>
    <#list users as user>
        <tr>
            <th>
                <a href="/user/${user.id}">More info</a>
            </th>
            <th>${user.email}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}<#sep>, </#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="submit" value="ban">
                </form>
            </th>
            <th>
                <a href="/admin/user/edit/${user.id}">Edit</a>
            </th>
        </tr>
    <#else>
        <h3>No users</h3>
    </#list>
</table>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>
        User edit info
    </title>
</head>
<br>
<h3>Information about ${user.username}</h3>
Username: ${user.username} <br><br>
Email: ${user.email} <br><br>
<h3>Change role</h3>
<form action="/admin/user/edit" method="post">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="text" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>
</body>
</html>
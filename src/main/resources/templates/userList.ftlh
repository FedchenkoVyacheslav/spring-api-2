<#import "parts/common.ftlh" as c>
<@c.page>
    <h2 style="display: inline-block; width: 100%;"
    >List of users</h2>
    <table>
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}" style="text-decoration: none; color: black; font-weight: bold;">Edit role</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
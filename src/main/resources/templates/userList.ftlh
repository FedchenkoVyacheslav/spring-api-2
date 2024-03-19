<#import "parts/common.ftlh" as c>
<@c.page>
    <table class="table table-bordered table-secondary">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>
                    <#list user.roles as role>${role}<#sep>, </#list>
                    <a href="/user/${user.id}" style="float: right; color: black; font-weight: bold;">Edit role</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
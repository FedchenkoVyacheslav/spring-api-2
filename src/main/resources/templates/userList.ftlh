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
                <td>${user.email}</td>
                <td>
                    <#list user.roles as role>${role}<#sep>, </#list>
                    <a href="/admin/${user.id}" style="text-decoration: none; float: right; color: black; font-weight: bold;">Edit</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
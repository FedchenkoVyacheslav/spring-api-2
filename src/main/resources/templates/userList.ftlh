<#import "parts/common.ftlh" as c>
<@c.page>
    <table class="table table-bordered table-secondary mb-5">
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
                    <a href="/admin/${user.id}" class="admin-edit-btn">Edit</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
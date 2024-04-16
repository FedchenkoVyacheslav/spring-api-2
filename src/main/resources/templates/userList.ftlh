<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-10">
            <form method="get" action="/admin" class="form-inline">
                <input type="text" class="form-control col-sm-6" name="filter" id="search-input" value="${filter!""}"
                       placeholder="Search for ..."/>
                <div id="error-el"></div>
                <button type="submit" class="btn btn-info ml-2" id="search-button">Search
                </button>
            </form>
        </div>
    </div>
    <table class="table table-bordered table-secondary mb-5">
        <#if users?has_content>
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Role</th>
            </tr>
            </thead>
        </#if>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.email}</td>
                <td>
                    <#list user.roles as role>${role}<#sep>, </#list>
                    <a href="/admin/${user.id}" class="admin-edit-btn">Edit</a>
                </td>
            </tr>
        <#else>
            <div id="error-el2" class="users-error">No results found!</div>
        </#list>
        </tbody>
    </table>
</@c.page>
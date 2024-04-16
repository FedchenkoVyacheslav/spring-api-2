<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>

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
    <#if !user?? && page.getTotalPages()!=0>
        <@p.pager url page "Users" true/>
    </#if>
    <table class="table table-striped table-light mb-5">
        <#if !user?? && page.getTotalPages()!=0>
            <thead>
            <tr>
                <th scope="col" class="col-sm-8">Name</th>
                <th scope="col" class="col-sm-2">Role</th>
            </tr>
            </thead>
        </#if>
        <tbody>
        <#list page.content as user>
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
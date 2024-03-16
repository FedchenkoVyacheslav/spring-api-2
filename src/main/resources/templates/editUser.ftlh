<#import "parts/common.ftlh" as c>
<@c.page>
    <h2 style="display: inline-block; width: 100%;"
    >Edit user</h2>
    <form action="/user" method="post" style="float: left;">
        <div style="clear:both; text-align:right; margin-bottom: 20px">
            <label for="n">Set new email: </label>
            <input type="text" name="username" value="${user.username}" placeholder="Enter email"/>
        </div>
        <#list roles as role>
        <div>
            <label>
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>
                ${role}
            </label>
        </div>
        </#list>
        <input type="hidden" name="identityId" value="${user.id}"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" style="margin: 20px 0; display: block;">Save changes</button>
    </form>
</@c.page>
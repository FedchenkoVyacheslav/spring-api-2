<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="title-subscribe-list" class="mb-3">
        <p class="title-subscribe-list mr-2">${userChannel.username}</p>
        <span class="">${type}</span>
    </div>
    <#if (users?size = 0)>
        <p class="empty-subscribe-list">No ${type}</p>
    </#if>
    <ul class="list-group col-sm-4">
        <#list users as user>
            <li class="list-group-item">
                <a class="link-subscribe-list" href="/user-messages/${user.id}">${user.getUsername()}</a>
            </li>
        </#list>
    </ul>
</@c.page>
<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="title-subscribe-list" class="mb-3">
        <p class="title-subscribe-list mr-1">${userChannel.username}</p>
        <span class="">${type}</span>
    </div>
    <#if (users?size = 0)>
        <p class="empty-subscribe-list">No ${type}</p>
    </#if>
    <ul class="list-group col-sm-3">
        <#list users as user>
            <li class="list-group-item subscribe-wrapper">
                <div class="subscribe-image-box mr-3">
                    <#if userChannel.photoUrl??>
                        <img src="/img/${user.photoUrl}">
                    <#else>
                        <img src="/../../pic/profile-empty.jpg">
                    </#if>
                </div>
                <div>
                    <a class="link-subscribe-list" href="/user-messages/${user.id}">${user.getUsername()}</a>
                    <p class="subscribe-text">${user.name} ${user.surname}</p>
                </div>
            </li>
        </#list>
    </ul>
</@c.page>
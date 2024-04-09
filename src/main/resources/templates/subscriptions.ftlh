<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <div class="title-subscribe-wrapper" class="mb-3">
        <p class="title-subscribe-title mr-1">${userChannel.name}'s</p>
        <span class="">${type}</span>
    </div>
    <#if (users?size = 0)>
        <p class="empty-subscribe-list">No ${type}</p>
    </#if>
    <ul class="subscribe-list list-group col-sm-10 mx-auto mb-4">
        <#list users as user>
            <li class="align-items-center list-group-item d-flex justify-content-between">
                <div class="subscribe-wrapper">
                    <div class="subscribe-image-box mr-3">
                        <#if user.photoUrl?has_content>
                            <img src="/img/${user.photoUrl}">
                        <#else>
                            <img src="/../../pic/profile-empty.jpg">
                        </#if>
                    </div>
                    <div>
                        <a class="link-subscribe-list" href="/user-messages/${user.id}">${user.getUsername()}</a>
                        <p class="subscribe-text">${user.name} ${user.surname}</p>
                    </div>
                </div>
                <div>
                    <#assign followers = user.followers>
                    <#if followers??>
                        <#if followers[0]?? && followers[0].id==currentUserId>
                            <a class="followers-btn btn btn-danger"
                               href="/user/unsubscribe/${user.id}">Unsubscribe</a>
                        <#elseif user.id==currentUserId && !isCurrentUser>
                            <p></p>
                        <#else>
                            <a class="followers-btn btn btn-info" href="/user/subscribe/${user.id}">Subscribe</a>
                        </#if>
                    <#else>
                        <a class="followers-btn btn btn-info" href="/user/subscribe/${user.id}">Subscribe</a>
                    </#if>
                </div>
            </li>
        </#list>
    </ul>
</@c.page>
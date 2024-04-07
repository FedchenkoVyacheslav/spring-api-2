<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <div class="title-subscribe-list" class="mb-3">
        <p class="title-subscribe-list mr-1">${userChannel.name}'s</p>
        <span class="">${type}</span>
    </div>
    <#if (users?size = 0)>
        <p class="empty-subscribe-list">No ${type}</p>
    </#if>
    <ul class="list-group col-sm-6 mb-4">
        <#list users as user>
            <li class="list-group-item d-flex justify-content-between">
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
                    <ul class="followers-list">
                        <#list user.followers as follower>
                            <#if user.followers??>
                                <li>
                                    <#if follower.id==currentUserId>
                                        <a class="subscribe-btn btn btn-info"
                                           href="/user/unsubscribe/${user.id}">Unsubscribe</a>
                                    <#elseif user.id==currentUserId && !isCurrentUser>
                                        <p></p>
                                    <#else>
                                        <a class="btn btn-info ml-3" href="/user/subscribe/${user.id}">Subscribe</a>
                                    </#if>
                                </li>
                            </#if>
                        <#else>
                            <a class="btn btn-info ml-3" href="/user/subscribe/${user.id}">Subscribe</a>
                        </#list>
                    </ul>
                </div>
            </li>
        </#list>
    </ul>
</@c.page>
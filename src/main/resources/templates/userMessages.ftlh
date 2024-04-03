<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as m>
<#import "parts/messageList.ftlh" as ms>

<@c.page>
    <div class="row">
        <div class="col">
            <div class="card subscribe">
                <div class="card-body subscribe-body">
                    <div class="subscribe-wrapper">
                        <div class="mr-4">
                            <p class="subscribe-text subscribe-email">${userChannel.email}</p>
                            <p class="subscribe-text">${userChannel.name} ${userChannel.surname}</p>
                        </div>
                        <div class="mt-1">
                            <div class="subscribe-wrapper">
                                <div class="subscribe-text mr-2">Subscriptions:</div>
                                <a class="subscribe-link"
                                   href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                            </div>
                            <div class="subscribe-wrapper">
                                <div class="subscribe-text mr-2">Subscribers:</div>
                                <a class="subscribe-link"
                                   href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                            </div>
                        </div>
                    </div>
                    <#if !isCurrentUser>
                        <#if isSubscriber>
                            <a class="btn btn-info ml-3"
                               href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
                        <#else>
                            <a class="btn btn-info ml-3" href="/user/subscribe/${userChannel.id}">Subscribe</a>
                        </#if>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser>
        <@m.edit "Save the changes"/>
    </#if>
    <@ms.messageList "You have no created messages"/>
</@c.page>
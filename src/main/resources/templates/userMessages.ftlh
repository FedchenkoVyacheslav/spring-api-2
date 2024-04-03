<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as m>
<#import "parts/messageList.ftlh" as ms>

<@c.page>
    <div class="container my-3">
        <h3>${userChannel.username}</h3>
        <#if !isCurrentUser>
            <#if isSubscriber>
                <a class="btn btn-info mb-3" href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
            <#else>
                <a class="btn btn-info mb-3" href="/user/subscribe/${userChannel.id}">Subscribe</a>
            </#if>
        </#if>
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#if isCurrentUser>
        <@m.edit "Save the changes"/>
    </#if>
    <@ms.messageList "You have no created messages"/>
</@c.page>
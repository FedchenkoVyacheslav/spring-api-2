<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as m>
<#import "parts/messageList.ftlh" as ms>

<@c.page>
    <#if isCurrentUser>
        <@m.edit "Save the changes"/>
    </#if>
    <@ms.messageList "User has no messages!"/>
</@c.page>
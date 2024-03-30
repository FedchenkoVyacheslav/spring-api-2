<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as m>

<@c.page>
    <#if isCurrentUser>
        <@m.edit "Save the changes"/>
    </#if>
    <#include "parts/messageList.ftlh" />
</@c.page>
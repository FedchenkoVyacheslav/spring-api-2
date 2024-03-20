<#assign
known = SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = SPRING_SECURITY_CONTEXT.authentication.principal
    email = user.getEmail()
    isAdmin = user.isAdmin()
    currentUserId = user.getId()
    >
<#else>
    <#assign
    email = "Not loggen in"
    isAdmin = false
    currentUserId = -1
    >
</#if>
<#assign
known = SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = SPRING_SECURITY_CONTEXT.authentication.principal
    email = user.getEmail()
    name = user.getName()
    surname = user.getSurname()
    isAdmin = user.isAdmin()
    currentUserId = user.getId()
    >
<#else>
    <#assign
    email = "Not logged in"
    name = ""
    surname = ""
    isAdmin = false
    currentUserId = -1
    >
</#if>
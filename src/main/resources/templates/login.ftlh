<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
    <@a.auth "/login" "/registration" "LOGIN" "I don't have an account" "Sign into your account" false/>
</@c.page>
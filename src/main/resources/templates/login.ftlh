<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
    <@a.auth "/login" "/registration" "LOGIN" "I don't have an account" "crimson"/>
</@c.page>
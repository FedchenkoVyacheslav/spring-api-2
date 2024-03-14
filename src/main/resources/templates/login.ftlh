<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
<div style="position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);">
<@a.auth "/login" "/registration" "LOGIN" "I don't have an account" "crimson"/>
</@c.page>
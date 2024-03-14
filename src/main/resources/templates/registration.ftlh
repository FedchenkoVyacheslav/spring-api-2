<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
    <div style="position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);">
    <div style="position: absolute; top: -35px; left: 76px; color: red;">${message!" "}</div>
    <@a.auth "/registration" "/login" "SIGN UP" "I have an account" "darkcyan"/>
</@c.page>
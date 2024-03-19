<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
    <div style="position: absolute; top: -35px; left: auto; color: red;">${message!""}</div>
    <@a.auth "/registration" "/login" "SIGN UP" "I have an account" "olivedrab"/>
</@c.page>
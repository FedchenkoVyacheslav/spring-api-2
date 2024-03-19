<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
    <div class="main-container">
        <div class="reg-error">${message!""}</div>
    </div>
    <@a.auth "/registration" "/login" "SIGN UP" "I have an account" "olivedrab"/>
</@c.page>
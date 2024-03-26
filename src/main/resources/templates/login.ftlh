<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
    <#if SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger col-sm-4" role="alert">
            User not found!
        </div>
    </#if>
    <@a.auth "/login" "/registration" "LOGIN" "I don't have an account" "Sign into your account" false/>
</@c.page>
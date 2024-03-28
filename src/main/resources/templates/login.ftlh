<#import "parts/common.ftlh" as c>
<#import "parts/auth.ftlh" as a>
<@c.page>
    <#if SPRING_SECURITY_LAST_EXCEPTION??>
        <div id="auth-error-wrapper">
            <div id="auth-error" class="mt-0">
                That combination of email and password is not recognized
            </div>
        </div>
    </#if>
    <@a.auth "/login" "/registration" "LOGIN" "I don't have an account" "Sign into your account" false/>
</@c.page>
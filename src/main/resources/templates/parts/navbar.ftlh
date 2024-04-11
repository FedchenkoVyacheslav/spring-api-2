<#include "security.ftlh">
<#import "auth.ftlh" as a>

<nav class="navbar navbar-expand-lg navbar-light bg-light px-4 py-3">
    <a class="navbar-brand mb-1" href="/main">Spring-boot App</a>
    <#if user??>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </#if>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user-messages/${currentUserId}">My messages</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Profile</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/admin">Admin dashboard</a>
                </li>
            </#if>
            <li class="nav-email">
                <div class="navbar-text mr-3">${email}</div>
            </li>
            <li class="nav-btn">
                <#if user??>
                    <@a.logout/>
                </#if>
            </li>
        </ul>
    </div>
</nav>
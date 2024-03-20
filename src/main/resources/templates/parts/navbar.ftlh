<#include "security.ftlh">
<#import "auth.ftlh" as a>

<nav class="navbar navbar-expand-lg navbar-light bg-light px-4 py-3">
    <a class="navbar-brand mb-1" href="/">Spring-boot App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Messages</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/admin">Admin dashboard</a>
                </li>
            </#if>
        </ul>
    </div>
    <div class="navbar-text mr-3">${name}</div>
    <#if user??>
        <@a.logout/>
    </#if>
</nav>
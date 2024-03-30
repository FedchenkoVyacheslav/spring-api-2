<#include "security.ftlh">

<div>
    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3">
                <#if message.filename??>
                    <img class="card-img-top py-2 px-2" src="/img/${message.filename}">
                <#else>
                    <img class="card-img-top" src="../static/pic/no-img.png">
                </#if>
                <div class="card-body">
                    <h5 class="card-title">${message.title}</h5>
                    <span class="card-text">${message.text}</span>
                </div>
                <div class="card-footer text-muted">
                    <div class="card-footer-image-box">
                        <#if message.authorAvatar??>
                            <img src="/img/${message.authorAvatar}">
                        <#else>
                            <img src="../static/pic/profile-empty.jpg">
                        </#if>
                    </div>
                    <div class="card-author">
                        <span>${message.authorName} ${message.authorSurname}</span>
                        <a class="user-messages" href="/user-messages/${message.author.id}">${message.authorEmail}</a>
                        <p class="card-date">${message.updatedAt?datetime?string("dd.MM.yyyy HH:mm")}</p>
                        <#if message.author.id == currentUserId && springMacroRequestContext.requestUri?contains("/user-messages/")>
                            <a class="edit-message" href="/user-messages/${message.author.id}?message=${message.id}">
                                <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M254.220457 716.393247h110.331678l452.512413-452.512413-107.789474-107.789474L254.220457 610.129096zM780.965243 84.401192l107.789474 107.789473a101.688183 101.688183 0 0 1 0 143.888779l-452.512413 452.512413a101.688183 101.688183 0 0 1-71.690169 29.998014H231.340616A76.266137 76.266137 0 0 1 152.532274 741.815293V610.129096a101.688183 101.688183 0 0 1 29.998014-71.690168l452.512413-452.512414a101.688183 101.688183 0 0 1 145.922542-1.525322zM105.247269 969.596822a50.844091 50.844091 0 1 1 0-101.688182h813.505462a50.844091 50.844091 0 0 1 0 101.688182z"
                                          fill="#666666"/>
                                </svg>
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        <#else>
            <div id="error-el2">No results found!</div>
        </#list>
    </div>
</div>
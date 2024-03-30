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
                    </div>
                    <p class="card-date">${message.createdAt?datetime?string("dd.MM.yyyy HH:mm")}</p>
                </div>
            </div>
        <#else>
            <div id="error-el2">No results found!</div>
        </#list>
    </div>
</div>
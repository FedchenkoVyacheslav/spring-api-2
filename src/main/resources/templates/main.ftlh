<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" class="form-control" name="filter" id="search-input" value="${filter!""}"
                       placeholder="Search for ..."/>
                <div id="error-el" style="position: absolute; top: -34px; color: white"></div>
                <button type="submit" class="btn btn-info ml-2" id="search-button">Search
                </button>
            </form>
        </div>
    </div>
    <button id="add-message" class="btn btn-info" data-toggle="collapse" href="#messageForm" role="button" aria-expanded="false"
            aria-controls="messageForm">
        Add new message
    </button>
    <div class="collapse <#if message??>show</#if>" id="messageForm">
        <div class="form-group mt-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" id="message-title" class="form-control ${(titleError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.title}</#if>" name="title"
                           placeholder="Add a message title"/>
                    <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" id="message-text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text"
                           placeholder="Add message text"/>
                    <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" id="formFile" name="file"/>
                        <label style="cursor: pointer;" class="custom-file-label" for="formFile">Choose file...</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" id="message-btn" class="btn btn-info">Send message
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div>
        <div class="card-columns" style="position: relative">
            <#list messages as message>
                <div class="card my-3">
                    <#if message.filename??>
                        <img class="card-img-top py-2 px-2" src="/img/${message.filename}" style="width: 100%">
                    <#else>
                        <img class="card-img-top" src="../static/pic/no-img.png" style="width: 100%">
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
                            <span>${message.authorEmail}</span>
                        </div>
                        <p class="card-date">${message.createdAt?datetime?string("dd.MM.yyyy HH:mm")}</p>
                    </div>
                </div>
            <#else>
                <div id="error-el2" style="color: red; position: absolute; top: 31px;">No results found!</div>
            </#list>
        </div>
    </div>
</@c.page>
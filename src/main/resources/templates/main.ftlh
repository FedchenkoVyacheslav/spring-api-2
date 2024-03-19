<#import "parts/common.ftlh" as c>

<@c.page>
    <form method="get" action="/main" style="display: flex; position: relative">
        <div class="col-3 mb-3">
            <input type="text" class="form-control" name="filter" id="search-input" value="${filter!""}"
                   placeholder="Search for ..."/>
        </div>
        <div id="error-el" style="position: absolute; top: -34px;"></div>
        <div class="col ml-2">
            <button type="submit" class="btn btn-primary" id="search-button" style="margin-left: 20px;">Search</button>
        </div>
    </form>

    <a class="btn btn-primary" data-toggle="collapse" href="#messageForm" role="button" aria-expanded="false"
       aria-controls="messageForm">
        Add new message
    </a>
    <div class="collapse" id="messageForm">
        <div class="form-group mt-3 mb-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <div class="form-group mb-3">
                    <input type="text" id="message-text" class="form-control" name="text" placeholder="Add your text"/>
                </div>
                <div class="form-group mb-3">
                    <input type="text" id="message-tag" class="form-control" name="tag" placeholder="Add your tag"/>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input class="form-control" type="file" id="formFile" name="file"/>
                        <label class="form-label" for="formFile"></label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" id="message-btn" class="btn btn-primary">Send message
                    </button>
                </div>
            </form>
        </div>
    </div>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}" style="width: 100%">
                <#else>
                    <span>Empty</span>
                </#if>
            </div>
        </div>
    <#else>
        <div style="position: relative">
            <div id="error-el2" style="color: red; position: absolute; top: 31px;">No results found!</div>
        </div>
    </#list>
    </tbody>
    </div>
    </div>
</@c.page>
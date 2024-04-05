<#import "parts/common.ftlh" as c>
<#import "parts/messageEdit.ftlh" as m>
<#import "parts/messageList.ftlh" as ms>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-10">
            <form method="get" action="/main" class="form-inline">
                <input type="text" class="form-control col-sm-6" name="filter" id="search-input" value="${filter!""}"
                       placeholder="Search for ..."/>
                <div id="error-el"></div>
                <button type="submit" class="btn btn-info ml-2" id="search-button">Search
                </button>
            </form>
        </div>
    </div>
    <button id="add-message" class="btn btn-info" data-toggle="collapse" href="#messageForm" role="button"
            aria-expanded="false"
            aria-controls="messageForm">
        Add new message
    </button>
    <@m.edit "Add new message"/>
    <@ms.messageList "No results found!"/>
</@c.page>
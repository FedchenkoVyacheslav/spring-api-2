<#import "parts/common.ftlh" as c>

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
    <#include "parts/messageEdit.ftlh" />
    <#include "parts/messageList.ftlh" />
</@c.page>
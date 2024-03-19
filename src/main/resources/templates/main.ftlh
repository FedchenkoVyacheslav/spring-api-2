<#import "parts/common.ftlh" as c>

<@c.page>
    <div style="display: flex; justify-content: end;">
        <button><a href="/user" style="text-decoration: none; color: black;">User roles</a></button>
    </div>
    <h2>Send new message</h2>
    <div>
        <form method="post" action="/main" enctype="multipart/form-data" style="float: left;">
            <div style="clear:both; text-align:right;">
                <label for="n">Text</label>
                <input type="text" id="message-text" name="text" placeholder="Add your text"/>
            </div>
            <div style="clear:both; text-align:right; margin-top: 10px">
                <label for="n">Tag</label>
                <input type="text" id="message-tag" name="tag" placeholder="Add your tag"/>
            </div>
            <div style="clear:both; text-align:right; margin-top: 10px; width: 100px;">
                <input type="file" id="n" name="file"/>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div style="display: table; margin-top: 10px; color: red; position: absolute">${error!" "}</div>
            <button type="submit" id="message-btn" style="margin-top: 20px">Send</button>
        </form>
    </div>
    <div style="display: inline-block;
    background-color: lightblue;">
        <h2 style="display: inline-block; width: 100%;"
        >Messages list</h2>
        <form method="get" action="/main" style="float: left;">
            <div style="clear:both; text-align:right;">
                <input type="text" name="filter" id="search-input" value="${filter!""}" placeholder="Search for"/>
            </div>
            <div style="position: relative">
                <div id="error-el" style="position: absolute; top: 12px;"></div>
            </div>
            <button type="submit" id="search-button" style="margin-top: 40px; margin-bottom: 20px">Search</button>
        </form>
        <table>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Text</th>
                <th scope="col">Tag</th>
                <th scope="col">Author</th>
                <th scope="col">File</th>
            </tr>
            </thead>
            <tbody>
            <#list messages as message>
                <tr>
                    <th scope="row">${message.id}</th>
                    <td>${message.text}</td>
                    <td>${message.tag}</td>
                    <td>${message.authorName}</td>
                    <td>
                        <#if message.filename??>
                            <img src="/img/${message.filename}" style="width: 100%">
                        <#else>
                            <span>Empty</span>
                        </#if>
                    </td>
                </tr>
            <#else>
                <div style="position: relative">
                    <div id="error-el2" style="color: red; position: absolute; top: 31px;">No results found!</div>
                </div>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
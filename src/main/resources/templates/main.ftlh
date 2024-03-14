<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        <form action="/logout" method="post" style="text-align: right; margin: 20px" ;>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Sign Out"/>
        </form>
    </div>
    <h2>Send new message</h2>
    <div>
        <form method="post" action="/main" style="float: left;">
            <div style="clear:both; text-align:right;">
                <label for="n">Text</label>
                <input type="text" id="n" name="text" placeholder="Add your text"/>
            </div>
            <div style="clear:both; text-align:right; margin-top: 10px">
                <label for="n">Tag</label>
                <input type="text" id="n" name="tag" placeholder="Add your tag"/>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div style="display: table; margin-top: 10px; color: red; position: absolute">${error!" "}</div>
            <button type="submit" style="margin-top: 40px">Send</button>
        </form>
    </div>
    <div style="display: inline-block;
    background-color: lightblue;
    margin-top: 30px;
    padding: 10px;">
        <h2 style="display: inline-block; width: 100%;"
        >Messages list</h2>
        <form method="post" action="/filter" style="float: left;">
            <div style="clear:both; text-align:right;">
                <input type="text" id="n" name="filter" placeholder="Search for"/>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div style="display: table; margin-top: 10px; color: red; position: absolute">${searchError!" "}</div>
            <button type="submit" style="margin-top: 40px; margin-bottom: 20px">Search</button>
        </form>
        <table>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Text</th>
                <th scope="col">Tag</th>
                <th scope="col">Author</th>
            </tr>
            </thead>
            <tbody>
            <#list messages as message>
                <tr>
                    <th scope="row">${message.id}</th>
                    <td>${message.text}</td>
                    <td>${message.tag}</td>
                    <td>${message.authorName}</td>
                </tr>
            <#else>
                No messages
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
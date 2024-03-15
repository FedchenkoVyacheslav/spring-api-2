<#macro auth path1 path2 button link linkColor>
    <form action="${path1}" method="post" name="auth" style="float: left;">
        <div style="clear:both; text-align:right; margin-bottom: 30px">
            <label for="n">User name: </label>
            <input type="text" name="username" placeholder="Enter email"/>
        </div>
        <div style="clear:both; text-align:right; margin-bottom: 30px">
            <label for="n">Password: </label>
            <input type="password" name="password" placeholder="Enter password"/>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" style="margin: 20px auto; display: block;">${button}</button>
    </form>
    <a href="${path2}" style="margin: 20px auto;
            display: block;
            width: max-content;
            font-size: 18px;
            color: ${linkColor};
            text-decoration: none;
            font-weight: bold;">
        ${link}
    </a>
</#macro>

<#macro logout>
    <form action="/logout" method="post" style="text-align: right; margin: 20px" ;>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Sign Out"/>
    </form>
</#macro>

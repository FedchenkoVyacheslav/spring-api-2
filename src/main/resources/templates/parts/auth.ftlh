<#macro auth path1 path2 button link linkColor>
    <form action="${path1}" method="post" style="float: left;">
        <div style="clear:both; text-align:right;">
            <label for="n">User name: </label>
            <input type="email" id="n" name="username" placeholder="Enter email"/>
        </div>
        <div style="clear:both; text-align:right; margin-top: 10px">
            <label for="n">Password: </label>
            <input type="password" id="n" name="password" placeholder="Enter password"/>
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
        ${link}</a>
    </div>
</#macro>
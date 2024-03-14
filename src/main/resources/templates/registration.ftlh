<#import "parts/common.ftlh" as c>
<@c.page>
    <div style="position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%);">
        <div style="position: absolute; top: -35px; left: 76px; color: red;">${message!" "}</div>
        <form action="/registration" method="post" style="float: left;">
            <div style="clear:both; text-align:right;">
                <label for="n">User name: </label>
                <input type="email" id="n" name="username" placeholder="Enter email"/>
            </div>
            <div style="clear:both; text-align:right; margin-top: 10px">
                <label for="n">Password: </label>
                <input type="password" id="n" name="password" placeholder="Enter password"/>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="margin: 20px auto; display: block;">SIGN UP</button>
        </form>
        <a href="/login" style="margin: 20px auto;
    display: block;
    width: max-content;
    font-size: 18px;
    color: darkcyan;
    text-decoration: none;
    font-weight: bold;">
            I have an account</a>
    </div>
</@c.page>
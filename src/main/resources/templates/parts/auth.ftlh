<#macro auth path1 path2 button link header>
    <h4 style="color: white" class="mb-4">${header}</h4>
    <form action="${path1}" method="post" name="auth">
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="text" class="form-control" name="email" placeholder="Enter email"/>
            </div>
        </div>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="password" class="form-control" name="password" placeholder="Enter password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary mb-4">${button}</button>
    </form>
    <a href="${path2}" style="
            color: white;
            text-decoration: none;
            font-weight: 600;">
        ${link}
    </a>
</#macro>

<#macro logout>
    <form action="/logout" method="post" style=" text-align: right; padding-left: 20px" ;>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Sign out</button>
    </form>
</#macro>

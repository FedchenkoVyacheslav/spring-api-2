<#macro auth path1 path2 button link header isRegisterForm>
    <h4 style="color: white" class="mb-4">${header}</h4>
    <form action="${path1}" method="post" name="auth">
        <#if isRegisterForm>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="text" id="name-register" class="form-control" name="name" placeholder="Enter your name"/>
            </div>
        </div>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="text" id="surname-register" class="form-control" name="surname" placeholder="Enter your surname"/>
            </div>
        </div>
        </#if>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="text" class="form-control" name="email" placeholder="Enter your email"/>
            </div>
        </div>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="password" class="form-control" name="password" placeholder="Enter your password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-info mb-4">${button}</button>
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
        <button type="submit" class="btn btn-info">Sign out</button>
    </form>
</#macro>

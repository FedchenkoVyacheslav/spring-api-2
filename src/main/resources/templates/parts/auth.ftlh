<#macro auth path1 path2 button link header isRegisterForm>
    <h4 style="color: white" class="mb-4">${header}</h4>
    <form action="${path1}" method="post" <#if !isRegisterForm> name="auth"</#if>>
        <#if isRegisterForm>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="text" name="name" placeholder="Enter your name"
                       value="<#if user??>${user.name}</#if>"
                       class="form-control ${(nameError??)?string('is-invalid', '')}"/>
                <#if nameError??>
                    <div class="invalid-feedback">
                        ${nameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="text" name="surname" placeholder="Enter your surname"
                       value="<#if user??>${user.surname}</#if>"
                       class="form-control ${(surnameError??)?string('is-invalid', '')}"/>
                <#if surnameError??>
                    <div class="invalid-feedback">
                        ${surnameError}
                    </div>
                </#if>
            </div>
        </div>
        </#if>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="text" id="auth-email" name="email" placeholder="Enter your email"
                       value="<#if user??>${user.email}</#if>"
                       class="form-control ${(emailError??)?string('is-invalid', '')} ${(message??)?string('is-invalid', '')}"/>
                <#if message??>
                    <div class="invalid-feedback">
                        ${message}
                    </div>
                </#if>
                <#if emailError??>
                    <div class="invalid-feedback">
                        ${emailError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row mb-4">
            <div class="col-sm-4 mb-2">
                <input type="password" name="password" placeholder="Enter your password"
                       value="<#if user??>${user.password}</#if>"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="mb-4">
            <div class="g-recaptcha" data-sitekey="6LeF8aQpAAAAAEz-U17EM4iz79UJ-dCAa7aphn67"></div>
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

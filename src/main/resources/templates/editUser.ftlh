<#import "parts/common.ftlh" as c>
<@c.page>

    <form action="/user" method="post">
        <div class="form-row">
            <div class="form-group col-md-5 mr-5">
                <label for="edit-email">Set new email</label>
                <input type="text" id="edit-email" class="form-control" name="username" value="${user.username}"
                       placeholder="Enter email"/>
            </div>
            <div class="form-group col-md-6 mx-auto">
                <p class="mb-1">Set role</p>
                <#list roles as role>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox"
                               name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>
                        ${role}
                        <label class="form-check-label"></label>
                    </div>
                </#list>
            </div>
            <input type="hidden" name="identityId" value="${user.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" id="edit-btn" class="btn btn-primary ml-1 mt-2">Save changes</button>
            </div>
        </div>
    </form>

<#--    <p class="pl-3">Set new email</p>-->
<#--    <form action="/user" method="post">-->
<#--        <div style="clear:both; text-align:right; margin-bottom: 20px">-->
<#--            <div class="col-sm-4 mb-2">-->
<#--                <input type="text" class="form-control" name="username" value="${user.username}"-->
<#--                       placeholder="Enter email"/>-->
<#--            </div>-->
<#--        </div>-->
<#--        <p class="pl-3">Set new role</p>-->
<#--        <#list roles as role>-->
<#--            <div>-->
<#--                <label>-->
<#--                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>-->
<#--                    ${role}-->
<#--                </label>-->
<#--            </div>-->
<#--        </#list>-->
<#--        <input type="hidden" name="identityId" value="${user.id}"/>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--        <button type="submit" class="btn btn-primary ml-2 mt-2">Save changes</button>-->
<#--    </form>-->
</@c.page>
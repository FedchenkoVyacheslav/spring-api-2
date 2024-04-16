<#import "parts/common.ftlh" as c>
<@c.page>
    <form action="/admin" method="post" name="admin">
        <div class="form-row">
            <div class="form-group col-md-5 mr-5">
                <label for="edit-email">Set new email</label>
                <input type="text" id="edit-email" class="form-control" name="email" value="${user.email}"
                       placeholder="Enter email"/>
            </div>
            <div class="form-group col-md-6 mx-auto">
                <p class="mb-1">Set role</p>
                <#list roles as role>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="check-role"
                               name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>
                        ${role}
                        <label class="form-check-label"></label>
                    </div>
                </#list>
            </div>
            <input type="hidden" name="userId" value="${user.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" id="edit-btn" class="btn btn-info ml-1 mt-3">Save changes</button>
            </div>
        </div>
    </form>
</@c.page>
<#import "parts/common.ftlh" as c>
<@c.page>

    <form action="/user" method="post">
        <div class="form-row">
            <div class="form-group col-md-5 mr-5">
                <label for="edit-name">Name</label>
                <input type="text" id="edit-name" class="form-control" name="name" value="${user.name!""}"
                       placeholder="Enter your name"/>
            </div>
            <div class="form-group col-md-5 mr-5">
                <label for="edit-surname">Surname</label>
                <input type="text" id="edit-surname" class="form-control" name="surname" value="${user.surname!""}"
                       placeholder="Enter your surname"/>
            </div>
            <div class="form-group col-md-5 mr-5">
                <label for="edit-location">Location</label>
                <input type="text" id="edit-location" class="form-control" name="location" value="${user.location!""}"
                       placeholder="Enter your location"/>
            </div>
            <div class="form-group col-md-5 mr-5">
                <label for="edit-age">Age</label>
                <input type="text" id="edit-age" class="form-control" name="age" value="${user.age!""}"
                       placeholder="Enter your age"/>
            </div>

            <input type="hidden" name="userId" value="${user.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" id="edit-btn" class="btn btn-primary ml-1 mt-2">Save changes</button>
            </div>
        </div>
    </form>
</@c.page>
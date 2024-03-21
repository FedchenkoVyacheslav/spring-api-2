<#import "parts/common.ftlh" as c>
<@c.page>
    <div style="display: flex">
        <div class="profile-img-box card bg-secondary mr-5 mt-2" style="width: 60%">
            <#if user.photoUrl??>
                <img class="profile-img card-img-top" src="/img/${user.photoUrl!""}">
            <#else>
                <img class="profile-img card-img-top" src="../static/pic/no-img.png">
            </#if>
        </div>
        <form action="/user" method="post" enctype="multipart/form-data">
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
                    <input type="text" id="edit-location" class="form-control" name="location"
                           value="${user.location!""}"
                           placeholder="Enter your location"/>
                </div>
                <div class="form-group col-md-5 mr-5">
                    <label for="edit-age">Age</label>
                    <input type="text" id="edit-age" class="form-control" name="age" value="${user.age!""}"
                           placeholder="Enter your age"/>
                </div>
                <div class="form-group col-md-5 mr-5 mt-4">
                    <div class="custom-file">
                        <input type="file" id="edit-photo" name="file"/>
                        <label style="cursor: pointer;" class="custom-file-label" for="edit-photo">Choose
                            file...</label>
                    </div>
                </div>
                <input type="hidden" name="userId" value="${user.id}"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group mt-3">
                    <button type="submit" id="edit-btn" class="btn btn-info ml-1 mt-2">Save changes</button>
                </div>
            </div>
        </form>
    </div>
</@c.page>
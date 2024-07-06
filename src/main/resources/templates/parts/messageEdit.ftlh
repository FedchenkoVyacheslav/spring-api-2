<#macro edit buttonName>
    <div class="collapse <#if message??>show</#if>" id="messageForm">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" name="message">
                <div class="form-group mb-4">
                    <input type="text" id="message-title"
                           class="form-control ${(titleError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.title}</#if>" name="title"
                           placeholder="Add a message title"/>
                    <#if titleError??><div class="invalid-feedback">${titleError}</div></#if>
                </div>
                <div class="form-group mb-4">
                    <input type="text" id="message-text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text"
                           placeholder="Add message text"/>
                    <#if textError??><div class="invalid-feedback">${textError}</div></#if>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" id="formFile" name="file"/>
                        <label class="custom-file-label" for="formFile">Choose file...</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="<#if message??>${message.id!""}</#if>"/>
                <div class="form-group">
                    <button type="submit" id="message-btn" class="btn btn-info">${buttonName}
                    </button>
                </div>
            </form>
        </div>
    </div>
</#macro>
<#include "security.ftlh">

<#macro messageList errorMessage>
    <div>
        <div class="card-columns">
            <#list messages as message>
                <div class="card my-3">
                    <#if message.filename??>
                        <img class="card-img-top py-2 px-2" src="/img/${message.filename}">
                    <#else>
                        <img class="card-img-top" src="../static/pic/no-img.png">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${message.title}</h5>
                        <span class="card-text">${message.text}</span>
                    </div>
                    <div class="card-footer text-muted">
                        <div class="card-footer-image-box">
                            <#if message.authorAvatar??>
                                <img src="/img/${message.authorAvatar}">
                            <#else>
                                <img src="../static/pic/profile-empty.jpg">
                            </#if>
                        </div>
                        <div class="card-author">
                            <span>${message.authorName} ${message.authorSurname}</span>
                            <a class="user-messages"
                               href="/user-messages/${message.author.id}">${message.authorEmail}</a>
                            <p class="card-date">${message.updatedAt?datetime?string("dd.MM.yyyy HH:mm")}</p>
                            <#if message.author.id == currentUserId && springMacroRequestContext.requestUri?contains("/user-messages/")>
                                <a data-toggle="modal" data-target="#deleteModal" class="delete-message">
                                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                                         version="1.1" viewBox="0 0 256 256"
                                         xml:space="preserve">
                                        <defs></defs>
                                        <g style="stroke: none; stroke-width: 0; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: none; fill-rule: nonzero; opacity: 1;"
                                           transform="translate(1.4065934065934016 1.4065934065934016) scale(2.81 2.81)">
                                            <path d="M 64.71 90 H 25.291 c -4.693 0 -8.584 -3.67 -8.859 -8.355 l -3.928 -67.088 c -0.048 -0.825 0.246 -1.633 0.812 -2.234 c 0.567 -0.601 1.356 -0.941 2.183 -0.941 h 59.002 c 0.826 0 1.615 0.341 2.183 0.941 c 0.566 0.601 0.86 1.409 0.813 2.234 l -3.928 67.089 C 73.294 86.33 69.403 90 64.71 90 z M 18.679 17.381 l 3.743 63.913 C 22.51 82.812 23.771 84 25.291 84 H 64.71 c 1.52 0 2.779 -1.188 2.868 -2.705 l 3.742 -63.914 H 18.679 z"
                                                  style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(191,46,46); fill-rule: nonzero; opacity: 1;"
                                                  transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                                            <path d="M 80.696 17.381 H 9.304 c -1.657 0 -3 -1.343 -3 -3 s 1.343 -3 3 -3 h 71.393 c 1.657 0 3 1.343 3 3 S 82.354 17.381 80.696 17.381 z"
                                                  style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(191,46,46); fill-rule: nonzero; opacity: 1;"
                                                  transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                                            <path d="M 58.729 17.381 H 31.271 c -1.657 0 -3 -1.343 -3 -3 V 8.789 C 28.271 3.943 32.214 0 37.061 0 h 15.879 c 4.847 0 8.789 3.943 8.789 8.789 v 5.592 C 61.729 16.038 60.386 17.381 58.729 17.381 z M 34.271 11.381 h 21.457 V 8.789 C 55.729 7.251 54.478 6 52.939 6 H 37.061 c -1.538 0 -2.789 1.251 -2.789 2.789 V 11.381 z"
                                                  style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(191,46,46); fill-rule: nonzero; opacity: 1;"
                                                  transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                                            <path d="M 58.33 74.991 c -0.06 0 -0.118 -0.002 -0.179 -0.005 c -1.653 -0.097 -2.916 -1.517 -2.819 -3.171 l 2.474 -42.244 c 0.097 -1.655 1.508 -2.933 3.171 -2.819 c 1.653 0.097 2.916 1.516 2.819 3.17 l -2.474 42.245 C 61.229 73.761 59.906 74.991 58.33 74.991 z"
                                                  style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(191,46,46); fill-rule: nonzero; opacity: 1;"
                                                  transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                                            <path d="M 31.669 74.991 c -1.577 0 -2.898 -1.23 -2.992 -2.824 l -2.473 -42.245 c -0.097 -1.654 1.165 -3.073 2.819 -3.17 c 1.646 -0.111 3.073 1.165 3.17 2.819 l 2.473 42.244 c 0.097 1.654 -1.165 3.074 -2.819 3.171 C 31.788 74.989 31.729 74.991 31.669 74.991 z"
                                                  style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(191,46,46); fill-rule: nonzero; opacity: 1;"
                                                  transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                                            <path d="M 45 74.991 c -1.657 0 -3 -1.343 -3 -3 V 29.747 c 0 -1.657 1.343 -3 3 -3 c 1.657 0 3 1.343 3 3 v 42.244 C 48 73.648 46.657 74.991 45 74.991 z"
                                                  style="stroke: none; stroke-width: 1; stroke-dasharray: none; stroke-linecap: butt; stroke-linejoin: miter; stroke-miterlimit: 10; fill: rgb(191,46,46); fill-rule: nonzero; opacity: 1;"
                                                  transform=" matrix(1 0 0 1 0 0) " stroke-linecap="round"/>
                                        </g>
                                    </svg>
                                </a>
                                <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                                     aria-labelledby="deleteModal" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="deleteModal">Delete message</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <p class="modal-text">A you sure you want to delete this message?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Cancel
                                                </button>
                                                <a href="/del-user-messages/${message.author.id}?message=${message.id}"
                                                   type="button" class="btn btn-danger">Delete</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <a class="edit-message"
                                   href="/user-messages/${message.author.id}?message=${message.id}">
                                    <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg">
                                        <path d="M254.220457 716.393247h110.331678l452.512413-452.512413-107.789474-107.789474L254.220457 610.129096zM780.965243 84.401192l107.789474 107.789473a101.688183 101.688183 0 0 1 0 143.888779l-452.512413 452.512413a101.688183 101.688183 0 0 1-71.690169 29.998014H231.340616A76.266137 76.266137 0 0 1 152.532274 741.815293V610.129096a101.688183 101.688183 0 0 1 29.998014-71.690168l452.512413-452.512414a101.688183 101.688183 0 0 1 145.922542-1.525322zM105.247269 969.596822a50.844091 50.844091 0 1 1 0-101.688182h813.505462a50.844091 50.844091 0 0 1 0 101.688182z"
                                              fill="#666666"/>
                                    </svg>
                                </a>
                            </#if>
                        </div>
                    </div>
                </div>
            <#else>
                <div id="error-el2">${errorMessage}</div>
            </#list>
        </div>
    </div>
</#macro>
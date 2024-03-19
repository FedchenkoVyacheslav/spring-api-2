<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="greeting-container">
        <h1 class="greeting-title">Welcome!</h1>
        <h5 class="greeting-text">This is simple spring boot mvc App</h5>
    </div>
    <script>
        setTimeout(function(){ window.location="/main"; },2000);
    </script>
</@c.page>
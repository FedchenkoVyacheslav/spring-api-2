<#macro page>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Spring-web-app</title>
        <style>
            table {
                border: 1px solid #eee;
                table-layout: fixed;
                width: 100%;
                margin-bottom: 20px;
            }

            table th {
                font-weight: bold;
                padding: 5px;
                background: #efefef;
                border: 1px solid #dddddd;
            }

            table td {
                padding: 5px 10px;
                border: 1px solid #eee;
                text-align: left;
            }

            table tbody tr:nth-child(odd) {
                background: #fff;
            }

            table tbody tr:nth-child(even) {
                background: #F7F7F7;
            }
        </style>
    </head>
    <body style="background-color: lightblue;">
    <#nested>
    </body>
    <script type="text/javascript">
        let input = document.getElementById("search-input");
        let btn = document.getElementById("search-button");
        let el = document.getElementById("error-el");
        let el2 = document.getElementById("error-el2");

        function check() {
            if(input.value.length < 1){
                el.innerHTML = "Enter your search query..."
                el2.innerHTML = ""
            } else {
                el.innerHTML = ""
            }
        }

        input.addEventListener('input', check);

        check();
    </script>
    </html>
</#macro>
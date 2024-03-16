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

            .invalid-text, .valid-text {
                margin: 5px 0 5px 76px;
                position: absolute;
                font-size: 14px;
                line-height: 16px;
            }

            .invalid-text {
                color: crimson;
            }

            .valid-text {
                color: olivedrab;
            }

            .invalid-input {
                border-style: solid;
                border-color: crimson;
            }

            .valid-input {
                border-style: solid;
                border-color: olivedrab;
            }
        </style>
    </head>
    <body style="background-color: lightblue; padding: 50px">
    <#nested>
    </body>
    <script type="text/javascript">
        let input = document.getElementById("search-input");
        let btn = document.getElementById("search-button");
        let el = document.getElementById("error-el");
        let el2 = document.getElementById("error-el2");

        if (input !== null) {
            function check() {
                if (input.value.length < 1) {
                    el.innerHTML = "Enter your search query..."
                    el2.innerHTML = ""
                } else {
                    el.innerHTML = ""
                }
            }

            input.addEventListener('input', check);
            check();
        }

        function getValuesForm(form) {
            let body = {};
            const inputs = form.querySelectorAll("input");
            for (let i = 0; i < inputs.length; i++) {
                const input = inputs[i];
                body[input.name] = input.value;
            }
            return body;
        }

        function errorMessageCreater(input, text) {
            let message = document.createElement("div");
            message.classList.add("invalid-text");
            message.innerText = text;

            let nextMessage = input.nextElementSibling;
            if (nextMessage != null) {
                return;
            }

            input.insertAdjacentElement("afterend", message);
            input.addEventListener("input", function handlerInput(event) {
                message.remove();
                input.removeEventListener("input", handlerInput);
            });
        }

        function rightMessageCreater(input, text) {
            let message = document.createElement("div");
            message.classList.add("valid-text");
            message.innerText = 'All right';

            let nextMessage = input.nextElementSibling;
            if (nextMessage != null) {
                return;
            }

            input.insertAdjacentElement("afterend", message);
            input.addEventListener("input", function handlerInput(event) {
                message.remove();
                input.removeEventListener("input", handlerInput);
            });
        }

        function setInvalidInput(input) {
            input.classList.add("invalid-input");
            input.addEventListener("input", function handlerInput(event) {
                input.classList.remove("invalid-input");
                input.removeEventListener("input", handlerInput);
            });
        }

        function setValidInput(input) {
            input.classList.add("valid-input");
            input.addEventListener("input", function handlerInput(event) {
                input.classList.remove("valid-input");
                input.removeEventListener("input", handlerInput);
            });
        }

        function setFormErrors(form, errors) {
            const inputs = form.querySelectorAll("input");
            for (let i = 0; i < inputs.length; i++) {
                const input = inputs[i];
                if (errors[input.name]) {
                    setInvalidInput(input);
                    errorMessageCreater(input, errors[input.name]);
                } else {
                    setValidInput(input);
                    rightMessageCreater(input, errors[input.name]);
                }
            }
        }

        function mailCheck(field) {
            return field.match(/^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+))/i);
        }

        let formAuth = document.forms["auth"];
        if (formAuth !== undefined) {
            (function () {
                formAuth.onsubmit = (event) => {
                    event.preventDefault();
                    let isValid = true;
                    const form = event.target;
                    const values = getValuesForm(form);
                    let errors = {};

                    if (values.username === null || values.username === "") {
                        errors.username = "This field is required";
                        isValid = false;
                    } else if (!mailCheck(values.username)) {
                        errors.username = "Invalid email";
                        isValid = false;
                    }

                    if (values.password === null || values.password === "") {
                        errors.password = 'This field is required';
                        setFormErrors(form, errors);
                        isValid = false;
                    } else if (values.password.length < 4) {
                        errors.password = 'Too short password';
                        setFormErrors(form, errors);
                        isValid = false;
                    }

                    if (isValid) {
                        event.currentTarget.submit();
                    }
                }
            })();
        }
    </script>
    </html>
</#macro>
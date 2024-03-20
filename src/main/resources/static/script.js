let input = document.getElementById("search-input");
let el = document.getElementById("error-el");
let el2 = document.getElementById("error-el2");
let messageText = document.getElementById("message-text");
let messageTitle = document.getElementById("message-title");
let messageBtn = document.getElementById("message-btn");
let editEmail = document.getElementById("edit-email");
let editBtn = document.getElementById("edit-btn");

if (input !== null) {
    function check() {
        if (input.value.length < 1) {
            el.innerHTML = "Enter your search query..."
            if (el2 !== null) {
                el2.innerHTML = ""
            }
        } else {
            el.innerHTML = ""
        }
    }

    input.addEventListener('input', check);
    check();
}

if (editEmail !== null) {
    const check3 = () => editBtn.disabled =
        editEmail.value.trim(" ").length < 1;

    editEmail.addEventListener('input', check3);
    check3();
}

if (messageText !== null && messageTitle !== null) {
    const check2 = () => messageBtn.disabled =
        messageText.value.trim(" ").length < 1 || messageTitle.value.trim(" ").length < 1;

    messageText.addEventListener('input', check2);
    messageTitle.addEventListener('input', check2);
    check2();
}

$(document).ready(function () {

    $('[data-toggle="collapse"]').click(function () {
        $(this).toggleClass("active");
        if ($(this).hasClass("active")) {
            $(this).text("Hide message form");
        } else {
            $(this).text("Add new message");
        }
    });


// document ready
});


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
let input = document.getElementById("search-input");
let el = document.getElementById("error-el");
let el2 = document.getElementById("error-el2");
let editBtn = document.getElementById("edit-btn");
let nameReg = document.getElementById("name-register");
let surnameReg = document.getElementById("surname-register");
let alert = document.querySelector('.alert-danger');
let authEmail = document.getElementById("auth-email");
let checkboxes = document.querySelectorAll("input[type=checkbox][id=check-role]");

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

let enabledSettings = []
checkboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {
        enabledSettings =
            Array.from(checkboxes)
                .filter(i => i.checked)
                .map(i => i.value)
        editBtn.disabled = enabledSettings.length === 0;
    })
});

$(document).ready(function () {
    $('[data-toggle="collapse"]').click(function () {
        $(this).toggleClass("active");
        if ($(this).hasClass("active")) {
            $(this).text("Hide message form");
        } else {
            $(this).text("Add new message");
        }
    });

    $('input[type="file"]').change(function (e) {
        let fileName = e.target.files[0].name.substring(0, 20) + "...";
        $('.custom-file-label').html(fileName);
    });
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

function errorMessageCreator(input, text) {
    let message = document.createElement("div");
    message.classList.add("invalid-feedback");
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

function setInvalidInput(input) {
    input.classList.add("is-invalid");
    input.addEventListener("input", function handlerInput(event) {
        input.classList.remove("is-invalid");
        input.removeEventListener("input", handlerInput);
    });
}

function setFormErrors(form, errors) {
    const inputs = form.querySelectorAll("input");
    for (let i = 0; i < inputs.length; i++) {
        const input = inputs[i];
        if (errors[input.name]) {
            setInvalidInput(input);
            errorMessageCreator(input, errors[input.name]);
        }
    }
}

if (alert !== null) {
    authEmail.addEventListener('click', function () {
        alert.classList.add("hidden")
    });
}

function mailCheck(field) {
    return field.match(/^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+))/i);
}

let auth = document.forms["auth"];
if (auth !== undefined) {
    (function () {
        auth.onsubmit = (event) => {
            event.preventDefault();
            let isValid = true;
            const form = event.target;
            const values = getValuesForm(form);
            let errors = {};
            if (values.email === null || values.email === "") {
                errors.email = "This field is required";
                setFormErrors(form, errors);
                isValid = false;
            } else if (!mailCheck(values.email)) {
                errors.email = "Email is not correct";
                setFormErrors(form, errors);
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
            if (nameReg !== null && surnameReg !== null) {
                if (values.name === null || values.name === "") {
                    errors.name = 'This field is required';
                    setFormErrors(form, errors);
                    isValid = false;
                } else if (values.name.length < 3) {
                    errors.name = 'Too short name';
                    setFormErrors(form, errors);
                    isValid = false;
                }
                if (values.surname === null || values.surname === "") {
                    errors.surname = 'This field is required';
                    setFormErrors(form, errors);
                    isValid = false;
                } else if (values.surname.length < 3) {
                    errors.surname = 'Too short surname';
                    setFormErrors(form, errors);
                    isValid = false;
                }
            }
            if (isValid) {
                event.currentTarget.submit();
            }
        }
    })();
}

let profile = document.forms["profile"];
if (profile !== undefined) {
    (function () {
        profile.onsubmit = (event) => {
            event.preventDefault();
            let isValid = true;
            const form = event.target;
            const values = getValuesForm(form);
            let errors = {};
            if (values.name === null || values.name === "") {
                errors.name = 'This field is required';
                setFormErrors(form, errors);
                isValid = false;
            } else if (values.name.length < 3) {
                errors.name = 'Too short name';
                setFormErrors(form, errors);
                isValid = false;
            }
            if (values.surname === null || values.surname === "") {
                errors.surname = 'This field is required';
                setFormErrors(form, errors);
                isValid = false;
            } else if (values.surname.length < 3) {
                errors.surname = 'Too short surname';
                setFormErrors(form, errors);
                isValid = false;
            }
            if (isValid) {
                event.currentTarget.submit();
            }
        }
    })();
}

let admin = document.forms["admin"];
if (admin !== undefined) {
    (function () {
        admin.onsubmit = (event) => {
            event.preventDefault();
            let isValid = true;
            const form = event.target;
            const values = getValuesForm(form);
            let errors = {};
            if (values.email === null || values.email === "") {
                errors.email = "This field is required";
                setFormErrors(form, errors);
                isValid = false;
            } else if (!mailCheck(values.email)) {
                errors.email = "Email is not correct";
                setFormErrors(form, errors);
                isValid = false;
            }
            if (isValid) {
                event.currentTarget.submit();
            }
        }
    })();
}
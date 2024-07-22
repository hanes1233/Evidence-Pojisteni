
let isArticleDisplayed = false;

// Validate if all required form's fields are completed and display information if not
(function () {
    'use strict'
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')
    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    })
})();

// Get clicked value from HTML main page
document.addEventListener('click', function(e) {
    parseClickedValue(e.target.id);
}, false);

window.addEventListener("load", loadTitle, true);

//Dynamically change page's tittle
function loadTitle() {
    var title = document.getElementById('title').innerText;
    document.title = title;
}

function returnToMainPage() {
    window.location.href='http://localhost:8080';
}

// Redirect to registration form
function openRegistrationForm() {
    window.location.href='http://localhost:8080/registrace';
}

// Display description about insurrance
function displayArticle(articleToDisplay) {
    // Clear description's section if already filled with text
        if(isArticleDisplayed) {
            document.getElementById('insurranceDescription').style.opacity = '0';
            document.getElementById('insurranceDescription').textContent = '';
        }
    setTimeout(() => {
        // Fetch data from txt files
        fetch('txt/' + articleToDisplay + '.txt')
            .then((res) => res.text())
            .then((text) => {
            // Fill description's section with text
            document.getElementById('insurranceDescription').textContent = text;
            document.getElementById('insurranceDescription').classList.add("fade");
            document.getElementById('insurranceDescription').style.cssText = "margin-top: 5%; font-size: 20px; opacity: 1;";
            isArticleDisplayed = true;
        })
            .catch((e) => console.error(e));
    }, 150);
}

// Analyze clicked value to decide how to act with
function parseClickedValue(clickedValue) {
    switch (clickedValue) {
        case 'instagram':
            window.location.href='http://instagram.com';
            break;
        case 'facebook':
            window.location.href='http://facebook.com';
            break;
        case 'linkedin':
            window.location.href='http://linkedin.com';
            break;
        case 'healthdescription':
            displayArticle('lifeinsurrance');
            break;
        case 'traveldescription':
            displayArticle('traveldescription');
            break;
        case 'propertydescription':
            displayArticle('propertyinsurrance');
            break;
        case 'cardescription':
            displayArticle('carinsurrance');
            break;
        case 'petdescription':
            displayArticle('petinsurrance');
            break;
        case 'droppdown-car':
            setTypeOfInsurance('0')
            break;
        case 'dropdown-life':
            setTypeOfInsurance('1');
            break;
        case 'dropdown-pets':
            setTypeOfInsurance('2');
            break;
        case 'dropdown-property':
            setTypeOfInsurance('3');
            break;
        case 'dropdown-travel':
            setTypeOfInsurance('4');
            break;
        default: break;
    }
}

// Change pop-up login form's display condition to "block" / "none"
function changeFormCondition() {
    let formCondition = document.getElementById("login-form").style.display;
    document.getElementById("login-form").style.display = (formCondition == '' || formCondition == 'none') ? "block" : "none";
}

// 404 image animation
function animateNoFoundImage() {
    setInterval(() => {
        if(document.getElementById("404") == undefined) {
            clearInterval();
            console.log("Breaking up");
            return;
        }
        document.getElementById("404").style.opacity = '1';
        setTimeout(()=> {
            document.getElementById('404').style.opacity = "0";
        },500)
    },1000);
}

// Set default type value into insurance order by it's index
function setTypeOfInsurance(index) {
    document.getElementById('type').selectedIndex = index;
}

// Hide alert message if it has not any text
function hideAlertMessage() {
    let alert = document.getElementById('status-alert').textContent;
    if(alert == '' || alert == undefined) {
        let alertBox = document.getElementById('alert-box');
        let buttonBox = document.getElementById('button-box');
        document.getElementById('row-box').removeChild(alertBox);
        buttonBox.classList.remove('col-3');
        buttonBox.classList.add('text-center','mb-4');
    }
}

// Prevent submit form
function preventSubmit() {
    document.getElementById("confirm-delete").onsubmit = function(event) {
        event.preventDefault();
    }
}

// Submit form by its id
function submitForm(form) {
    document.getElementById(form).submit();
}

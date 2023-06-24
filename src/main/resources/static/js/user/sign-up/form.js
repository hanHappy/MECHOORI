
document.addEventListener('DOMContentLoaded', function () {
    let emailConfirmCode = '';

    // Function to send email confirmation request
    function sendEmailConfirmationRequest() {
        const email = document.getElementById('email').value;
        const formData = new FormData();
        formData.append('e', email);


        fetch('/api/sign-up/form', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams(formData),
        })
            .then(function (response) {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('이메일 전송에 실패했습니다.');
                }
            })
            .then(function (data) {
                emailConfirmCode = data;
                console.log('인증코드가 이메일로 전송되었습니다.');
            })
            .catch(function (error) {
                console.log(error.message);
            });
    }

    function checkEmailConfirmationCode() {
        const userCode = document.getElementById('memailconfirm').value;
        const memailconfirmTxt = document.getElementById('memailconfirmTxt');
        const emconfirmchk = document.getElementById('emconfirmchk');

        if (userCode !== emailConfirmCode) {
            memailconfirmTxt.innerHTML = '<span id="emconfirmchk">인증번호가 잘못되었습니다.</span>';
            emconfirmchk.style.color = '#FA3E3E';
            emconfirmchk.style.fontWeight = 'bold';
            emconfirmchk.style.fontSize = '10px';
            console.log('인증번호가 잘못되었습니다.');
        } else {
            memailconfirmTxt.innerHTML = '<span id="emconfirmchk">인증번호 확인 완료</span>';
            emconfirmchk.style.color = '#0D6EFD';
            emconfirmchk.style.fontWeight = 'bold';
            emconfirmchk.style.fontSize = '10px';
            console.log('인증번호 확인 완료');
        }
    }

    // Event handler for email confirmation button click
    document.getElementById('checkEmail').addEventListener('click', function() {
        sendEmailConfirmationRequest();
    });

    // Event handler for email confirmation code input
    document.getElementById('memailconfirm').addEventListener('keyup', function() {
        checkEmailConfirmationCode();
    });

    //////////////////////////////////////////
    let password = document.querySelector("#password");
    let password1 = document.querySelector("#password1");
    let passwordCheck = document.getElementById("passwordCheck");

    password1.oninput = function () {
        if (password.value !== password1.value) {
            passwordCheck.innerText = "일치하지 않습니다.";
            passwordCheck.style.color = "red";
        } else {
            passwordCheck.innerText = "일치합니다.";
            passwordCheck.style.color = "black";
        }
    };

    password1.onkeydown = function (e) {
        if (e.key === "Backspace") { // Backspace 키 입력 시 inner 글자 지움
            passwordCheck.innerText = "";
        }
    };
////////////////////////////
    let email = document.getElementById("email");
    let emailCheck = document.getElementById("emailCheck");
    let Epattern = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/;

    email.oninput = function () {
        if (!Epattern.test(email.value)) {
            emailCheck.innerText = "유효하지 않은 이메일입니다.";
            emailCheck.style.color = "red";
        } else {
            emailCheck.innerText = "유효한 이메일입니다.";
            emailCheck.style.color = "black";
        }
    };
/////////////////////////

    let phone = document.querySelector("#phone");
    let numberCheck = document.getElementById("numberCheck");
    let Ppattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

    phone.oninput = function () {
        let phoneNumber = phone.value.replace(/[^0-9]/g, ""); // 입력된 번호에서 숫자만 추출
        let formattedNumber = formatPhoneNumber(phoneNumber); // 하이픈이 추가된 형식으로 변환
        phone.value = formattedNumber; // 변환된 번호를 입력란에 설정

        if (!Ppattern.test(phoneNumber)) {
            numberCheck.innerText = "유효하지 않은 번호입니다.";
            numberCheck.style.color = "red";
        } else {
            numberCheck.innerText = "유효한 번호입니다.";
            numberCheck.style.color = "black";
        }
    };

    phone.onkeydown = function (e) {
        if (e.key === "Backspace") { // Backspace 키 입력 시 inner 글자 지움
            numberCheck.innerText = "";
        }
    };

    function formatPhoneNumber(number) {
        let phone = "";

        if (number.length < 4) {
            return number;
        } else if (number.length < 7) {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3);
        } else if (number.length < 11) {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3, 3);
            phone += "-";
            phone += number.substr(6);
        } else {
            phone += number.substr(0, 3);
            phone += "-";
            phone += number.substr(3, 4);
            phone += "-";
            phone += number.substr(7);
        }
        return phone;
    }

});
document.addEventListener('DOMContentLoaded', function () {
    let emailConfirmCode = '';
    let emailConfirmMsg = document.querySelector("#msg-email-check");

    // FIXME 이메일 유효성 검사 메시지 조건문 수정
    // 유효성 검사 메시지 색 통일

    // Function to send email confirmation request
    function sendEmailConfirmationRequest() {
        const email = document.getElementById('email').value;
        const formData = new FormData();
        formData.append('e', email);

        fetch('/api/sign-up/emailCheck', {
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

                if (data === "0") {

                    emailConfirmMsg.innerText = "사용 중인 계정이거나 형식이 올바르지 않습니다"
                    emailConfirmMsg.style.color = "red";
                } else {
                    //아이디 없을 때
                    emailConfirmCode = data;
                    emailConfirmMsg.innerText = `인증코드가 이메일로 전송되었습니다`
                }
            })
            .catch(function (error) {
                console.log(error.message);
            });
    }

    function nicknameCheck() {
        const nickname = document.getElementById('nickname').value
        const nicknameCheck = document.querySelector('#msg-nickname-check');
        const submitBtn = document.querySelector(".next");

        const formData = new FormData();
        formData.append('nickname', nickname);

        fetch('/api/sign-up/nicknameCheck', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams(formData),
        })
            .then(function (response) {
                if (response.ok) {
                    return response.text();
                }
            })
            .then(function (data) {

                if (data === "cantuse") {
                    nicknameCheck.style.color = "red";
                    nicknameCheck.innerText = "사용 중인 닉네임입니다";
                    submitBtn.disabled = true;
                } else {
                    nicknameCheck.style.color = "blue";
                    nicknameCheck.innerText = "사용할 수 있는 닉네임입니다";
                    submitBtn.disabled = false;

                }
            })
            .catch(function (error) {
                console.log(error.message);
            });
    }

    // 이메일 인증번호
    function checkEmailConfirmationCode() {
        const userCode = document.getElementById('email-confirm').value;
        const emailConfirmMsg = document.querySelector('#msg-email-confirm-result');
        const emconfirmchk = document.getElementById('emconfirmchk');
        const submitBtn = document.querySelector(".next");


        if (userCode !== emailConfirmCode) {
            emailConfirmMsg.innerHTML = '<span id="emconfirmchk">인증번호가 잘못되었습니다.</span>';
            emconfirmchk.style.color = '#FA3E3E';
            emconfirmchk.style.fontWeight = 'bold';
            emconfirmchk.style.fontSize = '10px';
            submitBtn.disabled = true;

        } else {
            emailConfirmMsg.innerHTML = '<span id="emconfirmchk">인증번호가 일치합니다</span>';
            emconfirmchk.style.color = '#0D6EFD';
            emconfirmchk.style.fontWeight = 'bold';
            emconfirmchk.style.fontSize = '10px';
            submitBtn.disabled = false;


        }
    }

    // Event handler for email confirmation button click
    document.querySelector('#btn-email-confirm-request').addEventListener('click', function () {
        sendEmailConfirmationRequest();
    });

    // 인증번호 확인 버튼 클릭 시 일치 여부 판별
    let emailConfirmNumCheckBtn = document.querySelector('#btn-confirm-num-check');
    emailConfirmNumCheckBtn.onclick = function(){
        checkEmailConfirmationCode();
    }

    // Event handler for email confirmation button click
    document.getElementById('nickname').addEventListener('input', function () {
        nicknameCheck();
    });

    //////////////////////////////////////////////////////////
    let password = document.querySelector("#password");
    let password1 = document.querySelector("#password-confirm");
    let passwordCheck = document.querySelector("#password-check");

    password1.oninput = function () {

        if (password.value !== password1.value) {
            passwordCheck.innerText = "일치하지 않습니다.";
            passwordCheck.style.color = "red";
            submitBtn.disabled = true;

        } else {
            passwordCheck.innerText = "일치합니다.";
            passwordCheck.style.color = "black";
            submitBtn.disabled = false;

        }
    };

    password1.onkeydown = function (e) {
        if (e.key === "Backspace") { // Backspace 키 입력 시 inner 글자 지움
            passwordCheck.innerText = "";
        }
    }

///////////////////////////////////////////////////////
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
});

/////////////////////////////////////////////////////////

const form = document.querySelector("#form__wrap");
const submitBtn = document.querySelector(".submit");

form.addEventListener("input", function () {
    checkAnswerCompletion();
});

function checkAnswerCompletion() {
    let userName = document.querySelector(".input1").value;
    let nickname = document.querySelector(".input2").value;
    let password = document.querySelector(".input3").value;
    let passwordCheck = document.querySelector(".input4").value;
    let email = document.querySelector(".input5").value;
    let emailConfirm = document.querySelector(".input9").value;
    if (
        userName !== "" &&
        nickname !== "" &&
        password !== "" &&
        passwordCheck !== "" &&
        email !== "" &&
        emailConfirm !== ""
    ) {
        submitBtn.disabled = false;
    } else {
        submitBtn.disabled = true;
    }

}


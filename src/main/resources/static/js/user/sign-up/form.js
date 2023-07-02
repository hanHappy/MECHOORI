document.addEventListener('DOMContentLoaded', function () {
    let emailConfirmCode = '';
    let emailConfirm = document.getElementById("emailCheck");

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

                    emailConfirm.innerText = `사용 중 이거나, 이메일 확인 후 다시 입력 해주세요`
                    emailConfirm.style.color = "red";
                } else {
                    //아이디 없을 때
                    emailConfirmCode = data;
                    emailConfirm.innerText = `인증코드가 이메일로 전송되었습니다`
                    console.log('인증코드가 이메일로 전송되었습니다.');
                }
            })
            .catch(function (error) {
                console.log(error.message);
            });
    }

    function nicknameCheck() {
        const nickname = document.getElementById('nickname').value
        const nicknameCheck = document.getElementById('check');
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
                    nicknameCheck.innerText = `닉네임이 이미 사용 중 입니다`
                    submitBtn.disabled = true;
                } else {
                    nicknameCheck.style.color = "blue";
                    nicknameCheck.innerText = `닉네임을 사용하실 수 있습니다.`
                    submitBtn.disabled = false;

                }
            })
            .catch(function (error) {
                console.log(error.message);
            });
    }


    function checkEmailConfirmationCode() {
        const userCode = document.getElementById('memailconfirm').value;
        const memailconfirmTxt = document.getElementById('memailconfirmTxt');
        const emconfirmchk = document.getElementById('emconfirmchk');
        const submitBtn = document.querySelector(".next");


        if (userCode !== emailConfirmCode) {
            memailconfirmTxt.innerHTML = '<span id="emconfirmchk">인증번호가 잘못되었습니다.</span>';
            emconfirmchk.style.color = '#FA3E3E';
            emconfirmchk.style.fontWeight = 'bold';
            emconfirmchk.style.fontSize = '10px';
            console.log('인증번호가 잘못되었습니다.');
            submitBtn.disabled = true;

        } else {
            memailconfirmTxt.innerHTML = '<span id="emconfirmchk">인증번호 확인 완료</span>';
            emconfirmchk.style.color = '#0D6EFD';
            emconfirmchk.style.fontWeight = 'bold';
            emconfirmchk.style.fontSize = '10px';
            console.log('인증번호 확인 완료');
            submitBtn.disabled = false;


        }
    }

    // Event handler for email confirmation button click
    document.getElementById('checkEmail').addEventListener('click', function () {
        sendEmailConfirmationRequest();
    });

    // Event handler for email confirmation code input
    document.getElementById('memailconfirm').addEventListener('keyup', function () {
        checkEmailConfirmationCode();
    });

    // Event handler for email confirmation button click
    document.getElementById('nickname').addEventListener('input', function () {
        nicknameCheck();
    });

    //////////////////////////////////////////////////////////
    let password = document.querySelector("#password");
    let password1 = document.querySelector("#password1");
    let passwordCheck = document.getElementById("passwordCheck");

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
const submitBtn = document.querySelector(".next");

form.addEventListener("input", function () {
    needFilmAll();
});

function needFilmAll() {
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
        console.log("false");
    } else {
        submitBtn.disabled = true;
        console.log("nope");
    }

}


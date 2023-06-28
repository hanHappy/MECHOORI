document.addEventListener("DOMContentLoaded", function () {

    let emailConfirmCode = "";
    let emailConfirm = document.getElementById("emailCheck");

    function sendEmailConfirmationRequest() {
        let email = document.querySelector("#emailText").value;
        let emailTxt = document.querySelector("#emailText");
        let formData = new FormData();

        formData.append("email", email);

        fetch('/api/find-pwd/emailCheck', {
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
                if (data === "cant") {
                    console.log(data);
                    emailConfirm.innerText = `이메일 확인 후 다시 입력 해주세요`;
                    emailConfirm.style.color = "red";
                } else {
                    emailConfirmCode = data;
                    console.log('인증코드가 이메일로 전송되었습니다.');
                    emailConfirm.innerText = `인증코드가 이메일로 전송되었습니다`;
                    console.log(emailConfirmCode);
                    emailTxt.readOnly = true;
                }
            })
            .catch(function (error) {
                console.log(error.message);
            });
    }

    function resetPassword(email) {
        let main = document.querySelector("#main");
        let formData = new FormData();

        formData.append("email", email);

        fetch('/api/find-pwd', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams(formData),
        })
            .then(function (response) {
                if(response.ok)
                    return response.text();
                else
                    throw new Error("전송 실패")
                // Check response status and handle accordingly
            })
            .then(function (data) {
                // Process the data returned by the server
                main.innerHTML = "";
                console.log(email);

                let itemTemplate = `
          <main>
          
            <form action="/user/login/find-pwd" method="POST">
              <input type="text" name="email" value="${email}" readonly>
              <label for="passwordReset">비밀번호 재설정</label>
              <input type="text" name="password" class="passwordReset" id="passwordReset" placeholder="비밀번호를 입력해주세요">
              <!-- 이메일 값 전달 -->
              <button type="submit" id="submitBtn">재설정 하기</button>
              
            </form>
            <label for="checkPasswordReset">비밀번호 재확인</label>
            <input type="text" class="checkPasswordReset" id="checkPasswordReset" placeholder="비밀번호를 다시 입력해주세요">
            
          </main>
        `;
                main.insertAdjacentHTML("beforeend", itemTemplate);
            })
            .catch(function (error) {
                console.log("Error fetching data:", error);
            });
    }

    function checkEmailConfirmationCode() {
        const userCode = document.getElementById('Certification').value;
        const memailconfirmTxt = document.getElementById('emailconfirmTxt');
        const emconfirmchk = document.getElementById('emconfirmchk');
        const submitBtn = document.querySelector(".nextBtn");

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

    document.querySelector(".emailConfirm").addEventListener("click", function () {
        sendEmailConfirmationRequest();
    });

    document.querySelector("#Certification").addEventListener("keyup", function () {
        checkEmailConfirmationCode();
    });

    function needFilmAll() {
        let email = document.querySelector(".emailText").value;
        let emailConfirm = document.querySelector(".emailconfirmTxt").value;
        let submitBtn = document.querySelector(".nextBtn");

        if (email !== "" && emailConfirm !== "") {
            submitBtn.disabled = false;
            console.log("false");
        } else {
            submitBtn.disabled = true;
            console.log("nope");
        }
    }

    // 이벤트 핸들러 등록
    document.querySelector(".nextBtn").addEventListener("click", function (e) {
        e.preventDefault();
        let email = document.querySelector("#emailText").value;
        resetPassword(email);
    });

    /*      document.querySelector(".passwordReset").addEventListener("keyup", function (e){

              e.preventDefault();

              let con = document.querySelector(".passwordReset").innerText;
             console.log(con)
       })*/

});

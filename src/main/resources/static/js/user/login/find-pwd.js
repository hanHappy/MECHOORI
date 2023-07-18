import ModalCheck from "../../components/modal-check.js"

let form = document.querySelector('#form-reset-pwd')
let emailInput = form.querySelector('#email')
let emailCodeInput = form.querySelector('#email-confirm-num')

let emailConfirmCode = ""
let emailConfirm = form.querySelector("#msg-email-check")

let resetPwdSection = form.querySelector('.reset-pwd')

function sendEmailConfirmationRequest() {
    let email = form.querySelector("#email").value
    let formData = new FormData()

    formData.append("email", email)

    fetch('/api/find-pwd/email-check', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(formData),
    })
        .then(function (response) {
            if (response.ok) {
                return response.text()
            } else {
                throw new Error('이메일 전송에 실패했습니다.')
            }
        })
        .then(function (data) {
            if (data === "cant") {
                emailConfirm.innerText = `이메일 확인 후 다시 입력 해주세요`
                emailConfirm.style.color = "#FA3E3E"
            } else {
                emailConfirmCode = data
                emailConfirm.innerText = `인증코드가 이메일로 전송되었습니다`
                emailConfirm.style.color = "#282828"
            }
        })
        .catch(function (error) {
            console.log(error.message)
        })
}

function checkEmailConfirmationCode() {
    const userCode = form.querySelector('#email-confirm-num').value
    const emailConfirmMsg = form.querySelector('#msg-email-confirm-result')
    const submitBtn = form.querySelector(".btn-save")

    if (userCode !== emailConfirmCode) {
        emailConfirmMsg.textContent = '인증번호가 잘못되었습니다'
        emailConfirmMsg.style.color = '#FA3E3E'
        emailConfirmMsg.style.fontWeight = 'bold'
        emailConfirmMsg.style.fontSize = '14px'
        submitBtn.disabled = true
    } else {
        emailConfirmMsg.textContent = '인증번호가 일치합니다'
        emailConfirmMsg.style.color = '#0D6EFD'
        emailConfirmMsg.style.fontWeight = 'bold'
        emailConfirmMsg.style.fontSize = '14px'
        resetPwdSection.classList.remove('d-none')
        emailInput.readOnly = true
        emailCodeInput.readOnly = true
        emailInput.style.color = '#9D9999'
        emailCodeInput.style.color = '#9D9999'
    }
}

form.querySelector("#btn-email-confirm-request").addEventListener("click", function () {
    sendEmailConfirmationRequest()
})

form.querySelector("#btn-confirm-num-check").addEventListener("click", function () {
    checkEmailConfirmationCode()
})

// ============================ 비밀번호 재설정 ============================
let newPwdInput = form.querySelector('#new-pwd')
let checkPwdInput = form.querySelector('#check-pwd')
let checkValidMsg = form.querySelector('#msg-check-valid')
let checkSameMsg = form.querySelector('#msg-check-same')
let saveBtn = form.querySelector('.btn-save')

newPwdInput.addEventListener('input', (e)=>{ checkValidMsg.classList.add('d-none') })

checkPwdInput.addEventListener('input', ()=>{
    let newPwd = newPwdInput.value
    let checkPwd = checkPwdInput.value
    let isNewPwdValid = false
    if(8 <= newPwd.length && newPwd.length <= 32)
        isNewPwdValid = true

    if(!isNewPwdValid)
        checkValidMsg.classList.remove('d-none')
    else
        checkValidMsg.classList.add('d-none')


    if(newPwd==checkPwd && isNewPwdValid){
        checkSameMsg.classList.remove('d-none')
        saveBtn.disabled = false
    }
    else{
        checkSameMsg.classList.add('d-none')
        saveBtn.disabled = true
    }
})
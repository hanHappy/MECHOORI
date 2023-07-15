let newPwdInput = document.querySelector('#new-pwd')
let checkPwdInput = document.querySelector('#check-pwd')
let checkMsg = document.querySelector('#msg-check')
let saveBtn = document.querySelector('.btn-save')

checkPwdInput.addEventListener('input', ()=>{
    let newPwd = newPwdInput.value
    let checkPwd = checkPwdInput.value;

    if(newPwd==checkPwd){
        checkMsg.style.color = '#2292F9'
        checkMsg.textContent = "비밀번호가 일치합니다"
        saveBtn.disabled = false
        saveBtn.classList.add('active')
    }
    else{
        checkMsg.textContent = ''
        saveBtn.disabled = true
        saveBtn.classList.remove('active')
    }
})

// 저장 PUT 요청
saveBtn.addEventListener('click', (e)=>{
    if(saveBtn.disabled)
        return

    let memberId = saveBtn.dataset.mid
    let newPwd = newPwdInput.value

    let formData = new FormData();
    formData.append("mid", memberId)
    formData.append("np", newPwd)

    fetch('/api/user/my-page/edit-info/pwd', {
        method: 'PUT',
        body: formData
    })
    .then(response => response.json())
    .then(member =>{
        if(!member.id){

        }
    })
    
})
let newPwdInput = document.querySelector('#new-pwd')
let checkPwdInput = document.querySelector('#check-pwd')
let checkValidMsg = document.querySelector('#msg-check-valid')
let checkSameMsg = document.querySelector('#msg-check-same')
let saveBtn = document.querySelector('.btn-save')
let modalScreen = document.querySelector('.screen')
let modalPanel = modalScreen.querySelector('.modal-panel')

newPwdInput.addEventListener('input', (e)=>{ checkValidMsg.classList.add('d-none') })

checkPwdInput.addEventListener('input', ()=>{
    let newPwd = newPwdInput.value
    let checkPwd = checkPwdInput.value;
    let isNewPwdValid = false
    if(8 <= newPwd.length && newPwd.length <= 32)
        isNewPwdValid = true;

    if(!isNewPwdValid)
        checkValidMsg.classList.remove('d-none')
    else
        checkValidMsg.classList.add('d-none')


    if(newPwd==checkPwd && isNewPwdValid){
        checkSameMsg.classList.remove('d-none')
        saveBtn.disabled = false
        saveBtn.classList.add('active')
    }
    else{
        checkSameMsg.classList.add('d-none')
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
        if(member.id){
            modalScreen.classList.remove('d-none')
            setTimeout(() => {
                modalPanel.classList.add('show')
            }, 50);
        }
    })
    
})
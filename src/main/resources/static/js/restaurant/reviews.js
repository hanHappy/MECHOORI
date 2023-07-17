import ModalCheck from "../components/modal-check.js"

let rateList = document.querySelector('.review-list')
let reviewItem = rateList.querySelector('.review')
let modalCheck = document.querySelector('#modal-check')
let modalPanel = modalCheck.querySelector('.modal-panel')
let noBtn = modalCheck.querySelector('#no')
let yesBtn = modalCheck.querySelector('#yes')
let id = null
let review = null
const modal = new ModalCheck();

rateList.addEventListener('click', (e)=>{
    if(e.target.tagName != 'BUTTON')
        return

    modal.show(modalCheck, modalPanel)

    let button = e.target
    id = button.dataset.id

    review = button.parentElement.parentElement
})

modalCheck.addEventListener('click', (e)=>{
    if(e.target == e.currentTarget){
        modalCheck.classList.add('d-none')
        modalPanel.classList.remove('show')
    }
})

noBtn.addEventListener('click', (e)=>{
    modalCheck.classList.add('d-none')
    modalPanel.classList.remove('show')
})

yesBtn.addEventListener('click', (e)=>{
    fetch(`/api/rate/${id}`, {
        method: 'DELETE'
    })
    .then(response => response.json())
    .then(result => {
        if(result == 1){
            modalCheck.classList.add('d-none')
            review.remove()
        }
    })
})

let noListFoundMsg = rateList.querySelector('.msg-no-list-wrap')
if(!reviewItem)
    noListFoundMsg.classList.remove('d-none')

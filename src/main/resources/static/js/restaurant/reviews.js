let rateList = document.querySelector('.review-list')
let modalCheck = document.querySelector('#modal-check')
let noBtn = modalCheck.querySelector('#no')
let yesBtn = modalCheck.querySelector('#yes')
let id = null
let review = null

rateList.addEventListener('click', (e)=>{
    if(e.target.tagName != 'BUTTON')
        return

    modalCheck.classList.remove('d-none')

    let button = e.target
    id = button.dataset.id

    review = button.parentElement.parentElement
})

modalCheck.addEventListener('click', (e)=>{
    if(e.target == e.currentTarget)
        modalCheck.classList.add('d-none')
})

noBtn.addEventListener('click', (e)=>{
    modalCheck.classList.add('d-none')
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
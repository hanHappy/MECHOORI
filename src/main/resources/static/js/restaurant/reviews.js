let rateList = document.querySelector('.review-list');
let modalCheck = document.querySelector('#modal-check')
let noBtn = modalCheck.querySelector('#no')
let yesBtn = modalCheck.querySelector('#yes')
let id = null; // 전역변수로 올림
let review = null;

rateList.addEventListener('click', (e)=>{
    console.log(e.target.tagName) // 확인

    if(e.target.tagName != 'BUTTON') // 버튼 아니면 리턴
        return

    modalCheck.classList.remove('d-none') // 모달 등장

    let button = e.target
    console.log(button.dataset.id) // 심어놓은 평가 아이디를 출력
    id = button.dataset.id


    console.log(button.parentElement.parentElement)
    review = button.parentElement.parentElement
})


modalCheck.addEventListener('click', (e)=>{  // 모달 외 범위 클릭시 모달이 꺼지는되는 것
    if(e.target == e.currentTarget)
        modalCheck.classList.add('d-none')
})



noBtn.addEventListener('click', (e)=>{  // no클릭시 모달 제거
    modalCheck.classList.add('d-none')
})

yesBtn.addEventListener('click', (e)=>{ // yse클릭시 패치 요청
    fetch(`/api/rate/${id}`, {
        method: 'DELETE'
    })
    .then(response => response.json())
    .then(result =>{
        console.log(result);
        if(result == 1){
            modalCheck.classList.add('d-none') // 삭제가 되었을 떄 모달 삭제
            review.remove() // 리뷰 삭제하는 것
        }
    })
})

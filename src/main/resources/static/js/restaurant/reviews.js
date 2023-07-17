let rateList = document.querySelector('.review-list')
let modalCheck = document.querySelector('#modal-check')
let noBtn = modalCheck.querySelector('#no')
let yesBtn = modalCheck.querySelector('#yes')
let id = null

rateList.addEventListener('click', (e)=>{
    if(e.target.tagName != 'BUTTON')
        return

    modalCheck.classList.remove('d-none')

    let button = e.target
    id = button.dataset.id
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
})



//
// function reviewListLoad(url){
//     fetch(url)
//         .then(response=>response.json())
//         .then(list=>{
//
//             for(let list of item){
//
//
//                 let Template =`
//                 <section class="review">
//                     <h1 class="d-none">리뷰</h1>
//
//                     <!-- 프로필 -->
//                     <div class="profile-wrap">
//                         <div class="profile-img-wrap">
//                             <img src="/images/member/profile/coca-analysis.svg"alt="">
//                         </div>
//                         <span>${r.nickname}</span>
//                         <span class="reg-date">${#temporals.format(r.regDate, 'yyyy.MM.dd')}</span>
//                         <button class="btn-delete">×</button>
//                     </div>
//
//                     <!-- 평가 이미지 -->
//                     <div class="img-wrap">
//                         <img src="/images/member/review/coca-full.svg" alt="">
//                     </div>
//
//                     <!-- 평가 정보 -->
//                     <div class="rate-info-wrap">
//                         <p>메뉴명 &nbsp;&nbsp;<span>${r.menuName}</span></p>
//                         <p>가격 &nbsp;&nbsp;<span>${r.price}</span></p>
//                         <p>평가 &nbsp;&nbsp;<span>${r.ratePrice}</span>&nbsp;(<span>${r.value}</span>%)</p>
//                     </div>
//
//                     <!-- 리뷰 -->
//                     <div class="review-wrap">
//                         <p>${r.review}</p>
//                     </div>
//
//                 </section>`;
//
//                     list.insertAdjacentHTML("beforeend", Template);
//             }
//         })
//         .catch((error) => {
//             console.log("Error fetching data:", error);
//         });
//
//     let modal = document.querySelector(".modal-panel")
//     let modalYes = document.querySelector(".button-8")
//
//     modalYes.addEventListener("click",function (){
//
//         let url = "http://localhost:10222/api/rate/{id}"
//         reviewListLoad(url);
//     })
//
//
//
// }
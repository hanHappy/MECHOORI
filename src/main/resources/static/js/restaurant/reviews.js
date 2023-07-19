let rateList = document.querySelector('.review-list')
let reviewItem = rateList.querySelector('.review')
let modalCheck = document.querySelector('#modal-check')
let noBtn = modalCheck.querySelector('#no')
let yesBtn = modalCheck.querySelector('#yes')
let id = null
let offset = 0;

let resId = rateList.dataset.resId


console.log(rateList.dataset)

console.log(offset);


rateList.addEventListener('click', (e) => {
    if (e.target.tagName != 'BUTTON')
        return

    modalCheck.classList.remove('d-none')

    let button = e.target
    id = button.dataset.id
})

modalCheck.addEventListener('click', (e) => {
    if (e.target == e.currentTarget)
        modalCheck.classList.add('d-none')
})

noBtn.addEventListener('click', (e) => {
    modalCheck.classList.add('d-none')
})

yesBtn.addEventListener('click', (e) => {

    fetch(`/api/rate/${id}`, {
        method: 'DELETE'
    })
})


function reviewListLoad(url) {
    fetch(url)
        .then(response => response.json())
        .then(list => {

            for (let r of list) {
                let RatedPrice = r.ratePrice.toLocaleString();
                let Template = `
              <section class="review">
                <h1 class="d-none">리뷰</h1>

                <!-- 프로필 -->
                <div class="profile-wrap">
                    <div class="profile-img-wrap">
                        <img src=${r.memberImg} alt="">
                    </div>
                    <span>${r.nickname}</span>
                    <button class="btn-delete">×</button>
                </div>

                <!-- 평가 이미지 -->
                <div class="img-wrap">
                    <img src=${r.reviewImg} alt="">
                </div>

                <!-- 평가 정보 -->
                <div class="rate-info-wrap">
                    <p>메뉴명 &nbsp;&nbsp;<span>${r.menuName}</span></p>
                    <p>가격 &nbsp;&nbsp;<span>${r.price}원</span></p>
                    <p>평가 &nbsp;&nbsp;<span>${RatedPrice}원</span>&nbsp;(<span>${r.value}</span>%)</p>
                </div>

                <!-- 리뷰 -->
                <div class="review-wrap">
                    <p>${r.review}</p>
                </div>

            </section>
              `;

                rateList.insertAdjacentHTML("beforeend", Template);
            }
        })
        .catch((error) => {
            console.log("Error fetching data:", error);
        });

}

window.addEventListener("scroll", function () {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop;
    let windowHeight = document.documentElement.clientHeight;

    if (scrollTop + windowHeight >= documentHeight) {
        // 스크롤이 맨 아래에 도달했을 때
        offset += 6;

        console.log(offset)

        let url = `http://192.168.0.67:8080/api/rate/${resId}/reviews?offset=${offset}`;
        console.log(url)
        reviewListLoad(url);
    }
})

let noListFoundMsg = rateList.querySelector('.msg-no-list-wrap')
if(!reviewItem)
    noListFoundMsg.classList.remove('d-none')

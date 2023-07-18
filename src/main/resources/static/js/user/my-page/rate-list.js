let rateList = document.querySelector('.rate-list');
let offset = 0;


console.log(offset)


rateList.addEventListener('click', (e)=>{
    if(e.target.className=='rate-list')
        return

    let target = e.target;
    
    while (target !== e.currentTarget) {
        target = target.parentNode;
        if(target.className == 'item')
            break;
    }

    target.addEventListener('click', flipper)
})

  function flipper (e) {
    const target = e.currentTarget
    target.style.transform = "rotateY(180deg)"
    target.addEventListener("click", innerFlipper)
  }

  function innerFlipper (e) {
    const target = e.currentTarget
    target.style.transform = "rotateY(0deg)"
    target.addEventListener("click", flipper)
    target.removeEventListener("click", innerFlipper)
  }

    function listLoad(url){
        fetch(url)
            .then((response) => response.json())
            .then((list) => {
                console.log(list)
                for (let item of list) {
                    let itemTemplate = `

              <section class="item">
          <div class="front">
            <img src="${item.img}" alt="">
            <h1><span>${item.resName}</span> · <span>${item.menuName}</span></h1>
            <p>가격</p>
            <p>${item.price}</p>
            <p>평가</p>
            <p><span>${item.ratePrice}</span>원 (<span>${item.value}</span>%)</p>
          </div>
          <div class="back">
            <div class="review-wrap">
              <!-- TODO 45자까지만 가져오기 -->
              <p class="review">${item.review}</p>
            </div>
            <a href="" class="to-detail">상세 페이지로 ></a>
          </div>
        </section>`;
                    rateList.insertAdjacentHTML("beforeend", itemTemplate);
                }
            })
            .catch((error) => {
                console.log(error);
            });
}

// 스크롤 이벤트 리스너 등록
window.addEventListener("scroll", function () {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop;
    let windowHeight = document.documentElement.clientHeight;

    if (scrollTop + windowHeight >= documentHeight) {
        offset += 6;

        console.log("offset", offset);
        let url = `http://localhost:8080/api/user/my-page/rate-list?offset=${offset}`;
        console.log(url)

        listLoad(url);
    }
});

let item = rateList.querySelector('.item');
let noListFoundMsg = rateList.querySelector('.msg-no-list-wrap')
if(!item)
    noListFoundMsg.classList.remove('d-none')


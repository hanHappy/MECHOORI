let rateList = document.querySelector('.rate-list');
let offset = 0;


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

// 스크롤 이벤트 리스너 등록
window.addEventListener("scroll", function () {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop;
    let windowHeight = document.documentElement.clientHeight;

    let foodList = document.querySelector(".foodList")

    let url = `http://localhost:8080/api/user/my-page/rate-list?offset=${offset}`;
    console.log(document.documentElement.scrollHeight);
    console.log(document.documentElement.clientHeight);
    console.log(document.documentElement.scrollTop);

    if (windowHeight + scrollTop + 200 >= documentHeight) {
        console.log(windowHeight)
        console.log(scrollTop)
        console.log(documentHeight)
        offset += 6;

        console.log("offset", offset);

        fetch(url)
            .then((response) => response.json())
            .then((list) => {
                console.log(list)
                for (let item of list) {
                    let itemTemplate = `
              <div class="adjImg1 adji">
                <img src="/images/foods/${item.img}" class="foodImg1 FI" alt="">
                <div class="Texts">
                  <p>${item.resName}</p>
                  <p>${item.menuName}</p>
                  <p>${item.price}</p>
                  <p>${item.ratePrice}</p>
                </div>
            </div>`;

                    foodList.insertAdjacentHTML("beforeend", itemTemplate);
                }
            })
            .catch((error) => {
                console.log(error);
            });
    }

});





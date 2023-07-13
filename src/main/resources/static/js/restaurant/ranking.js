let offset = 0;

let rankingNums = document.querySelectorAll(".rankingNum");
for(let i = 0; i<rankingNums.length; i++){
    rankingNums[i].innerText = i+1;
}



let rankingList = document.querySelector(".ranking-list-sections");
document.addEventListener("DOMContentLoaded", function () {


    function restaurantListLoad(url) {
        fetch(url)
            .then((response) => response.json())
            .then((list) => {
                // 아이템 채우기
                console.log(list);

                for (let item of list) {
                    let avgprice = item.avgPrice.toLocaleString();
                    let avgRatedPrice = item.avgRatedPrice.toLocaleString();
                    let itemTemplate = `
            
            <div>
            <a class="thymeleaf" href="/restaurant/${item.id}">

            <img src="/images/foods/${item.img}" class="img">
            <ul class="ranking-list">
                <li class="rankingNum"></li>
                <li>${item.name}</li>
                <li>
                     <span>${avgprice}</span><br/>
                      <span>(${avgRatedPrice})</span>
                </li>
                <li>${item.value}%</li>
            </ul>
        </a>
        </div>
        
          `;

                    rankingList.insertAdjacentHTML("beforeend", itemTemplate);
                }
            })
            .catch((error) => {
                console.log("Error fetching data:", error);
            });


    }

    let dropbox = document.getElementById("categoryDropBox");
    dropbox.onchange = function (e) {
        e.preventDefault();
        rankingList.innerHTML = "";
        offset = 0;




        let selectedIndex = dropbox.selectedIndex;
        let selectedOption = dropbox.options[selectedIndex];
        let value = selectedOption.dataset.id;
        console.log(value);

        if (typeof value === "undefined") {
            value = "";
        }

        if (e.target.innerText === "전체") {
            // 드롭박스가 변경되면 기존 내용을 삭제하고 새로운 데이터를 받아옵니다.

            categoryId = null;
            let url = "http://localhost:10222/api/restaurant/ranking";
            restaurantListLoad(url);
        } else {
            let url = `http://localhost:10222/api/restaurant/ranking?ctgId=${value}`;
            console.log(url);
            restaurantListLoad(url);
        }

        for(let i = 0; i<rankingNums.length; i++){
            rankingNums[i].innerText = i+1;
        }
    };

    // 스크롤 이벤트 리스너 등록
    window.addEventListener("scroll", function () {
        let documentHeight = document.documentElement.scrollHeight;
        let scrollTop = document.documentElement.scrollTop;
        let windowHeight = document.documentElement.clientHeight;


        let rankingNums = document.querySelectorAll(".rankingNum");
        for(let i = 6; i<rankingNums.length; i++){
            rankingNums[i].innerText = i+1;
        }


        if (scrollTop + windowHeight >= documentHeight) {
            // 스크롤이 맨 아래에 도달했을 때
            offset += 6;

            let dropbox = document.getElementById("categoryDropBox");
            let selectedIndex = dropbox.selectedIndex;
            let selectedOption = dropbox.options[selectedIndex];
            let value = selectedOption.dataset.id;
            console.log(value);
            if (typeof value === "undefined") {
                value = "";
            }

            let url = `http://localhost:10222/api/restaurant/ranking?ctgId=${value}&offset=${offset}`;

            console.log(url);
            restaurantListLoad(url);
        }
    });


    /*================================================================================*/



});

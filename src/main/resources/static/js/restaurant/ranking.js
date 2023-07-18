let offset = 0;

let rankingNums = document.querySelectorAll(".rankingNum");
for (let i = 0; i < rankingNums.length; i++) {
    rankingNums[i].innerText = i + 1;
}

let rankingList = document.querySelector(".ranking-list-sections");


function restaurantListLoad(url) {
    fetch(url)
        .then((response) => response.json())
        .then((list) => {
            // 아이템 채우기
            console.log(list);

            for (let i = 0; i < list.length; i++) {
                let avgprice = list[i].avgPrice.toLocaleString();
                let avgRatedPrice = list[i].avgRatedPrice.toLocaleString();

                let itemTemplate = `
            <div>
            <a class="thymleaf" href="/restaurant/${list[i].id}">
            <div class ="black-filter">
            <ul class="ranking-list">
                    <img src="/images/foods/${list[i].img}" class="img">
                <li class="rankingNum">${i + 1}</li>
                <li class="resto-name">${list[i].name}</li>
                <li>
                </li>
                <li class="value">
                     <span >${avgprice}</span>
                     <span >(${avgRatedPrice})</span>
                     &nbsp;<span class="valueText">${list[i].value}%</span>
                </li>
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
        let url = "http://localhost:8080/api/restaurant/ranking";
        restaurantListLoad(url);
    } else {
        let url = `http://localhost:8080/api/restaurant/ranking?ctgId=${value}`;
        console.log(url);
        restaurantListLoad(url);
    }
};

// 스크롤 이벤트 리스너 등록
window.addEventListener("scroll", function () {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop;
    let windowHeight = document.documentElement.clientHeight;


    let rankingNums = document.querySelectorAll(".rankingNum");
    for (let i = 6; i < rankingNums.length; i++) {
        rankingNums[i].innerText = i + 1;
    }


    if (scrollTop + windowHeight >= documentHeight) {
        // 스크롤이 맨 아래에 도달했을 때
        offset += 8;

        let dropbox = document.getElementById("categoryDropBox");
        let selectedIndex = dropbox.selectedIndex;
        let selectedOption = dropbox.options[selectedIndex];
        let value = selectedOption.dataset.id;
        console.log(value);
        if (typeof value === "undefined") {
            value = "";
        }

        let url = `http://localhost:8080/api/restaurant/ranking?ctgId=${value}&offset=${offset}`;

        console.log(url);
        restaurantListLoad(url);
    }
});


/*================================================================================*/
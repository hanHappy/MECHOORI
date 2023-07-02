document.addEventListener("DOMContentLoaded", function () {

    function restaurantListLoad(url) {
        let rankingList = document.querySelector(".ranking-list-sections");

        fetch(url)
            .then(response => response.json())
            .then(list => {
                // 방 비우기
                rankingList.innerHTML = "";
                // 아이템 채우기
                console.log(list);

                for (let item of list) {
                    let avgprice = item.avgPrice.toLocaleString();
                    let avgRatedPrice = item.avgRatedPrice.toLocaleString();
                    let itemTemplate = `
         <section class="thymleaf">
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
        </section>
      `;

                    rankingList.insertAdjacentHTML("beforeend", itemTemplate);
                }
            })
            .catch(error => {
                console.log("Error fetching data:", error);

            });
    }

    let dropbox = document.getElementById("categoryDropBox");
    dropbox.onchange = function (e) {
        e.preventDefault();

        let selectedIndex = dropbox.selectedIndex;
        let selectedOption = dropbox.options[selectedIndex];
        let value = selectedOption.dataset.id;
        console.log(value);

        let url = `http://localhost:10222/api/restaurant/ranking?ctgId=${value}`;
        console.log(url); //작동 o
        restaurantListLoad(url);
    }

    function moreInfo() {
        let list = document.querySelector(".ranking-list-sections");
        let infos = document.createElement("moreInfos");

        let screenHeight = screen.height;
        let oneTime = false;

        document.addEventListener("scroll", onscroll, {passive: true});

        function onscroll() {
            let height = document.documentElement.offsetHeight;
            let scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            let clientHeight = document.documentElement.clientHeight;

            if (scrollTop + clientHeight >= height && !oneTime) {
                oneTime = true;
                // 페이지 추가 로직 작성
            }
        }
    }



})




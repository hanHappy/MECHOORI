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
                    let itemTemplate = `
        
         <section >
            <img src="/images/foods/${item.img}" class="img">


            <ul class="ranking-list">
                <li class="rankingNum">${item.rank}</li>
                <li>${item.name}</li>
                <li>
                    <span>${item.avgPrice}</span><br/>
                    <span>(${item.avgRatedPrice})</span>
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
        restaurantListLoad(url);
    }


})

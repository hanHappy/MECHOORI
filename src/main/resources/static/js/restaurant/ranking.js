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
        if (typeof value === "undefined") {
            value = ""; //
        }

        if (e.target.innerText === '전체') {
            categoryId = null;
            let url = "http://localhost:10222/api/restaurant/ranking";
            restaurantListLoad(url);
        } else {
            let url = `http://localhost:10222/api/restaurant/ranking?ctgId=${value}`;
            console.log(url); //작동 o
            restaurantListLoad(url);
        }
    }


    document.querySelector(".moreInfo").addEventListener("click", function (e) {
        // let rankingList = document.querySelector(".ranking-list-sections");
        //
        //
        // fetch(url)
        //     .then(response => response.json())
        //     .then(list => {
        //         rankingList.innerHTML = ""

        //         for (let item of list) {
        //             let avgprice = item.avgPrice.toLocaleString();
        //             let avgRatedPrice = item.avgRatedPrice.toLocaleString();
        //             let itemTemplate = `
        //             <section class="thymleaf">
        //                 <img src="/images/foods/${item.img}" class="img">
        //                     <ul class="ranking-list">
        //                         <li class="rankingNum"></li>
        //                         <li>${item.name}</li>
        //                         <li>
        //                             <span>${avgprice}</span><br/>
        //                             <span>(${avgRatedPrice})</span>
        //                         </li>
        //                         <li>${item.value}%</li>
        //                     </ul>
        //             </section>
        //                 <button type="submit" class = "moreInfo">
        //                     더보기
        //                 </button>
        //                 `;
        //
        //             rankingList.insertAdjacentHTML("beforeend", itemTemplate);
        //         }
        //     })
        //         .catch(error => {
        //             console.log("Error fetching data:", error);
        //
        //         })
        // })





    })

})

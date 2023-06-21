function restaurantListLoad(url) {
    let rankingSection = document.querySelector(".ranking-list-sections");
    let rankingList = rankingSection.querySelector(".ranking-list");



    fetch(url)
        .then(response => response.json())
        .then(list => {

            if(list === 한식)



            // 방 비우기
            rankingList.innerHTML = "";
            console.log("click");
            //  // 아이템 채우기
            for (let l of list) {
                let itemTemplate =
                `<section class= "ranking-list-sections">
             <div>
                <div class = "position"></div>
                 <div class="sec">
            <p>1</p>
            <div class = "img1 img"></div>
            <p>${l.name}</p> <br>
            <p>${l.price}</p>
            <span style="color: blue;"> <p>${l.cumulativeRatedPrice}</p></span>
                <p> 133%</p>
            </div>
              </div>
            </section>`;

                rankingList.insertAdjacentHTML("beforeend", itemTemplate);
            }
        });
}


    let dropbox = document.getElementById("categoryDropBox")
    dropbox.onchange = function (e){

        e.preventDefault()

        // let categoryValue = e.target.value;
        console.log("click");




        // 랭킹 목록 로드
        restaurantListLoad(url);

        if(e.target.innerText ==="한식")
        let url = 'http://localhost:8080/api/restaurant/ranking?ctgId=${e.target.dataset.ctgId}';

    }



function restaurantListLoad(url) {
    let rankingSection = document.querySelector(".ranking-list-sections");
    let rankingList = rankingSection.querySelector(".ranking-list");



    fetch(url)
        .then(response => response.json())
        .then(list => {
            //.then(menu=>menu.name);

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

let dropbox = document.querySelector(".category")

dropbox.onchange =(e)=>{

    e.preventDefault()

    let categoryValue = e.target.value;
    let url = `/api/ranking?category=${categoryValue}`;
    console.log(url);

    console.log("click");
    // 랭킹 목록 로드
    restaurantListLoad(url);

}


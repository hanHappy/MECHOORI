function restaurantListLoad(url) {
    let rankingList = document.querySelector(".ranking-list-sections");

    fetch(url)
        .then(response => response.json())
        .then(list => {
            // 방 비우기
            rankingList.innerHTML = "";
            // 아이템 채우기
            console.log(list);

            for (let l of list) {
                let itemTemplate =
                    `
          <div>
            <div class = "position"></div>
              <div class="ranking-list">
                <p>1</p>
                <div class = "img1 img"></div>
                <span>${l.name}</span><span>${l.avgPrice}</span><span style="color: blue;"> ${l.avgRatedPrice}</span>
                <p>${l.value}</p>
            </div>
          </div>`;



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

        let url = `http://localhost:8080/api/restaurant/ranking?ctgId=${value}`;
        restaurantListLoad(url);
}

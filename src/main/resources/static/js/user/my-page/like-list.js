// console.log("ㅇㄹ");
let likeList = document.querySelector(".like-list");


//dom이 잘 왔는지 확인
// likeList.addEventListener("click", function(){
    // console.log("ㅇㄹ");
// })


likeList.addEventListener("click", function(e){
    // console.log(e.target.tagName);  버튼 인지 색별하는 과정
    if(e.target.tagName !== 'BUTTON')
    return;
    // console.log("cccccccc");
    //태그에서 데이터를 담는다 e.target이 buotton
    let restaurantId = e.target.dataset.restaurantId;
    let memberId = e.target.dataset.memberId;
    // console.log(restaurantId);
    // console.log(memberId);


    let formData = new FormData();
    formData.append("restaurantId", restaurantId);
    formData.append("memberId", memberId);

    fetch("/api/user/my-page/like-list", {
        method:"DELETE",
        body: formData
    })
    .then(response => response.json())
    .then(result => {
        // console.log(result);
        
        // 사라지는 효과
        if(result == 1){
            e.target.classList.add('vanish');
            setTimeout(()=>{
                e.target.parentElement.remove();
            }, 420);
        }

    })
    
});

// let likeBtn = 
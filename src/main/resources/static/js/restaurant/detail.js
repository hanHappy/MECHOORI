let likeBtn = document.querySelector('.like');

likeBtn.onclick = function(e){

    let {restaurantId, memberId} = likeBtn.dataset;

    // 회원 아니면 return
    if(memberId===undefined){
        return;
    }
    e.preventDefault();

    let likeCount = document.querySelector("#like-count");
    
    if(!likeBtn.classList.contains("active")){
        let data = {memberId, restaurantId};
        let jsonData = JSON.stringify(data);

        fetch("/api/restaurantlike",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: jsonData
            })
        .then(response=>response.json())
        .then(result=>{
            if(result == 1){
                // 하트 불 밝히기
                likeBtn.classList.add("active");
                
                fetch(`/api/restaurantlike/count?rid=${restaurantId}`)
                .then(response=>response.json())
                .then(count=>{
                    likeCount.textContent = count;
                });
            }
        });
    }

    // Like 삭제
    else {
        fetch(`/api/restaurantlike/${restaurantId}/members/${memberId}`, {
            method:"DELETE",
        })
        .then(response=>response.json())
        .then(result=>{
            if(result == 1){
                // 하트 불 끄기
                likeBtn.classList.remove("active");
                                    
                fetch(`/api/restaurantlike/count?rid=${restaurantId}`)
                .then(response=>response.json())
                .then(count=>{
                    likeCount.innerText = count;
                });
            }
        });
    }

}
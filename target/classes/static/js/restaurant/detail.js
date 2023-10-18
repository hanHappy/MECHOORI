let likeBtn = document.querySelector('.like');
let copyButton = document.querySelector('#map-address-copy-button');
let addressText = document.querySelector('#map-address-copy-text');
let modalCopy = document.getElementById('modal-copy');
let shareBtn = document.querySelector('.share')

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

// 주소 복사
copyButton.addEventListener('click', () => {
    console.log("c");
    const address = addressText.textContent;
    navigator.clipboard.writeText(address)
        .then(() => {
            modalCopy.style.display = 'block';
            setTimeout(() => {
                modalCopy.style.display = 'none';
            }, 1000);
        })
});

// 공유하기
shareBtn.onclick = function(e){
    const shareObject = {
        title: window.name,
        url: window.location.href
    };

    if (navigator.share) {
        navigator
        .share(shareObject)
        .then(() => {
        })
        .catch((error) => {
            alert('error: 다시 시도해주세요.')
        })
    } else {
        alert('error: 미지원 브라우저입니다.')
    }
    }    


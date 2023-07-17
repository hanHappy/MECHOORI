let likeBtn = document.querySelector('.like');
let copyButton = document.getElementById('map-address-copy-button');
let addressText = document.getElementById('map-address-copy-text');
let modalCopy = document.getElementById('modal-copy');

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
    const address = addressText.textContent;
    navigator.clipboard.writeText(address)
        .then(() => {
            modalCopy.style.display = 'block';
            setTimeout(() => {
                modalCopy.style.display = 'none';
            }, 1000);
        })
});

// 전화하기 버튼
// document.addEventListener('DOMContentLoaded', function () {
//     const btn = document.getElementById('popupBtn');
//     const modal = document.getElementById('modalWrap');
//     const closeBtn = document.getElementById('closeBtn');
//     const callBtn = document.getElementById('callBtn');

//     modal.style.display = 'none';

//     btn.onclick = function () {
//         modal.style.display = 'block';
//     }
//     closeBtn.onclick = function () {
//         modal.style.display = 'none'; // 모달 닫기
//     }
//     callBtn.onclick = function () {
//         modal.style.display = 'none'; // 모달 닫기
//     }

//     window.onclick = function (event) {
//         if (event.target == modal) {
//             modal.style.display = 'none'; // 모달 외부
//         }
//     }
// });
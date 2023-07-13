let likeList = document.querySelector('.like-list');
let item = likeList.querySelector('.item');
let noListFoundMsg = likeList.querySelector('.msg-no-list-wrap');
console.log(item);
if(item === null)
    noListFoundMsg.classList.remove('d-none');
    

likeList.addEventListener("click", function (e) {
    if (e.target.tagName !== 'BUTTON')
        return;

    let restaurantId = e.target.dataset.restaurantId;
    let memberId = e.target.dataset.memberId;

    let formData = new FormData();
    formData.append("restaurantId", restaurantId);
    formData.append("memberId", memberId);

    fetch("/api/user/my-page/like-list", {
        method: 'DELETE',
        body: formData
    })
        .then(response => response.json())
        .then(result => {
            if (result == 1) {
                // 카드 사라지는 효과 ==================
                e.target.classList.add('vanish');
                setTimeout(() => {
                    e.target.parentElement.remove();
                    let item_ = likeList.querySelector('.item');
                    if(item_ === null)
                        noListFoundMsg.classList.remove('d-none');
                }, 420);
                // -----------------------------------
            }
        });
});
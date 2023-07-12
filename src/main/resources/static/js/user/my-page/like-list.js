let likeList = document.querySelector('.like-list');

likeList.addEventListener("click",function(e){
    // let likeBtn = document.querySelector('.like');
    // if(!likeBtn)
    //     return;

    
    if(e.target.tagName !== 'BUTTON')
    return;
    
    let restaurantId = e.target.dataset.restaurantId;
    let memberId = e.target.dataset.memberId;
    let formData = new FormData();
    formData.append("restaurantId", restaurantId);
    formData.append("memberId", memberId);
        
    fetch("/api/user/my-page/like-list",{
        method: "DELETE",
        body: formData
    })
    .then(response =>response.json())
    .then(result =>{
        if(result ==1){
            e.target.classList.add('vanish');
            setTimeout(() => {
                e.target.parentElement.remove();
            }, 420);
        }
    })
})
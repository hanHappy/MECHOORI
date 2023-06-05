window.addEventListener('load', function () {

    let likeBtn = this.document.querySelectorAll(".like");

    let likeControl = function (e) {
        let isLiked = !(e.target.classList.contains("inactive"));
        if (isLiked)
            e.target.classList.add("inactive");
        else
            e.target.classList.remove("inactive");
    }

    for(let i = 0; i < likeBtn.length; i++)
        likeBtn[i].onclick = likeControl;

})
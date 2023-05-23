window.addEventListener('load', function(){
    const emptyHeart = this.document.querySelectorAll('.empty-heart');
    const heart = this.document.querySelectorAll('.heart');

    let like = function(e){
        let index = Array.from(emptyHeart).indexOf(e.target);
        emptyHeart[index].style.display = 'none';
        heart[index].style.display = 'block';
    }
    
    function likeCancle(e){
        let index = Array.from(heart).indexOf(e.target);
        emptyHeart[index].style.display = 'block';
        heart[index].style.display = 'none';
    }

    

    for(let i = 0; i < heart.length; i++){
        emptyHeart[i].onclick = like;
        heart[i].onclick = likeCancle;
    }
})
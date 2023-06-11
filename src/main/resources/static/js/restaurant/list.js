let likeBtns = this.document.querySelectorAll('.like');
let tags = this.document.querySelectorAll('.category-tag');
let categoryBtns = document.querySelectorAll('.rs-menu');
let otherCategories = document.querySelector('.category.others');
let categoryOthersContainer = document.querySelector('.category-others-container');

let likeControl = function (e) {
    let isLiked = e.target.classList.contains("active");
    if (isLiked)
        e.target.classList.remove('active');
    else
        e.target.classList.add('active');
}

let tagControl = function (e){
    let selected = e.target.classList.contains("active");
    if(selected)
        e.target.classList.remove('active');
    else
        e.target.classList.add('active');
}

let categoryControl = function(e){
    let isOthers = e.target.classList.contains("others");
    if(isOthers)
        categoryOthersContainer.classList.add('slide-open');
    else
        categoryOthersContainer.classList.remove('slide-open');
}

for (let btn of likeBtns)
    btn.onclick = likeControl;

for (let tag of tags)
    tag.onclick = tagControl;

for(let btn of categoryBtns)
    btn.onclick = categoryControl;
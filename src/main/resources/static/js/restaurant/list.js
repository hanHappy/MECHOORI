let likeBtns = this.document.querySelectorAll('.like');
let categoryBtns = document.querySelectorAll('.rs-menu');
let categoryOthersContainer = document.querySelector('.category-others-container');
let otherCategories = categoryOthersContainer.querySelector('.others');
let tagArea = document.querySelector('.category.others');

let likeControl = function (e) {
    let isLiked = e.target.classList.contains("active");
    if (isLiked)
        e.target.classList.remove('active');
    else
        e.target.classList.add('active');
}

tagArea.onclick = function (e) {
    if(e.target.tagName !== 'A')
        return;
    let activeTag = otherCategories.querySelector('.active');
    if (activeTag != null)
        activeTag.classList.remove('active');
    e.target.classList.add('active');
};

let categoryControl = function (e) {
    let isOthers = e.target.classList.contains("others");
    if (isOthers)
        categoryOthersContainer.classList.add('slide-open');
    else
        categoryOthersContainer.classList.remove('slide-open');
}
// TODO : 수정 (영역 전체에 click event 적용 후 prevent default - event bubbling 막기)

for (let btn of likeBtns)
    btn.onclick = likeControl;

for (let btn of categoryBtns)
    btn.onclick = categoryControl;
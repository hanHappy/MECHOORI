let likeBtns = this.document.querySelectorAll('.like');
let topCategoryNav = document.querySelector('.top-category-nav');
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
for (let btn of likeBtns)
    btn.onclick = likeControl;

tagArea.onclick = function (e) {
    e.preventDefault();
    if(e.target.tagName !== 'BUTTON')
        return;
    let activeTag = otherCategories.querySelector('.active');
    if (activeTag != null)
        activeTag.classList.remove('active');
    e.target.classList.add('active');
};

topCategoryNav.onclick = function(e){
    e.preventDefault();
    if(e.target.tagName !== 'A')
        return;
    
    if(e.target.innerText == '기타')
        categoryOthersContainer.classList.add('slide-open');
    else
        categoryOthersContainer.classList.remove('slide-open');
};
let likeBtns = this.document.querySelectorAll('.like');
let topCategoryNav = document.querySelector('.top-category-nav');
let categoryOthersContainer = document.querySelector('.category-others-container');
let otherCategories = categoryOthersContainer.querySelector('.others');
let tagArea = document.querySelector('.category.others');
let header = document.querySelector('header');
let headerSide = header.querySelectorAll('.header-side');
let logo = header.querySelector('header>a');
let searchContainer = header.querySelector('.search-container');

header.onclick = function(e){
    if(!e.target.className.includes('search')){
        return;
    }
    headerSide[0].style.display = 'none';
    headerSide[1].style.display = 'none';
    logo.style.display = 'none';
    searchContainer.classList.remove('d-none');
}

// 좋아요 버튼
let likeControl = function (e) {
    let isLiked = e.target.classList.contains("active");
    if (isLiked)
        e.target.classList.remove('active');
    else
        e.target.classList.add('active');
}
for (let btn of likeBtns)
    btn.onclick = likeControl;

// 기타 태그 영역
tagArea.onclick = function (e) {
    e.preventDefault();
    if(e.target.tagName !== 'BUTTON')
        return;
    let activeTag = otherCategories.querySelector('.active');
    if (activeTag != null)
        activeTag.classList.remove('active');
    e.target.classList.add('active');
};

// Top Category 영역
topCategoryNav.onclick = function(e){
    e.preventDefault();
    if(e.target.tagName !== 'A')
        return;
    
    if(e.target.innerText == '기타')
        categoryOthersContainer.classList.add('slide-open');
    else
        categoryOthersContainer.classList.remove('slide-open');
};
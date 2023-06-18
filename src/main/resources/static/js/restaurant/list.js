let header = document.querySelector('header');
let logo = header.querySelector('.logo');
let headerSide = header.querySelectorAll('.header-side');
let searchContainer = header.querySelector('.search-container');
let topCategorySection = header.querySelector('.top-category-section');
let otherCategorySection = header.querySelector('.other-category-section');
let otherCategories = otherCategorySection.querySelector('.others');
let tagArea = otherCategorySection.querySelector('.category.others');
let likeBtns = this.document.querySelectorAll('.like');

// Header 돋보기 버튼 클릭 -> input 나타나게
header.onclick = function(e){
    if(!e.target.className.includes('search')){
        return;
    }
    headerSide[0].style.display = 'none';
    headerSide[1].style.display = 'none';
    logo.style.display = 'none';
    searchContainer.classList.remove('d-none');
}

// Top Category 영역
topCategorySection.onclick = function(e){
    e.preventDefault();
    if(e.target.tagName !== 'A')
        return;
    
    if(e.target.innerText == '기타')
        otherCategorySection.classList.add('slide-open');
    else
        otherCategorySection.classList.remove('slide-open');
};

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
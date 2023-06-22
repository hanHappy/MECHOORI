const header = document.querySelector('header');
const logo = header.querySelector('.logo');
const headerSide = header.querySelectorAll('.header-side');
const searchContainer = header.querySelector('.search-container');
const topCategorySection = header.querySelector('.top-category-section');
const otherCategorySection = header.querySelector('.other-category-section');
const otherCategories = otherCategorySection.querySelector('.others');
const tagArea = otherCategorySection.querySelector('.category.others');
const likeBtns = this.document.querySelectorAll('.like');
const searchBar = header.querySelector('#search-bar');
const searchBtn = header.querySelector('.search-btn');

// index 검색어
let searchParam = new URLSearchParams(window.location.search);
const query = searchParam.get('q');

// 검색바 보이기()
function showSearchBar(){
    headerSide[0].style.display = 'none';
    headerSide[1].style.display = 'none';
    logo.style.display = 'none';
    searchContainer.classList.remove('d-none');
}

// Header 돋보기 버튼 클릭 -> 검색바 나타나기
header.onclick = function(e){
    let isSearchBtn = e.target.className.includes('search');
    if(!isSearchBtn)
        return;
    showSearchBar();
}
// index에서 검색으로 넘어왔다면 검색바+키워드 보여주기
if(query != null){
    // 검색바에 검색 키워드 남겨놓기
    searchBar.value = query;
    // URLSearchParams 인스턴스 초기화 안 해주면 
    // index 페이지든 list 페이지든 검색바랑 키워드 계속 떠있음
    // => 검색바에 키워드 남아있지 않도록 초기화
    searchParam = null;
    showSearchBar();
}

function restaurantListLoad(url){
    let restaurantListSection = document.querySelector(".restaurant-list-section");
    let restaurantList = restaurantListSection.querySelector(".restaurant-list");
    
    fetch(url)
          .then(response => response.json())
          .then(list => {
             //.then(menu=>menu.name);
 
             // 방 비우기
             restaurantList.innerHTML = "";
 
            //  // 아이템 채우기
             for (let r of list) {
                let itemTemplate =
                   `<section class="restaurant-1">
                   <div class="content">
                       <!-- 이미지 -->
                       <div class="image-box">
                           <img src="/images/foods/${r.img}"  alt="이미지"
                               class="image">
                           <!-- 하트 -->
                           <button class="like">좋아요</button>
                           <div class="data-box">
                               <p>
                                   <span>좋아요 이미지</span>
                                   <span>${r.likedCount}</span>
                                   <span>평가 이미지</span>
                                   <span>${r.ratedCount}</span>
                               </p>
                           </div>
                       </div>
                       <!-- 정보 -->
                       <div class="info-box">
                           <div class="name-wrapper">
                               <span class="name">${r.name}</span>
                           </div>
                           <div class="info">
                               <p>
                                   평균가격
                                   <span>${r.avgPrice}</span>
                                   <span class="value subject">· 가성비</span>
                                   <span class="value">${r.value}%</span>
                               </p>
                           </div>
                       </div>
                       <!-- 버튼 -->
                       <div class="btn-box">
                           <div>
                               <a href="/restaurant/${r.id}">
                                   <button class="button button-12">상세보기</button></a>
                           </div>
                           <div>
                               <a href="/restaurant/${r.id}/rate">
                                   <button class="button button-12">평가하기</button></a>
                           </div>
                       </div>
                   </div>
               </section>`;
 
                restaurantList.insertAdjacentHTML("beforeend", itemTemplate);
             }
          });
 }


// 검색어 입력 시 RESTful API 요청
function getListByQuery(e) {
    e.preventDefault();
    let url = `/api/restaurant/list?q=${searchBar.value}`;
    restaurantListLoad(url);
}
searchBar.onchange = getListByQuery;
searchBtn.onclick = getListByQuery;

// Top Category 영역
// Top Category 클릭 시 RESTful API 요청
topCategorySection.onclick = function(e){
    searchBar.value = "";
    e.preventDefault();
    if(e.target.tagName !== 'A')
        return;
    //========== 추가
    if(e.target.innerText == '전체') {
        let url = '/api/restaurant/list';
        restaurantListLoad(url);
        otherCategorySection.classList.remove('slide-open');
    } else if(e.target.innerText == '기타')
        otherCategorySection.classList.add('slide-open');
    else {
        let url = `/api/restaurant/list?c=${e.target.dataset.id}`;
        restaurantListLoad(url);
        otherCategorySection.classList.remove('slide-open');
    }
};

// 기타 카테고리(태그) 영역
// 기타 카테고리 클릭 시 RESTful API 요청
tagArea.onclick = function (e) {
    searchBar.value = "";
    e.preventDefault();
    if(e.target.tagName !== 'BUTTON')
        return;
    let activeTag = otherCategories.querySelector('.active');
    if (activeTag != null)
        activeTag.classList.remove('active');
    e.target.classList.add('active');

    

    //========== 추가
    if (e.target.innerText == '#전체') {
        let url = `/api/restaurant/list?c=6`;
        restaurantListLoad(url);
    } else {
        let url = `/api/restaurant/list?c=${e.target.dataset.id}`;
        restaurantListLoad(url);
    }
};

// 좋아요 버튼
// FIXME 좋아요 버튼 Rland처럼 수정해야 함
let likeControl = function (e) {
    let isLiked = e.target.classList.contains("active");
    if (isLiked)
        e.target.classList.remove('active');
    else
        e.target.classList.add('active');
}

for (let btn of likeBtns)
    btn.onclick = likeControl;

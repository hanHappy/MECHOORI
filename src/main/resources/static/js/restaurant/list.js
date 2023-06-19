let header = document.querySelector('header');
let logo = header.querySelector('.logo');
let headerSide = header.querySelectorAll('.header-side');
let searchContainer = header.querySelector('.search-container');
let topCategorySection = header.querySelector('.top-category-section');
let otherCategorySection = header.querySelector('.other-category-section');
let otherCategories = otherCategorySection.querySelector('.others');
let tagArea = otherCategorySection.querySelector('.category.others');
let likeBtns = this.document.querySelectorAll('.like');


// 카테고리 클릭 시 api 요청
function restaurantListLoad(url){
	let restaurantListSection = document.querySelector(".restaurant-list-section");
	let restaurantList = restaurantListSection.querySelector(".restaurant-list");
	
	fetch(url)
			.then(response => response.json())
			.then(list => {
				// 방 비우기
				restaurantList.innerHTML = "";

				// 아이템 채우기
				for (let r of list) {
					let itemTemplate =
						`<section class="restaurant-1">
                        <div class="content">
                            <!-- 이미지 -->
                            <div class="image-box">
                                <img src="/images/foods/${r.img}" alt="이미지"
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
    else{
        otherCategorySection.classList.remove('slide-open');
        let url = `http://localhost:8080/api/restaurant/list?c=${e.target.dataset.id}`;
        restaurantListLoad(url);
    }
    
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


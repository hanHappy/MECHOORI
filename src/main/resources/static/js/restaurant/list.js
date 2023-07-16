import ModalToast from "../components/modal-toast.js"

const header = document.querySelector("header")
const logo = header.querySelector(".logo")
const headerSide = header.querySelectorAll(".header-side")
// 검색바
const searchContainer = header.querySelector(".search-container")
const searchBar = header.querySelector("#search-bar")
const searchBtn = header.querySelector(".search-btn")
// 카테고리
const topCategorySection = header.querySelector(".top-category-section")
// 기타 카테고리
const otherCategorySection = header.querySelector(".other-category-section")
// 필터
const filterBox = header.querySelector(".filter-box")
const filter = filterBox.querySelector('#filter')

const restaurantListSection = document.querySelector(".restaurant-list-section")
const restaurantList = restaurantListSection.querySelector(".restaurant-list")

const modalLike = document.querySelector(".modal-toast")

// 검색 변수
let searchParam = new URLSearchParams(window.location.search)
let variables = {
    query: searchParam.get('q'),
    topCategoryId: null,
    categoryId: searchParam.get('c'),
    filterId: null,
    offset: 0
}

let otherCategoryAllId = 6
let isLoadingOnScroll = false;

// ==================== 검색바 ====================
// 검색바 보이기() --------------------------------
function showSearchBar(){
    headerSide[0].style.display = 'none'
    headerSide[1].style.display = 'none'
    logo.style.display = 'none'
    searchContainer.classList.remove('d-none')
    setTimeout(() => {
        searchContainer.classList.add('visible')
    }, 50)
}

// Header 돋보기 버튼 클릭() -> 검색바 나타나기 ----
header.onclick = function(e){
    let isSearchBtn = e.target.className.includes('search')
    if(!isSearchBtn)
        return
    showSearchBar()
}

// index 검색 -> 검색바+키워드 보여주기 -----------
if(variables.query != null){
    // 검색바에 검색 키워드 남겨놓기
    searchBar.value = query
    // URLSearchParams 인스턴스 초기화 안 해주면 
    // index 페이지든 list 페이지든 검색바랑 키워드 계속 떠있음
    // => 검색바에 키워드 남아있지 않도록 초기화
    searchParam = null
    showSearchBar()
}

// ===================================================
// 초기화 함수
function resetVariables(){
    variables.query = null
    variables.topCategoryId = null
    variables.categoryId = null
    variables.filterId = null
    variables.offset = 0
    filter.selectedIndex = 0
    isLoadingOnScroll = false
}

// TODO url에 offset 포함

// -------------- 식당 리스트 로드 함수 ---------------
function restaurantListLoad(url){
    let memberId = null
    if(document.querySelector("#member-id")!=null)
        memberId = document.querySelector("#member-id").value

    console.log(url);

    fetch(url)
        .then(response => response.json())
        .then(list => {
            console.log(list);
            if(!isLoadingOnScroll)
                restaurantList.innerHTML = ""

            // 아이템 채우기
            for (let r of list) {
                let itemTemplate =
                    `
                    <section class="restaurant">
                        <div class="content">
                            <!-- 이미지 -->
                            <div class="image-box">
                                <img src="/images/foods/${r.img}" alt="이미지" class="image">
                                <div class="image-screen">
                                    <!-- 하트 -->
                                    <a 
                                        href="/user/login"
                                        data-member-id=${memberId}
                                        data-restaurant-id="${r.id}"
                                        class="like ${r.like ? 'active' : ''}">좋아요
                                    </a>
                                    <div class="data-box">
                                        <p>
                                            <span>좋아요 이미지</span>
                                            <span id="like-count">${r.likeCount}</span>
                                            <span>평가 이미지</span>
                                            <span>${r.rateCount}</span>
                                        </p>
                                    </div>
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
                    </section>
                `
                restaurantList.insertAdjacentHTML("beforeend", itemTemplate)
            }
        })
 }

// ------------------- 검색어 입력 -------------------
function searchHandler(e) {
    // e.preventDefault()
    resetVariables()

    variables.query = searchBar.value
    restaurantListLoad(`/api/restaurant/list?q=${variables.query}&o=${variables.offset}`)
}

searchBar.onchange = searchHandler
searchBtn.onclick = searchHandler

// ---------------- Top Category Section ----------------
topCategorySection.onclick = function(e){
    if(e.target.tagName !== 'A')
        return
    e.preventDefault()
    searchBar.value = ""

    // 선택된 카테고리 강조 선 제거
    let selectedCategory = topCategorySection.querySelector('.selected')
    if(selectedCategory != null)
        selectedCategory.classList.remove('selected')

    if(e.target.innerText == '전체') {
        resetVariables()

        restaurantListLoad(`/api/restaurant/list?o=${variables.offset}`)

        otherCategorySection.classList.remove('slide-open')
        e.target.classList.add('selected')
    } 
    else if(e.target.innerText == '기타'){
        otherCategorySection.classList.add('slide-open')
        e.target.classList.add('selected')
    }
    else {
        resetVariables()
        variables.categoryId = e.target.dataset.id

        restaurantListLoad(`/api/restaurant/list?c=${variables.categoryId}&o=${variables.offset}`)

        otherCategorySection.classList.remove('slide-open')
        e.target.classList.add('selected')
    }
}

// ----------------- 기타 카테고리 Section ---------------
otherCategorySection.onclick = function (e) {
    searchBar.value = ""
    if(e.target.tagName !== 'BUTTON')
        return

    resetVariables()
    let tag = e.target
    
    // 기타 카테고리 태그 on/off
    let activeTag = otherCategorySection.querySelector('.active')
    if (activeTag != null)
        activeTag.classList.remove('active')
    tag.classList.add('active')

    if (tag.innerText == '#전체') {
        variables.topCategoryId = otherCategoryAllId
        restaurantListLoad(`/api/restaurant/list?tc=${variables.topCategoryId}&o=${variables.offset}`)
    } else {
        variables.categoryId = tag.dataset.id
        restaurantListLoad(`/api/restaurant/list?c=${variables.categoryId}&o=${variables.offset}`)
    }
}

// --------------------- 필터링 -----------------------
filterBox.onchange = function(e){
    variables.filterId = e.target.value
    variables.offset = 0
    let url = null

    if(topCategoryId)
        url = `/api/restaurant/list?tc=${variables.topCategoryId}&f=${variables.filterId}&o=${variables.offset}`
    else if(categoryId)
        url = `/api/restaurant/list?c=${variables.categoryId}&f=${variables.filterId}&o=${variables.offset}`
    else if(query)
        url = `/api/restaurant/list?q=${variables.query}&f=${variables.filterId}&o=${variables.offset}`
        
    restaurantListLoad(url)
}

// ------------------ 좋아요 -------------------
restaurantList.onclick = function(e){
    let el = e.target
    let likeCount = el.parentElement.querySelector("#like-count")
    
    if(!el.classList.contains("like"))
        return

    let {restaurantId, memberId} = el.dataset // destructuring

    // 회원 아니면 return
    if(memberId==='null' || memberId===undefined){
        return
    }


    e.preventDefault()
    
    // Like 추가
    if(!el.classList.contains("active")){
        let data = {memberId, restaurantId}
        let jsonData = JSON.stringify(data)

        fetch("/api/restaurantlike",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: jsonData
            })
        .then(response=>response.json())
        .then(result=>{
            if(result == 1){
                // 하트 불 밝히기
                el.classList.add("active")
                
                fetch(`/api/restaurantlike/count?rid=${restaurantId}`)
                .then(response=>response.json())
                .then(count=>{
                    likeCount.textContent = count
                })
                .then(()=>{
                    const modalToast = new ModalToast()
                    modalToast.show(modalLike)
                })
            }
        })
    }

    // Like 삭제
    else {
        fetch(`/api/restaurantlike/${restaurantId}/members/${memberId}`, {
            method:"DELETE",
        })
        .then(response=>response.json())
        .then(result=>{
            if(result == 1){
                // 하트 불 끄기
                el.classList.remove("active")
                                    
                fetch(`/api/restaurantlike/count?rid=${restaurantId}`)
                .then(response=>response.json())
                .then(count=>{
                    likeCount.innerText = count
                })
            }
        })
    }
}

// 스크롤 -> offset 증가, restaurantListLoad()
window.addEventListener("scroll", function () {
    let documentHeight = document.documentElement.scrollHeight
    let scrollTop = document.documentElement.scrollTop
    let windowHeight = document.documentElement.clientHeight

    isLoadingOnScroll = true

    // 스크롤 바닥에 닿으면
    if (scrollTop + windowHeight >= documentHeight){
        variables.offset += 6
        console.log("x");
        window.scroll({
            top: 300
            });
    }

    let url = "/api/restaurant/list?"

    let urls = {
        query: `q=${variables.query}`,
        topCategoryId: `tc=${variables.topCategoryId}`,
        categoryId: `c=${variables.categoryId}`,
        filterId: `f=${variables.filterId}`,
        offset: `o=${variables.offset}`
    }

    let keyArr = Object.keys(variables)
    let and = "&"
    for(let i = 0; i < keyArr.length; i++){
        if(variables[keyArr[i]] !== null){
            url = `${url}${urls[keyArr[i]]}`
            if(i<keyArr.length-2)
                url = `${url}${and}`
        }
    }
    console.log(url);
    // restaurantListLoad(url);
})
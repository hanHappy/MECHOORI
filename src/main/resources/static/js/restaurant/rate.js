// 슬라이더 --------------------------------------
let slider = document.querySelector("#my-range");
let output = document.querySelector("#my-price-value");

// 슬라이더 조작 -> input 값 업데이트
slider.oninput = function () {
  output.value = this.value;
}

// 평가 확인 모달 ------------------------------------------
let header = document.querySelector('header');
let backBtn = header.querySelector('.header-icon.back');
let homeBtn = header.querySelector('.header-icon.home');
let logo = header.querySelector('.logo');
let modalComplete = document.querySelector('.modal.check-complete');
let noComplete = modalComplete.querySelector('.no');
let modalBack = document.querySelector('.modal.check-back');
let noBack = modalBack.querySelector('.no');
let modalHome = document.querySelector('.modal.check-home');
let noHome = modalHome.querySelector('.no');
let rateBtn = document.querySelector('.rate-btn');

// 모달 나타나게
header.onclick = function (e) {
  if (e.target != backBtn
    && e.target != homeBtn
    && e.target != logo)
    return;
  if (e.target == backBtn)
    modalBack.classList.remove('d-none');
  if (e.target == homeBtn)
    modalHome.classList.remove('d-none');
}
rateBtn.onclick = function (e) {
  modalComplete.classList.remove('d-none');
}

// 모달 영역 밖 클릭 시 모달 닫기
window.addEventListener('click', function (e) {
  let isModal = true;
  if (e.target == modalComplete
    || e.target == modalBack
    || e.target == modalHome)
    isModal = false;

  if (!isModal) {
    e.target.classList.add('d-none');
  }
});

// 아니요 버튼 클릭 시 모달 닫기
modalBack.onclick = function(e){
  if(e.target == noBack)
    modalBack.classList.add('d-none');
}
modalHome.onclick = function(e){
  if(e.target == noHome)
    modalHome.classList.add('d-none');
}
modalComplete.onclick = function(e){
  if(e.target == noComplete)
    modalComplete.classList.add('d-none');
}

// dropbox에서 메뉴 선택 시 판매 가격 업데이트
let dropbox = document.querySelector('.menu-dropbox');

dropbox.onchange = function (e) {
  let menuPrice = document.querySelector('.selling-price-value');
  let selectedIndex = dropbox.selectedIndex;
  let selectedOption = dropbox.options[selectedIndex];
  let value = selectedOption.dataset.price;
  menuPrice.innerText = value;
  slider.min = value * 0.8;
  slider.max = value * 1.2;
  slider.value = value;
  output.value = value;
}

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

// 가격 관련
let isMenuPriceFilled = false;
let isRatePriceValid = false;
let menuPrice = document.querySelector('.selling-price-value');
let ratePrice = document.querySelector("#my-price-value");
let slider = document.querySelector("#my-range");

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

dropbox.addEventListener("change", (e)=>{
  let menuPrice = document.querySelector('.selling-price-value');
  let selectedIndex = dropbox.selectedIndex;
  let selectedOption = dropbox.options[selectedIndex];
  let value = selectedOption.dataset.price;
  menuPrice.innerText = value;
  slider.min = value * 0.8;
  slider.max = value * 1.2;
  slider.value = value;
  ratePrice.value = value;

  if(menuPrice.innerText == "undefined")
    menuPrice.innerText = "";
});

// ===================== 폼 작성 완료 검사 ===================== 
// 평가 버튼 핸들러 (disable <-> able)
function rateBtnHandler(){
  if(isMenuPriceFilled && isRatePriceValid){
    rateBtn.classList.add("active");
    rateBtn.disabled = false;
  }
  else{
    rateBtn.classList.remove("active");
    rateBtn.disabled = true;
  }
}

// 슬라이더 조작 -> input 값 업데이트
slider.oninput = function () {
  ratePrice.value = Math.round(this.value/100)*100;
  isRatePriceValid = true;

  rateBtnHandler();
}

// 메뉴 선택 박스
dropbox.addEventListener("change", (e)=>{
  if(menuPrice.innerText != ""){
    isMenuPriceFilled = true;
    isRatePriceValid = true;
  }
  else
    isMenuPriceFilled = false;

    rateBtnHandler();
  
})

// 평가 가격
ratePrice.addEventListener("input", (e)=>{
  if(ratePrice.value != ""){
    isRatePriceValid = true;
  }
  else{
    isRatePriceValid = false;
  }

  let menuPrice_ = parseInt(menuPrice.innerText);
  let ratePrice_ = parseInt(ratePrice.value);
  let min = Math.round((menuPrice_ * 0.8)/100)*100;
  let max = Math.round((menuPrice_ * 1.2)/100)*100;

  if(min <= ratePrice_ && ratePrice_ <= max){
    isRatePriceValid = true;
  } else{
    isRatePriceValid = false;
  }
  
  rateBtnHandler();
})

// 이미지 업로드
let reviewSection = document.querySelector(".review");
let reviewImgUploadBtn = reviewSection.querySelector(".btn-upload");
let imgInput = reviewSection.querySelector("#review-image");
imgInput.addEventListener("change", (e)=>{
  let temp = imgInput.value.split("\\");
  let fileName = temp[temp.length-1];
  reviewImgUploadBtn.textContent = fileName;
  if(imgInput.value!="")
    reviewImgUploadBtn.classList.add("uploaded");
  else
    reviewImgUploadBtn.classList.remove("uploaded");
});

// let reviewImageLabel = reviewSection.querySelector("#review-image-label");
// reviewImageLabel.addEventListener("change", (e)=>{
//   console.log("c");
// });


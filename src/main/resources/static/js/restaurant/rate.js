// 슬라이더 --------------------------------------
let slider = document.querySelector("#my-range");
let output = document.querySelector("#demo");

// 슬라이더 조작 -> input 값 업데이트
slider.oninput = function () {
  output.value = this.value;
}

// 평가 확인 모달 ------------------------------------------
let rateBtn = document.querySelector('.rate-btn');
let backBtn = document.querySelector('.header-icon-back');
let homeBtn = document.querySelector('.header-icon-home');
let modalComplete = document.querySelector('.modal.check-complete');
let modalBack = document.querySelector('.modal.check-back');
let modalHome = document.querySelector('.modal.check-home');
let no = this.document.querySelectorAll('.no');

let closeModal = function(){
  if (modalComplete.style.display == 'block')
    modalComplete.style.display = 'none';
  else if (modalBack.style.display == 'block')
    modalBack.style.display = 'none';
  else if (modalHome.style.display == 'block')
    modalHome.style.display = 'none';
}

window.addEventListener('click', function(e) {
  switch(e.target){
    case backBtn:
      modalBack.style.display = 'block';
      break;
    case homeBtn:
      modalHome.style.display = 'block';
      break;
    case rateBtn:
      modalComplete.style.display = 'block';
      break;
  }
});

// 모달 영역 밖 클릭 시 모달 닫기
window.addEventListener('click', function(e) {
  let isModal = true;
  if (e.target == modalComplete 
    || e.target == modalBack 
    || e.target == modalHome)
    isModal = false;
    
    if(!isModal) {
      e.target.style.display = 'none';
    }
  });

  // 아니오 버튼 클릭시 모달 닫기
  for(let i = 0; i < no.length; i++)
    no[i].addEventListener('click', closeModal);

let dropbox = document.querySelector('.menu-dropbox');
let option = dropbox.querySelector('.option');

option.onchange = function(e){
  console.log(e.target);
}
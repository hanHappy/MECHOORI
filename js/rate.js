window.addEventListener("load", function () {
  // 슬라이더 --------------------------------------
  let slider = document.querySelector("#my-range");
  let output = document.querySelector("#demo");

  // 슬라이더 조작 -> input 값 업데이트
  slider.oninput = function () {
    output.value = this.value;
  }

  // 평가 확인 모달 ------------------------------------------
  let rateBtn = document.querySelector('#rate-btn');
  let modal = document.querySelector('.modal');
  let no = this.document.querySelector('.no');

  // 모달 열기
  function openModal() {
    modal.style.display = 'block';
  }

  // 모달 닫기 (아니오 클릭 시)
  function closeModal() {
    modal.style.display = 'none';
  }

  // 모달 닫기 (모달 외부 클릭 시)
  window.addEventListener('click', function (e) {
    if (e.target!=modal)
      return;
    closeModal();
  })

  // 평가 버튼 -> 모달 열기
  rateBtn.onclick = openModal;

  // 닫기 버튼 -> 모달 닫기
  no.onclick = closeModal;

});

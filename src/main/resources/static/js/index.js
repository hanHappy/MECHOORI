//이미지에 접근하는 인덱스
let index = 0;   
window.onload = function(){
    slideShow();
}

function slideShow() {
  let x = document.getElementsByClassName("rateAd");  //slide1에 대한 dom 참조
  for (let i = 0; i < x.length; i++) {
    x[i].style.display = "none";   //처음에 전부 display를 none으로 한다.
  }

  if (!window.matchMedia("(min-width: 1024px)").matches) {
    index++;
    if (index > x.length) {
      index = 1;  //인덱스가 초과되면 1로 변경
    }
    x[index - 1].style.display = "block";  //해당 인덱스는 block으로
  }

  setTimeout(slideShow, 2000);   //함수를 4초마다 호출
}
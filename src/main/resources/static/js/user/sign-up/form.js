window.addEventListener("load", function() {
 
    let password = document.querySelector("#password");
    let password1 = document.querySelector("#password1");
    let passwordCheck = document.getElementById("passwordCheck");

    password1.oninput = function() {
      if (password.value !== password1.value) {
        passwordCheck.innerText = "일치하지 않습니다.";
        passwordCheck.style.color = "red";
      } else {
        passwordCheck.innerText = "일치합니다.";
        passwordCheck.style.  color = "black";
      }
    };
    
    password1.onkeydown = function(e) {
      if (e.key === "Backspace") { // Backspace 키 입력 시 inner 글자 지움
        passwordCheck.innerText = "";
      }
    };

////////////////////////////

  let email = document.getElementById("email");
  let emailCheck = document.getElementById("emailCheck");
  let Epattern = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/;

  email.oninput = function() {
    if (!Epattern.test(email.value)) {
      emailCheck.innerText = "유효하지 않은 이메일입니다.";
      emailCheck.style.color = "red";
    } else {
      emailCheck.innerText = "유효한 이메일입니다.";
      emailCheck.style.color = "black";
    }
  };
/////////////////////////

let phone = document.querySelector("#phone");
let numberCheck = document.getElementById("numberCheck");
let Ppattern = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;

phone.oninput = function() {
  let phoneNumber = phone.value.replace(/[^0-9]/g, ""); // 입력된 번호에서 숫자만 추출
  let formattedNumber = formatPhoneNumber(phoneNumber); // 하이픈이 추가된 형식으로 변환
  phone.value = formattedNumber; // 변환된 번호를 입력란에 설정

  if (!Ppattern.test(phoneNumber)) {
    numberCheck.innerText = "유효하지 않은 번호입니다.";
    numberCheck.style.color = "red";
  } else {
    numberCheck.innerText = "유효한 번호입니다.";
    numberCheck.style.color = "black";
  }
};

  phone.onkeydown = function(e) {
    if (e.key === "Backspace") { // Backspace 키 입력 시 inner 글자 지움
      numberCheck.innerText = "";
    }
  };

  function formatPhoneNumber(number) {
    let phone = "";

    if(number.length < 4) {
        return number;
    } else if(number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
    } else if(number.length < 11) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
    } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7);
    }
    return phone;
}


});

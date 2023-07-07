let realUpload = document.querySelector('.real-upload');
let fileInput = document.querySelector('.upload');
let file = null;
let profileCircleImg = document.querySelector('.profile-circle img');
let submitBtn = document.querySelector('.submit-btn');
let imgFile = null;
let imageElement = document.querySelector('.btn-upload');
const imgElement = document.querySelector('.real-upload img');

// 파일 업로드 input 변경 이벤트 핸들러 추가
fileInput.addEventListener('change', function () {
    // 선택한 파일 가져오기
    let selectedFile = fileInput.files[0];

    // FileReader 객체 생성
    let reader = new FileReader();

    // FileReader 로드 완료 이벤트 핸들러 추가
    reader.onload = function (event) {
        // 이미지 경로 가져오기
        event.preventDefault();
        const imageUrl = event.target.result;
        // console.log(imageUrl);

        profileCircleImg.src = imageUrl;

    };

    // 선택한 파일을 Data URL 형태로 읽기
    reader.readAsDataURL(selectedFile);
});

// 저장 버튼
submitBtn.onclick = function (e) {
    let profileImgInput = document.querySelector("#profile-img-input");
    let file = profileImgInput.files[0];
    let memberId = profileImgInput.dataset.memberId;
    let profileFrame = document.querySelector("#profile-frame");

    console.log("c");

    // 그릇
    let formData = new FormData();

    formData.append("file", file);

    fetch(`/api/user/${memberId}/image`, {
        method: "PUT",
        body: formData
    })
    .then(response => response.text())
    .then(path =>{
        profileFrame.innerHTML = `<img id="profile-image" src=${path}>`;
    });

    // 요청 결과 = 이미지 경로


    // modalAlert.show(false);
}
    let realUpload = document.querySelector('.real-upload');
    let fileInput = document.querySelector('.upload');
    let file = null;
    let imgFile = null;
    let profileCircleImg = document.querySelector('.profile-circle img');
    let submitBtn = document.querySelector('.submit-btn');
    // let imageElement = document.querySelector('.img-click');
    // let imgElement = document.querySelector('.real-upload img');

    
    // 파일 업로드 input 변경 이벤트 핸들러 추가
    fileInput.addEventListener('change', function() {
      // 선택한 파일 가져오기
      let selectedFile = fileInput.files[0];
      
      // FileReader 객체 생성
      let reader = new FileReader();
      
    // FileReader 로드 완료 이벤트 핸들러 추가
      reader.onload = function(event) {
        // 이미지 경로 가져오기
        event.preventDefault();
        const imageUrl = event.target.result;
        // console.log(imageUrl);
    
        profileCircleImg.src = imageUrl;
        
    };
    
    // 선택한 파일을 Data URL 형태로 읽기
    reader.readAsDataURL(selectedFile);
});



submitBtn.onclick = function(e){
    let profileImgImput = document.querySelector("#add-profile-image")
    let file = profileImgImput.files[0];
    let memberId = profileImgImput.dataset.memberId;
    
    //담을 그릇
    let formData = new FormData();

    fetch(`/api/user/{memberId}/image`, {
        method:"PUT",
        body: formData
    })
    .then(response => response.json())
    .then(url => {
      
    }


    // modalAlert.show(false);
};
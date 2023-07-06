window.addEventListener('load', function(){
    
    let realUpload = document.querySelector('.real-upload');
    let fileInput = document.querySelector('.upload');
    let file = null;
    let profileCircleImg = document.querySelector('.profile-circle img');
    let submitBtn = document.querySelector('.submit-btn');
    let imgFile = null;
    let imageElement = document.querySelector('.img-click');
    const imgElement = document.querySelector('.real-upload img');

    // 호버 이벤트 처리
    imgElement.addEventListener('mouseover', function () {
        // 이미지 소스 변경
        imgElement.src = '/images/icons/이미지수정호버.svg';
    });

    imgElement.addEventListener('mouseout', function () {
        // 호버 해제 시 원래 이미지로 복원
        imgElement.src = '/images/icons/이미지수정.svg';
    });
        //이미지가 클릭 될때 mouseover가 되버린다...


    // 이미지 교체 이벤트 핸들러 추가
    imageElement.addEventListener('click', function() {
      
      fileInput.click();
    });
    
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
    e.preventDefault();
    console.log("클릭");
    let files = document.getElementById("btn1").files;
    
    (async () => {
           
                if(!file)
                    return;
                let imgData = new ImgData();
                imgData.append("file", file);

                imgFile = e.dataTransfer.files;
                file = imgFile;

                // 사진 식별자를 이용해 파일을 업로드
                let response = await fetch(`/api/user/${id}/image`, {
                    method: "POST",
                    body: formData
                });

                // 컨트롤러부터 응답받은 이미지 url
                let imgUrl = await response.json(); // /upload/files/??.jpg
                let imgSrc = imgUrl[0];

                console.log(imgSrc);

                // modalAlert.show(false);
        })();
}
});
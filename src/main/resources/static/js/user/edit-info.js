
let realUpload = document.querySelector('.real-upload');
let upload = document.querySelector('.upload');
// let defautImg = document.getElementById('default1');

// defautImg.addEventListener('mouseover', function() {
//     defautImg.setAttribute("src", "/images/icons/이미지수정클릭.svg");
//     console.log("마우스오버");
//   });

//   // 이미지에서 마우스 벗어남 이벤트 추가
//   defautImg.addEventListener('mouseleave', function() {
//     defautImg.setAttribute("src", "/images/icons/이미지수정.svg");
//     console.log("나 간다잉~~");
//   });

function getImageFiles(e) {
    let files = e.currentTarget.files;
    console.log(typeof files, files);
}


function createElement(e, file) {
        
        let img = document.createElement('img');
        let profileCircle = document.querySelector('.profile-circle');
        img.setAttribute('src', e.target.result);
        img.setAttribute('data-file', file.name);
        profileCircle.appendChild(img);

    return profileCircle;
}
realUpload.addEventListener('click', () => upload.click());
upload.addEventListener('change', getImageFiles);

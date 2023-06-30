
let realUpload = document.querySelector('.real-upload');
let upload = document.querySelector('.upload');


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
console.log(e.target.result);
realUpload.addEventListener('click', () => upload.click());
profileCircle.addEventListener('change', getImageFiles);

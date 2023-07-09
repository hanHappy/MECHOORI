let form = document.querySelector('form');
let profileImgUploadBtn = form.querySelector('#btn-upload');
let profileImgInput = form.querySelector('#profile-img-input');
let profileImg = form.querySelector('.profile-image');

let saveBtn = form.querySelector('#btn-save');
let file = null;

let isImgChanged = false;

let nicknameInput = form.querySelector('#nickname');
let nicknameOrigin = nicknameInput.value;

// 새 프로필 이미지 업로드 시 새로운 이미지로 교체 (UI만)
profileImgInput.addEventListener('change', (e) => {
    file = profileImgInput.files[0];

    let reader = new FileReader();

    // FileReader 로드 완료 이벤트 핸들러 추가
    reader.onload = function (event) {
        // 이미지 경로 가져오기
        event.preventDefault();
        const imageUrl = event.target.result;
        profileImg.src = imageUrl;
    };

    reader.readAsDataURL(file);

    isImgChanged = true;

    saveBtn.classList.add('active');
    saveBtn.disabled = false;
});

// 닉네임 중복 확인
async function checkNicknameUnique() {
    let nicknameNew = nicknameInput.value;
    let nicknameCheck = form.querySelector('#msg-nickname-check');

    const formData = new FormData();
    formData.append('nickname', nicknameNew);

    return fetch('/api/sign-up/nicknameCheck', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(formData),
    })
    .then(function (response) {
        if (response.ok)
            return response.text();
    })
    .then(function (data) {
        // 기존 닉네임과 동일하면 -> 확인 메시지 삭제
        if(nicknameInput.value == nicknameOrigin){
            nicknameCheck.innerText = "";
            return false;
        } else if (data === "cantuse") {
            nicknameCheck.style.color = "red";
            nicknameCheck.innerText = "사용 중인 닉네임입니다";
            return false;
        } else {
            nicknameCheck.style.color = "blue";
            nicknameCheck.innerText = "";
            return true;
        }
    })
    .catch(function (error) {
        console.log(error.message);
    });
}

// 기존 닉네임 <-> 타이핑 중인 닉네임 비교
function checkNicknameChange() {
    let nicknameNew = nicknameInput.value;

    if (nicknameOrigin == nicknameNew)
        return false;
    else
        return true;
}

// 닉네임 변경 시 저장 버튼 
nicknameInput.addEventListener('input', async (e) => {
    let isNicknameUnique = await checkNicknameUnique();
    let isNicknameChanged = checkNicknameChange();

    if (isNicknameChanged && isNicknameUnique) {
        saveBtn.classList.add('active');
        saveBtn.disabled = false;
    } else if (!(isNicknameChanged || isNicknameUnique || isImgChanged)) {
        saveBtn.classList.remove('active');
        saveBtn.disabled = true;
    }

});


// 저장 버튼
saveBtn.onclick = function (e) {
    let memberId = profileImgInput.dataset.memberId;
    let profileFrame = document.querySelector("#profile-frame");

    // 그릇
    let formData = new FormData();

    formData.append("file", file);

    fetch(`/api/user/${memberId}/image`, {
        method: "PUT",
        body: formData
    })
        .then(response => response.text())
        .then(path => {
            profileFrame.innerHTML = `<img id="profile-image" src=${path}>`;
        });

    // 요청 결과 = 이미지 경로
}
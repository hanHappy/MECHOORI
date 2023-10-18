// Kakao Maps API 초기화
kakao.maps.load(function () {
    var container = document.getElementById('map'),
        options = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 1
        };

    var map = new kakao.maps.Map(container, options);

    let l = document.getElementById("map-address-text").innerText;
    let name = document.getElementById("resName").innerText;

    // let l = /*[[${rstnDetail.address}]]*/ '';  //위치
    //let name = /*[[${name}]]*/ '';             //마커내용

    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(l, function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
            });

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            // var infowindow = new kakao.maps.InfoWindow({
            //     content: `<div style="width:150px;text-align:center;padding:6px 0;">${name}</div>`,
            // });
            // infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }
    });
});

let section = document.querySelector(".bottom-sheet-section")
let isUpper = false;

section.addEventListener("click", function (e) {
    if (isUpper) {
        isUpper = false;
    } else {
        upperSection();
        isUpper = true;
    }
});

const restaurantId = section.getAttribute('data-restaurant-id');

function upperSection() {
    section.style.transform = "translateY(-900%)";

    setTimeout(() => {
        window.location.href = '/restaurant/'+restaurantId;
    }, 350);
}



// ===================================================================================================================
//
//     const copyButton = document.getElementById('address-copy-button');
//     const addressText = document.getElementById('map-address-text');
//
//     copyButton.addEventListener('click', () => {
//         const address = addressText.textContent;
//         navigator.clipboard.writeText(address)
//             .then(() => {
//                 copyButton.onclick = function () {
//                     modal.style.display = 'block';
//                 }
//                 console.log('주소가 복사되었습니다.');
//             })
//             .catch((error) => {
//                 console.error('복사 실패:', error);
//             });
//     });


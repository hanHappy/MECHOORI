// window.addEventListener("load", function (callback) {
//     const names = document.querySelectorAll(".addresses");
//     let markers = [];
//
//     for (let name of names) {
//         markers.push(name.textContent);
//     }
//
//     kakao.maps.load(function (callback) {
//         var container = document.getElementById('map');
//         var options = {
//             center: new kakao.maps.LatLng( 37.55265499581108, 126.93762683368536 ),
//
//             level: 1
//         };
//
//         var map = new kakao.maps.Map(container, options);
//         var geocoder = new kakao.maps.services.Geocoder();
//
//         markers.forEach(function (address) {
//             geocoder.addressSearch(address, function (result, status) {
//                 if (status === kakao.maps.services.Status.OK) {
//                     var marker = new kakao.maps.Marker({
//                         position: new kakao.maps.LatLng(result[0].y, result[0].x)
//                     });
//                     marker.setMap(map);
//                 }
//             });
//         });
//
//         let icon = new kakao.maps.MarkerImage(
//
//
//
//     });
// });

// window.addEventListener("load", function () {
//     const names = document.querySelectorAll(".addresses");
//     let markers = [];
//
//     for (let name of names) {
//         markers.push(name.textContent);
//     }
//
//     kakao.maps.load(function () {
//         var container = document.getElementById('map');
//         var options = {
//             center: new kakao.maps.LatLng(37.55265499581108, 126.93762683368536),
//             level: 1
//         };
//
//         var map = new kakao.maps.Map(container, options);
//         var geocoder = new kakao.maps.services.Geocoder();
//
//         markers.forEach(function (address) {
//             geocoder.addressSearch(address, function (result, status) {
//                 if (status === kakao.maps.services.Status.OK) {
//                     var markerImage = new kakao.maps.MarkerImage(
//                         'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png', //아이콘 주소
//                         new kakao.maps.Size(31, 35) //아이콘 크기
//
//                 );
//
//                     var marker = new kakao.maps.Marker({
//                         position: new kakao.maps.LatLng(result[0].y, result[0].x),
//                         image: markerImage
//                     });
//                     marker.setMap(map);
//                 }
//             });
//         });
//     });
// });

window.addEventListener("load", function () {
    const addresses = document.querySelectorAll(".addresses");
    const resNames = document.querySelectorAll(".resName");
    const contactNumbers = document.querySelectorAll(".contactNumber");
    let markers = [];

    for (let i = 0; i < addresses.length; i++) {
        const name = resNames[i].textContent;
        const address = addresses[i].textContent;
        const contactNumber = contactNumbers[i].textContent;
        markers.push({ name, address, contactNumber });
    }

    kakao.maps.load(function () {
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.55265499581108, 126.93762683368536),
            level: 1
        };

        var map = new kakao.maps.Map(container, options);
        var geocoder = new kakao.maps.services.Geocoder();

        markers.forEach(function (markerInfo) {
            const { name, address, contactNumber } = markerInfo;

            geocoder.addressSearch(address, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var markerImage = new kakao.maps.MarkerImage(
                       'https://imgur.com/vSOP7KD.png',
                        new kakao.maps.Size(31, 35)
                    );

                    var marker = new kakao.maps.Marker({
                        position: new kakao.maps.LatLng(result[0].y, result[0].x),
                        image: markerImage
                    });
                    marker.setMap(map);

                    var infowindow = new kakao.maps.InfoWindow({
                        content: `<div style="width: 100%; height: 100%;background-color:white; border: 1px solid black "><strong>${name}</strong><br>주소: ${address}<br>전화번호: ${contactNumber}</div>`,
                        maxWidth: 500

                    });

                    kakao.maps.event.addListener(marker, 'mouseover', function () {
                        infowindow.open(map, this);
                    });

                    kakao.maps.event.addListener(marker, 'mouseout', function () {
                        infowindow.close();
                    });
                }
            });
        });
    });
});




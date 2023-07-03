window.addEventListener("load", function () {
    const addresses = document.querySelectorAll(".addresses");
    const resNames = document.querySelectorAll(".resName");
    const contactNumbers = document.querySelectorAll(".contactNumber");
    let markers = [];

    var currentInfoWindow = null; // 현재 열려있는 인포윈도우 추적 변수

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

                    var infowindow = new kakao.maps.InfoWindow({
                        content: `<div style="width: 100%; height: 100%;background-color:white; border: 1px solid black "><strong>${name}</strong><br>주소: ${address}<br>전화번호: ${contactNumber}</div>`,
                        maxWidth: 500
                    });

                    kakao.maps.event.addListener(marker, 'click', function () {
                        if (currentInfoWindow === infowindow) {
                            // 현재 열려있는 인포윈도우를 닫음
                            infowindow.close();
                            currentInfoWindow = null;
                        } else {
                            // 새로운 인포윈도우를 열고 현재 열려있는 인포윈도우를 업데이트
                            if (currentInfoWindow) {
                                currentInfoWindow.close();
                            }
                            infowindow.open(map, marker);
                            currentInfoWindow = infowindow;
                        }
                    });

                    kakao.maps.event.addListener(marker, 'touchstart', function () {
                        infowindow.open(map, marker);
                    });

                    kakao.maps.event.addListener(marker, 'mouseout', function () {
                        infowindow.close();
                    });

                    marker.setMap(map);
                }
            });
        });

        // 지도를 클릭했을 때 기존에 열려있는 인포윈도우를 닫음
        kakao.maps.event.addListener(map, 'click', function () {
            if (currentInfoWindow) {
                currentInfoWindow.close();
                currentInfoWindow = null;
            }
        });
    });
});

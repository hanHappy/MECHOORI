window.addEventListener("DOMContentLoaded", function (e) {

    const addresses = document.querySelectorAll(".addresses");
    const resNames = document.querySelectorAll(".resName");
    const contactNumbers = document.querySelectorAll(".contactNumber");
    const resIds = document.querySelectorAll("#resId");

    let map;
    let infowindow;
    let currentInfoWindow = null;
    let markers = [];

    for (let i = 0; i < addresses.length; i++) {
        const name = resNames[i].textContent;
        const address = addresses[i].textContent;
        const contactNumber = contactNumbers[i].textContent;
        const id = resIds[i].textContent;
        markers.push({ name, address, contactNumber, id });
    }

    kakao.maps.load(function () {
        var container = document.getElementById("map");
        var options = {
            center: new kakao.maps.LatLng(37.55265499581108, 126.93762683368536),
            level: 1,
        };

        map = new kakao.maps.Map(container, options);
        infowindow = new kakao.maps.InfoWindow({
            // removable: true
        });

        markers.forEach(function (markerInfo) {
            const { name, address, contactNumber, id } = markerInfo;

            var geocoder = new kakao.maps.services.Geocoder();
            geocoder.addressSearch(address, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var markerImage = new kakao.maps.MarkerImage(
                        "https://imgur.com/vSOP7KD.png",
                        new kakao.maps.Size(60, 70)
                    );

                    var marker = new kakao.maps.Marker({
                        position: new kakao.maps.LatLng(result[0].y, result[0].x),
                        image: markerImage,
                    });

                    var content = `<a href="restaurant/${id}" style="color:black; display: block;  width: 100%; border: 1px solid black;">
                                            <div style=" background-color:white; padding: 15px;">
                                            <strong>${name}</strong>
                                            <br>주소: ${address}<br>연락처: ${contactNumber}</div>
                                            </a>`;

                    kakao.maps.event.addListener(marker, "click", function () {
                        if (currentInfoWindow === infowindow) {
                            infowindow.close();
                            currentInfoWindow = null;
                        } else {
                            if (currentInfoWindow) {
                                currentInfoWindow.close();
                            }
                            infowindow.setContent(content);
                            infowindow.open(map, marker);
                            currentInfoWindow = infowindow;
                        }
                        map.setCenter(marker.getPosition());
                    });

                    marker.setMap(map);
                }
            });
        });

        // Close the currently open info window when the map is clicked
        kakao.maps.event.addListener(map, "click", function () {
            if (currentInfoWindow) {
                currentInfoWindow.close();
                currentInfoWindow = null;
            }
        });

        function zoomIn() {
            map.setLevel(map.getLevel() - 1);
        }

        function zoomOut() {
            map.setLevel(map.getLevel() + 1);
        }

        document.querySelector(".zoomIn").addEventListener("click", zoomIn);
        document.querySelector(".zoomOut").addEventListener("click", zoomOut);



    });

    function getCurrentLocation() {
        return new Promise(function (resolve, reject) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(resolve, reject);
            } else {
                reject(new Error('Geolocation is not supported.'));
            }
        });
    }

    function currentLocation() {
        getCurrentLocation()
            .then(function (position) {
                var lat = position.coords.latitude; // 위도
                var lon = position.coords.longitude; // 경도

                var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다

                // 마커를 표시합니다
                displayMarker(locPosition);
            })
            .catch(function (error) {
                console.log(error);
                var locPosition = new kakao.maps.LatLng(37.4812845080678, 126.952713197762);

                // 마커를 표시합니다
                displayMarker(locPosition);
            });
    }

    function displayMarker(locPosition) {
        var imageSize = new kakao.maps.Size(40, 35);
        var imageSrc = 'https://imgur.com/WKIrIDH.png'; // 마커 이미지 URL, 스프라이트 이미지를 사용합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        var marker = new kakao.maps.Marker({
            map: map,
            position: locPosition,
            image: markerImage,
        });

        map.setCenter(locPosition);
    }

    currentLocation();


});

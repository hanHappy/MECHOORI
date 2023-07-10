/*
window.addEventListener("DOMContentLoaded", function (e) {

    const addresses = document.querySelectorAll(".addresses");
    const resNames = document.querySelectorAll(".resName");
    const contactNumbers = document.querySelectorAll(".contactNumber");
    const resIds = document.querySelectorAll("#resId");


    let map;
    // let infowindow;
    let currentInfoWindow = null;
    let markers = [];


    for (let i = 0; i < addresses.length; i++) {
        const name = resNames[i].textContent;
        const address = addresses[i].textContent;
        const contactNumber = contactNumbers[i].textContent;
        const id = resIds[i].textContent;
        markers.push({name, address, contactNumber, id});
    }

    kakao.maps.load(function () {
        var container = document.getElementById("map");
        var options = {
            center: new kakao.maps.LatLng(37.55265499581108, 126.93762683368536),
            level: 1,
        };

        var map = new kakao.maps.Map(container, options);
        var geocoder = new kakao.maps.services.Geocoder();

        markers.forEach(function (markerInfo) {
            const {name, address, contactNumber, id} = markerInfo;

            geocoder.addressSearch(address, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var markerImage = new kakao.maps.MarkerImage(
                        "https://imgur.com/vSOP7KD.png",
                        new kakao.maps.Size(60, 70),
                    );

                    var marker = new kakao.maps.Marker({
                        position: new kakao.maps.LatLng(result[0].y, result[0].x),
                        image: markerImage,
                    });

                    var infowindow = new kakao.maps.InfoWindow({
                        maxWidth: 500,
                    });

                    infowindow.setContent(`<a href="restaurant/${id}" style="color: black"><div style="width: 100%; height: 100%;background-color:white; border: 1px solid black; top:15px"><strong>${name}</strong><br>주소: ${address}<br>연락처: ${contactNumber}</div></a>`);

                    kakao.maps.event.addListener(marker, "click", function () {
                        if (currentInfoWindow === infowindow) {
                            infowindow.close();
                            currentInfoWindow = null;
                        } else {
                            if (currentInfoWindow) {
                                currentInfoWindow.close();
                            }
                            infowindow.open(map, marker);
                            currentInfoWindow = infowindow;
                        }
                    });

                    kakao.maps.event.addListener(marker, "touchstart", function () {
                        map.setCenter(marker.getPosition());
                        infowindow.open(map, marker);
                    });

                    kakao.maps.event.addListener(marker, "click", function () {
                        map.setCenter(marker.getPosition());
                        infowindow.open(map, marker);
                    });

                    kakao.maps.event.addListener(marker, "mouseout", function () {
                        infowindow.close();
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
///////////////////////////////////////////////////////////////////////////
///////////////////////////       수정 필요        ////////////////////////
///////////////////////////////////////////////////////////////////////////

    var ps = new kakao.maps.services.Places();

// 키워드 검색을 요청하는 함수입니다
    function searchPlaces() {
        let keyword = document.getElementById("keyword").value;
        e.preventDefault();

        if (!keyword.replace(/^\s+|\s+$/g, "")) {
            alert("키워드를 입력해주세요!");
            return false;
        }

        let placesService = new kakao.maps.services.Places();

        placesService.keywordSearch(keyword, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                displayPlaces(result);
                displayPagination(result);
            } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                alert("검색 결과가 없습니다.");
                return;
            } else if (status === kakao.maps.services.Status.ERROR) {
                alert("검색 결과 중 오류가 발생했습니다.");
                return;
            }
        });
    }


    function displayPlaces(places) {

        var listEl = document.getElementById('placesList'),
            menuEl = document.getElementById('menu_wrap'),
            fragment = document.createDocumentFragment(),
            bounds = new kakao.maps.LatLngBounds(),
            listStr = '';

        // 검색 결과 목록에 추가된 항목들을 제거합니다
        removeAllChildNods(listEl);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        for (var i = 0; i < places.length; i++) {

            // 마커를 생성하고 지도에 표시합니다
            var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
                marker = addMarker(placePosition, i),
                itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(placePosition);

            // 마커와 검색결과 항목에 mouseover 했을때
            // 해당 장소에 인포윈도우에 장소명을 표시합니다
            // mouseout 했을 때는 인포윈도우를 닫습니다
            (function (marker, title) {
                kakao.maps.event.addListener(marker, 'mouseover', function () {
                    displayInfowindow(marker, title);
                });

                kakao.maps.event.addListener(marker, 'mouseout', function () {
                    infowindow.close();
                });

                itemEl.onmouseover = function () {
                    displayInfowindow(marker, title);
                };

                itemEl.onmouseout = function () {
                    infowindow.close();
                };
            })(marker, places[i].place_name);

            fragment.appendChild(itemEl);
        }

        // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
        listEl.appendChild(fragment);
        menuEl.scrollTop = 0;

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    }

// 검색결과 항목을 Element로 반환하는 함수입니다
    function getListItem(index, places) {

        var el = document.createElement('li'),
            itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

        if (places.road_address_name) {
            itemStr += '    <span>' + places.road_address_name + '</span>' +
                '   <span class="jibun gray">' + places.address_name + '</span>';
        } else {
            itemStr += '    <span>' + places.address_name + '</span>';
        }

        itemStr += '  <span class="tel">' + places.phone + '</span>' +
            '</div>';

        el.innerHTML = itemStr;
        el.className = 'item';

        return el;
    }

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, idx, title) {
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
            imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
            imgOptions = {
                spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            },
            markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage
            });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker);  // 배열에 생성된 마커를 추가합니다

        return marker;
    }

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
    function removeMarker() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    function displayPagination(pagination) {
        var paginationEl = document.getElementById('pagination'),
            fragment = document.createDocumentFragment(),
            i;

        // 기존에 추가된 페이지번호를 삭제합니다
        while (paginationEl.hasChildNodes()) {
            paginationEl.removeChild(paginationEl.lastChild);
        }

        for (i = 1; i <= pagination.last; i++) {
            var el = document.createElement('a');
            el.href = "#";
            el.innerHTML = i;

            if (i === pagination.current) {
                el.className = 'on';
            } else {
                el.onclick = (function (i) {
                    return function () {
                        pagination.gotoPage(i);
                    }
                })(i);
            }

            fragment.appendChild(el);
        }
        paginationEl.appendChild(fragment);
    }

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
    function displayInfowindow(marker, title) {
        var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

        infowindow.setContent(content);
        infowindow.open(map, marker);
    }

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    function removeAllChildNods(el) {
        while (el.hasChildNodes()) {
            el.removeChild(el.lastChild);
        }
    }
});

/////////////////////////////////////////////////////
/////////////////////////////////////////////////////

//map.html
function currentLocation() {
    // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
    if (navigator.geolocation) {

        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {

            var lat = position.coords.latitude, // 위도
                lon = position.coords.longitude; // 경도

            var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            var message = '<div style="width: 100%; height100%; padding:5px;">현위치</div>'; // 인포윈도우에 표시될 내용입니다

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition, message);
        });
    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

        var locPosition = new kakao.maps.LatLng(37.4812845080678, 126.952713197762),
            message = '현재 위치를 알 수 없어 기본 위치로 이동합니다.'

        currentLatLon['lat'] = 33.450701
        currentLatLon['lon'] = 126.570667

        displayMarker(locPosition, message);
    }
    return true
}

currentLocation();

////////////////////////////////////////////////////////
////////////////////////////////////////////////////////

function displayMarker(locPosition, message) {
    var imageSize = new kakao.maps.Size(24, 35);
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png'; // 마커 이미지 URL, 스프라이트 이미지를 사용합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 지도 생성
    var container = document.getElementById('map');
    var options = {
        center: locPosition,
        level: 3
    };
    var map = new kakao.maps.Map(container, options);

    // 마커 생성
    var marker = new kakao.maps.Marker({
        map: map,
        position: locPosition,
        image: markerImage
    });

    // 인포윈도우 생성
    var infowindow = new kakao.maps.InfoWindow({
        content: message,
        removable: true
    });

    // 마커에 인포윈도우 오픈
    infowindow.open(map, marker);

    // 지도의 중심 좌표를 마커 위치로 설정합니다
    map.setCenter(locPosition);
}*/


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

                    var content = `<a href="restaurant/${id}" style="color: black;"><div style="width: 100%; height: 100%; background-color:white; border: 1px solid black; padding: 15px;"><strong>${name}</strong><br>주소: ${address}<br>연락처: ${contactNumber}</div></a>`;

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

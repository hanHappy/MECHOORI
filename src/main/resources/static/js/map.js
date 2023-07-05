window.addEventListener("DOMContentLoaded", function (e) {



    const addresses = document.querySelectorAll(".addresses");
    const resNames = document.querySelectorAll(".resName");
    const contactNumbers = document.querySelectorAll(".contactNumber");
    const resIds = document.querySelectorAll("#resId");

    let markers = [];

    var currentInfoWindow = null; // Variable to track the currently open info window

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

                    // Set infowindow content outside the geocoder callback
                    infowindow.setContent(`<a href="restaurant/${id}" style="color: black"><div style="width: 100%; height: 100%;background-color:white; border: 1px solid black; top:15px"><strong>${name}</strong><br>주소: ${address}<br>연락처: ${contactNumber}</div></a>`);

                    kakao.maps.event.addListener(marker, "click", function () {
                        if (currentInfoWindow === infowindow) {
                            // Close the currently open info window
                            infowindow.close();
                            currentInfoWindow = null;
                        } else {
                            // Open a new info window and update the currently open info window
                            if (currentInfoWindow) {
                                currentInfoWindow.close();
                            }
                            infowindow.open(map, marker);
                            currentInfoWindow = infowindow;
                        }
                    });

                    kakao.maps.event.addListener(marker, "touchstart", function () {
                        infowindow.open(map, marker);
                    });

                    kakao.maps.event.addListener(marker, "click", function () {
                        infowindow.open(map, marker);
                    });

                    // kakao.maps.event.addListener(marker, "mouseout", function () {
                    //     infowindow.close();
                    // });

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
// window.addEventListener("DOMContentLoaded", function (callback) {
//     const addresses = document.querySelectorAll(".addresses");
//     const resNames = document.querySelectorAll(".resName");
//     const resIds = document.querySelectorAll(".resId");
//     const contactNumbers = document.querySelectorAll(".contactNumber");
//
//     let mapContainer = document.getElementById("map");
//     let mapOption = {
//         center: new kakao.maps.LatLng(37.566826, 126.9786567),
//         level: 3,
//     };
//
//     let markers = [];
//     var currentInfoWindow = null;
//
//     let map = new kakao.maps.Map(mapContainer, mapOption);
//
//     for (let i = 0; i < addresses.length; i++) {
//         const name = resNames[i].textContent;
//         const address = addresses[i].textContent;
//         const contactNumber = contactNumbers[i].textContent;
//         const id = resIds[i].textContent;
//         markers.push({name, address, contactNumber, id});
//     }
//     for (let i = 0; i < markers.length; i++) {
//         displayMarker(markers[i]);
//     }
//
//     function displayMarker(place) {
//         let marker = new kakao.maps.Marker({
//             map: map,
//             position: new kakao.maps.LatLng(place.address.y, place.address.x),
//         });
//
//         kakao.maps.event.addListener(marker, "click", function () {
//             // 마커를 클릭하면 해당 장소의 정보를 표시하는 팝업 생성
//             createInfoWindow(place);
//         });
//     }
//
//     function createInfoWindow(place) {
//         let infoWindow = new kakao.maps.InfoWindow({
//             content: `
//                 <div class="infoWindow">
//                     <h4>${place.name}</h4>
//                     <p>${place.address}</p>
//                     <p>${place.contactNumber}</p>
//                 </div>
//             `,
//         });
//
//         infoWindow.open(map, marker.getPosition());
//     }
//
//     function searchPlaces() {
//         let keyword = document.getElementById("keyword").value;
//
//         if (!keyword.replace(/^\s+|\s+$/g, "")) {
//             alert("키워드를 입력해주세요!");
//             return false;
//         }
//
//         let placesService = new kakao.maps.services.Places();
//
//         placesService.keywordSearch(keyword, function (result, status) {
//             if (status === kakao.maps.services.Status.OK) {
//                 displayPlaces(result);
//                 displayPagination(result);
//             } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
//                 alert("검색 결과가 없습니다.");
//                 return;
//             } else if (status === kakao.maps.services.Status.ERROR) {
//                 alert("검색 결과 중 오류가 발생했습니다.");
//                 return;
//             }
//         });
//     }
//
//     function displayPlaces(places) {
//         let listEl = document.getElementById("placesList");
//         let paginationEl = document.getElementById("pagination");
//
//         let bounds = new kakao.maps.LatLngBounds();
//
//         // 검색 결과 목록을 빈칸으로 초기화
//         while (listEl.hasChildNodes()) {
//             listEl.removeChild(listEl.lastChild);
//         }
//
//         // 지도에 표시되고 있는 마커를 제거
//         removeMarker();
//
//         for (let i = 0; i < places.length; i++) {
//             let place = places[i];
//
//             let marker = addMarker(place);
//
//             let itemEl = getListItem(i, place);
//             bounds.extend(place.latlng);
//             (function (marker, place) {
//                 kakao.maps.event.addListener(marker, "click", function () {
//                     createInfoWindow(place).open(map, marker);
//                 });
//                 itemEl.onclick = function () {
//                     createInfoWindow(place).open(map, marker);
//                 };
//             })(marker, place);
//
//             listEl.appendChild(itemEl);
//         }
//
//         map.setBounds(bounds);
//     }
//
//     function addMarker(place) {
//         let marker = new kakao.maps.Marker({
//             position: place.latlng,
//         });
//         marker.setMap(map);
//         markers.push(marker);
//         return marker;
//     }
//
//     function removeMarker() {
//         for (let i = 0; i < markers.length; i++) {
//             markers[i].setMap(null);
//         }
//         markers = [];
//     }
//
//     function getListItem(index, place) {
//         let el = document.createElement("li");
//         let itemStr = `
//             <span class="markerbg marker_${index + 1}"></span>
//             <div class="info">
//                 <h5>${place.place_name}</h5>
//                 <p>${place.road_address_name}</p>
//             </div>
//         `;
//         el.innerHTML = itemStr;
//         el.className = "item";
//         return el;
//     }
//
//     function displayPagination(pagination) {
//         let paginationEl = document.getElementById("pagination");
//         let fragment = document.createDocumentFragment();
//         let i;
//
//         while (paginationEl.hasChildNodes()) {
//             paginationEl.removeChild(paginationEl.lastChild);
//         }
//
//         for (i = 1; i <= pagination.last; i++) {
//             let el = document.createElement("a");
//             el.href = "#";
//             el.innerHTML = i;
//
//             if (i === pagination.current) {
//                 el.className = "on";
//             } else {
//                 el.onclick = (function (i) {
//                     return function () {
//                         pagination.gotoPage(i);
//                     };
//                 })(i);
//             }
//
//             fragment.appendChild(el);
//         }
//         paginationEl.appendChild(fragment);
//     }
// });

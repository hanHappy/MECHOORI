window.addEventListener("load", function () {
    const names = document.querySelectorAll(".addresses");
    let markers = [];



    for (let name of names) {
        markers.push(name.textContent);
    }



    kakao.maps.load(function () {
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng( 37.55265499581108, 126.93762683368536 ),

            level: 1
        };

        var map = new kakao.maps.Map(container, options);
        var geocoder = new kakao.maps.services.Geocoder();

        markers.forEach(function (address) {
            geocoder.addressSearch(address, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var marker = new kakao.maps.Marker({
                        position: new kakao.maps.LatLng(result[0].y, result[0].x)
                    });
                    marker.setMap(map);
                }
            });
        });

        let icon = new kakao.maps.MarkerImage(
            'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png',
            new kakao.maps.Size(31, 35));


        let points = {};
        for (i = 0; i < points.length; i++) {
            // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
            marker =     new kakao.maps.Marker({ position : points[i] });
            marker.setMap(map);

            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(points[i]);
        }

        function setBounds() {
            // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
            // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
            map.setBounds(bounds);
        }

        markers.forEach(function (address) {
            geocoder.addressSearch(address, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var marker = new kakao.maps.Marker({
                        position: new kakao.maps.LatLng(result[0].y, result[0].x)
                    });

                    var infowindow = new kakao.maps.InfoWindow({
                        content: address
                    });

                    kakao.maps.event.addListener(marker, 'mouseover', function () {
                        infowindow.open(map, marker);
                    });

                    kakao.maps.event.addListener(marker, 'mouseout', function () {
                        infowindow.close();
                    });

                    marker.setMap(map);
                }
            });
        });


    });
});

window.addEventListener("DOMContentLoaded", function () {
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
        markers.push({ name, address, contactNumber, id });
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
            const { name, address, contactNumber, id } = markerInfo;

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
        function setMapType(maptype) {
            var roadmapControl = document.getElementById('btnRoadmap');
            var skyviewControl = document.getElementById('btnSkyview');
            if (maptype === 'roadmap') {
                map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
                roadmapControl.className = 'selected_btn';
                skyviewControl.className = 'btn';
            } else {
                map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
                skyviewControl.className = 'selected_btn';
                roadmapControl.className = 'btn';
            }
        }

        document.querySelector(".zoomIn").addEventListener("click", zoomIn);
        document.querySelector(".zoomOut").addEventListener("click", zoomOut);

        document.getElementById('btnRoadmap').addEventListener('click', function() {
            setMapType('roadmap');
        });

        document.getElementById('btnSkyview').addEventListener('click', function() {
            setMapType('skyview');
        });

    });
});

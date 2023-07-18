let mapcontainer = document.getElementById("map1");
let options = {
  center: new kakao.maps.LatLng(37.5503, 126.9381),
  level: 2,
};

let map = new kakao.maps.Map(mapcontainer, options);

let blacknoodle = new kakao.maps.LatLng(37.55015, 126.93809);

let blackNoodle = new kakao.maps.Marker({
  position: blacknoodle,
});

blackNoodle.setMap(map);

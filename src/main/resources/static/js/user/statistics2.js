window.addEventListener("DOMContentLoaded", function () {
  let leftBtn = document.querySelector(".leftBtn");
  let rightBtn = document.querySelector(".rightBtn");
  let monthElement0 = document.querySelector("ul li:nth-child(2)");
  let monthElement1 = document.querySelector("ul li:nth-child(3)");
  let monthElement2 = document.querySelector("ul li:nth-child(4)");
  let dateTxt = document.querySelector(".dateTxt");

  leftBtn.addEventListener("click", function () {
    let currentMonth1 = parseInt(monthElement0.textContent);
    monthElement0.textContent =
      currentMonth1 - 1 <= 0 ? "12월" : currentMonth1 - 1 + "월";
    let currentMonth2 = parseInt(monthElement1.textContent);
    monthElement1.textContent =
      currentMonth2 - 1 <= 0 ? "12월" : currentMonth2 - 1 + "월";
    let currentMonth3 = parseInt(monthElement2.textContent);
    monthElement2.textContent =
      currentMonth3 - 1 <= 0 ? "12월" : currentMonth3 - 1 + "월";

    updateDateRange();
  });

  rightBtn.addEventListener("click", function () {
    let currentMonth1 = parseInt(monthElement0.textContent);
    monthElement0.textContent =
      currentMonth1 + 1 > 12 ? "1월" : currentMonth1 + 1 + "월";
    let currentMonth2 = parseInt(monthElement1.textContent);
    monthElement1.textContent =
      currentMonth2 + 1 > 12 ? "1월" : currentMonth2 + 1 + "월";
    let currentMonth3 = parseInt(monthElement2.textContent);
    monthElement2.textContent =
      currentMonth3 + 1 > 12 ? "1월" : currentMonth3 + 1 + "월";

    updateDateRange();
  });

  function updateDateRange() {
    let currentDate = new Date();
    let year = currentDate.getFullYear();
    let startMonth = parseInt(monthElement1.textContent);
    let endMonth = startMonth + 1;

    let startDate = new Date(year, startMonth - 1, 1);
    let endDate = new Date(year, endMonth - 1, 0);

    let startDateStr = formatDate(startDate);
    let endDateStr = formatDate(endDate);
    dateTxt.textContent = startDateStr + " ~ " + endDateStr;
  }

  function formatDate(date) {
    let year = date.getFullYear();
    let month = ("0" + (date.getMonth() + 1)).slice(-2);
    let day = ("0" + date.getDate()).slice(-2);
    return year + "." + month + "." + day;
  }

  updateDateRange();



  async function updateDateRange() {

    let categoryData;
    let url = `/api/user/my-page/statistics2`;

    await fetch(url)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        categoryData = data;

        // console.log(categoryData);
        let data1 = categoryData.shift().rateCount;
        let data2 = categoryData.shift().rateCount;
        let data3 = categoryData.shift().rateCount;
        let data4 = categoryData.shift().rateCount;
        let data5 = categoryData.shift().rateCount;
        let data6 = categoryData.shift().rateCount;
        let data7 = categoryData.shift();
        let data8 = categoryData.shift();
        let data9 = categoryData.shift();
        let data10 = categoryData.shift();

        console.log(data1);
        console.log(data2);
        console.log(data3);
        console.log(data4);
        console.log(data5);
        console.log(data6);
        console.log(data7);
        console.log(data8);
        console.log(data9);
        console.log(data10);

        let dataArray = [
          data1, data2, data3, data4, data5, data6, data7, data8, data9, data10
        ]


        new Chart(document.getElementById("barChart"), {
          // type: 'bar',
          type: 'doughnut',
          data: {
            labels: ["한식", "중식", "일식", "양식", "분식", "베트남", "태국", "인도", "튀르키예", "멕시칸"],
            // labels: ["한식", "중식"],
            datasets: [{
              backgroundColor: [
                '#FFD1AA', // 살구색
                '#B0E0E6', // 파우더 블루
                '#FFC0CB', // 핑크
                '#D8BFD8', // 산호색
                '#E6E6FA', // 라벤더
                '#F0E68C', // 카나리아 옐로우
                '#98FB98', // 라이트 그린
                '#FFA07A', // 라이트 코랄
                '#87CEFA', // 라이트 스카이 블루
                '#F08080' // 연분홍색
              ],
              data: [6,2,1,null,null,2,null,null,1,1],
              // data: dataArray
              data: [data1.rateCount,
                      data2.rateCount,
                      data3.rateCount,
                      data4.rateCount,
                      data5.rateCount,
                      data6.rateCount,
                      data7.rateCount,
                      data8.rateCount,
                      data9.rateCount,
                      data10.rateCount]
            }]
          },
          options: {
            // radius: '90%',

            responsive: true, // 차트 크기 반응형 설정
            maintainAspectRatio: false, // 가로 세로 비율 유지 여부
            cutoutPercentage: 50, // 도넛 차트의 가운데 빈 공간 크기 (0~100)
            rotation: -0.5 * Math.PI, // 차트 시작 각도 (-0.5 * Math.PI는 12시 방향)
            circumference: 2 * Math.PI, // 차트의 감싸는 각도 (전체 원)

            legend: {
              display: true
            },

            title: {
              display: true,
              text: '내가 평가한 식당 카테고리?'
            },


            maintainAspectRatio: false, // 차트의 가로 세로 비율 유지 여부
            layout: {
              padding: {
                left: 30,
                right: 30,
                top: 0,
                bottom: 10
              },
            },

            animation: {
              animateRotate: true, // (default네)
              animateScale: true,
              duration: 3000 // 애니메이션 지속 시간 (밀리초)
            }
          }

        });
      });
  }

});




// let priceData = {other:0,me:0};

// await fetch(`/api/user/statistics`)
// // .then(function(response){
// //   console.log(response.json);
// //   return response.json
// // })
//   .then(response => response.json())
//   .then(data => {
//     console.log(data);
//     priceData = data;

//   })
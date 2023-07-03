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

    let valueData = {other:0,me:0};
    let url = `/api/user/my-page/statistics`;
    await fetch(url)
      .then(response => response.json())
      .then(data =>{
        valueData = data;
        console.log(data);
      })


    new Chart(document.getElementById("barChart"), {
      type: 'bar',
      data: {
        labels: ["유저평균", "나의평균"],
        datasets: [
          {
            // label: "Population (millions)",
            backgroundColor: ["#2292F9", "#FFE551"],
            data: [valueData.other, valueData.me],
            // borderWidth: 10,
            barThickness: 1,
            maxBarThickness: 3
            // inflateAmount: 1, 
            // borderColor: #333
          }
        ]
      },
      options: {

        legend: { display: false },

        title: {
          display: true,
          text: '가성비 성과 비교'
        },

        // responsive: true, // 차트가 반응형으로 크기 조절 여부
        // maintainAspectRatio: false, // 차트의 가로 세로 비율 유지 여부
        layout: {
          padding: {
            left: 30,
            right: 30,
            top: 0,
            bottom: 10
          },
        },

        scales: {
          xAxes: [{
            ticks: {
              fontColor: "#333", // x축 눈금의 글자 색상
              fontSize: 14 // x축 눈금의 글자 크기
            }
          }],
          yAxes: [{
            ticks: {
              min: 80, // y축 최소값
              max: 110, // y축 최대값
              stepSize: 10, // y축 눈금 간격
              fontColor: "#333", // y축 눈금의 글자 색상
              fontSize: 12 // y축 눈금의 글자 크기
            }
          }]
        },

        animation: {
          duration: 1000 // 애니메이션 지속 시간 (밀리초)
        }

      }
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
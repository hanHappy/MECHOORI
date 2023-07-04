window.addEventListener("DOMContentLoaded", function () {

  updateDateRange();


  async function updateDateRange() {

    let valueData = { other: 0, me: 0 };
    let url = `/api/user/my-page/statistics`;
    await fetch(url)
      .then(response => response.json())
      .then(data => {
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


    let gap = this.document.querySelector("#gap");
    let gapM = this.document.querySelector("#gapM");

    gap.textContent = Math.abs(valueData.me - valueData.other);

    console.log(gap.textContent);

    if (valueData.me < valueData.other) {
      gapM.textContent = "낮은"
    } else {
      gapM.textContent = "높은"
    }
  }


});




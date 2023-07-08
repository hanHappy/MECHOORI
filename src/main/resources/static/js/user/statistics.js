const dataBtn1 = document.querySelector(".chart-btn1");
const dataBtn2 = document.querySelector(".chart-btn2");
const dataBtn3 = document.querySelector(".chart-btn3");
const barChart = document.querySelector("#barChart");

updateDateRange();

dataBtn2.onclick = function () {
  console.log("클릭");
  barChart.innerHTML = "";
  (async () => {

    let categoryData;
    let url = `/api/user/my-page/statistics2`;


    await fetch(url)
      .then(response => response.json())
      .then(data => {
        // console.log(data);
        categoryData = data;

        // console.log(categoryData.nickname);
        // console.log(categoryData);

        // let chart = 
        new Chart(document.getElementById("barChart"), {
          type: 'doughnut',
          data: {
            labels: ["1", "2", "3"],
            datasets: [{
              backgroundColor: [
                '#FF6E54',// (코랄 핑크)
                '#FFC153', //(황토색)
                '#FFD700',// (골든 옐로우)
                '#ACD8AA', //(민트색)
                '#87CEEB', //(스카이 블루)
                '#9370DB', //(퍼플)
                '#FFB6C1', //(라이트 핑크)
                '#FF4136',// (타마린)
                '#FF69B4', //(핑크 보라)
                '#F08080' // 연분홍색
              ],
              data: [10, 5, 2]
            }]
          },

          options: {
            maintainAspectRatio: false, // 가로 세로 비율을 유지할지 여부 (기본값: true)
            cutoutPercentage: 30, // 도넛 차트의 가운데 빈 공간 크기 (0~100, 기본값: 50)

            legend: {
              // display: true // 범례 표시 여부 (기본값: true)
            },
            title: {
              display: true, // 제목 표시 여부 (기본값: true)
              text: '내가 평가한 식당 카테고리?' // 차트 제목 텍스트
            },
            layout: {
              padding: {
                left: 5, // 왼쪽 여백
                right: 5, // 오른쪽 여백
                top: 10, // 상단 여백
                bottom: 10 // 하단 여백
              }
            },
            animation: {
              // animateRotate: true, // 차트 회전 애니메이션 효과 여부 (기본값: true)
              // animateScale: true, // 차트 크기 애니메이션 효과 여부 (기본값: true)
              duration: 3000
            }
          }

        });

        for (let v of categoryData) {
          chart.data.labels.push(v.name);
          chart.data.datasets[0].data.push(v.rateCount);
        }

        // for(let i=0; i<categoryData.length; i++){
        //   console.log(chart.data.labels[i]);
        //   console.log(chart.data.datasets[0].data(i));
        // }

        let cateText = document.querySelector("#cateText");
        cateText.textContent = chart.data.labels[0];

      });
  })()

}

dataBtn1.onclick =function(){
  updateDateRange();
}

// ---------- 1번 차트 버튼 -----------------
  
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
  } else if (valueData.me == valueData.other) {
    gapM.textContent = "같은"
  } else {
    gapM.textContent = "높은"
  }
}




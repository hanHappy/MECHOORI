const dataBtn1 = document.querySelector(".chart-btn1");
const dataBtn2 = document.querySelector(".chart-btn2");
const dataBtn3 = document.querySelector(".chart-btn3");
const barChart = document.querySelector("#barChart");

updateDateRange();


// ---------- 1번 차트 -----------------
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

          // inflateAmount: 1, 
          // borderColor: #333
        }
      ]
    },
    options: {
      // maintainAspectRatio: false, 
      // barThickness: 1,
      // maxBarThickness: 3,
      legend: { display: false },

      title: {
        display: true,
        text: '가성비 성과 비교',
        fontSize: 18
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






// ---------- 2번 차트 -----------------
const dataSection = document.querySelector("#dataSection");
const nickName = document.querySelector("#nickName").dataset.nickname;

console.log(nickName);
dataBtn2.onclick = function () {
  // console.log("클릭");
  dataSection.innerHTML = "";


  (async () => {

    let categoryData;
    let url = `/api/user/my-page/statistics2`;

    dataSection.innerHTML =
      `
      <canvas id="barChart" width="375" height="130"></canvas>

      <section class="MiddleBack">
          <div class="rateImg"></div>
          <div class="rateTextArea">
            <p class="rateText">
              <span sec:authorize="isAuthenticated()" sec:authentication="principal.nickname" class="username">${nickName}</span>
               회원님은 <br/>
              <span id="cateText" style="color: #2292F9">중식</span>을 
              가장 많이 드셨군요!
              
            </p>
          </div>
        </div>
      </section>
      `

    await fetch(url)
      .then(response => response.json())
      .then(data => {
        categoryData = data;

        let chart =
          new Chart(document.getElementById("barChart"), {
            // type: 'line',
            // type: 'pie',
            type: 'doughnut',
            data: {
              labels: [],
              datasets: [{
                backgroundColor: [
                  'rgb(255, 99, 132)',
                  'rgb(54, 162, 235)',
                  'rgb(255, 205, 86)',
                  'rgb(0, 184, 148)',
                  '#9370DB', //(퍼플)
                  '#FFC153', //(황토색)
                  '#FF6E54',// (코랄 핑크)
                  '#FFB6C1', //(라이트 핑크)
                  '#ACD8AA', //(민트색)
                  '#FF4136',// (타마린)
                  // '#FFD700',// (골든 옐로우)
                ],
                data: []
              }]
            },

            options: {
              maintainAspectRatio: false, // 가로 세로 비율을 유지할지 여부 (기본값: true)
              cutoutPercentage: 30, // 도넛 차트의 가운데 빈 공간 크기 (0~100, 기본값: 50)

              legend: {
                // display: false
                display: true // 범례 표시 여부 (기본값: true)
              },
              title: {
                display: true, // 제목 표시 여부 (기본값: true)
                text: '내가 방문한 식당 카테고리', // 차트 제목 텍스트
                fontSize: 18
              },
              layout: {
                padding: {
                  left: 30, // 왼쪽 여백
                  right: 30, // 오른쪽 여백
                  top: 10, // 상단 여백
                  bottom: 10 // 하단 여백
                }
              },
              animation: {
                // display:true,
                animateRotate: true, // 차트 회전 애니메이션 효과 여부 (기본값: true)
                animateScale: true, // 차트 크기 애니메이션 효과 여부 (기본값: true)
                duration: 1000
              },
              //   pieceLabel: {
              //     mode:"label",
              //     position:"outside",
              //     fontSize: 8,
              //     fontStyle: 'bold'
              //  },
            }

          });

        for (let v of categoryData) {
          chart.data.labels.push(v.name);
          chart.data.datasets[0].data.push(v.rateCount);
        }

        let cateText = document.querySelector("#cateText");
        cateText.textContent = chart.data.labels[0];

      });
  })()

}






// ---------- 3번 차트 -----------------

dataBtn3.onclick = function () {
  console.log("3번 차트")
  dataSection.innerHTML = "";

    (async () => {

      let myRankDate;
      let url = `/api/user/my-page/statistics3`;

      dataSection.innerHTML =
        `
    <canvas id="aaChart" width="375" height="540"></canvas>

    <section class="MiddleBack">
        <div class="rateImg"></div>
        <div class="rateTextArea">
          <p class="rateText">
            <span sec:authorize="isAuthenticated()" sec:authentication="principal.nickname" class="username"> 회원</span> 님이 평가한 가성비 1등<br/>
           식당은 <span id="resText" style="color: #2292F9">000</span>네요!
          </p>
        </div>
      </div>
    </section>
    `

      await fetch(url)
        .then(response => response.json())
        .then(data => {
          // console.log(data);
          myRankDate = data;

          // console.log(myRankDate(0));
          console.log(data);

          let chart =
            new Chart(document.getElementById("aaChart"), {
              type: 'bar',
              data: {
                // labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10'],
                labels: [],
                datasets: [
                  {
                    backgroundColor: [
                      'rgb(255, 99, 132)',
                      'rgb(54, 162, 235)',
                      'rgb(255, 205, 86)',
                      'rgb(0, 184, 148)',
                      '#9370DB', //(퍼플)
                      '#FFC153', //(황토색)
                      '#FF6E54',// (코랄 핑크)
                      '#FFB6C1', //(라이트 핑크)
                      '#ACD8AA', //(민트색)
                      '#FF4136',// (타마린)
                      '#FFD700',// (골든 옐로우)
                    ],
                    // data: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
                    data: []
                  }]
              },
              options: {
                legend: {
                  display: false
                  // display: true // 범례 표시 여부 (기본값: true)
                },
                title: {
                  display: true, // 제목 표시 여부 (기본값: true)
                  text: '나만의 가성비 식당 TOP10',
                  fontSize: 18
                },
                layout: {
                  padding: {
                    left: 10, // 왼쪽 여백
                    right: 10, // 오른쪽 여백
                    top: 10, // 상단 여백
                    bottom: 10 // 하단 여백
                  }
                },
                scales: {
                  xAxes: [{
                    ticks: {
                      fontColor: "#333", // x축 눈금의 글자 색상
                      fontSize: 11 // x축 눈금의 글자 크기
                    }
                  }],
                  yAxes: [{
                    ticks: {
                      min: 80, // y축 최소값
                      max: 150, // y축 최대값
                      stepSize: 10, // y축 눈금 간격
                      fontColor: "#333", // y축 눈금의 글자 색상
                      fontSize: 12 // y축 눈금의 글자 크기
                    }
                  }]
                },
                maintainAspectRatio: false, // 가로 세로 비율을 유지할지 여부 (기본값: true)
                animation: {
                  duration: 3000 // 애니메이션 지속 시간 (밀리초)
                },
              }

            });
          // let chartContainer = document.getElementById("aaChart");
          // chartContainer.style.width = "500px";
          // chartContainer.style.height = "400px";

          for (let m of myRankDate) {
            chart.data.labels.push(m.resName);
            chart.data.datasets[0].data.push(m.value);
          }


          // for(let i=0; i<myRankDate.length; i++){
          //   console.log(chart.data.labels[i]);
          //   console.log(chart.data.datasets[0].data[i]);
          // }

          let resText = document.querySelector("#resText");
          resText.textContent = chart.data.labels[0];

        });
    })()






}

  // ------- 1번 버튼---------
  dataBtn1.onclick = function () {

    dataSection.innerHTML =
      `    <section id="dataSection">
      <canvas id="barChart" width="55" height="60"></canvas>
  
      <section class="MiddleBack">
        <div class="rateImg"></div>
        <div class="rateTextArea">
          <p class="rateText">
            메추리 전체 회원보다 <br />
            <span id="gap" style="color: #2292F9">30</span><span style="color: #2292F9">%</span> <span
              id="gapM">높은</span> 가성비를 누리셨네요!
          </p>
        </div>
      </section>
    </section>`

    updateDateRange();
  }
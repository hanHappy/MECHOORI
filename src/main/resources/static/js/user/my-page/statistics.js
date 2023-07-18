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
          backgroundColor: [
            'rgba(34, 146, 249, 0.7)', 'rgba(255, 229, 81, 0.7)'
            // "#2292F9", "#FFE551" 
          ],
          data: [valueData.other, valueData.me],
          borderColor: [
            "#2292F9", "#FFE551"
          ],
          borderWidth: 2
        }
      ]
    },
    options: {
      legend: { display: false },

      title: {
        display: true,
        text: '가성비 성과 비교',
        fontSize: 18
      },
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


  // 도넛 차트를 그리는 함수
  (async () => {

    let categoryData;
    let url = `/api/user/my-page/statistics2`;

    dataSection.innerHTML =
      `
    <canvas id="barChart"></canvas>

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
        console.log(data);
        categoryData = data;
      })

    // 데이터 배열
    var data = {
      labels: [],
      datasets: [{
        data: [],
        backgroundColor: [
        'rgb(255, 99, 132, 0.7)',
        'rgb(54, 162, 235, 0.7)',
        'rgb(255, 205, 86, 0.7)',
        'rgb(0, 184, 148, 0.7)',
        'rgba(147, 112, 219, 0.7)',
        'rgba(252, 246, 189, 0.7)',
        'rgba(255, 110, 84, 0.7)',
        'rgba(255, 182, 193, 0.7)',
        'rgba(172, 216, 170, 0.7)',
        'rgba(255, 65, 54, 0.7)'

        // '#9370DB', //(퍼플)
        // "#FCF6BD",
        // '#FF6E54',// (코랄 핑크)
        // '#FFB6C1', //(라이트 핑크)
        // '#ACD8AA', //(민트색)
        // '#FF4136',// (타마린)
      ]
          }]
  };

  for (let v of categoryData) {
    data.labels.push(v.name);
    data.datasets[0].data.push(v.rateCount);
  }

  // 도넛 차트 옵션
  var options = {
    cutoutPercentage: 40,
    title: {
      display: true,
      text: '내가 방문한 식당 카테고리',
      fontSize: 18
    },
    legend: {
      display: true,
      position: 'bottom'
    },
    plugins: {
      datalabels: {
        color: '#111',
        textAlign: 'center',
        font: {
          lineHeight: 1.6
        },
        formatter: function (value, ctx) {
          var dataset = ctx.chart.data.datasets[0];
          var total = dataset.data.reduce(function (previousValue, currentValue) {
            return previousValue + currentValue;
          });
          var percentage = ((value * 100) / total).toFixed(1) + '%';
          return dataset.labels[ctx.dataIndex] + '\n' + percentage;
        }
      }
    },
    animation: {
      duration: 1000
    }
  };

  // 도넛 차트 생성
  new Chart(document.getElementById("barChart"), {
    type: 'doughnut',
    data: data,
    options: options
  });

  // html 입력
  let cateText = document.querySelector("#cateText");
  cateText.textContent = data.labels[0];
}) ()
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
    <canvas id="aaChart" width="375" height="418"></canvas>

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
        myRankDate = data;
      })

    // 데이터 배열
    var data = {
      labels: [],
      datasets: [{
        data: [],
        backgroundColor: [

          'rgba(255, 99, 132, 0.5)',
          'rgba(54, 162, 235, 0.5)',
          'rgba(255, 206, 86, 0.5)',
          'rgba(138, 201, 38, 0.5)',
          'rgba(255, 159, 64, 0.5)',
          'rgba(196, 196, 196, 0.5)',
          'rgba(0, 159, 157, 0.5)',
          'rgba(252, 226, 100, 0.5)',
          'rgba(231, 29, 54, 0.5)',
          'rgba(255, 184, 184, 0.5)'
          // "#FF6384",
          // "#36A2EB",
          // "#FFCE56",
          // "#8AC926",
          // "#FF9F40",
          // "#C4C4C4",
          // "#009F9D",
          // "#FCF6BD",
          // "#E71D36",
          // "#FFB8B8"
        ],
        borderColor: [
          'rgba(255, 99, 132)',
          'rgba(54, 162, 235)',
          'rgba(255, 206, 86)',
          'rgba(138, 201, 38)',
          'rgba(255, 159, 64)',
          'rgba(196, 196, 196)',
          'rgba(0, 159, 157)',
          'rgba(252, 226, 100)',
          'rgba(231, 29, 54)',
          'rgba(255, 184, 184)'
        ],
        borderWidth: 2
      }]
    };

    for (let m of myRankDate) {
      data.labels.push(m.resName);
      data.datasets[0].data.push(m.value);
    }

    // 차트 옵션
    var options = {
      title: {
        display: true,
        text: '나만의 가성비 식당 TOP10',
        fontSize: 18
      },
      legend: {
        display: false,
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
      animation: {
        duration: 2000
      }
    };

    // 도넛 차트 생성
    new Chart(document.getElementById("aaChart"), {
      type: 'bar',
      data: data,
      options: options
    });



    // html 입력
    let resText = document.querySelector("#resText");
    resText.textContent = data.labels[0];

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
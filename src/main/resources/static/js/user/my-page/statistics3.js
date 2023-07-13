
updateDateRange();
async function updateDateRange() {

  let myRankDate;
  let url = `/api/user/my-page/statistics3`;

  await fetch(url)
    .then(response => response.json())
    .then(data => {
      // console.log(data);
      myRankDate = data;

      // console.log(myRankDate(0));
      // console.log(data);

      let chart =
        new Chart(document.getElementById("barChart"), {
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

        for (let m of myRankDate){
          chart.data.labels.push(m.resName);
          chart.data.datasets[0].data.push(m.value);
        }


      for(let i=0; i<myRankDate.length; i++){
        console.log(chart.data.labels[i]);
        console.log(chart.data.datasets[0].data[i]);
      }

      let resText = document.querySelector("#resText");
      resText.textContent = chart.data.labels[0];

    });
}




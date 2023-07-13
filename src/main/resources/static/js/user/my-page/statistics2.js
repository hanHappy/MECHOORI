window.addEventListener("DOMContentLoaded", function () {


updateDateRange();

async function updateDateRange() {

  let categoryData;
  let url = `/api/user/my-page/statistics2`;

  await fetch(url)
    .then(response => response.json())
    .then(data => {
      console.log(data);
      categoryData = data;

      // console.log(categoryData.nickname);
      console.log(categoryData);

      let chart =
        new Chart(document.getElementById("barChart"), {
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
                "#FCF6BD",
                // '#FFC153', //(황토색)
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
            responsive: true, // 차트 크기를 반응형으로 설정할지 여부 (기본값: true)
            maintainAspectRatio: false, // 가로 세로 비율을 유지할지 여부 (기본값: true)
            cutoutPercentage: 30, // 도넛 차트의 가운데 빈 공간 크기 (0~100, 기본값: 50)
            // rotation: -0.5 * Math.PI, // 차트 시작 각도 (-0.5 * Math.PI는 12시 방향, 기본값: -0.5 * Math.PI)
            // circumference: 2 * Math.PI, // 차트의 감싸는 각도 (전체 원, 기본값: 2 * Math.PI)
            legend: {
              // display: true // 범례 표시 여부 (기본값: true)
            },
            title: {
              display: true, // 제목 표시 여부 (기본값: true)
              text: '내가 평가한 식당 카테고리?', // 차트 제목 텍스트
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
            animation: {
              // animateRotate: true, // 차트 회전 애니메이션 효과 여부 (기본값: true)
              // animateScale: true, // 차트 크기 애니메이션 효과 여부 (기본값: true)
              duration: 1000 // 애니메이션 지속 시간 (밀리초)
              // onComplete: function (){
              //   let cateText = document.querySelector("#cateText");
              //   cateText.textContent = chart.data.labels[0];
              // }
            },
            
          }

        });

      for (let v of categoryData) {
        chart.data.labels.push(v.name);
        chart.data.datasets[0].data.push(v.rateCount);
      }

      // for(let i=0; i<categoryData.length; i++){
        // console.log(chart.data.labels[i]);
        // console.log(chart.data.datasets[i]);
        // console.log(chart.data.datasets[0].data(i));
      // }

      let cateText = document.querySelector("#cateText");
      cateText.textContent = chart.data.labels[0];

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
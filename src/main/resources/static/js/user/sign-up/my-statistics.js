window.addEventListener("load", function () {
  let leftBtn = document.getElementById("leftBtn");
  let rightBtn = document.getElementById("rightBtn");
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
    let year = 2023;
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
});

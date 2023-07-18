
const modal = document.getElementById("modalWrap")
const btn = document.getElementById("popupBtn")


modal.style.display = "none";

// FIXME 브라우저 콘솔 에러 
// login.js:6 Uncaught TypeError: Cannot read properties of null (reading 'style')

btn.onclick = function() {
    modal.style.display ="block";
}

window.onclick = function(event) {
    if(event.target === modal){
        modal.style.display = "none";
    }
}
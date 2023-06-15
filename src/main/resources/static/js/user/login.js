
const modal = document.getElementById("modalWrap")
const btn = document.getElementById("popupBtn")


modal.style.display = "none";


btn.onclick = function() {
    modal.style.display ="block";
}

window.onclick = function(event) {
    if(event.target === modal){
        modal.style.display = "none";
    }
}
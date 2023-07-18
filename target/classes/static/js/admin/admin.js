let listSection = document.querySelector(".restaurant-list")
let deleteBtn = document.querySelector("#delete-btn")

let id =null;




listSection.addEventListener("click", (e)=>{
        e.preventDefault();

    if(e.target.tagName != 'BUTTON')
        return

    let button = e.target
    id = button.dataset.id
    console.log(id);
})

deleteBtn.addEventListener("click",(e)=>{

    fetch(`/api/admin/menu/${id}`,{
        method: 'DELETE'
    })
})
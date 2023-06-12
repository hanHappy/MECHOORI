let datas = document.querySelectorAll('.data');
let inputs = document.querySelectorAll('input');
let edit = document.querySelector('.edit');
let cmplt = document.querySelector('.cmplt');

edit.onclick = function(){
    for(let data of datas)
        data.style.display = 'none';
    for(let input of inputs){
        input.style.display = 'block'
        console.log(input.style.display);
    }
    edit.style.display = 'none';
    cmplt.style.display = 'block';
}

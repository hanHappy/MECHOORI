let menu = null;
let modalMenu = document.querySelector('.modal-menu');

if(document.querySelector('#menu'))
    menu = document.querySelector('#menu');
    
menu.addEventListener('click', (e)=>{
    e.preventDefault();
    modalMenu.classList.remove('d-none')
    setTimeout(() => {
        modalMenu.classList.add('show')
    }, 50);
});

this.onclick = (e) => {
    if(e.target.className != 'screen')
        return;
    modalMenu.classList.remove('show');
    setTimeout(() => {
        modalMenu.classList.add('d-none');
    }, 500);
};


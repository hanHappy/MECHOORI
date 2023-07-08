let menu = null;
let modalMenu = document.querySelector('.modal-menu');

if(document.querySelector('#menu'))
    menu = document.querySelector('#menu');
    
menu.addEventListener('click', (e)=>{
    e.preventDefault();
    modalMenu.classList.add('show');
});

this.onclick = (e) => {
    if(e.target.className != 'screen')
        return;
    modalMenu.classList.remove('show');
};


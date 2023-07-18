export default class ModalCheck {
    constructor(){
    }
    show(modalScreen, modalPanel){
        modalScreen.classList.remove('d-none')
            setTimeout(() => {
                modalPanel.classList.add('show')
            }, 50);
    }
}
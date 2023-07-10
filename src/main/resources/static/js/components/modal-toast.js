export default class ModalToast {
    constructor(){
    }
    show(modal){
        modal.classList.remove("d-none");
        setTimeout(() => {
            modal.classList.add("active");
            setTimeout(() => {
                modal.classList.remove("active");
                setTimeout(() => {
                    modal.classList.add("d-none");
                }, 1000);
            }, 1200);
        }, 100);
    }
}
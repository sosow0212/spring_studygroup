



let modals = document.querySelectorAll('.modalClass');

for(let i=0; i<modals.length; i++) {
    let modal = modals.item(i);
    let show = modal.querySelector('.show');
    let close = modal.querySelector('.close');
    let background = modal.querySelector('.background');

    show.addEventListener('click', () => {
        background.className = "background show";
    })

    close.addEventListener('click', () => {
        background.className = "background";
    })

}

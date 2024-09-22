const menu = document.querySelector('menu');
const menubtn = document.getElementById('menu-btn');

menubtn.addEventListener('click', onMenuClick);

function onMenuClick() {
    menu.classList.toggle('d-none');
}
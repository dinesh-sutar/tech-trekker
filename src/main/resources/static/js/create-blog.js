const fileInput = document.getElementById('banner');
const bannerPreview = document.querySelector('.banner-preview');
fileInput.addEventListener('change', OnFileChange);

function OnFileChange(event) {
    const file = event.target.files[0];
    const url = URL.createObjectURL(file);

    // const img = document.createElement('img');
    // img.setAttribute('src', url);

    bannerPreview.innerHTML = `<img src="${url}">`;
}
document.addEventListener('scroll', function () {
    const navbar = document.querySelector('nav');
    const recentSection = document.querySelector('.recent-blog-section');
    const recentSectionPosition = recentSection.getBoundingClientRect();

    if (recentSectionPosition.top <= navbar.offsetHeight) {
        navbar.style.backgroundColor = '#d8bfd8';
        navbar.style.padding = '1rem';
    } else {
        navbar.style.backgroundColor = 'initial';
    }
});
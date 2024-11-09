let left = document.querySelectorAll('.FormLeft');
let buttonright = document.querySelectorAll('.btn-insert-delete');

left.forEach(element => {
    element.style.display = 'none';
});
console.log("andre huilo");
console.log(left.length);
console.log(buttonright.length);

for (let i = 0; i < buttonright.length; i++) {
    buttonright[i].addEventListener('click', () => {
        for (let j = 0; j < left.length; j++) {
            if (j === i) {
                left[j].style.display = 'block';
                buttonright[j].classList.add('active'); 
            } else {
                left[j].style.display = 'none';
                buttonright[j].classList.remove('active');
            }
        }
    });
}



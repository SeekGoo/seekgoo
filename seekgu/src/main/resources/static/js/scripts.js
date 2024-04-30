/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

document.addEventListener('DOMContentLoaded', function() {
    const spans = document.querySelectorAll('.menus .menu');
    
    // 초기 상태로 "모집중" 텍스트에 스타일을 적용합니다.
    spans[0].style.color = 'black';
    spans[1].style.color = 'gray';
    spans[2].style.color = 'gray';
    spans[0].style.textDecoration = 'underline';
    
    spans.forEach(span => {
        span.addEventListener('click', function() {
            // 클릭된 요소의 텍스트 색상을 검정색으로 변경하고 밑줄을 추가합니다.
            this.style.color = 'black';
            this.style.textDecoration = 'underline';
    
            // 나머지 요소들의 텍스트 색상을 회색으로 변경하고 밑줄을 제거합니다.
            spans.forEach(otherSpan => {
                if (otherSpan !== this) {
                    otherSpan.style.color = 'gray';
                    otherSpan.style.textDecoration = 'none';
                }
            });
        });
    });
});
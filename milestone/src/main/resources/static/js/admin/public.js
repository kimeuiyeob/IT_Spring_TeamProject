const body = document.querySelector('body');
const $body = $('body');

/* ----------사이드 메뉴 마우스 이벤트------------- */
var $menuLink = $('.menu-icon').closest('.menu-link');
var $active = $('.active');

$menuLink.on("click", function () {
    $(this).next().slideToggle(100);
    $(this).css('color', '#fff');
})
$menuLink.on("mouseover", function () {
    $(this).css('color', '#fff');
})
$menuLink.on("mouseout", function () {
    $(this).css('color', '#9D9DA6');
})

$active.on("mouseover", function () {
    $(this).css('color', '#fff');
})
$active.on("mouseout", function () {
    $(this).css('color', '#9D9DA6');
})

/* ------------------헤더 검색 클릭--------------------- */
// const $headerSearch = $('.toolImg-wrap').eq(0);
// const $headerSearchDropdown = $('.header-search-box');

// let check = false;

// $body.on('click', function (e) {
//     if (check) {
//         if (e.target.closest('.header-search-box') == e.currentTarget.querySelector('.header-search-box').closest('.header-search-box')) {
//         } else {
//             $headerSearchDropdown.css('display', 'none');
//             $headerSearch.css('background-color', '#fff');
//             check = !check;
//         }
//     }
// })
// $headerSearch.on("click", function () {
//     if ($headerSearchDropdown.css('display') == 'none') {
//         $headerSearch.css('background-color', '#f6f8fa');
//         $headerSearchDropdown.css('display', 'block');
//         setTimeout(() => {
//             check = !check;
//         }, 100);
//     }
// })

/* -------------------시계---------------------- */
function showClock() {
    var currentDate = new Date();
    var divClock = document.getElementById("divClock");

    var msg = currentDate.getHours() + "시 "
    msg += currentDate.getMinutes() + "분 ";

    divClock.innerText = msg;

    setTimeout(showClock, 1000);
}


/*--------------------------------------------------*/
const $reset1 = $("#reset1");
const $option1 = $("#option1");
const $option2 = $("#option2");
const $reset2 = $("#reset2");
const $option3 = $("#option3");
const $option4 = $("#option4");
const $reset3 = $("#reset3");
const $option5 = $("#option5");
const $option6 = $("#option6");
const $reset4 = $("#reset4");
const $option7 = $("#option7");
const $reset5 = $("#reset5");
const $option8 = $("#option8");
const $reset6 = $("#reset6");
const $option9 = $("#option9");
const $option10 = $("#option10");
const $reset7 = $("#reset7");
const $option11 = $("#option11");
const $option12 = $("#option12");

$reset1.click(function(){
    $option1.text("옵션 선택");
    $option2.text("옵션 선택");
});

$reset2.click(function(){
    $option3.text("옵션 선택");
    $option4.text("옵션 선택");
});

$reset3.click(function(){
    $option5.text("옵션 선택");
    $option6.text("옵션 선택");
});
$reset4.click(function(){
    $option7.text("옵션 선택");
});
$reset5.click(function(){
    $option8.text("옵션 선택");
    $option12.text("옵션 선택");
});
$reset6.click(function(){
    $option9.text("옵션 선택");
    $option10.text("옵션 선택");
});
$reset7.click(function(){
    $option11.text("옵션 선택");
});

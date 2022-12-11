var swiper = new Swiper(".mySwiper", {
    pagination: {
        el: ".swiper-pagination",
        type: "fraction",
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    // autoplay: {     //자동슬라이드 (false-비활성화)
    //   delay: 3000, // 시간 설정
    //   disableOnInteraction: false, // false-스와이프 후 자동 재생
    // },
    loop: true,   // 슬라이드 반복 여부
    loopAdditionalSlides: 1,
});

/* 다음 버튼 */
var classHtml1 = $(".swiper-button-next");
var color1 = $(".banner2");

classHtml1.on("click", function () {
    console.log($(".swiper-pagination-current").text());
    if ($(".swiper-pagination-current").text() == 1) {
        $(".banner2").css('background-color', 'rgb(48, 52, 65)');
    } else if ($(".swiper-pagination-current").text() == 2) {
        $(".banner2").css('background-color', 'rgb(55, 17, 108)');
    } else if ($(".swiper-pagination-current").text() == 3) {
        $(".banner2").css('background-color', 'rgb(55, 184, 107)');
    } else if ($(".swiper-pagination-current").text() == 4) {
        $(".banner2").css('background-color', 'rgb(10, 195, 221)');
    }
});
/* 이전 버튼 */
var classHtml2 = $(".swiper-button-prev");

classHtml2.on("click", function () {
    console.log($(".swiper-pagination-current").text());
    if ($(".swiper-pagination-current").text() == 1) {
        $(".banner2").css('background-color', 'rgb(48, 52, 65)');
    } else if ($(".swiper-pagination-current").text() == 2) {
        $(".banner2").css('background-color', 'rgb(55, 17, 108)');
    } else if ($(".swiper-pagination-current").text() == 3) {
        $(".banner2").css('background-color', 'rgb(55, 184, 107)');
    } else if ($(".swiper-pagination-current").text() == 4) {
        $(".banner2").css('background-color', 'rgb(10, 195, 221)');
    }
});
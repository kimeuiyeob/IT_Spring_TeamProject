/*----------------유튜브 모달-------------------*/

$(".video4 a").click(function () {
    $(".video_modal_popup").addClass("reveal"),
        $(".video_modal_popup .video-wrapper").remove(),
        $(".video_modal_popup").append("<div class='video-wrapper'><iframe width='1168' height='657' src='https://www.youtube.com/embed/fpDQDDst2HE/" + $(this).data("video") + "?rel=0&playsinline=1&autoplay=1' allow='autoplay; encrypted-media' allowfullscreen></iframe></div>")
}),
    $(".video_modal_popup").click(function () {
        $(".video_modal_popup .video-wrapper").remove(),
            $(".video_modal_popup").removeClass("reveal")
    });
/* ============================슬라이드 배너 글자 텍스트 애니메이션============================== */
const text = document.querySelector(".text");

// 글자 모음
const letters = [
    "보육원 이름을 검색해 보세요.",
    "너에게 두 손이 있는 이유는",
    "너와 타인을 돕기 위해서이다. - 오드리 햅번",
    "좋은 사람이건 나쁜 사람이건 상관하지 않고",
    "이웃을 사랑하는 사람이야 말로",
    "가장 완전한 사람이다. -마호메트"
];

// 글자 입력 속도
const speed = 70;
let i = 0;

// 타이핑 효과
const typing = async () => {
    const letter = letters[i].split("");

    while (letter.length) {
        await wait(speed);
        text.innerHTML += letter.shift();
    }

    // 잠시 대기
    await wait(800);

    // 지우는 효과
    remove();
}

// 글자 지우는 효과
const remove = async () => {
    const letter = letters[i].split("");

    while (letter.length) {
        await wait(speed);

        letter.pop();
        text.innerHTML = letter.join("");
    }

    // 다음 순서의 글자로 지정, 타이핑 함수 다시 실행
    i = !letters[i + 1] ? 0 : i + 1;
    typing();
}

// 딜레이 기능 ( 마이크로초 )
function wait(ms) {
    return new Promise(res => setTimeout(res, ms))
}

// 초기 실행
setTimeout(typing, 1500);
/*----------검색창 커서 들어가고 나갔을때-------------*/
// const $search1 = $(".search3");
const $search2 = $("label[for = 'hero-search2']");
const $searchText = $(".search-text1");
const $search1 = $(".search1");

$search1.on('click', function () {
    $searchText.hide();
    $search2.show();
    $('#hero-search2').focus()
})

$('#hero-search2').on('blur', function () {
    $(this).val('');
    $searchText.show();
    $search2.hide();
})

//도움이 필요한 보육원
const $help4 = $('.help4');
const $bannerHashtag = $('.slide-text5');

$help4.on('click', function (e) {
    e.preventDefault();
    location.href = "/school/read?userId=" + $(this).attr("href");
})

$bannerHashtag.on('click', function (e) {
    e.preventDefault();
    location.href = "/school/read?userId=" + $(this).attr("href");
})


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
    "한동석 강사님",
    "사랑합니다.",
    "의엽이 보육원",
    "해준이 보육원",
    "서림이 보육원",
    "지수 킹왕짱"
];

// 글자 입력 속도
const speed = 100;
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
const $search1 = $(".search3");
const $search2 = $(".search4");
const $searchText = $(".search-text1");

$search1.on("focus", function () {
    $search1.hide();
    $search2.show().focus();
    $search2.css('outline', 'none');
    $searchText.hide();
});
$search2.on("blur", function () {
    $search2.hide();
    $search2.val('');
    $search1.show();
    $search1.css('outline', 'none');
    $searchText.show();
});

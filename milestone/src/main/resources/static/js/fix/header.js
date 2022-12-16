const $navText1 = $("#nav-text1");
const $navText2 = $("#nav-text2");
const $navText3 = $("#nav-text3");
const $navText4 = $("#nav-text4");
const $yellow1 = $("#yellow1");
const $yellow2 = $("#yellow2");
const $yellow3 = $("#yellow3");
const $yellow4 = $("#yellow4");
let $profile = $("#login-profile9");
let $myMenu = $("#login-profile-menu1");
let $message = $("#message-button");
let $myMessage = $("#message-id1");

$profile.click(function () {
    $myMenu.toggle(0);
    $myMessage.hide();
});

$(window).scroll(function () {
    $myMenu.hide();
});

$message.click(function () {
    // 주황불 떠있을 때만 실행 가능
    getAlarm(showAlarm)
    $myMessage.toggle(0);
    $myMenu.hide();
});

/*===============황지수=============*/

/*안읽은 알람 목록*/
/*ajax*/
function getAlarm(callback) {
    $.ajax({
        url: "/alarm/noneCheckAlarm",
        success: function (data) {
            callback(data);
        }
    })
}
/*뿌리기*/
function showAlarm(alarmList){
    console.log(alarmList)
    let text = "";
    alarmList.forEach(alarm => {
        console.log(alarm.name)
        text += `<li class = "alarmStyle">`
        text += alarm.name
        if(alarm.type == "방문일"){
            text += `님이 방문기부를 신청했습니다.`
        }else if(alarm.type == "기부금"){
            text += `님이 기부금을 전달했습니다.`
        }else if(alarm.type == "재능기부일"){
            text += `에서 재능기부를 신청했습니다.`
        }
        text += `</li>`
        console.log(text)
    })
    if(alarmList.length != 0){
        $('.message7').hide();
        $('.alarmList').show();
        $('.alarmList').html(text);
    }else{
        $('.message7').show();
        $('.alarmList').hide();
        $('.alarmList').html(text);
    }
}

/*/안읽은 알람 목록*/

$(window).scroll(function () {
    $myMessage.hide();
});

$(document).click(function (e) {
    if ($(e.target).closest('#message-id1').length == 0 && $(e.target).closest('#message-button').length == 0) {
        if ($myMessage.css("display") == "block") {
            $myMessage.toggle(0);
        }
    }
    if ($(e.target).closest('#profile1').length == 0) {
        if ($myMenu.css("display") == "block") {
            $myMenu.toggle(0);
        }
    }
});

// noinspection DuplicatedCode,DuplicatedCode
$(document).ready(function () {
    $navText1.hover(function () {
        $yellow1.show();
    }, function () {
        $yellow1.hide();
    });
    $navText2.hover(function () {
        $yellow2.show();
    }, function () {
        $yellow2.hide();
    });
    $navText3.hover(function () {
        $yellow3.show();
    }, function () {
        $yellow3.hide();
    });
    $navText4.hover(function () {
        $yellow4.show();
    }, function () {
        $yellow4.hide();
    });
});

/*----------------------------------------------*/
/*--------------------황지수---------------------*/
/*----------------------------------------------*/
const $loginBtn = $('#loginBtn');
const $loginModalWrap = $('.loginModalWrap');
const $loginModalScope = $('.loginModalScope');
const $closeBackground = $('.closeBackground');
const $body = $('body');


document.addEventListener('click', function (e) {
    if (e.target == $loginModalScope[0]) {
        $loginModalWrap.hide();
        $body.css('overflow', 'auto');
    }
})

$loginBtn.on('click', function () {
    $loginModalWrap.show();
    $body.css('overflow', 'hidden');
})

$closeBackground.on('click', function () {
    $loginModalWrap.hide();
    $body.css('overflow', 'auto');
})


/*----------------------------------------------*/
/*--------------반응형 헤더 메뉴----------------*/
/*----------------------------------------------*/

const $leftCloseIcon = $("#close-icon");
const $menuIcon = $("#menu-icon");
const $leftSubMenu = $(".left_sub_menu");
const $rightSubMenu = $(".right_sub_menu");
const $closeIconRight = $("#close-icon-right");
const $searchIcon = $("#search-icon");

$(function () {
    $menuIcon.click(function () {
        console.info("메뉴 누름");
        $leftSubMenu.fadeToggle(300);
        $menuIcon.hide();
        $leftCloseIcon.show();
        $rightSubMenu.hide();
    });
    $leftCloseIcon.click(function () {
        console.info("닫기 누름");
        $leftSubMenu.fadeToggle(300);
        $menuIcon.show();
        $leftCloseIcon.hide();
    });

    $(window).on('resize', function () {
        if (window.innerWidth >= 768) {
            $leftSubMenu.hide();
            $menuIcon.show();
            $leftCloseIcon.hide();
        }
    });
});

/*----------------------------------------------*/
/*--------------반응형 헤더 검색----------------*/
/*----------------------------------------------*/

$(function () {
    $searchIcon.click(function () {
        console.info("검색 누름");
        $leftCloseIcon.hide();
        $leftSubMenu.hide();
        $rightSubMenu.fadeToggle(300);
        $searchIcon.hide();
        $closeIconRight.show();
    });
    $closeIconRight.click(function () {
        console.info("닫기 누름");
        $menuIcon.show();
        $rightSubMenu.fadeToggle(300);
        $searchIcon.show();
        $closeIconRight.hide();
    });

    $(window).on('resize', function () {
        if (window.innerWidth >= 768) {
            $rightSubMenu.hide();
            $searchIcon.show();
            $closeIconRight.hide();
        }
    });
});

/*----------------------------------------------*/
/*------------반응형 헤더 검색 텍스트-----------*/
/*----------------------------------------------*/

const $mobileText = document.querySelector(".mobile-text");

// 글자 모음
const mobileLetters = [
    "한동석 강사님",
    "사랑합니다.",
    "의엽이 보육원",
    "해준이 보육원",
    "서림이 보육원",
    "지수 킹왕짱"
];

// 글자 입력 속도
const mobileSpeed = 100;
let j = 0;

// 타이핑 효과
const mobileTyping = async () => {
    const $mobileLetter = mobileLetters[j].split("");

    while ($mobileLetter.length) {
        await mobileWait(mobileSpeed);
        $mobileText.innerHTML += $mobileLetter.shift();
    }

    // 잠시 대기
    await mobileWait(800);

    // 지우는 효과
    mobileRemove();
}

// 글자 지우는 효과
const mobileRemove = async () => {
    const $mobileLetter = mobileLetters[j].split("");

    while ($mobileLetter.length) {
        await mobileWait(mobileSpeed);

        $mobileLetter.pop();
        $mobileText.innerHTML = $mobileLetter.join("");
    }

    // 다음 순서의 글자로 지정, 타이핑 함수 다시 실행
    j = !mobileLetters[j + 1] ? 0 : j + 1;
    mobileTyping();
}

// 딜레이 기능 ( 마이크로초 )
function mobileWait(ms) {
    return new Promise(res => setTimeout(res, ms))
}

// 초기 실행
setTimeout(mobileTyping, 1500);

const $mobileSearch5 = $("#mobile-search5");
const $mobileText1 = $("#mobile-auto-text2");

$mobileSearch5.on("focus", function () {
    console.log("누름");
    $mobileText1.hide();
});
$mobileSearch5.on("blur", function () {
    console.log("떠남");
    $mobileText1.show();
});


$(window).load(function () {
    $leftSubMenu.css('display', 'none');
    $leftCloseIcon.css('display', 'none');
    $rightSubMenu.css('display', 'none');
    $closeIconRight.css('display', 'none');

});
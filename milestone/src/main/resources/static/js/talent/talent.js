window.onload = function () {

    $("#talentModal").css({"display": "none"})
    $("#all").css({"display": "flex"})
    $("#pseudo").css({"transform": "translateX(0%)"})

    $(this).css({"color": "rgb(48, 52, 65)", "font-weight": "700"})

    $(".education").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".exercise").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".music").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".art").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".program").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
}


$(".all").click(function () {

    $("#all").css({"display": "flex"})
    $("#pseudo").css({"transform": "translateX(0%)"})

    $(this).css({"color": "rgb(48, 52, 65)", "font-weight": "700"})

    $(".education").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".exercise").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".music").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".art").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".program").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})

})

$(".education").click(function () {

    $("#education").css({"display": "flex"})
    $("#pseudo").css({"transform": "translateX(100%)"})

    $(this).css({"color": "rgb(48, 52, 65)", "font-weight": "700"})

    $(".all").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".exercise").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".music").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".art").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".program").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})

})

$(".exercise").click(function () {

    $("#exercise").css({"display": "flex"})
    $("#pseudo").css({"transform": "translateX(200%)"})

    $(this).css({"color": "rgb(48, 52, 65)", "font-weight": "700"})

    $(".all").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".education").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".music").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".art").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".program").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})

})

$(".music").click(function () {

    $("#music").css({"display": "flex"})
    $("#pseudo").css({"transform": "translateX(300%)"})

    $(this).css({"color": "rgb(48, 52, 65)", "font-weight": "700"})

    $(".all").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".education").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".exercise").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".art").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".program").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})

})

$(".art").click(function () {

    $("#art").css({"display": "flex"})
    $("#pseudo").css({"transform": "translateX(400%)"})

    $(this).css({"color": "rgb(48, 52, 65)", "font-weight": "700"})

    $(".all").css({"font-weight": "normal"})
    $(".all").css({"color": "rgb(154, 155, 167)"})


    $(".all").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".education").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".exercise").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".music").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".program").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})

})

$(".program").click(function () {

    $("#program").css({"display": "flex"})
    $("#pseudo").css({"transform": "translateX(500%)"})

    $(this).css({"color": "rgb(48, 52, 65)", "font-weight": "700"})


    $(".all").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".education").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".exercise").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".music").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})
    $(".art").css({"font-weight": "normal", "color": "rgb(154, 155, 167)"})

    /*===========이부분은 JS로 처리한 부분===========*/
    /*    $("#all").css({"display":"none"})
        $("#education").css({"display":"none"})
        $("#exercise").css({"display":"none"})
        $("#music").css({"display":"none"})
        $("#art").css({"display":"none"})*/

})

/*=====================지역 드랍 다운===========================*/

$(".location").mouseover(function () {
    $(this).css({"padding-bottom": "10px"})
})

$(".location").mouseout(function () {
    $(this).css({"padding-bottom": "0"})
})

$(".wholeLocation").css({"background-color": "transparent"});
$(".wholeLocation").css({"border": "solid 1px black"});


/*===================글작성 선택 모달==========================*/


let $cancels = $('#pencil');
let $modalWrap = $('.modalWrap');
let body = $('body');
let $modalBtn = $('.modalBtn');
let modalWrap = document.querySelector('.modalWrap');


$cancels.on('click', function () {
    $modalWrap.show();
    body.css('overflow', 'hidden');
})


$("#cancelNow").click(function () {
    $(".modalWrap").css({"display": "none"})
    $writePlease.hide();
    body.css('overflow', 'auto');
})

document.addEventListener('click', function (e) {
    if (e.target == modalWrap || e.target == $modalBtn[1]) {
        $modalWrap.hide();
        body.css('overflow', 'auto');
    }
    console.log(e.target);
})

/*=================================================*/

/*=================재능 기부 박스 클릭시 해당 상세페이지===========================================================================================*/
let $talentBox = $('.talentBox');
let $talentmodal = $('.talentModal');

let talentModal = document.querySelector('.talentModal');

$talentmodal.hide();

$($talentBox).on('click', function () {
    $talentmodal.show();
    body.css('overflow', 'hidden');
});

$('.css-nh621w').on('click', function () {
    $talentmodal.hide();
    $modalWrap.hide();
    $writePlease.hide();
    $category.val("") & $cateDate.val("") & $cateTitle.val("") & $cateContent.val("");
    $telent.val("");
    body.css('overflow', 'auto');
})

document.addEventListener('click', function (e) {
    if (e.target == talentModal || e.target == $modalBtn[1]) {

        $category.val("") & $cateDate.val("") & $cateTitle.val("") & $cateContent.val("");
        $telent.val("");
        $talentmodal.hide();

        body.css('overflow', 'auto');
    }
    console.log(e.target);
})

/*==================================================================================================================================================*/

/* ==========모달 내부 카테고리 목록 드롭다운=========== */
const $moreSelect = $('div.inputCos');
const $moreSelectList = $('div.moreSelectWrap');
const $moreSelectItems = $('div.moreSelectItem');
const $inputBank = $('input[name = "bank"]');

$moreSelectItems.on('click', function () {
    console.log($(this).text());
    $inputBank.css("color", 'black');
    $inputBank.val($(this).text());
    $moreSelectList.hide();
})

document.addEventListener('click', function (e) {
    if (!(e.target == $moreSelectList[0])) {
        if ($moreSelectList.css("display") == 'block') {
            $moreSelectList.hide();
            return;
        }
    }
    if ($(e.target).closest('.inputCos')[0] === $moreSelect[0]) {
        $moreSelectList.toggle();
    }
})

/* ==========모달 내부 지역 목록 드롭다운=========== */

const $inputCos = $('#inputCos');
const $moreSelectWrap = $('#moreSelectWrap');
const $place = $('div.place');
const $talent = $('input[name = "talent"]');

$place.on('click', function () {
    $talent.css("color", '#303441');
    $talent.val($(this).text());
    $moreSelectWrap.hide();
})

document.addEventListener('click', function (e) {
    if (!(e.target == $moreSelectWrap[0])) {
        if ($moreSelectWrap.css("display") == 'block') {
            $moreSelectWrap.hide();
            return;
        }
    }
    if ($(e.target).closest('#inputCos')[0] === $inputCos[0]) {
        $moreSelectWrap.toggle();
    }
})
/*======================글자수 제한======================*/

const $textareaCos = $('#kimeuiyeob');
const $contentLength = $('.contentLength');
const maxContent = 300;

$textareaCos.keyup(function (e) {
    $contentLength.text($textareaCos.val().length)
    if ($textareaCos.val().length > maxContent) {
        $textareaCos.val($textareaCos.val().substring(0, maxContent));
        $contentLength.text(maxContent);
    }
})

/*====================검색 옆지역 선택================*/

var checkDrop = false;
var checkLocal = {
    checkSeoul: false, checkKyungki: false, checkKangwon: false,
    checkChungcheong: false, checkJeolla: false, checheckGyeong: false, checkJeju: false
}
var saveLocal = [];

/*==================== 드롭다운 버튼==================== */

$("button.dropbtn").on('click', function () {
    if (!checkDrop) {
        $(".dropdown-content").show();
        checkDrop = true;
    } else {
        $(".dropdown-content").hide();
        checkDrop = false;

        /* 눌린 값들 불러와서 검색창에 띄우기 */
        var changedPlaceHolder = "";
        console.log("saveLocal : " + saveLocal);
        if (saveLocal.length) {
            saveLocal.forEach(element => {
                changedPlaceHolder += "-" + element;
            });

            placeHolderArr = changedPlaceHolder.split("-");
            var text = "";

            /* 저장한 지역들 placeholder에 띄워줌 */
            placeHolderArr.forEach(element => {
                /* element와 일치하는 className이 있으면 해당 값 가져옴 */
                for (var i = 0; i < 7; i++) {
                    if (element == $(".dropLoc").eq(i).attr('class').split(" ")[1]) {
                        text += " " + $(".dropLoc").eq(i).text();
                    }
                }
            });
            $(".placeholder").text(text);
        } else {
            $(".placeholder").text("전체");
        }
    }
})


/*================글 작성 완료 모달 유효성 검사==================== */

let $writeFinish = $('#writeFinish');
let $writePlease = $('#writePlease');

let $category = $('input[name=bank]');
let $telent = $('#talent');
let $cateDate = $('#dateTime');
let $cateTitle = $('#kim2');
let $cateContent = $('#kimeuiyeob');


$($writeFinish).on('click', function () {
    if ($category.val() == "" || $telent.val() == "" || $cateDate.val() == "" || $cateTitle.val() == "" || $cateContent.val() == "") {
        $writePlease.show();
    } else {
        $writePlease.hide();
        $writeFinish.submit();
        $category.val("") & $cateDate.val("") & $cateTitle.val("") & $cateContent.val("");
        $telent.val("");
        $modalWrap.hide();
    }
});


/*==================== 드롭다운 버튼 선택(중복가능)==================== */

$(".dropLoc").on('click', function () {

    switch ($(this).attr('class').split(" ")[1] + "") {
        case "checkSeoul" :
            if (!checkLocal.checkSeoul && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkSeoul = true;
                saveLocal.push("checkSeoul");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "checkSeoul") {
                            saveLocal.splice(i, 1);
                            console.log("splice로 삭제하고 false로 바꿈")
                            checkLocal.checkSeoul = false;
                        }
                    }
                }
            }
            break;
        case "checkKyungki" :
            if (!checkLocal.checkKyungki && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkKyungki = true;
                saveLocal.push("checkKyungki");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "checkKyungki") {
                            saveLocal.splice(i, 1);
                            console.log("splice로 삭제하고 false로 바꿈")
                            checkLocal.checkKyungki = false;
                        }
                    }
                }
            }
            break;
        case "checkKangwon" :
            if (!checkLocal.checkKangwon && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkKangwon = true;
                saveLocal.push("checkKangwon");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "checkKangwon") {
                            saveLocal.splice(i, 1);
                            console.log("splice로 삭제하고 false로 바꿈")
                            checkLocal.checkKangwon = false;
                        }
                    }
                }
            }
            break;
        case "checkChungcheong" :
            if (!checkLocal.checkChungcheong && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkChungcheong = true;
                saveLocal.push("checkChungcheong");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "checkChungcheong") {
                            saveLocal.splice(i, 1);
                            console.log("splice로 삭제하고 false로 바꿈")
                            checkLocal.checkChungcheong = false;
                        }
                    }
                }
            }
            break;
        case "checkJeolla" :
            if (!checkLocal.checkJeolla && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkJeolla = true;
                saveLocal.push("checkJeolla");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "checkJeolla") {
                            saveLocal.splice(i, 1);
                            console.log("splice로 삭제하고 false로 바꿈")
                            checkLocal.checkJeolla = false;
                        }
                    }
                }
            }
            break;
        case "checkGyeong" :
            if (!checkLocal.checkGyeong && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkGyeong = true;
                saveLocal.push("checkGyeong");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "checkGyeong") {
                            saveLocal.splice(i, 1);
                            console.log("splice로 삭제하고 false로 바꿈")
                            checkLocal.checkGyeong = false;
                        }
                    }
                }
            }
            break;
        case "checkJeju" :
            if (!checkLocal.checkJeju && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkJeju = true;
                saveLocal.push("checkJeju");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "checkJeju") {
                            saveLocal.splice(i, 1);
                            console.log("splice로 삭제하고 false로 바꿈")
                            checkLocal.checkJeju = false;
                        }
                    }
                }
            }
            break;
    }
    console.log(saveLocal);

});














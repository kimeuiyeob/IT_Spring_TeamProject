let save = [];

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


/* 전체보기를 눌렀을 땐 작은 지역들 선택을 해제한다 */
$(".wholeLocation").click(function () {
    $(".wholeLocation").css({"background-color": "transparent"});
    $(".wholeLocation").css({"border": "solid 1px black"});
    $(".location2").css({"background-color": "#f2f3f7"})
    $(".location2").css({"border": "none"})
    save = [];
})

/* 지역선택시 지역저장 */
$(".location2").click(function () {
    $(".wholeLocation").css({"background-color": "#f2f3f7"})
    $(".wholeLocation").css({"border": "none"})

    if ($(this).css('background-color') == 'rgb(242, 243, 247)') {
        $(this).css({"background-color": "transparent"})
        $(this).css({"border": "solid 1px black"})
        /* 담긴게 없다면 무조건 push */
        if (save.length == 0) {
            save.push($(this).text());
        } else if (save.length > 0) {
            for (var i = 0; i < save.length; i++) {
                /* 담을 지역이 담은 지역과 겹치는게 없다면 push */
                if (save[i] != $(this).text()) {
                    save.push($(this).text());
                    break;
                }
            }
        }
    } else {
        $(this).css({"background-color": "#f2f3f7"})
        $(this).css({"border": "none"})
        save.forEach(element => {
            /* 담을 지역이 담은 지역과 겹치는게 있다면 splice */
            if (element == $(this).text()) {
                save.splice(save.indexOf(element), 1);
            }
        });
    }
    console.log(save);
})


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

/*===================목록 클릭 상세페이지====================*/

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

/*==================모달 내부 카테고리 드랍다운====================*/

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

/*==================모달 내부 지역 드랍다운====================*/

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
/*==================글자수 제한====================*/

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

/*================글 작성 모달 유효성 검사================ */

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

/*===============재능기부 목록에서 신청하기 버튼==================*/

let $writeApply = $('#writeApply');
$($writeApply).on("click", function () {
    $writeApply.submit();
    $talentmodal.hide();
})

/*==============검색창 옆 드롭다운 버튼 선택(중복가능)============== */

var checkDrop = false;
var checkLocal = {
    checkSeoul: false, checkKyungki: false, checkKangwon: false,
    checkChungcheong: false, checkJeolla: false, checkGyeongsang: false, checkJeju: false
};
/* 저장된 지역 */
var saveLocal = [];

/* 드롭다운 버튼 */
$("button.dropbtn").on('click', function () {

    if (!checkDrop) {
        $(".dropdown-content").show();
        checkDrop = true;
    } else {
        var placeholderText = "";

        $(".dropdown-content").hide();
        checkDrop = false;

        /* 눌린 값들 불러와서 검색창에 띄우기 */
        if (0 < saveLocal.length && saveLocal.length < 7) {
            for (let i = 0; i < saveLocal.length; i++) {
                placeholderText += saveLocal[i] + " ";
            }
            $(".placeholder").text(placeholderText);
        } else {
            // console.log(saveLocal.length);
            $(".placeholder").text("전체");
        }
    }
})


/* 드롭다운 버튼 선택(중복가능) */
$(".dropLoc").on('click', function () {

    switch ($(this).text()) {
        case "서울" :
            if (!checkLocal.checkSeoul && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkSeoul = true;
                saveLocal.push("서울");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "서울") {
                            saveLocal.splice(i, 1);
                            checkLocal.checkSeoul = false;
                        }
                    }
                }
            }
            break;
        case "경기도" :
            if (!checkLocal.checkKyungki && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkKyungki = true;
                saveLocal.push("경기도");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "경기도") {
                            saveLocal.splice(i, 1);
                            checkLocal.checkKyungki = false;
                        }
                    }
                }
            }
            break;
        case "강원도" :
            if (!checkLocal.checkKangwon && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkKangwon = true;
                saveLocal.push("강원도");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "강원도") {
                            saveLocal.splice(i, 1);
                            checkLocal.checkKangwon = false;
                        }
                    }
                }
            }
            break;
        case "충청도" :
            if (!checkLocal.checkChungcheong && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkChungcheong = true;
                saveLocal.push("충청도");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "충청도") {
                            saveLocal.splice(i, 1);
                            checkLocal.checkChungcheong = false;
                        }
                    }
                }
            }
            break;
        case "전라도" :
            if (!checkLocal.checkJeolla && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkJeolla = true;
                saveLocal.push("전라도");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "전라도") {
                            saveLocal.splice(i, 1);
                            checkLocal.checkJeolla = false;
                        }
                    }
                }
            }
            break;
        case "경상도" :
            if (!checkLocal.checkGyeongsang && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkGyeongsang = true;
                saveLocal.push("경상도");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "경상도") {
                            saveLocal.splice(i, 1);
                            checkLocal.checkGyeongsang = false;
                        }
                    }
                }
            }
            break;
        case "제주도" :
            if (!checkLocal.checkJeju && saveLocal) {
                $(this).css('background-color', '#e2e2e2');
                checkLocal.checkJeju = true;
                saveLocal.push("제주도");
            } else {
                $(this).css('background-color', 'transparent');
                if (saveLocal) {
                    for (var i = 0; i < saveLocal.length; i++) {
                        if (saveLocal[i] == "제주도") {
                            saveLocal.splice(i, 1);
                            checkLocal.checkJeju = false;
                        }
                    }
                }
            }
            break;
    }
    console.log(saveLocal);
});

/*============================================================*/
/*제목으로 검색*/
const $talentName = $('input[name = talentName]');

$talentName.on('keyup', function (e) {
    if (e.keyCode == 13) {
        searchForm.submit();
    }
})


/*=========================Ajax 사용=========================*/

/*지역 선택*/

function getTalentList1(param, callback, error) {

    let test = param.talentPlace.length != 0 ? "/" + param.talentPlace : "/null";

    $.ajax({
        url: "/talentrest/list/" + test,
        type: "get",
        success: function (talentDTO, status, xhr) {
            if (callback) {
                callback(talentDTO);
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}
/*=================호석이가 ajax 도와준곳=====================*/

$("#education").on("click",function () {

    $.ajax({
        url: "/talentrest/showall",
        type: "post",
        success:function (lists) {

            let text= "";

            lists.forEach(function (list) {
                text += `<div style="padding-bottom:20px">`
                text += `<article class="talentBox">`
                text += `<div class="talentBox2">`
                text += `<div class="talentBox3">`
                text += `<img src="../imgs/myPage/normalProfile.png" class="talentImg">`
                text += `</div>`
                text += `<div class="talentBox4">`
                text += `<p class="talentTitle">` + list.talentTitle + `</p>`
                text += `<p class="talentContent">` + list.talentContent + `</p>`
                text += `<div class="talentboxs">`
                text += `<div class="talentType">`
                text += `<img src="/imgs/talent/care2.png" style="width: 25px; height: 25px; margin-bottom: -6px; margin-left: -10px;">` + list.talentCategory
                text += `</div>`
                text += `<div class="talentPlace">`
                text += `<img src="/imgs/talent/pin.png" style="width: 25px; height: 25px; margin-bottom: -6px; margin-left: -10px;">` + list.talentPlace
                text += `</div>`

                let date = new Date(list.talentAbleDate);
                let year = date.getFullYear().toString().substr(2);
                let month = date.getMonth();
                let day = date.getDay();

                text += `<div class="talentTime">`
                text += `<img src="/imgs/talent/mylove.png" style="width: 25px; height: 25px; margin-bottom: -7px; margin-left: -15px;">` + year + '년 ' + ("00" +month.toString()).slice(-2) + "월 " + ("00" +day.toString()).slice(-2)+ "일"
                text += `</div>`
                text += `</div>`
                text += `</div>`
                text += `</div>`
                text += `</article>`
                text += `</div>`
                $(".talentBoxAll").html(text);

            })
        }
    })
})








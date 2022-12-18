let save = [];

/*===========================================================================================*/
/*지역 드랍 다운*/

$(".location").mouseover(function () {
    $(this).css({"padding-bottom": "10px"})
})

$(".location").mouseout(function () {
    $(this).css({"padding-bottom": "0"})
})

$(".wholeLocation").css({"background-color": "transparent"});
$(".wholeLocation").css({"border": "solid 1px black"});

/*===========================================================================================*/
/*전체보기를 눌렀을 땐 작은 지역들 선택을 해제한다 */

$(".wholeLocation").click(function () {
    $(".wholeLocation").css({"background-color": "transparent"});
    $(".wholeLocation").css({"border": "solid 1px black"});
    $(".location2").css({"background-color": "#f2f3f7"})
    $(".location2").css({"border": "none"})
    save = [];
})

/*===========================================================================================*/
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
})

/*===========================================================================================*/
/*글작성 선택 모달 */

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
})

/*===========================================================================================*/
/*목록 클릭 신청하기 상세페이지*/

let $talentModal = $('.talentModal');
let talentModal = document.querySelector('.talentModal');

$(".AllTalentBox").on("click", ".talentBox", function () {
    $talentModal.show()
    body.css('overflow', 'hidden');

    let donationId = $(this).find("div.donationId").html();

    $.ajax({
        url: "/talentrest/showmodal/" + donationId,
        type: "get",
        success: function (lists) {

            var talent = lists[0];

            let date = new Date(talent.talentAbleDate);
            let year = date.getFullYear().toString().substr(2);
            let month = date.getMonth() + 1;
            let day = date.getDate();

            let text = ""

            text += `<div class="talentScope">`
            text += `<div class="talentHead">`

            text += `<div style="display:flex; justify-content: space-between;width: 100%;">`
            text += `<div style="padding-top: 15px;">`
            text += `<div class="css-1gtanqs e1y81j3a3">`
            text += `<div class="css-14prtx6 e1dh882h2">` + talent.peopleNickname + `</div>`
            text += `</div>`
            text += `</div>`

            text += `<div class="donationId2" style="display: none">` + talent.donationId + `</div>`

            text += `<span class="css-1oteowz eklkj751">`
            text += `<span role="img" color="#e4e5ed" rotate="0" class="css-nh621w e181xm9y1">`
            text += `<svg width="24" height="24" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true" focusable="false" style="cursor: pointer"
                preserveAspectRatio="xMidYMid meet" class="css-7kp13n e181xm9y0">`
            text += `<path xmlns="http://www.w3.org/2000/svg" d="M6.34314575,4.92893219 L12.000039,10.585039 L17.6568542,4.92893219 C18.0473785,4.5384079 18.6805435,4.5384079 19.0710678,4.92893219 C19.4615921,5.31945648 19.4615921,5.95262146 19.0710678,6.34314575 L13.415039,12.000039 L19.0710678,17.6568542 C19.4615921,18.0473785 19.4615921,18.6805435 19.0710678,19.0710678 C18.6805435,19.4615921 18.0473785,19.4615921 17.6568542,19.0710678 L12.000039,13.415039 L6.34314575,19.0710678 C5.95262146,19.4615921 5.31945648,19.4615921 4.92893219,19.0710678 C4.5384079,18.6805435 4.5384079,18.0473785 4.92893219,17.6568542 L10.585039,12.000039 L4.92893219,6.34314575 C4.5384079,5.95262146 4.5384079,5.31945648 4.92893219,4.92893219 C5.31945648,4.5384079 5.95262146,4.5384079 6.34314575,4.92893219 Z">`
            text += `</path>`
            text += `</svg>`
            text += `</span>`
            text += `</span>`
            text += `</div>`

            text += `<div style = "display:flex; justify-content: space-between; border-bottom: 1px solid rgb(228, 229, 237); padding-bottom: 15px;margin-top: -15px; width: 100%;" >`

            text += `<div style="padding-top: 10px;font-size: 18px;margin-top: 15px;">` + "저의 재능으로 아이들에게 보답할께요." + `</div>`

            text += `<div style = "display: flex;">`
            text += `<div role = "link" color = "yellow" href = "#" class = "css-8x0gm eklkj752" id = "writeApply" style = "width:125px;">`
            text += `<span class = "css-1oteowz eklkj751" >` + "신청하기" + `</span>`
            text += `</div>`
            text += `</div>`
            text += `</div>`


            text += `<div style="display :flex">`
            text += `<div style = "display:flex; justify-content: space-between; padding-top: 35px;">`
            text += `<div class = "abc" style = "padding-right: 50px;">`
            text += `<span class = "abcd" style = "padding-bottom: 20px;">`

            /*=========================================================================================================*/

            if(talent.fileName == null) {
                //텍스트 추가 이미지src 에다 talent로 가져온 filePath + fileUUid _ fileName으로 이미지를 찾아온다.
                text += `<img src="/imgs/myPage/normalProfile.png" style = "border-radius: 10px; inset: 0px; box-sizing: border-box; padding: 0px; border: none; margin: auto; display: block;">`
            }else {
                text += `<img style="width:203px; height:204px; border-radius: 10px; inset: 0px; box-sizing: border-box; padding: 0px; border: none; margin: auto; display: block;" class="talentImg" src="/file/display?fileName=`+ talent.filePath + `/` + talent.fileUuid + `_` + talent.fileName +`">`
            }

            /*=========================================================================================================*/

            text += `</span>`

            text += `<div class = "talentType" style = "display: flex; justify-content: space-between; width: 100%; background: none;" >`
            text += `<img src = "/imgs/talent/care2.png" style = "margin-top: 7px; width: 25px; height: 25px; margin-bottom: -6px;" >` + talent.talentCategory + `</div>`
            text += `<div class = "talentPlace" style = "display: flex; justify-content: space-between; width: 100%; background: none;" >`
            text += `<img src = "/imgs/talent/pin.png" style = " margin-top: 7px; width: 25px; height: 25px; margin-bottom: -6px;" >` + talent.talentPlace + `</div>`
            text += `<div class = "talentTime" style = "display: flex; justify-content: space-between; width: 100%; background: none;" >`
            text += `<img src = "/imgs/talent/mylove.png" style = " margin-top: 7px; width: 25px; height: 25px; margin-bottom: -7px; " >` + year + '년 ' + ("00" + month.toString()).slice(-2) + "월 " + ("00" + day.toString()).slice(-2) + "일" + `</div>`
            text += `</div>`
            text += `</div>`


            text += `<div>`
            text += `<div class = "css-1438eek e1y81j3a6" style="padding-top :30px">`
            text += `<div class = "css-13mgtqc e1y81j3a9" >`

            text += `<div>`
            text += `<div class = "margin-bottom-8 flexBetween" style = "margin-bottom: 6px;" >`
            text += `<div class = "inputTitle" >` + "제목" + `</div>`
            text += `</div>`
            text += `<div>` + talent.talentTitle + `</div>`
            text += `</div>`

            text += `<div>`
            text += `<div class = "margin-bottom-8 flexBetween" style = "margin-bottom: 6px; padding-top: 4px; margin-top: 15px;" >`
            text += `<div class = "inputTitle" >` + "내용" + `</div>`
            text += `</div>`

            text += `<div>` + talent.talentContent + `</div>`
            text += `</div>`

            text += `</div>`
            text += `</div>`
            text += `</div>`

            text += `</div>`
            text += `</div>`

            text += `</div>`
            text += `</div>`
            text += `</div>`

            $(".talentModal").html(text);
            if (sessionId === "" || sessionId === "people") {
                $("#writeApply").hide();
            } else if (sessionId === "school") {
                $("#writeApply").show();
            }

        }

    })

})

/*===========================================================================================*/
/*재능기부 목록 취소버튼 = 상세페이지모달 취소*/

$(".talentModal").on('click', ".css-nh621w", function () {
    console.log("취소1")
    $talentModal.hide();
    $modalWrap.hide();
    $writePlease.hide();
    $category.val("") & $cateDate.val("") & $cateTitle.val("") & $cateContent.val("");
    $telent.val("");
    body.css('overflow', 'auto');

})

/*===========================================================================================*/
/*재능기부 목록 취소버튼 = 글작성모달 취소*/

$('.css-nh621w').on('click', function () {
    console.log("취소2")
    $talentModal.hide();
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
        $talentModal.hide();

        body.css('overflow', 'auto');
    }
})

/*===========================================================================================*/
/*모달 내부 카테고리 드랍다운*/

const $moreSelect = $('div.inputCos');
const $moreSelectList = $('div.moreSelectWrap');
const $moreSelectItems = $('div.moreSelectItem');
const $inputBank = $('input[name = "bank"]');
const $Place = $('div.place');

let textCategory = null;
let textPlace = null;

$moreSelectItems.on('click', function () {
    console.log($(this).text());
    textCategory = ($(this).text());

    $inputBank.css("color", 'black');
    $inputBank.val($(this).text());
    $moreSelectList.hide();
})

$Place.on('click', function () {
    textPlace = $(this).text();
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

/*===========================================================================================*/
/*모달 내부 지역 드랍다운*/

const $inputCos = $('#inputCos');
const $moreSelectWrap = $('#moreSelectWrap');
const $place = $('div.place');
const $talent = $('input[name = "talent"]')


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

/*===========================================================================================*/
/*글자수 제한*/

const $textareaCos = $('#kimeuiyeob');
const $contentLength = $('.contentLength');
const maxContent = 300;

$textareaCos.keyup(function () {
    $contentLength.text($textareaCos.val().length)
    if ($textareaCos.val().length > maxContent) {
        $textareaCos.val($textareaCos.val().substring(0, maxContent));
        $contentLength.text(maxContent);
    }
})

/*===========================================================================================*/
/*글 작성 모달 유효성 검사 = 글작성 완료*/

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
        registerTalent({
            talentTitle: $cateTitle.val(),
            talentContent: $cateContent.val(),
            talentAbleDate: $cateDate.val(),
            talentCategory: textCategory,
            talentPlace: textPlace,
        }, hideModal);
    }
});


function registerTalent(talentDTO, callback, error) {
    talentDTO.talentAbleDate = talentDTO.talentAbleDate + " 00:00:00";
    $.ajax({
        url: "/talentrest/write",
        type: "post",
        data: JSON.stringify(talentDTO),
        contentType: "application/json; charset=utf-8",
        success: function () {
            if (callback) {
                callback();
            }
        },

        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}

function hideModal() {
    $talentModal.hide();
    $modalWrap.hide();
    $writePlease.hide();
    $category.val("") & $cateDate.val("") & $cateTitle.val("") & $cateContent.val("");
    $telent.val("");
    body.css('overflow', 'auto');
    show();
}

/*===========================================================================================*/
/*재능기부 목록에서 신청하기 버튼*/

$(".talentModal").on('click', "#writeApply", function () {

    let donationId2 = $("div.donationId2").html();

    signTalent(
       donationId2
    , hiddenModal);
})


function signTalent(donationId, callback, error) {
    $.ajax({
        url: "/talentrest/sign",
        type: "post",
        data: JSON.stringify(donationId),
        contentType: "application/json; charset=utf-8",
        success: function () {
            if (callback) {
                callback();
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}


function hiddenModal() {
    $talentModal.hide();
    body.css('overflow', 'auto');
    show();
}

/*===========================================================================================*/
/*검색창 옆 드롭다운 버튼 선택(중복가능)*/

var checkDrop = false;
var checkLocal = {
    checkSeoul: false, checkKyungki: false, checkKangwon: false,
    checkChungcheong: false, checkJeolla: false, checkGyeongsang: false, checkJeju: false
};

/*===========================================================================================*/
/* 저장된 지역 */
var saveLocal = [];

/* 드롭다운 버튼 */
$(".dropbtn").on('click', function () {
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
});

/*===========================================================================================*/
/* 인풋 데이트 타입 오늘전날짜 클릭 금지 */

var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset() * 60000;
var today = new Date(now_utc - timeOff).toISOString().split("T")[0];
document.getElementById("dateTime").setAttribute("min", today);

/*===========================================================================================*/
/* 검색창 제목 입력시 */

const $talentSearch = $('input[name = talentName]');
const $talentName = $('#talentName');
const $realSearhBox = $('.realSearhBox');
const $talentClick = $('.css-7kp13n');

/*===========================================================================================*/

/*전체 클릭시*/

function getTalentList(param, callback, error) {

    const list = [];

    list.push(param.talentCategory);

    let talentPlaceList = param.talentPlace;

    let talentPlaceVal = "";
    for (let i = 0; i < talentPlaceList.length; i++) {
        talentPlaceVal += talentPlaceList[i];
    }
    list.push(talentPlaceVal);
    list.push(param.talentTitle);

    let lastIndex = -1;
    for (let i = 0; i < list.length; i++) {
        if (list[i] == null || list[i] == undefined || list[i] == '') {
            list[i] = null;
        } else {
            lastIndex = Math.max(lastIndex, i);
        }
    }
    let queryString = '/' + param.page + '/';
    for (let i = 0; i <= lastIndex; i++) {
        queryString += (list[i] + "/");
    }
    // 마지막 '/' 제거
    if (queryString.charAt(queryString.length - 1) === '/') {
        queryString = queryString.substring(0, queryString.length - 1);
    }

    $.ajax({
        url: "/talentrest/list" + queryString,
        type: "get",
        success: function (schoolResp, status, xhr) {
            if (callback) {
                callback(schoolResp);
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}

/*===========================================================================================*/





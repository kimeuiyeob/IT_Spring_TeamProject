/*  작은지역들 플래그 */
// let check = false;
/* 하트 플래그 */
let check2 = false;
/* 지역저장 */
let save = [];

$(".location").mouseover(function () {
    $(this).css({"transform": "translateY(-10px)"})
})

$(".location").mouseout(function () {
    $(this).css({"transform": "translateY(0px)"})
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


/* 찜하기 하트 버튼 */
$(".redHeart").css({"display": "none"})

$("section.schoolList").on('click', "span.heartWrap", function () {
    if ($(this).children(".redHeart").css("display")=="none") {
        $(this).children(".redHeart").css({"display": "inline"})
        $(this).children(".emptyHeart").css({"display": "none"})
        likeSchool($(this).closest('article').find('.donationButton').attr("href"))
    } else {
        $(this).children(".redHeart").css({"display": "none"})
        $(this).children(".emptyHeart").css({"display": "inline"})
        cancelLikeSchool($(this).closest('article').find('.donationButton').attr("href"))
    }
})

$("span.heart").mouseover(function () {
    /*$(this).css({"background-color":"rgb(102 102 102 / 29%)"})*/
    $(this).css({"border-radius": "3px"})
    $(this).css({"transition": "all .2s ease"})
})
$("span.heart").mouseout(function () {
    /*$(this).css({"background-color":"transparent"})*/
    $(this).css({"border-radius": "3px"})
    $(this).css({"transition": "all .2s ease"})
})




$(".dropdown").hide();

if (window.matchMedia('(max-width: 768px)').matches) {
    $('div.loactions').css('display', 'none');
    $('div.dropdown').css('display', 'block');
    $('div.totalCount2').css('display', 'none');
}


/*=====================반응형 지역선택=========================*/

var checkDrop = false;
var checkLocal = {
    checkSeoul: false, checkKyungki: false, checkKangwon: false,
    checkChungcheong: false, checkJeolla: false, checkGyeongsang: false, checkJeju: false
};
/* 저장된 지역 */
let saveLocal = [];

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


/*--------------------황지수----------------------------*/
/*보육원 검색*/
const $schoolName = $('#schoolName');
const $realSearhBox = $('.realSearhBox');


/*지역으로 검색*/
function getList1(param, callback, error) {
    let existAddress = param.schoolAddress.length != 0;
    let existSchoolName =  param.schoolName != null && param.schoolName.length != 0;
    let queryString = "/" + param.page || 1;
    queryString += existAddress ? "/" + param.schoolAddress : "";
    if (!existAddress && existSchoolName) {
        queryString += "/null";
    }
    queryString += existSchoolName ? "/" + param.schoolName : "";
    $.ajax({
        url: "/schoolrest/list" + queryString,
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

//========================좋아요=========================
//내가 좋아한 보육원
function getLikeSchoolList1(callback, error){
    $.ajax({
        url:"/schoolrest/likeSchool",
        type: "get",
        success:function(likeSchoolList){
            callback(likeSchoolList);
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    })
}

//좋아요 누름
function likeSchool(userId, callback, error){
    $.ajax({
        url:"/schoolrest/like/" + userId,
        type: "get",
        success:function(likeCount){
            // callback(likeCount);
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    })
}

//좋아요 취소
function cancelLikeSchool(userId, callback, error){
    $.ajax({
        url:"/schoolrest/cancel/" + userId,
        type: "get",
        success:function(likeCount){
            // callback(likeCount);
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    })
}

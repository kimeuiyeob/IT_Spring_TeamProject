/*==============확인 클릭시 배경 하얀색===============*/

$('.listSection').on('click', '.items-cancel', function () {
    // $('div.listItem').css({"background-color": "rgb(255 252 238)"});
    console.log("눌림");
    var alarmId = $(this).closest('article').find('input[name="alarmId"]').val();
    var checkAlarm = $(this).closest('article').find('input[name="checkAlarm"]').val();
    console.log(alarmId);
    console.log(checkAlarm);
    console.log("alarmId : " + alarmId);

    $.ajax({
        url: "/myPageRest/checkAlarm/" + alarmId,
        type: "get",
        success: function () {
            console.log("확인");
            show()
        }
    })

})


// 화면에 알람 목록
globalThis.page = 0;
let $pagingBtnFlex = $('.paging-number-flex');
let pageInfo;

// 처음 목록 가져오기
show();

function getList(alarmResp) {

    console.log(JSON.stringify(alarmResp));

    let text = "";
    pageInfo = alarmResp.arAlarmDTO;
    alarmResp.arAlarmDTO.content.forEach(alarms => {
        console.log("나와"+alarms.checkAlarm)

        text += `<article>`
        if (alarms.checkAlarm == true) {
            console.log('true에 들어옴')
            text += `<div style="background-color: white;" class="listItem flexBetween">`
            text += `<input type="hidden" name="alarmId" value="` + alarms.alarmId + `">`
            text += `<input  type="hidden" id="checkAlarm" name="checkAlarm" value="` + alarms.checkAlarm + `">`
            text += `<div class="items-name flexCol">` + alarms.name + `</div>`
            text += `<div class="items-location" style="width: 30%;">` + (alarms.type + ' ' + alarms.item + ' 에' +`<br>`+ ' 신청 하였습니다') + `</div>`
            text += `<div class="items-date flexCol">` + alarms.phoneNumber + `</div>`
            text += `<div class="items-cancel flexCol" style="padding-left: 30px; cursor: default; color: darkgray;">확인</div>`
            text += `</div>`
            text += `</article>`
        } else {
            console.log('false에 들어옴')
            text += ` <div style="background-color: rgb(255 252 238);" class="listItem flexBetween">`
            text += `<input type="hidden" name="alarmId" value="` + alarms.alarmId + `">`
            text += `<input  type="hidden" id="checkAlarm" name="checkAlarm" value="` + alarms.checkAlarm + `">`
            text += `<div class="items-name flexCol">` + alarms.name + `</div>`
            text += `<div class="items-location" style="width: 30%;">` + (alarms.type + ' ' + alarms.item + ' 에' +`<br>`+ ' 신청 하였습니다') + `</div>`
            text += `<div class="items-date flexCol">` + alarms.phoneNumber + `</div>`
            text += `<div class="items-cancel flexCol" style="padding-left: 30px;">확인</div>`
            text += `</div>`
            text += `</article>`
        }

    })
    $(".listSection").html(text)
    pageingBtn();
}

function getListSend(param, callback, error) {
    let queryString = "/" + param.page || 1;

    console.log(JSON.stringify(param));
    console.log("queryString : " + queryString);

    $.ajax({
        url: "/myPageRest/peopleAlarm" + queryString,
        type: "get",
        success: function (serviceResp, status, xhr) {
            if (callback) {
                callback(serviceResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}


function show() {
    getListSend({
        page: globalThis.page
    }, getList)
}





/* 페이징 처리 */
function pageingBtn() {
    let text = "";
    let nowPage = pageInfo.pageable.pageNumber + 1;
    let endPage = Math.ceil(nowPage / 5) * 5;
    let startPage = endPage - 5 + 1;
    // <!--페이징 처리-->
    // 이전
    if (startPage > 1) {
        text += `<div class="prev-page page-number-padding">`
        text += `<a class="page-number-link" href="` + (startPage - 2) + `">`
        text += `<div class="prev-page-prevArrow"></div>`
        text += `</a>`
        text += `</div>`
    } else {
        text += `<div class="prev-page page-number-padding">`
        text += `</div>`
    }
    // 페이지 버튼

    for (let i = startPage; i < startPage + 5 && i <= pageInfo.totalPages; i++) {
        text += `<div class="page-number-padding">`
        if (i == nowPage) {
            text += `<div class="page-number-link" style="color:#303441">` + i + `</div>`
        } else {
            text += `<a class="page-number-link" href="` + (i - 1) + `">` + i + `</a>`
        }
        text += `</div>`
    }
    //이후
    if (4 < endPage && endPage < pageInfo.totalPages) {
        text += `<div class="next-page page-number-padding">`
        text += `<a class="page-number-link" href="` + (endPage) + `">`
        text += `<div class="prev-page-nextArrow"></div>`
        text += `</a>`
        text += `</div>`
        text += `</div>`
        text += `</div>`
    }
    $pagingBtnFlex.html(text);
}

$pagingBtnFlex.on('click', ".page-number-link", function (e) {
    e.preventDefault();
    window.scrollTo({top: 0})
    globalThis.page = $(this).attr("href");
    show();
})

/*  페이지 이동  */
$pagingBtnFlex.on('mouseover', "a.page-number-link", function () {
    $(this).css('background-color', '#f4f6fa');
    // $(this).css('color', '#009ef7');
})

$pagingBtnFlex.on('mouseout', "a.page-number-link", function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#9a9ba7');
})

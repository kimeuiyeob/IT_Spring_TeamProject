/* ------------------필터 클릭, 호버--------------------- */
const $filter = $('.card-toolbar-item').eq(0);
const $filterDropdown = $('.menu-sub-dropdown');
const $filters = $('.card-toolbar-item');
let check1 = false;

$(document).on('click', function (e) {
    if (check1) {
        if (e.target.closest('.menu-sub-dropdown') == e.currentTarget.querySelector('.menu-sub-dropdown').closest('.menu-sub-dropdown')) {
            if($(".apply-button").text().match('닫기')){
                $filterDropdown.css('display', 'none');
                $filter.css('background-color', '#f6f8fa');
                $filter.css('color', '#009ef7');
                $filter.find('#filter-img').css({
                    'background': `url('/imgs/admin/filterBlue.png')`,
                    'background-size': '13px'
                });
                check1 = !check1;
                globalThis.page=0;
                search();
            }
        } else {
            $filterDropdown.css('display', 'none');
            $filter.css('background-color', '#f6f8fa');
            $filter.css('color', '#009ef7');
            $filter.find('#filter-img').css({
                'background': `url('/imgs/admin/filterBlue.png')`,
                'background-size': '13px'
            });
            check1 = !check1;
        }
    }
})

$filter.on("click", function () {
    if ($filterDropdown.css('display') == 'none') {
        $filter.css('background-color', '#009ef7');
        $filter.css('color', '#fff');
        $filter.find('#filter-img').css({
            'background': `url('/imgs/admin/filterWhite.png')`,
            'background-size': '13px'
        });
        $filterDropdown.css('display', 'flex');
        setTimeout(() => {
            check1 = !check1;
        }, 100);
    }
})

$filters.on('mouseover', function () {
    $(this).css('background-color', '#009ef7');
    $(this).css('color', '#fff');
    $(this).find('#out-img').css({
        'background': `url('/imgs/admin/outWhite.png') no-repeat`,
        'background-size': '13px'
    })
    $(this).find('#plusBlue-img').css({
        'background': `url('/imgs/admin/plusWhite.png') no-repeat`,
        'background-size': '17px'
    })
    $(this).find('#filter-img').css({
        'background': `url('/imgs/admin/filterWhite.png')`,
        'background-size': '13px'
    })
})

$filters.on('mouseout', function () {
    $(this).css('background-color', '#f6f8fa');
    $(this).css('color', '#009ef7');
    $(this).find('#out-img').css({
        'background': `url('/imgs/admin/out.png') no-repeat`,
        'background-size': '13px'
    })
    $(this).find('#plusBlue-img').css({
        'background': `url('/imgs/admin/plusBlue.png') no-repeat`,
        'background-size': '17px'
    })
    $(this).find('#filter-img').css({
        'background': `url('/imgs/admin/filterBlue.png')`,
        'background-size': '13px'
    })
})

/* ---------------------필터 서브-------------- */
const $selectOption = $('.menu-sub-dropdown-option-box');

$selectOption.on('click', function () {
    if ($(this).find('input').is(':checked')) {
        $(this).find('.menu-sub-dropdown-option-sub').css('display', 'flex');
        $(this).find('input').prop('checked', true);
    } else {
        $(this).find('.menu-sub-dropdown-option-sub').css('display', 'none');
        $(this).find('input').prop('checked', false);
    }
})

/* ----------------- 필터 서브 옵션 호버---------------- */
const $subOption = $('.menu-sub-dropdown-option-sub-item');

$subOption.on('mouseover', function () {
    $(this).css('background-color', '#f4f6fa');
    $(this).css('color', '#009ef7');
})
$subOption.on('mouseout', function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#5e6278');
})

$subOption.on('click', function () {
    $(this).closest('label').find('.menu-sub-dropdown-option-text').html($(this).html());
})

/* ------------------ 필터 서브 옵션 푸터 호버----------------*/

const $subOptionResetChoose = $('.menu-sub-dropdown-Botton').eq(0);
const $subOptionApllyChoose = $('.menu-sub-dropdown-Botton').eq(1);
$subOptionApllyChoose.css('background-color', '#009ef7');
$subOptionApllyChoose.css('color', '#fff');

$subOptionResetChoose.on('mouseover', function () {
    $(this).css('color', '#009ef7');
})
$subOptionResetChoose.on('mouseout', function () {
    $(this).css('color', '#7e8299');
})

$subOptionApllyChoose.on('mouseover', function () {
    $(this).css('background-color', '#0095e8');
})
$subOptionApllyChoose.on('mouseout', function () {
    $(this).css('background-color', '#009ef7');
})

/* -------------- 페이지 이동 ---------------- */
const $pageNumberLink = $('.page-number-link');

$pageNumberLink.on('mouseover',function(){
    $(this).css('background-color','#f4f6fa');
    $(this).css('color','#009ef7');
})

$pageNumberLink.on('mouseout',function(){
    $(this).css('background-color','#fff');
    $(this).css('color','#5e6278');
})

globalThis.page = 0;
let $pagingBtnFlex = $('.paging-number-flex');
let pageInfo;


/* Asc이 최신순으로 바뀌었음*/
/* 처음 뿌리기 */
showList();

function showList(){
    getList1({
        withdrawalReason: "",
        page: globalThis.page
    }, getList)
}

function getList(withdrawalResp) {
    let text = "";
    pageInfo = withdrawalResp.arWithdrawalDTO;
    withdrawalResp.arWithdrawalDTO.content.forEach(withdrawal => {
        text += `<tr>`
        text += `<th class="card-body-title-padding" style="width: 25%;">`
        text += `<div class="donater-info" style="height: 50px">`
        text += `<div class="donater-info-text">`
        text += `<div class="donater-name">`+withdrawal.withdrawalUserType+`</div>`
        text += `</div>`
        text += `</div>`
        text += `</th>`
        text += `<th class="card-body-title-padding" style="width: 43%;">`
        text += `<div class="donate-info-height">`+withdrawal.withdrawalReason+`</div>`
        text += `<th class="card-body-title-padding" style="width: 30%;">`

        var createdDateView = withdrawal.createdDate.split('T')[0].split('-');

        var year = createdDateView[0]+'년 ';
        var month = createdDateView[1]+'월 ';
        var date = createdDateView[2]+'일';

        var result = year + month + date;

        text += `<div class="donate-info-height date-time">`+ result +`</div>`
        text += `</th>`
        text += `</tr>`
    })
    $(".card-body-main-box").html(text)
    pageingBtn();
}

function getList1(param, callback, error) {

    let existWithdrawal = param.withdrawalReason.length != 0;
    let queryString = "/" + param.page || 1;

    queryString += existWithdrawal ? "/" + param.withdrawalReason : "";

    console.log("queryString : "+queryString)

    $.ajax({
        url: "/adminRest/withdrawal" + queryString,
        type: "get",
        success: function (withdrawalResp, status, xhr) {
            if (callback) {
                callback(withdrawalResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}

function getList1Asc(param, callback, error) {

    let existWithdrawal = param.withdrawalReason.length != 0;
    let queryString = "/" + param.page || 1;

    queryString += existWithdrawal ? "/" + param.withdrawalReason : "";

    console.log("queryString : "+queryString)

    $.ajax({
        url: "/adminRest/withdrawalAsc" + queryString,
        type: "get",
        success: function (withdrawalResp, status, xhr) {
            if (callback) {
                callback(withdrawalResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}

/* 회원 검색 */
function search() {

    var word1 = $("#option5").text();   //탈퇴이유
    var word2 = $("#option6").text();   //탈퇴날짜
    console.log(word1);
    console.log(word2);

    // globalThis.page=0;

    if(word1.match('옵션')){
        word1 = "";
    }

    // 옵션이면
    if(word2=="최신순" || word2=="옵션 선택"){
        getList1({
            withdrawalReason: word1,
            page: globalThis.page
        }, getList)
    }else if(word2=="오래된순"){
        getList1Asc({
            withdrawalReason: word1,
            page: globalThis.page
        }, getList)
    }

}

$("#reset3").on('click',function () {
    getList1({
        withdrawalReason: "",
        page: globalThis.page
    }, getList)
})

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
    search();
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

window.onresize = function () {
    document.location.reload();
};
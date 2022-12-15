/* ------------------필터 클릭, 호버--------------------- */
const $filter = $('.card-toolbar-item').eq(0);
const $filterDropdown = $('.menu-sub-dropdown');
const $filters = $('.card-toolbar-item');
let check1 = false;

$(document).on('click', function (e) {
    if (check1) {
        if (e.target.closest('.menu-sub-dropdown') == e.currentTarget.querySelector('.menu-sub-dropdown').closest('.menu-sub-dropdown')) {
            if($(".apply-button").text().match('닫기')){
                console.log("들어옴1")
                $filterDropdown.css('display', 'none');
                $filter.css('background-color', '#f6f8fa');
                $filter.css('color', '#009ef7');
                $filter.find('#filter-img').css({
                    'background': `url('/imgs/admin/filterBlue.png')`,
                    'background-size': '13px'
                });
                check1 = !check1;
                globalThis.page=0;

                if($("#option2").text().match('최신순')||$("#option2").text().match('옵션 선택')) {
                    console.log("option1 : "+$("#option1").text());
                    console.log("option2 : "+$("#option2").text());
                    if($("#option1").text().match('많은순')||$("#option1").text().match('옵션 선택')) {
                        search1();
                    }else if($("#option1").text().match('적은순')){
                        search1asc();
                    }
                }
                if($("#option2").text().match('오래된순')){
                    if($("#option1").text().match('많은순')||$("#option1").text().match('옵션 선택')){
                        search2asc();
                    }else if($("#option1").text().match('적은순')){
                        search2();
                    }
                }
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



let $search = $("#search-bar");
globalThis.page = 0;
let $pagingBtnFlex = $('.paging-number-flex');
let pageInfo;

showList()

/*최신순, 많은순*/
function showList(){
    console.log("showList()실행")

    getList1({
        keyword : $search.val(),
        page: globalThis.page
    }, getList)
}

function getList(moneyResp) {

    console.log("들어온 것 : "+JSON.stringify(moneyResp))

    let text = "";
    pageInfo = moneyResp.arMoneyDTO;
    moneyResp.arMoneyDTO.content.forEach(money => {
        text += `<tr>`
        text += `<th class="card-body-title-padding" style="width: 20%;">`
        text += `<div class="donater-info">`
        text += `<div class="donater-info-text">`
        text += `<div class="donater-name">`+money.peopleUserName+`</div>`
        text += `<div>`+ money.peopleUserEmail+`</div>`
        text += `</div>`
        text += `</div>`
        text += `</th>`
        text += `<th class="card-body-title-padding" style="width: 15%;">`
        text += `<div class="donate-info-height">` + money.peopleNickname + `</div>`
        text += `</th>`
        text += `<th class="card-body-title-padding" style="width: 21%;">`
        text += `<div class="donate-info-height">`+ money.schoolName +`</div>`
        text += `</th>`
        text += `<th class="card-body-title-padding" style="width: 20%;">`
        text += `<div class="donate-info-height">`+ money.moneyCash +`</div>`
        text += `</th>`
        text += `<th class="card-body-title-padding" style="width: 23%;">`

        var createdDateView = money.createdDate.split('T')[0].split('-');
        var year = createdDateView[0]+'년 ';
        var month = createdDateView[1]+'월 ';
        var date = createdDateView[2]+'일';
        var result = year + month + date;

        text += `<div class="donate-info-height">`+ result +`</div>`
        text += `</th>`
    })
    $(".card-body-main-box").html(text)
    pageingBtn();
}



/* 최신순, 많은순 */
function getList1(param, callback, error){
    let existKeyword = param.keyword.length != 0;
    let queryString = "/" + param.page || 1;
    queryString += existKeyword ? "/" + param.keyword : "";
    $.ajax({
        url : "/adminRest/money"+queryString,
        type : "get",
        success : function (moneyResp, status, xhr) {
            if(callback){
                callback(moneyResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}

/* 오래된순, 많은순 */
function getList1Asc(param, callback, error){
    let existKeyword = param.keyword.length != 0;
    let queryString = "/" + param.page || 1;
    queryString += existKeyword ? "/" + param.keyword : "";
    $.ajax({
        url : "/adminRest/moneyAsc"+queryString,
        type : "get",
        success : function (schoolResp, status, xhr) {
            if(callback){
                callback(schoolResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}
/* 최신순, 적은순 */
function getList2(param, callback, error){
    let existKeyword = param.keyword.length != 0;
    let queryString = "/" + param.page || 1;
    queryString += existKeyword ? "/" + param.keyword : "";
    $.ajax({
        url : "/adminRest/moneyAmount"+queryString,
        type : "get",
        success : function (moneyResp, status, xhr) {
            if(callback){
                callback(moneyResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}
/* 오래된순, 적은순 */
function getList2Asc(param, callback, error){
    let existKeyword = param.keyword.length != 0;
    let queryString = "/" + param.page || 1;
    queryString += existKeyword ? "/" + param.keyword : "";
    $.ajax({
        url : "/adminRest/moneyAmountAsc"+queryString,
        type : "get",
        success : function (moneyResp, status, xhr) {
            if(callback){
                callback(moneyResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}










/* 최신, 많은순 */
function search1() {
    console.log("search1()실행")
    getList1({
        keyword: $search.val(),
        page: globalThis.page
    }, getList);
}
/* 최신, 적은순 */
function search1asc() {
    console.log("search1asc()실행")

    getList2({
        keyword: $search.val(),
        page: globalThis.page
    }, getList);
}


/* 오래된순, 많은순 */
function search2() {
    console.log("search2()실행")

    getList1Asc({
        keyword: $search.val(),
        page: globalThis.page
    }, getList);
}
/* 오래된순, 적은순 */
function search2asc() {
    console.log("search2asc()실행")

    getList2Asc({
        keyword: $search.val(),
        page: globalThis.page
    }, getList);
}





/* 엔터키 이벤트 */
$search.on("keyup", function (event) {
    if (event.keyCode === 13) {
        globalThis.page=0;
        search1()
    }
});

//-----------------------------페이징 버튼 처리-----------------------------

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
    search1();
    console.log("페이징 처리 쪽 search1()실행")
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

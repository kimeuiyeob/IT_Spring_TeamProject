/* ------------------필터 클릭, 호버--------------------- */
const $filter = $('.card-toolbar-item').eq(0);
const $filterDropdown = $('.menu-sub-dropdown');
const $filters = $('.card-toolbar-item');
let check1 = false;

$(document).on('click', function (e) {
    if (check1) {
        if (e.target.closest('.menu-sub-dropdown') == e.currentTarget.querySelector('.menu-sub-dropdown').closest('.menu-sub-dropdown')) {
            if($(".apply-button").text().match('적용')){
                $filterDropdown.css('display', 'none');
                $filter.css('background-color', '#f6f8fa');
                $filter.css('color', '#009ef7');
                $filter.find('#filter-img').css({
                    'background': `url('/imgs/admin/filterBlue.png')`,
                    'background-size': '13px'
                });
                check1 = !check1;
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
    console.log('방가')
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


showWithdrawalAll();

/*===========Ajax===============================*/
/*============전체목록 조회=====================*/
function showWithdrawalAll(){
    $.ajax({
        url : "/adminRest/withdrawal",
        type : "post",
        success : function (items) {
            let text="";
            items.forEach(function(item){
                text += `<tr>`
                text += `<th class="card-body-title-padding" style="width: 25%;">`
                text += `<div class="donater-info" style="height: 50px">`
                text += `<div class="donater-info-text">`
                text += `<div class="donater-name">`+ item.withdrawalUserType +`</div>`
                text += `</div>`
                text += `</div>`
                text += `</th>`
                text += `<th class="card-body-title-padding" style="width: 43%;">`
                text += `<div class="donate-info-height">`+item.withdrawalReason+`</div>`
                text += `<th class="card-body-title-padding" style="width: 30%;">`

                let date = new Date(item.createdDate);
                let year = date.getFullYear().toString().substr(2) + '년 ';
                let month = date.getMonth() + 1 + '월 ';
                let day = date.getDate() + '일';
                let createdDateView = year + month + day;

                text += `<div class="donate-info-height">`+createdDateView+`</div>`
                text += `</th>`
                text += `</tr>`
            })
            $(".card-body-main-box").html(text)
        }
    })
}


/*필터*/
// $filterAccept = $(".apply-button");
//
// $filterAccept.on("click",function(){
//     if($("#option6").text().match('최신순')){
//         showWithdrawalAll();
//     }else{
//         showAsc();
//     }
// })


/*오름차순 조회*/
function showAsc(){
    $.ajax({
        url : "/adminRest/withdrawalAsc",
        type : "post",
        success : function (withdrawalsAsc) {
            let text="";
            withdrawalsAsc.forEach(function(withdrawal){
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

/*                let date = new Date(withdrawal.createdDate);
                let year = date.getFullYear().toString().substr(2) + '년 ';
                let month = date.getMonth() + 1 + '월 ';
                let day = date.getDate() + '일';
                let createdDateView = year + month + day;*/

                text += `<div class="donate-info-height">`+withdrawal.createdDate+`</div>`
                text += `</th>`
                text += `</tr>`
            })
            $(".card-body-main-box").html(text)
        }
    })
}
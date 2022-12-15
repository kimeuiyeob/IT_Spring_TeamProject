/* ------------------필터 클릭, 호버--------------------- */
const $filter = $('.card-toolbar-item').eq(0);
const $filterDropdown = $('.menu-sub-dropdown');
const $filters = $('.card-toolbar-item');
const $filtersLast = $('.card-toolbar-itemBox').children().last()

let check1 = false;

$(document).on('click', function (e) {
    if (check1) {
        if (e.target.closest('.menu-sub-dropdown') == e.currentTarget.querySelector('.menu-sub-dropdown').closest('.menu-sub-dropdown')) {
            console.log('들어옴1');
            if($(".apply-button").text().match('닫기')){
                console.log('들어옴2');
                $filterDropdown.css('display', 'none');
                $filter.css('background-color', '#f6f8fa');
                $filter.css('color', '#009ef7');
                $filter.find('#filter-img').css({
                    'background': `url('/imgs/admin/filterBlue.png')`,
                    'background-size': '13px'
                });
                check1 = !check1;
                globalThis.page=0;
                if($("#option4").text().match('내림차순')) {
                    console.log('search()실행')
                    search();
                }else if($("#option4").text().match('오름차순')){
                    console.log('search()2실행')
                    search2();
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
    $(this).find('#update-img').css({
        'background': `url('/imgs/admin/updateWhite.png') no-repeat`,
        'background-size': '17px'
    })
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
    $(this).find('#update-img').css({
        'background': `url('/imgs/admin/updateBlue.png') no-repeat`,
        'background-size': '17px'
    })
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

/* 삭제 모달*/
$filtersLast.on('click', function () {
    if ($('input:checkbox[name=check]:checked').length == 0) {
        $(".delete-modal1").css('display','block');
        body.style.overflow = 'hidden';
    } else {
        if (deleteModal.classList.contains('show')) {
            body.style.overflow = 'hidden';
        }
        deleteModal.classList.toggle('show');
    }
})

/* 확인 모달창 닫기 */
$(".delete-modal-cancel1").on('click', function () {
    $(".delete-modal1").css('display','none');
    body.style.overflow = 'unset';
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

/* ----------------체크박스-------------------- */

/*전체선택*/
$('.card-body-title-box').on('click','.notice-checked-all' , function(e) {

    const $checkBox = $('.notice-checked');
    const $checkBoxAll = $('.notice-checked-all');

    if ($checkBoxAll.is(':checked')) {
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBoxAll.prev().css('display', 'flex');
        $checkBox.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBox.prev().css('display', 'flex');
        $checkBox.prop('checked', true);
    } else {
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBoxAll.prev().css('display', 'none');
        $checkBox.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBox.prev().css('display', 'none');
        $checkBox.prop('checked', false);
    }

})

/* 하나 선택 */
$('.card-body-main-box').on('click', '.notice-checked', function (e) {

    const $checkBox = $('.notice-checked');
    const $checkBoxAll = $('.notice-checked-all');

    if ($(this).is(':checked')) {
        $(this).closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $(this).prev().css('display', 'flex')
    } else {
        $(this).closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5')
        $(this).prev().css('display', 'none')
        $checkBoxAll.prop('checked', false);
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBoxAll.prev().css('display', 'none');
    }
    if ($checkBox.filter(":checked").length == $checkBox.length) {
        $checkBoxAll.prop('checked', true);
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBoxAll.prev().css('display', 'flex');
    }

})

function fnGetdata(){
    var chkArray = new Array(); // 배열 선언

    $('input:checkbox[name=check]:checked').each(function() { // 체크된 체크박스의 value 값을 가지고 온다.
        chkArray.push($(this).siblings('.noticeId').val());
    });

    // 삭제할 회원번호들
    $('#hiddenValue').val(chkArray);
    console.log('hidden : '+$('#hiddenValue').val());
    // console.log('chkArray : '+chkArray[0]);

    $.ajax({
        type : 'POST'
        ,url : '/noticeRest/noticeDelete'
        ,data : {chkArray: chkArray}
        ,traditional: true
        ,success : function(result) {
            alert("해당 게시글이 정상적으로 삭제되었습니다.");
            location.replace("/admin/notice")
        },
        error: function(request, status, error) {
        }
    })
}


/* -----------공지사항 추가,수정 모달창-------------- */
const $addNotice = $('.card-toolbar-item').eq(2);
const $updateNotice = $('.card-toolbar-item').eq(1);
const addNoticeModal = $('.add-schedule-modal')[0];
const updateNoticeModal = $('.update-notice-modal')[0];
const $modalSubmit = $('.modal-submit');
const $closeImg = $('.close-img');

$closeImg.on('click', function () {
    if ($(this).closest('.update-notice-modal').length > 0) {
        updateNoticeModal.classList.toggle('show');
    }
    if ($(this).closest('.add-schedule-modal').length > 0) {
        addNoticeModal.classList.toggle('show');
    }
    body.style.overflow = 'auto';
})

$modalSubmit.on('click', function (e) {
    e.preventDefault();
    console.log("$modalSubmit : "+$(this))

    if ($(this).closest('.update-notice-modal').length > 0) {
        updateNoticeModal.classList.toggle('show');
    }
    if ($(this).closest('.add-schedule-modal').length > 0) {
        addNoticeModal.classList.toggle('show');
    }
})


$updateNotice.on('click', function () {
    console.log("$updateNotice : "+$(this))

    updateNoticeModal.classList.toggle('show');

    if (updateNoticeModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
})

$(".donate-info-text").on('click', function () {
    updateNoticeModal.classList.toggle('show');

    if (updateNoticeModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
})

$addNotice.on('click', function () {
    addNoticeModal.classList.toggle('show');

    if (addNoticeModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
})

$("#hover6").on('click',function () {
    $(".notice-title").val('');
})

/* -----------삭제 모달창-------------- */
const $delete = $('.donate-outBox');
const $finalDelete = $('.delete-modal-delete');
const $finalDeleteCancel = $('.delete-modal-cancel');
const deleteModal = $('.delete-modal')[0];

/* 삭제 OK 모달*/
$finalDelete.on('click', function () {
    deleteModal.classList.toggle('show');
    body.style.overflow = 'auto';
    fnGetdata();    //회원삭제
})
$finalDeleteCancel.on('click', function () {
    deleteModal.classList.toggle('show');
    body.style.overflow = 'auto';
})

$delete.on('click', function () {
    deleteModal.classList.toggle('show');

    if (deleteModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
})

$delete.on('mouseover', function () {
    $(this).css('color', '#009ef7');
    $(this).css('background-color', '#f1faff');
})
$delete.on('mouseout', function () {
    $(this).css('color', '#7e8299');
    $(this).css('background-color', '#f5f8fa');
})

/* -----------------썸머노트-------------- */
$(document).ready(function() {
    jb('.summernote').summernote({
        placeholder: '공지사항을 작성하세요',
        tabsize: 2,
        height: 280,
        lang: "ko-KR",
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
        ]
    });
});

$(document).ready(function() {
    jb('.summernote2').summernote({
        placeholder: '공지사항을 작성하세요',
        tabsize: 2,
        height: 280,
        lang: "ko-KR",
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
        ],
    });
    $(document).on('click', '.modal-submit', function () {
        update()
    });
});

/* -------------- 페이지 이동 ---------------- */
const $pageNumberLink = $('.page-number-link');

$pageNumberLink.on('mouseover', function () {
    $(this).css('background-color', '#f4f6fa');
    $(this).css('color', '#009ef7');
})

$pageNumberLink.on('mouseout', function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#5e6278');
})

/*=========================================================*/
const $search = $('input[name = search]');
globalThis.page = 0;
let $pagingBtnFlex = $('.paging-number-flex');
let pageInfo;

/* 처음 뿌리기 */
noticeShow();

function noticeShow(){
    console.log('몇페이지 : '+page);
    getNoticeList1({
        noticeTitle: $search.text(),
        page: globalThis.page
    }, getNoticeList)
}

function getNoticeList(noticeResp) {
    let text = "";
    pageInfo = noticeResp.arNoticeDTO;
    noticeResp.arNoticeDTO.content.forEach(notice => {
        text += `<tr>`
        text += `<th class="card-body-title-checkbox-padding" style="width: 14%;margin-top: 22px;padding-bottom: 31px;padding-top: 7px;}">`
        text += `<label class="card-body-title-user-checkbox">`
        text += `<div class="check-img"></div>`
        text += `<input class="notice-checked checked" type="checkbox" name="check">`
        text += `<input type="hidden" value="` + notice.noticeId + `"name ="check2" class="noticeId">`
        text += `<input type="hidden" name="hiddenValue" id="hiddenValue" value=""/>`
        text += `</label>`
        text += `</th>`
        text += `<th class="card-body-title-padding" style="width: 50%;">`
        text += `<div class="donate-info-height">`
        text += `<div class="donate-info-text-title" style="cursor: pointer">` + notice.noticeTitle + `</div>`
        text += `</div>`
        text += `</div>`
        text += `</th>`
        text += `<th class="card-body-title-padding" style="width: 32%;">`

        let date = new Date(notice.createdDate);
        let year = date.getFullYear().toString().substr(2)+'년 ';
        let month = date.getMonth() + 1 +'월 ';
        let day = date.getDate()+'일';
        let createdDateView = year+month+day;


        text += `<div class="donate-info-height">` + createdDateView + `</div>`
        text += `</div>`
        text += `</th>`
        text += `</tr>`

    })
    $(".card-body-main-box").html(text)
    pageingBtn();
}



function getNoticeList1(param, callback, error) {

    let existNoticeTitle = param.noticeTitle.length != 0;
    let queryString = "/" + param.page || 1;

    queryString += existNoticeTitle ? "/" + param.noticeTitle : "";
    $.ajax({
        url: "/noticeRest/list" + queryString,
        type: "get",
        success: function (noticeResp, status, xhr) {
            if (callback) {
                callback(noticeResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}

function getNoticeList1Asc(param, callback, error) {

    let existNoticeTitle = param.noticeTitle.length != 0;
    let queryString = "/" + param.page || 1;

    queryString += existNoticeTitle ? "/" + param.noticeTitle : "";
    $.ajax({
        url: "/noticeRest/listAsc" + queryString,
        type: "get",
        success: function (noticeResp, status, xhr) {
            if (callback) {
                callback(noticeResp)
            }
        },
        error: function (xhr, status, err) {
            if (error) {
                error(err);
            }
        }
    });
}

/* 검색 */
function search() {
    console.log("$search.text() : "+$search.val())
    getNoticeList1({
        noticeTitle: $search.val(),
        page: globalThis.page
    }, getNoticeList);
}

/* 검색 오름차순 */
function search2() {
    console.log("$search.text() : "+$search.val())
    getNoticeList1Asc({
        noticeTitle: $search.val(),
        page: globalThis.page
    }, getNoticeList);
}







/* 이름, 주소 검색 */
$search.on("keyup", function (event) {
    if (event.keyCode === 13) {
        globalThis.page=0;
        search()
    }
});

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

$(".add-submit").on('click', function () {
    alert('공지사항이 등록되었습니다')
})



$(".card-body").on('click','.donate-info-text-title', function (event) {
    event.preventDefault();
    // console.log($(this).closest('.donate-info-height').closest('.card-body-title-padding').siblings('.card-body-title-checkbox-padding').children('.card-body-title-user-checkbox').children('.noticeId').val())

    let noticeId = $(this).closest('.donate-info-height').closest('.card-body-title-padding').siblings('.card-body-title-checkbox-padding').children('.card-body-title-user-checkbox').children('.noticeId').val()

    updateNoticeModal.classList.toggle('show');

    if (updateNoticeModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }

    //상세보기 데이터 전달
    showDetail(noticeId);
})

var existContent = "";
function showDetail(noticeId) {
    $.ajax({
        url: "/noticeRest/info/" + noticeId,
        type: "get",
        success: function (result) {
            $(".hidden-noticeId").val(noticeId);
            $(".modal-info-padding").val(result.noticeTitle);
            $(".note-editable").eq(0).html(result.noticeContent);
        },
    });


}

function update() {

    let noticeId = $(".hidden-noticeId").val()
    let noticeTitle = $(".modal-info-padding").eq(0).val();
    let noticeContent = $(".note-editable").eq(0).html();
    console.log("noticeId" + noticeId);
    console.log(noticeTitle);
    console.log(noticeContent);

    $.ajax({
        url: "/noticeRest/modify",
        data: {noticeId: noticeId, noticeTitle: noticeTitle, noticeContent: noticeContent},
        type: "get",
        success: function (result) {
            console.log(result)
            alert('게시글 수정이 완료되었습니다');
        },
    });

}

// $('#summernote').on('summernote.change', function(we, contents, $editable) {
//     $('input[name=groupContent]').attr('value', $(".note-editable").html());
// });

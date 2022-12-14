const modalCancel = $('.modalBtn').eq(0);
let cancelSchedule;
$cancels.on('click', function () {
    console.log("눌림")
    cancelSchedule = $(this).parent();
})

modalCancel.on('click', function () {
    $modalWrap.hide();
    body.css('overflow', 'auto');
    // cancelSchedule.remove();
    fnGetdata();
})

function fnGetdata() {

    $('input:hidden[name=donationId]').each(function () { // 체크된 체크박스의 value 값을 가지고 온다.
        // 삭제할 회원번호들
        // console.log('hidden : '+$('#hiddenValue').val());
        // console.log('chkArray : '+chkArray[0]);

        $.ajax({
            type: 'POST'
            , url: '/myPageRest/serviceDelete'
            , data: {donationId: donationId}
            , traditional: true
            , success: function (result) {
                alert("해당 기부가 정상적으로 삭제되었습니다.");
                location.replace("schedule")
            },
            error: function (request, status, error) {
            }
        })
    })
}

    globalThis.page = 0;
    let $pagingBtnFlex = $('.paging-number-flex');
    let pageInfo;

// 처음 목록 가져오기
    show();

    function getList(serviceResp) {
        let text = "";
        pageInfo = serviceResp.arServiceDTO;
        serviceResp.arServiceDTO.content.forEach(services => {
            text += `<div class="listItems flexBetween">`
            text += `<input type="hidden" value="` + services.donationId + `" name ="donationId" class="donationId"/>`
            text += `<input type="hidden" name="hiddenValue" id="hiddenValue" value=""/>`
            text += `<div class="items-name flexCol">` + services.schoolName + `</div>`
            text += `<div class="items-location">` + (services.schoolAddress + services.schoolAddressDetail) + `</div>`
            text += `<div class="items-date flexCol">` + (services.serviceVisitDate.substr(0, 10)) + `</div>`
            text += `<div class="items-cancel flexCol">취소</div>`
            text += `</div>`
        })
        $(".listSection").html(text)
        pageingBtn();
    }


    function getListSend(param, callback, error) {

        let queryString = "/" + param.page || 1;

        console.log(JSON.stringify(param));
        console.log("queryString : " + queryString);

        $.ajax({
            url: "/myPageRest/schedule" + queryString,
            type: "get",
            success: function (likeResp, status, xhr) {
                if (callback) {
                    callback(likeResp)
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

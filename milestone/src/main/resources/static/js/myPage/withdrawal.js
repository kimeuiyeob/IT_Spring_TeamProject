const $precautionCheck = $('.precautionCheck'); //주의사항 모두 확인
const $withdrawalBtn = $('.withdrawalBtn'); //회원탈퇴하기 버튼
const $reasonRadio = $('input[name = "reasonRadio"]'); //탈퇴이유 각각 체크박스
const $reasonInputWrap = $('.reasonInputWrap'); //직접 입력
const $reasonInput = $('.reasonInput'); //직접 입력 인풋태그

const $radio = $("input[type = 'radio']"); //타입 radio 체크
let reason = "";
/*====================================================================*/
//체크박스 체크했을때 값
$radio.on('click', function () {
    reason = $(this).parent().next().text();
    $('#reason').val(reason);
    console.log($('#reason').val())
})

/*====================================================================*/
//인풋태그 입력한 값
$reasonInput.on('blur', function () {
    $('#reason').val($reasonInput.val());
    console.log($('#reason').val())
})

/*====================================================================*/
/*유효성 검사 -> 둘다 체크했을때 회원탈퇴 클릭 가능!!*/
$precautionCheck.on('click', function () {
    console.log("주의사항 클릭")
    if ($radio.is(':checked')) {
        $withdrawalBtn.attr("disabled", !$precautionCheck.is(':checked'));
    }
})

$radio.on('click', function () {
    console.log("체크박스 클릭")
    if ($precautionCheck.is(':checked') && $reasonInput.val().length === 0) {
        $withdrawalBtn.attr("disabled", !$radio.is(':checked'));
    }
})

/*====================================================================*/

$reasonRadio.on('click', function () {
    if ($reasonRadio.last().is(':checked')) {
        $reasonInputWrap.show();
    } else {
        $reasonInputWrap.hide();
    }
})

// $withdrawalBtn.on('click', function () {
//     console.log("이유 :  " + reason + "  회원탈퇴 버튼 클릭!!!")
//     location.href = "/mypage/deleteUser?reason=" + reason;
// })

/*====================================================================*/
$withdrawalBtn.on('click', function () {
    deleteAndSave(reason);
})
/*====================================================================*/
function deleteAndSave(reason) {
    $.ajax({
        url: "/mypage/deleteUser",
        type: "post",
        data: JSON.stringify(reason),
        contentType: "application/json; charset=utf-8",
        success: function () {
            location.href = "/main/main"
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
/*====================================================================*/
$withdrawalBtn.on('click', function () {
    deleteAndSave2(reason);
})
/*====================================================================*/
function deleteAndSave2(reason) {
    $.ajax({
        url: "/mypage/deleteUser2",
        type: "post",
        data: JSON.stringify(reason),
        contentType: "application/json; charset=utf-8",
        success: function () {
            location.href = "/main/main"
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
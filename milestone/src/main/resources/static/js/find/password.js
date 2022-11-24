const $submitBtn = $('.submitBtn');
const $modalIcon = $('.modalIcon');
const $modalWrap = $('.modalWrap');
const $modalContent = $('.modalContent');
const $detailMsg = $('.detailMsg');
const $exclamationMark = $('.exclamationMark');
const $exclamationMarkSuccess = $('.exclamationMarkSuccess');
const $password = $('input [type="password"]');

$submitBtn.on('click', function (e) {
    // $password.eq(0).attr('required',false);
    // $password.eq(1).attr('required',false);
    e.preventDefault();
    $exclamationMark.hide();
    $exclamationMarkSuccess.show();
    $modalContent.text('비밀번호 재설정')
    $detailMsg.text('비밀번호가 변경되었습니다')
    $modalWrap.show();
    setTimeout(function () {
        $modalWrap.hide();
    }, 2000)
})
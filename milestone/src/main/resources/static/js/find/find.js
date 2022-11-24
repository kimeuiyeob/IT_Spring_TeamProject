const $submitBtn = $('.submitBtn');
const $modalIcon = $('.modalIcon');
const $modalWrap = $('.modalWrap');
const $modalContent = $('.modalContent');
const $detailMsg = $('.detailMsg');
const $exclamationMark = $('.exclamationMark');
$exclamationMark.show();

$submitBtn.on('click', function (e) {
    e.preventDefault();
    if ($(this).text() == '아이디 찾기') {
        $modalContent.text('회원 정보 없음')
        $detailMsg.text('입력하신 정보는 마일스톤에 존재하지 않는 회원입니다.')
        $modalWrap.show();
        setTimeout(function () {
            $modalWrap.hide();
        }, 2000)
    } else {
        $modalContent.text('이메일 오류')
        $detailMsg.text('해당 이메일은 마일스톤에 가입되어 있지 않습니다')
        $modalWrap.show();
        setTimeout(function () {
            $modalWrap.hide();
        }, 2000)

    }

})
/*========================지수모달==========================*/

let writeTalent = $('#writeTalent');
let $modalWrap = $('.modalWrap');
let body = $('body');
let $modalBtn = $('.modalBtn');
let modalWrap = document.querySelector('.modalWraps');


$(writeTalent).on('click', function () {
    $modalWrap.show();
    body.css('overflow', 'hidden');
})


$(".css-1oteowz").click(function () {
    $(".modalWrap").css({"display": "none"})
    body.css('overflow','auto');
})

document.addEventListener('click', function (e) {
    if (e.target == modalWrap || e.target == $modalBtn[1]) {
        $modalWrap.hide();
        body.css('overflow', 'auto');
    }
    console.log(e.target);
});

/*=================================================*/

/* ===========카테고리 목록 드롭다운================== */

const $moreSelect = $('div.inputCos');
const $moreSelectList = $('div.moreSelectWrap');
const $moreSelectItems = $('div.moreSelectItem');
const $inputBank = $('input[name = "bank"]');

$moreSelectItems.on('click', function () {
    console.log($(this).text());
    $inputBank.css("color", 'black');
    $inputBank.val($(this).text());
    $moreSelectList.hide();
})

document.addEventListener('click', function (e) {
    if (!(e.target == $moreSelectList[0])) {
        if ($moreSelectList.css("display") == 'block') {
            $moreSelectList.hide();
            return;
        }
    }
    if ($(e.target).closest('.inputCos')[0] === $moreSelect[0]) {
        $moreSelectList.toggle();
    }
})

/*===================================================*/

/* ===================지역 선택 목록 드롭다운================= */

const $inputCos = $('#inputCos');
const $moreSelectWrap = $('#moreSelectWrap');
const $place = $('div.place');
const $talent = $('input[name = "talent"]');

$place.on('click', function () {
    $talent.css("color", '#303441');
    $talent.val($(this).text());
    $moreSelectWrap.hide();
})

document.addEventListener('click', function (e) {
    if (!(e.target == $moreSelectWrap[0])) {
        if ($moreSelectWrap.css("display") == 'block') {
            $moreSelectWrap.hide();
            return;
        }
    }
    if ($(e.target).closest('#inputCos')[0] === $inputCos[0]) {
        $moreSelectWrap.toggle();
    }
})

/*======================글자수제한======================*/

const $textareaCos = $('#kimeuiyeob');
const $contentLength = $('.contentLength');
const maxContent = 300;

$textareaCos.keyup(function(e){
    $contentLength.text($textareaCos.val().length)
    if($textareaCos.val().length > maxContent){
        $textareaCos.val($textareaCos.val().substring(0, maxContent));
        $contentLength.text(maxContent);
    }
})
const $precautionCheck = $('.precautionCheck');
const $withdrawalBtn = $('.withdrawalBtn');
const $reasonRadio = $('input[name = "reasonRadio"]');
const $reasonInputWrap = $('.reasonInputWrap');
const $reasonInput = $('.reasonInput');

const $radio = $("input[type = 'radio']");
let reason = "";

$radio.on('click', function () {
    reason = $(this).parent().next().text();
    $('#reason').val(reason);
    console.log($('#reason').val())
})

$reasonInput.on('blur', function () {
    $('#reason').val($reasonInput.val());
    console.log($('#reason').val())
})

$precautionCheck.on('click', function () {
    $withdrawalBtn.attr("disabled", !$precautionCheck.is(':checked'));
})

$reasonRadio.on('click', function () {
    if ($reasonRadio.last().is(':checked')) {
        $reasonInputWrap.show();
    } else {
        $reasonInputWrap.hide();
    }
})
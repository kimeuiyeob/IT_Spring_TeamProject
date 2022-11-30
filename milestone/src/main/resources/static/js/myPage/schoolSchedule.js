const modalCancel = $('.modalBtn').eq(0);
let cancelSchedule;
$cancels.on('click', function () {
    cancelSchedule = $(this).parent();
})

modalCancel.on('click', function () {
    $modalWrap.hide();
    body.css('overflow', 'auto');
    cancelSchedule.remove();
})



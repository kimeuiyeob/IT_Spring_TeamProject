const $cancels = $('.items-cancel');
let $modalWrap = $('.modalWrap');
let body = $('body');
let $modalBtn = $('.modalBtn');
let modalWrap = document.querySelector('.modalWrap');
const element = document.getElementsByClassName('.item-name');

// $cancels.on('click', function () {
//     $modalWrap.show();
//     body.css('overflow', 'hidden');
// })

$(".listSection").on('click', ".items-cancel" ,function () {
    cancelSchedule = $(this).parent();
    $modalWrap.show();
    body.css('overflow', 'hidden');
    console.log($('input:hidden[name=donationId]').val());
    console.log($(this).siblings('.donationId').val());
    fnGetdata()
});



document.addEventListener('click', function (e) {
    if (e.target == modalWrap || e.target == $modalBtn[1]) {
        $modalWrap.hide();
        body.css('overflow', 'auto');
    }
})

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

$(".listSection").on('click', ".items-cancel", function () {
    donationId = $(this).closest('article').find('input[name="donationId"]').val();
    cancelSchedule = $(this).parent();
    $modalWrap.show();
    body.css('overflow', 'hidden');
    console.log(donationId);
});



// const serviceVisitDate = $(this).closest('article').find('input[name="serviceVisitDate"]').val();
// const serviceVisitDate1 = document.getElementsByName('serviceVisitDate').values();
// $(document).ready(function () {
//     var test = new Array(); // 배열 선언
//     $('input[name="serviceVisitDate"]').each(function () { // 체크된 체크박스의 value 값을 가지고 온다.
//         test.push(document.getElementsByName('serviceVisitDate').values());
//         test.forEach(element => {
//             console.log(element);
//         })
//     });
// })



document.addEventListener('click', function (e) {
    if (e.target == modalWrap || e.target == $modalBtn[1]) {
        $modalWrap.hide();
        body.css('overflow', 'auto');
    }
})

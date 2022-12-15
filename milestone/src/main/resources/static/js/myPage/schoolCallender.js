/* 달력 헤드 */
const $prevBtn = $('.prevMonth');
const $nextBtn = $('.nextMonth');
const $nowMonth = $('.nowMonth');
let $dateScope;

let today = new Date();
let todayYear = today.getFullYear();
let todayMonth = today.getMonth() + 1;
let year = todayYear;
let month = todayMonth;
let planDates = [];
let count = 0;
let $serviceVisitDate = $('input[name="serviceVisitDate"]');
console.log($serviceVisitDate.eq(2).val());
$("input[name='serviceVisitDate']").each(function (i) {
    console.log(($serviceVisitDate).eq(i).val().split("T")[0].split("-")[0]+("년 "));
    var year = ($serviceVisitDate).eq(i).val().split("T")[0].split("-")[0]+("년 ");
    console.log(($serviceVisitDate).eq(i).val().split("T")[0].split("-")[1]+("월 "));
    var month = ($serviceVisitDate).eq(i).val().split("T")[0].split("-")[1]+("월 ");
    console.log(($serviceVisitDate).eq(i).val().split("T")[0].split("-")[2]+("일"));
    var day = ($serviceVisitDate).eq(i).val().split("T")[0].split("-")[2]+("일");
    console.log(year + month + day);
    var dates =  (year + month + day);
    console.log(dates);
    planDates.push({ "planDay": dates });
});
// planDates.push({"planDay": ''})
/* 지금은 기부일정 임시로 넣어놓은 값 */
// planDates.push({ "planDay": '2022년 11월 2일' });
// planDates.push({ "planDay": '2022년 11월 5일' });
// planDates.push({ "planDay": '2022년 11월 15일' });
// planDates.push({ "planDay": '2022년 12월 25일' });

createCallender();

function createCallender() {
    createScope();
    callender();
}

/* 달력 요소 생성 */
function createScope() {
    let $callenderBody = $('.callenderBody');
    let text = "";
    for (let i = 0; i < 6; i++) {
        text += "<tr>"
        for (let i = 0; i < 7; i++) {
            text += "<td>"
            text += "<div class='daysBackground'>"
            text += "</div>"
            text += "</td>"
        }
        text += "</tr>"
    }
    $callenderBody.append(text);
    $dateScope = $('.daysBackground');
}

/* 달력 생성 */
function callender() {
    count = 0;
    nextCount = 0;
    firstDay = new Date(year, month - 1, 1).getDay();
    lastDate = new Date(year, month - 1, 0).getDate();
    thisDate = new Date(year, month, 0).getDate();
    $dateScope.css("background-color", "#fff");
    $nowMonth.text(year + '년 ' + month + '월');

    for (let i = 0; i < $dateScope.length; i++) {
        /* 이전 달 숫자 생성 */
        if (i < firstDay) {
            $dateScope.eq(i).css("color", 'rgb(200, 201, 207)')
            $dateScope.eq(i).text(lastDate - (firstDay - 1) + i);
        }
        /* 현재 달 숫자 생성 */
        if (i >= firstDay && (count <= thisDate)) {
            count++;
            $dateScope.eq(i).css("color", '#303441')
            $dateScope.eq(i).text(count);
            /* 일정 체크 */
            planDates.forEach(function (plan) {
                let splitSpace = plan.planDay.replace(' ', '');
                let planYear = splitSpace.split('년')[0];
                let planMonth = splitSpace.split('년')[1].split('월')[0];
                let planDate = splitSpace.split('년')[1].split('월')[1].replace('일', '').replace(' ', '');

                if ($dateScope.eq(i).text() == planDate) {
                    if (month == planMonth) {
                        if (year == planYear) {
                            $dateScope.eq(i).css("background-color", "#ffd400");
                        }
                    }
                }
            })
        }
        /* 다음 달 숫자 생성 */
        if (count > thisDate) {
            nextCount++;
            $dateScope.eq(i).css("color", 'rgb(200, 201, 207)')
            $dateScope.eq(i).text(nextCount);
        }
    }
}


/* 이전버튼 */
$prevBtn.on('click', function () {
    /* 달력 헤더  */
    month = month - 1;
    if (month == 0) {
        month = 12;
        year--;
        if (year < 0) {
            year = 99;
        }
    }
    $nowMonth.text(year + '년 ' + month + '월');
    /* 달력 바디 */
    callender();

})

/* 다음버튼 */
$nextBtn.on('click', function () {
    /* 달력 헤더  */
    month = month + 1;
    if (month == 13) {
        month = 1;
        year++;
    }
    $nowMonth.text(year + '년 ' + month + '월');
    /* 달력 바디 */
    callender();
})

/* 달력 바디 호버 이벤트*/
// const $tds = $('td');
// $tds.hover(function () {
//     $(this).find('.daysBackground').css("filter", "brightness(90%)");
// }, function () {
//     $(this).find('.daysBackground').css("filter", "brightness(100%)");
// })

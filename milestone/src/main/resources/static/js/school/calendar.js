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

/* 오늘 날짜 */
let todayAr = [today.getFullYear(), today.getMonth() + 1, today.getDate()];
let result = todayAr[0] + "년 " + todayAr[1] + "월 " + todayAr[2] + "일";
planDates.push({"planDay": result});

/* 날짜 클릭 */
/* 눌린 날짜 */
let saveDate = "";
/* 날짜 플래그 */
let checkC = false;

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

    let thisIsToday = 0;
    let thisIsMonth = 0;
    let thisIsYear = 0;


    /* 이번달 저장 */
    thisIsMonth = today.getMonth() + 1;

    /* 이번연도 저장 */
    thisIsYear = today.getFullYear();

    for (let i = 0; i < $dateScope.length; i++) {

        ;

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


            /* 저장된 날짜 뿌려주기 */
            if (saveDate) {
                let dateTemp = saveDate.split('-');
                let savedYear = dateTemp[0];
                let savedMonth = dateTemp[1];
                let savedDate = dateTemp[2];
                if ($dateScope.eq(i).text() == savedDate) {
                    if (month == savedMonth) {
                        if (year == savedYear) {
                            $dateScope.eq(i).css({"background-color": "#ffd400"});
                        }
                    }
                }
            }

            /* 오늘 표시 */
            planDates.forEach(function (plan) {
                let splitSpace = plan.planDay.replace(' ', '');
                let planYear = splitSpace.split('년')[0];
                let planMonth = splitSpace.split('년')[1].split('월')[0];
                let planDate = splitSpace.split('년')[1].split('월')[1].replace('일', '').replace(' ', '');

                if ($dateScope.eq(i).text() == planDate) {
                    if (month == planMonth) {
                        if (year == planYear) {
                            $dateScope.eq(i).css({"color": "rgb(0 95 255)"});
                            /* 오늘 일 index로 저장 */
                            thisIsToday = i;
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
            $dateScope.eq(i).css({"pointer-events": "none"});
        }


        /* 오늘날짜보다 이전날짜 선택 막아주기 위한 컬러*/
        if (thisIsToday == 0 && month <= thisIsMonth && year == thisIsYear) {
            $dateScope.eq(i).css("color", 'rgb(189 186 186)');
        }else {
            $dateScope.eq(i).addClass("thisMonthDays")
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


// /* 달력 바디 호버 이벤트*/
const $tds = $('td');
// $tds.hover(function () {
//     $(this).find('.daysBackground').css("filter", "brightness(90%)");
// }, function () {
//     $(this).find('.daysBackground').css("filter", "brightness(100%)");
// })

$tds.on('click', function () {
    $tds.find(".daysBackground").css("background-color", "transparent");
    saveDate = "";
    var str = year + (month<10? "-0":"-") + month + (this.innerText<10? "-0":"-") + this.innerText + " 00:00:00";
    thisIsMonth = today.getMonth() + 1;
    thisIsYear = today.getFullYear();

    if ($(this).find("div.daysBackground").css("color") === 'rgb(189, 186, 186)' && month <= thisIsMonth && year == thisIsYear) {
        alert("지난 날짜는 선택하실 수 없습니다");
    } else {
        if ($(this).find("div.daysBackground").css("color") === 'rgb(48, 52, 65)') {
            $(this).find(".daysBackground").css("background-color", "#ffd400");
            saveDate += str;
            checkC = true;
        }
    }
})



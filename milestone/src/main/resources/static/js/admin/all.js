/* ----------사이드 메뉴 마우스 이벤트------------- */
var $menuLink = $('.menu-icon').closest('.menu-link');
var $active = $('.active');

$menuLink.on("click", function () {
    $(this).next().slideToggle(100);
    $(this).css('color', '#fff');
})
$menuLink.on("mouseover", function () {
    $(this).css('color', '#fff');
})
$menuLink.on("mouseout", function () {
    $(this).css('color', '#9D9DA6');
})

$active.on("mouseover", function () {
    $(this).css('color', '#fff');
})
$active.on("mouseout", function () {
    $(this).css('color', '#9D9DA6');
})


/* ------------------필터 클릭--------------------- */
const $filter = $('.card-toolbar-item').eq(0);
const $filterDropdown = $('.menu-sub-dropdown');
let check1 = false;

$body.on('click', function (e) {
    if (check1) {
        if (e.target.closest('.menu-sub-dropdown') == e.currentTarget.querySelector('.menu-sub-dropdown').closest('.menu-sub-dropdown')) {
        } else {
            $filterDropdown.css('display', 'none');
            $filter.css('background-color', '#f6f8fa');
            $filter.css('color', '#009ef7');
            $filter.find('#filter-img').css({
                'background': `url('/imgs/admin/filterBlue.png')`,
                'background-size': '13px'
            });
            check1 = !check1;
        }
    }
})

$filter.on("click", function () {
    if ($filterDropdown.css('display') == 'none') {
        $filter.css('background-color', '#009ef7');
        $filter.css('color', '#fff');
        $filter.find('#filter-img').css({
            'background': `url('/imgs/admin/filterWhite.png')`,
            'background-size': '13px'
        });
        $filterDropdown.css('display', 'flex');
        setTimeout(() => {
            check1 = !check1;
        }, 100);
    }
})

/* ---------------------필터 서브-------------- */
const $selectOption = $('.menu-sub-dropdown-option-box');

$selectOption.on('click', function () {
    console.log('방가')
    if ($(this).find('input').is(':checked')) {
        $(this).find('.menu-sub-dropdown-option-sub').css('display', 'flex');
        $(this).find('input').prop('checked', true);
    } else {
        $(this).find('.menu-sub-dropdown-option-sub').css('display', 'none');
        $(this).find('input').prop('checked', false);
    }
})

/* ----------------- 필터 서브 옵션 호버---------------- */
const $subOption = $('.menu-sub-dropdown-option-sub-item');

$subOption.on('mouseover', function () {
    $(this).css('background-color', '#f4f6fa');
    $(this).css('color', '#009ef7');
})
$subOption.on('mouseout', function () {
    $(this).css('background-color', '#fff');
    $(this).css('color', '#5e6278');
})

$subOption.on('click', function () {
    $(this).closest('label').find('.menu-sub-dropdown-option-text').html($(this).html());
})

/* ------------------ 필터 서브 옵션 푸터 호버----------------*/

const $subOptionResetChoose = $('.menu-sub-dropdown-Botton').eq(0);
const $subOptionApllyChoose = $('.menu-sub-dropdown-Botton').eq(1);
$subOptionApllyChoose.css('background-color', '#009ef7');
$subOptionApllyChoose.css('color', '#fff');

$subOptionResetChoose.on('mouseover', function () {
    $(this).css('color', '#009ef7');
})
$subOptionResetChoose.on('mouseout', function () {
    $(this).css('color', '#7e8299');
})

$subOptionApllyChoose.on('mouseover', function () {
    $(this).css('background-color', '#0095e8');
})
$subOptionApllyChoose.on('mouseout', function () {
    $(this).css('background-color', '#009ef7');
})

/* ----------------체크박스-------------------- */
const $checkBox = $('.notice-checked');
const $checkBoxAll = $('.notice-checked-all');

$checkBoxAll.on('click', function () {
    if ($checkBoxAll.is(':checked')) {
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBoxAll.prev().css('display', 'flex');
        $checkBox.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBox.prev().css('display', 'flex');
        $checkBox.prop('checked', true);
    } else {
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBoxAll.prev().css('display', 'none');
        $checkBox.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBox.prev().css('display', 'none');
        $checkBox.prop('checked', false);
    }
})

$checkBox.on('click', function (e) {

    if ($(this).is(':checked')) {
        $(this).closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $(this).prev().css('display', 'flex')
    } else {
        $(this).closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5')
        $(this).prev().css('display', 'none')
        $checkBoxAll.prop('checked', false);
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#eff2f5');
        $checkBoxAll.prev().css('display', 'none');
    }
    if ($checkBox.filter(":checked").length == $checkBox.length) {
        $checkBoxAll.prop('checked', true);
        $checkBoxAll.closest('.card-body-title-user-checkbox').css('background-color', '#009ef7');
        $checkBoxAll.prev().css('display', 'flex');
    }
})


/* -----------공지사항 추가 모달창-------------- */
const body = document.querySelector('body');
const $addNotice = $('.card-toolbar-item').eq(1);
const addNoticeModal = $('.add-schedule-modal')[0];
const $modalSubmit = $('.modal-submit');
const $closeImg = $('.close-img');

$closeImg.on('click', function () {
    addNoticeModal.classList.toggle('show');
    body.style.overflow = 'auto';
})

$modalSubmit.on('click', function () {
    addNoticeModal.classList.toggle('show');
    body.style.overflow = 'auto';
})

$addNotice.on('click', function () {
    addNoticeModal.classList.toggle('show');

    if (addNoticeModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
})

/* -----------삭제 모달창-------------- */
const $delete = $('.donate-outBox');
const $finalDelete = $('.delete-modal-delete');
const $finalDeleteCancel = $('.delete-modal-cancel');
const deleteModal = $('.delete-modal')[0];

$finalDelete.on('click', function () {
    deleteModal.classList.toggle('show');
    body.style.overflow = 'auto';
})

$finalDeleteCancel.on('click', function () {
    deleteModal.classList.toggle('show');
    body.style.overflow = 'auto';
})

$delete.on('click', function () {
    deleteModal.classList.toggle('show');

    if (deleteModal.classList.contains('show')) {
        body.style.overflow = 'hidden';
    }
})

/* -----------------썸머노트-------------- */

jb('#summernote').summernote({
    placeholder: 'Hello stand alone ui',
    tabsize: 2,
    height: 280,
    toolbar: [
        ['style', ['style']],
        ['font', ['bold', 'underline', 'clear']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['table', ['table']],
        ['insert', ['link', 'picture', 'video']],
        ['view', ['fullscreen', 'codeview', 'help']]
    ]
});

/* ----------그래프------------- */
google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(synthesisPieChart);
google.charts.setOnLoadCallback(generalLineChart);
google.charts.setOnLoadCallback(nurserySchoolLineChart);
google.charts.setOnLoadCallback(nonMemberLineChart);
google.charts.setOnLoadCallback(synthesisLineChart);

function synthesisLineChart() {
    var data = google.visualization.arrayToDataTable([
        ['일', '종합'],
        ['28일', 2022],
        ['29일', 2047],
        ['30일', 2352],
        ['1일', 2590],
        ['2일', 2513],
        ['3일', 2510],
        ['4일', 2604]
    ]);

    var options = {
        legend: { position: 'none' },
        colors: ['#d33b2f'],
        chartArea: { left: 50, top: 10, width: '100%', height: '90%' },
    };

    var chart = new google.visualization.LineChart(document.getElementById('synthesisLineChart'));

    chart.draw(data, options);
}

function synthesisPieChart() {
    var data = google.visualization.arrayToDataTable([
        ['회원종류', '방문수'],
        ['일반', 456],
        ['보육원', 150],
        ['비회원', 1871],
    ]);

    var options = {
        legend: { position: 'none' },
        pieHole: 0.7,
        chartArea: { left: 0, top: 0, width: '100%', height: '100%' },
        colors: ['#5d9df0', '#7fca8f', '#e5e6ee']
    };
    var chart = new google.visualization.PieChart(document.getElementById('synthesisPieChart'));
    chart.draw(data, options);
}

function generalLineChart() {
    var data = google.visualization.arrayToDataTable([
        ['일', '방문수'],
        ['1일', 478],
        ['02일', 465],
        ['03일', 434],
        ['04일', 456]
    ]);

    var options = {
        legend: { position: 'none' },
        colors: ['#5d9df0'],
        chartArea: { left: 30, top: 10, width: '100%', height: '80%' },
    };

    var chart = new google.visualization.LineChart(document.getElementById('generalLineChart'));

    chart.draw(data, options);
}

function nurserySchoolLineChart() {
    var data = google.visualization.arrayToDataTable([
        ['일', '방문수'],
        ['1일', 124],
        ['02일', 138],
        ['03일', 131],
        ['04일', 150]
    ]);

    var options = {
        legend: { position: 'none' },
        colors: ['#7fca8f'],
        chartArea: { left: 30, top: 10, width: '100%', height: '80%' },
    };

    var chart = new google.visualization.LineChart(document.getElementById('nurserySchoolLineChart'));

    chart.draw(data, options);
}

function nonMemberLineChart() {
    var data = google.visualization.arrayToDataTable([
        ['일', '방문수'],
        ['1일', 1988],
        ['02일', 1910],
        ['03일', 1945],
        ['04일', 1998]
    ]);

    var options = {
        legend: { position: 'none' },
        colors: ['#e5e6ee'],
        chartArea: { left: 30, top: 10, width: '100%', height: '80%' },
    };

    var chart = new google.visualization.LineChart(document.getElementById('nonMemberLineChart'));

    chart.draw(data, options);
}

 /* -------------------달력------------------------ */
 document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        navLinks: true, // can click day/week names to navigate views
        businessHours: true, // display business hours
        editable: true,
        selectable: true,
        locale: 'ko',
        events: [
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-27T13:00:00',
                constraint: 'businessHours'
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-25T13:00:00',
                constraint: 'businessHours'
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-21T13:00:00',
                constraint: 'businessHours'
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-05T13:00:00',
                constraint: 'businessHours'
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-10T13:00:00',
                constraint: 'businessHours'
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-09-25T13:00:00',
                constraint: 'businessHours'
            },
        ]
    });

    calendar.render();
});




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
        editable: false,
        selectable: true,
        locale: 'ko',
        events: [
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-27T13:00:00',
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-25T13:00:00',
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-21T13:00:00',
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-05T13:00:00',
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-10-10T13:00:00',
            },
            {
                title: '지수수박님 뱀 보육원',
                start: '2022-09-25T13:00:00',
            },
        ]
    });

    calendar.render();
});
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
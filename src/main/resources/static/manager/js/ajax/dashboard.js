$(document).ready(function () {
    $.ajax({
        url: '/api/revenue/linechart',
        success: function (result) {
            var date = JSON.parse(result).date;
            var revenue = JSON.parse(result).revenue;
            drawLineChart(date, revenue)
        }
    })

    function drawLineChart(date, revenue) {
        Highcharts.chart('hightchars', {
            chart: {
                type: 'line',
                width: null
            },
            title: {
                text: 'Doanh số các ngày trong tuần'
            },
            xAxis: {
                categories: date,
                title: {
                    text: 'Month'
                },
                labels: {
                    style: {
                        fontSize: '14px' // Cấu hình kích thước font size cho nhãn trục x
                    }
                }
            },
            yAxis: {
                title: {
                    text: 'Value'
                },
                labels: {
                    style: {
                        fontSize: '14px'
                    }
                }
            },
            tooltip: {
                formatter: function () {
                    return '<strong>' + this.x + ': <strong>' + this.y;
                },
                style: {
                    fontSize: '15px'
                }
            },
            series: [{
                data: revenue
            }]
        });
    }

    $.ajax({
        url: '/api/revenue',
        method: "POST",
        success: function (data) {
            $('#getRevenueWidthMonth').empty();

            $.each(data, function (index, week) {
                $.each(week, function (index, result) {
                    var totalOrder = result[1] !== null ? result[1].toLocaleString('vi-VN') : 0;
                    var revenue = result[0] !== null ? result[0].toLocaleString('vi-VN') : 0;
                    var startDate = result[2] !== null ? formatDate(result[2]) : 0;
                    var endDate = result[3] !== null ? formatDate(result[3]) : 0;

                    var row = '<tr>' +
                        '<td>' + result[2] + '</td>' +
                        '<td>' + result[3] + '</td>' +
                        '<td>' + totalOrder + '</td>' +
                        '<td>' + revenue + '</td>' +
                        '</tr>';
                    $("#getRevenueWidthMonth").append(row);
                });
            });
        },
        error: function (xhr, status, error) {
            console.error("Lỗi khi lấy dữ liệu doanh số:", error);
        }
    })


    function formatDate(dateString) {
        // Chuyển đổi chuỗi ngày tháng từ yyyy-mm-dd thành dd-mm-yyyy
        var dateParts = dateString.split("-");
        return dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
    }
});
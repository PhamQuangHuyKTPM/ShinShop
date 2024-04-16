$(document).ready(function() {
    $('.viewOrder').click(function() {
        // Lấy giá trị của input ẩn chứa id
        var orderId = $(this).closest('tr').find('input[type="hidden"]').val();

        $.ajax({
            type: 'POST',
            url: '/api/order/orderDetail',
            contentType: 'application/json',
            data: orderId,
            success: function(response) {
                console.log('Gửi thành công:', response);
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi nếu gửi thất bại
                console.error('Lỗi khi gửi dữ liệu:', error);
            }
        });
    });
});
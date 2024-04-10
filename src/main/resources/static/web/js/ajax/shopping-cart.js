$(document).ready(function() {
    // Lắng nghe sự kiện click cho nút tăng số lượng
    $(document).on('click', '.qty-control__increase', function() {
        var input = $(this).siblings('.qty-control__number');
        var newValue = parseInt(input.val());
        input.val(newValue);
        
        updateSubtotal(input);
    });

    // Lắng nghe sự kiện click cho nút giảm số lượng
    $(document).on('click', '.qty-control__reduce', function() {
        var input = $(this).siblings('.qty-control__number');
        var newValue = parseInt(input.val());
        if (newValue >= 1) {
            input.val(newValue);
            updateSubtotal(input);
        }
    });

    // Hàm cập nhật giá trị subtotal
    function updateSubtotal(input) {
        var quantity = parseInt(input.val());
        var price = parseFloat(input.closest('tr').find('.shopping-cart__product-price').text().replace('$', ''));
        var subtotal = quantity * price;
        input.closest('tr').find('.shopping-cart__subtotal').text(subtotal.toFixed(2));
        var productId = input.closest('tr').find('.shopping-cart__product-item__id').val();
        console.log(productId);
        
        $.ajax({
                type: 'POST',
                url: '/api/shopping-cart/update', // Thay đổi đường dẫn đến endpoint của bạn nếu cần
                data: {
                    id: productId,
                    quantity: quantity
                },
                success: function(response) {
                    if(response != "null"){
                        console.log(response);
                        $("#totalCartPrice").text(response.totalPrices);
                    }
                },
                error: function(xhr, status, error) {
                
                    console.error("Lỗi khi cập nhật số lượng sản phẩm:", error);
                }
        });

    }

    $(document).on('click', '.remove-cart', function() {
        var productId = $(this).closest('tr').find('.shopping-cart__product-item__id').val();
        $.ajax({
            type: 'POST',
            url: '/api/shopping-cart/delete', // Đường dẫn đến endpoint xử lý xóa sản phẩm
            data: productId,
            success: function(response) {
                if(response != "null"){
                    console.log(response);
                    $("#totalCartPrice").text(response.totalPrices);
                }
                
            },
            error: function(xhr, status, error) {
                // Xử lý lỗi nếu có
                console.error("Lỗi khi xóa sản phẩm khỏi giỏ hàng:", error);
            }
        });
    });

});
$(document).ready(function() {
    $(document).on('click', '.js-add-cart', function() {
        // Lấy id sản phẩm từ thuộc tính data-id của nút
        var id = $(this).data('id');

        $.ajax({
                type: 'POST',
                url: '/api/shopping-cart/add-item-to-cart',
                contentType: 'application/json',
                data: id,
                success: function (response) {
                  if (response == "null") {
                    console.log(response);
                    window.location.href = "/home/login";
                  } else {
                    var listItemCart = $("#list-item-shopping-cart");
                    console.log(response);
                  }
                },
                error: function (xhr, status, error) {
                  console.error(error.responseText);
                }
              });
    });
});
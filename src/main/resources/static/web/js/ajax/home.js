$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/api/shopping-cart",
        success: function(cart) {
            
            console.log(cart);
            if(cart != "null"){
                $(".js-cart-items-count").text(cart.length);

                var totalCartPrice = 0;
                var listItemCart = $("#list-item-shopping-cart");
                // Tạo HTML cho mỗi mục trong giỏ hàng và thêm vào cartDrawer
                cart.forEach(function(item) {
                    var cartItemHtml = `
                    <div class="cart-drawer-item d-flex position-relative">
                        <div class="position-relative">
                            <img loading="lazy" class="cart-drawer-item__img" src="/api/files/${item.product.image}" width="120" height="120">
                        </div>
                        <div class="cart-drawer-item__info flex-grow-1">
                            <h6 class="cart-drawer-item__title fw-normal">${item.product.name}</h6>

                            <p class="cart-drawer-item__option text-secondary">Size: ${item.size}</p>
                            <div class="d-flex align-items-center justify-content-between mt-1">
                                <div class="qty-control position-relative qty-initialized">
                                    <input type="number" name="quantity" value="${item.quantity}" min="1" class="qty-control__number border-0 text-center">
                                    <div class="qty-control__reduce text-start">-</div>
                                    <div class="qty-control__increase text-end">+</div>
                                </div><!-- .qty-control -->
                                <span class="cart-drawer-item__price money price">${item.totalPrice}</span>
                            </div>
                        </div>
                        <button class="btn-close-xs position-absolute top-0 end-0 js-cart-item-remove"></button>
                    </div><!-- /.cart-drawer-item d-flex -->`;

                    // Thêm HTML của mục vào cartDrawer
                    listItemCart.append(cartItemHtml);
                    listItemCart.append('<hr class="cart-drawer-divider">');
                    totalCartPrice += item.totalPrice;
                });

                $("#totalCartPrice").text(totalCartPrice);

            }else{
                var alertCart = $("#list-item-shopping-cart");
                alertCart.append('<div class="text-center alert-warning pt-2 pb-2 "><p>No item in your cart</p></div>')
            }
            
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi nếu cần
            console.error("Lỗi khi lấy danh sách sản phẩm:", error);
        }
    });


});
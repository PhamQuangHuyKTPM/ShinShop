$(document).ready(function () {

    $('#btn-filter').click(function (event) {
        // Ngăn chặn hành động mặc định của nút "Lọc"
        event.preventDefault();

        // Lấy giá trị của .price-range-slider
        var priceRangeValue = $('.price-range-slider').val();

        // Chia chuỗi giá trị thành mảng các giá trị riêng biệt
        var priceValues = priceRangeValue.split(',');

        // Gán giá trị minPrice và maxPrice cho các phần tử input tương ứng
        $('#minPrice').val(priceValues[0]);
        $('#maxPrice').val(priceValues[1]);

        var selectElement = document.getElementById("category-select");
        var cate = [];

        console.log(typeof selectElement);
        for (var i = 0; i < selectElement.options.length; i++) {
            // Nếu option có thuộc tính selected
            if (selectElement.options[i].selected) {
                cate.push(selectElement.options[i].value);
            }
        }
        for(var i = 0; i < cate.length; i++){
             console.log(cate[i]);
             console.log(typeof cate[i]);
        }

        var dataToSend = {
            categories: cate,
            sizes: null,
            minPrice: priceValues[0],
            maxPrice : priceValues[1]
        };
        

        $.ajax({
            type: 'POST',
            url: '/api/product/filterProduct',
            data: JSON.stringify(dataToSend),
            contentType: 'application/json',
            success: function (data) {
                console.log("ok");
                $('#products-grid').empty();
                $.each(data, function(index, product){
                    var imageUrl = '/api/files/' + product.image;
                    var newProductHTML = `
                    <div class="product-card-wrapper">
                        <div class="product-card mb-3 mb-md-4 mb-xxl-5">
                            <div class="pc__img-wrapper">
                                <div class="swiper-container background-img js-swiper-slider swiper-container-initialized swiper-container-horizontal swiper-container-pointer-events" data-settings="{&quot;resizeObserver&quot;: true}">
                                    <div class="swiper-wrapper" id="swiper-wrapper-64dd3a6e9e638b82" aria-live="polite" style="transform: translate3d(-660px, 0px, 0px); transition-duration: 0ms;">
                                        <div class="swiper-slide swiper-slide-duplicate swiper-slide-duplicate-active" data-swiper-slide-index="1" role="group" aria-label="1 / 4" style="width: 330px;">
                                            <a href="./product1_simple.html"><img loading="lazy" src="${imageUrl}" width="330" height="400" alt="Cropped Faux leather Jacket" class="pc__img"></a>
                                        </div>
                                        <div class="swiper-slide swiper-slide-prev swiper-slide-duplicate-next" data-swiper-slide-index="0" role="group" aria-label="2 / 4" style="width: 330px;">
                                            <a href="./product1_simple.html"><img loading="lazy" src="${imageUrl}" width="330" height="400" alt="Cropped Faux leather Jacket" class="pc__img"></a>
                                        </div><!-- /.pc__img-wrapper -->
                                        <div class="swiper-slide swiper-slide-active" data-swiper-slide-index="1" role="group" aria-label="3 / 4" style="width: 330px;">
                                            <a href="./product1_simple.html"><img loading="lazy" src="${imageUrl}" width="330" height="400" alt="Cropped Faux leather Jacket" class="pc__img"></a>
                                        </div><!-- /.pc__img-wrapper -->
                                        <div class="swiper-slide swiper-slide-duplicate swiper-slide-next swiper-slide-duplicate-prev" data-swiper-slide-index="0" role="group" aria-label="4 / 4" style="width: 330px;">
                                            <a href="./product1_simple.html"><img loading="lazy" src="${imageUrl}" width="330" height="400" alt="Cropped Faux leather Jacket" class="pc__img"></a>
                                        </div>
                                    </div>
                                    <span class="pc__img-prev" tabindex="0" role="button" aria-label="Previous slide" aria-controls="swiper-wrapper-64dd3a6e9e638b82"><svg width="7" height="11" viewBox="0 0 7 11" xmlns="http://www.w3.org/2000/svg">
                                            <use href="#icon_prev_sm"></use>
                                        </svg></span>
                                    <span class="pc__img-next" tabindex="0" role="button" aria-label="Next slide" aria-controls="swiper-wrapper-64dd3a6e9e638b82"><svg width="7" height="11" viewBox="0 0 7 11" xmlns="http://www.w3.org/2000/svg">
                                            <use href="#icon_next_sm"></use>
                                        </svg></span>
                                    <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
                                </div>
                                <button class="pc__atc btn anim_appear-bottom btn position-absolute border-0 text-uppercase fw-medium js-add-cart js-open-aside" data-aside="cartDrawer" title="Add To Cart">Thêm vào giỏ hàng</button>
                            </div>

                            <div class="pc__info position-relative">
                                <p class="pc__category">${product.category.categoryName}</p>
                                <h6 class="pc__title"><a href="./product1_simple.html">${product.name}</a>
                                </h6>
                                <div class="product-card__price d-flex">
                                    <span class="money price">${product.totalPriceVND}</span>
                                </div>

                                <button class="pc__btn-wl position-absolute top-0 end-0 bg-transparent border-0 js-add-wishlist" title="Add To Wishlist">
                                    <svg width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <use href="#icon_heart"></use>
                                    </svg>
                                </button>
                            </div>
                        </div>
                    </div>
                    `;
                    $('#products-grid').append(newProductHTML);
                });
            },
            error: function (xhr, status, error) {
                console.log(error);
            }
        });
    });

});
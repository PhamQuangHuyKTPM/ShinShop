$(document).ready(function() {
        $('#submitForm').click(function() {
            var formData = {
                name: $('#name').val(),
                price: $('#price').val(),
                price_sale: $('#price_sale').val()
                // Thêm các trường khác nếu cần
            };

            $.ajax({
                type: 'POST',
                url: '/api/product/validate',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function(response) {
                    console.log(response);
                    $('.error').text('');

                    $('.form-group').removeClass("has-error");
                
                    if (response.fieldErrors) {
                        // Display field-specific error messages
                        $.each(response.fieldErrors, function(field, errorMessage) {
                            $('#' + field + 'Error').text(errorMessage);
                            $('#' + field + 'Error').addClass("text-danger");
                            $('#' + field + 'Error').parent().addClass("has-error");
                        });
                    } else {
                        $("#formSave").submit();
                    }
                },
                error: function(xhr, status, error) {
                    console.error(xhr.responseText);
                }
            });
        });
});
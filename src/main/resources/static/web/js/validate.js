$(document).ready(function() {
    $("#btn-register").click(function(event) {
        // Ngăn chặn hành vi mặc định của nút (tránh submit form)
        event.preventDefault();

        var formData = {
            username: $('#username').val(),
            password: $('#password').val(),
            repeatPassword: $('#repeatPassword').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/register/validate',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);

                $('.form-control_gray').removeClass("input-error");
                $('.error-message').text("");

                if(response.hasOwnProperty("usernameExists")){
                    $('#usernameError').text(response.usernameExists);
                    $('#username').addClass("input-error");
                }
                if (response.fieldErrors) {
                    $.each(response.fieldErrors, function(field, errorMessage) {
                        $('#' + field + 'Error').text(errorMessage);
                        $('#' + field + 'Error').addClass("error-message");
                        $('#' + field ).addClass("input-error");
                    });
                }else if(response.hasOwnProperty("passwordErrorLogin")){
                          $('#repeatPasswordError').text(response.passwordMismatch);
                          $('#repeatPassword').addClass("input-error");
                }else if(response.hasOwnProperty("successMessage")){
                          $("#register-formm").submit();
                }
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
        
    });
});
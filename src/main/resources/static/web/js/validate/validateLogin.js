$(document).ready(function() {
    $("#btn-login").click(function(event) {
        // Ngăn chặn hành vi mặc định của nút (tránh submit form)
        event.preventDefault();

        var formData = {
            username: $('#usernameLogin').val(),
            password: $('#passwordLogin').val(),
            repeatPassword: $('#passwordLogin').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/login/validate',
            data: JSON.stringify(formData),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);


                $('.form-control_gray').removeClass("input-errorLogin");
                $('.error-messageLogin').text("");

                if(response.hasOwnProperty("usernameErrorLogin")){
                    $('#usernameErrorLogin').text(response.usernameErrorLogin);
                    $('#usernameLogin').addClass("input-errorLogin");
                }
                if (response.fieldErrors) {
                    $.each(response.fieldErrors, function(field, errorMessage) {
                        $('#' + field + 'ErrorLogin').text(errorMessage);
                        $('#' + field + 'ErrorLogin').addClass("error-messageLogin");
                        $('#' + field + "Login" ).addClass("input-errorLogin");
                    });
                }
                else if(response.hasOwnProperty("passwordErrorLogin")){
                        $('#passwordErrorLogin').text(response.passwordErrorLogin);
                        $('#passwordLogin').addClass("input-errorLogin");
                }
                else if(response.hasOwnProperty("successMessage")){
                        $("#login-form").submit();
                }
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });

    });
});
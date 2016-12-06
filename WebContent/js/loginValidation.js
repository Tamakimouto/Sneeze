$(function() {
    $("#signInButton").click(function() {
        var username = $(this).val();

        var $formFields = $('#signInForm :input');
        var isValid = "Nope";
        var values = {};
        $formFields.each(function() {
            values[this.name] = $(this).val();
        });


        $.ajax({
            type: "POST",
            url: "Validation",
            data: {user: values["user"], pass: values["pass"]},
            success: function(msg) {
                if (msg == "valid") {
                    $('#signInForm').submit();
                } else {
                    $('.signInStatus').html("Invalid Username/Password");
                }

            }
        });
        return false;
    });
});

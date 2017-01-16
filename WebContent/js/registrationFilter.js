$(function() {
    $("#user.signUpField").on('change', function() {
        var username = $(this).val();
        if (username.length > 5) {
            $(".status").html("Checking Availability...");
            $.ajax({
                type: "POST",
                url: "Availability",
                data: "username=" + username,
                success: function(msg) {
                    if(msg == "This username is taken"){
                        $("#signUpButton").addClass("disabled");
                        $("#signUpButton").prop("disabled", true);
                    }else{
                        $("#signUpButton").removeClass("disabled");
                        $("#signUpButton").prop("disabled", false);
                    }
                    $(".status").html(msg);
                }
            });
        } else {
            $(".status").html("Username should be 5 or more characters");
            $("#signUpButton").addClass("disabled");
            $("#signUpButton").prop("disabled", true);
        }
    });
});

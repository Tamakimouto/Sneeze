$(function() {
    $("#user .signInField").on("change", function() {
        var username = $(this).val();
        if (username.length > 5) {
            $(".status").html("Checking Availibility...");
            $.ajax({
                type: "POST",
                url: "Availability",
                data: "username=" + username,
                success: function(msg) {
                    $(".status").ajaxComplete(function(event, request, settings) {
                        $(".status").html(msg);
                    });
                }
            });
        } else {
            $(".status").html("Username should be 5 or more characters");
        }
    });
});

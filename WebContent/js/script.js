function enableAccess() {
    var rdy = true;
    $(".signInField").each(function() {
        if (!$(this).val())
            rdy = false;
    });
    if (rdy) {
        $("#signInButton").removeClass("disabled");
        $("#signInButton").prop("disabled", false);
    } else {
        $("#signInButton").addClass("disabled");
        $("#signInButton").prop("disabled", true);
    }
}

$(window).on("load", function() {
    $(".logo")
        .animate({opacity: "1"}, "fast")
        .animate({width: "40vw"}, "slow")
        .animate({height: "50vh"}, "slow", function() {
            $(".logo-img")
                .animate({transform: "translate(0, 0)"}, "slow")
                .addClass("is-flying");
        });
});

/* Doc Ready */
$(function () {
    $("#aboutButton").click(function() {
        $("html, body").animate({
            scrollTop: $("#aboutSection").offset().top + 1
        }, 600);
    });

    $("#homeButton").click(function() {
        $("html, body").animate({
            scrollTop: $("#homebg").offset().top
        }, 600);
    });

    $("#aboutlinks").affix({
        offset: {
            top: $(window).height()
        }
    });

    $(window).on("scroll", function(e) {
        var pos = $(this).scrollTop();
        var screen = $(this).height();

        if (pos >= screen)
            $(".topNav").css("color", "#337AB7");
        else
            $(".topNav").css("color", "white");
    });

    $(".signInField").on("input", enableAccess);
});

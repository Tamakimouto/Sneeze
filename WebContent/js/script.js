function enableAccess() {
    var rdySignIn = true;

    $(".signInField").each(function() {
        if (!$(this).val())
            rdySignIn = false;
    });

    var signIn = $("#signInButton");
    if (rdySignIn) {
        signIn.removeClass("disabled");
        signIn.prop("disabled", false);
    } else {
        signIn.addClass("disabled");
        signIn.prop("disabled", true);
    }
}

function enableRegister() {
    var rdySignUp = true;

    $(".signUpField").each(function() {
        if (!$(this).val())
            rdySignUp = false;
    });

    var signUp = $("#signUpButton");
    if (rdySignUp) {
        signUp.removeClass("disabled");
        signUp.prop("disabled", false);
    } else {
        signUp.addClass("disabled");
        signUp.prop("disabled", true);
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
    enableAccess();
    enableRegister();
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
    $(".signUpField").on("input", enableRegister);
});

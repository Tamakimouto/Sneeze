$(window).on("load", function(){
    $(".logo")
        .animate({opacity: "1"}, "fast")
        .animate({width: "40vw"}, "slow")
        .animate({height: "50vh"}, "slow");
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
        var screen = $(window).height();

        if (pos >= screen)
            $(".topNav").css("color", "#337AB7");
        else
            $(".topNav").css("color", "white");
    });
});

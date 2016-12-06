$(function() {
    var sneezeApp = new Vue({
        el: "#sneezeApp",
        data: {
            animations: ["bounceInLeft", "tada", "fadeInDown", "flipInX", "rotateIn", "slideInDown", "zoomIn"]
        },
        computed: {
            animation: function() {
                var animation = this.animations[Math.floor(Math.random() * this.animations.length)];
                var ret = {};
                ret[animation] = true;
                return ret;
            }
        }
    });

    $(".log-out").on("click", function() {
        window.location.href = "index.html";
    });
});

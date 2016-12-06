function getCookieValue(a, b) {
    b = document.cookie.match('(^|;)\\s*' + a + '\\s*=\\s*([^;]+)');
    return b ? b.pop() : '';
}

$(window).on("load", function() {
    var userName = getCookieValue('sneezeUser');
    $("#user .signInField").value = userName;
});

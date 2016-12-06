/**
 * loadCookie.js
 *
 * Reads a certain cookie on initial render and fills in username slot.
 *
 * @author  Anthony Zheng   <Anthony@fopen-dream.space>
 */

function getCookieValue(a, b) {
    b = document.cookie.match('(^|;)\\s*' + a + '\\s*=\\s*([^;]+)');
    return b ? b.pop() : '';
}

$(window).on("load", function() {
    var userName = getCookieValue('sneezeUser');
    $("#user .signInField").value = userName;
});

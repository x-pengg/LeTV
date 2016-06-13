$.ajaxSetup({cache: false, timeout: 60000, dataType: 'json'});

$(document).ajaxError(function (event, jqxhr, settings, thrownError) {
    if (thrownError === 'parsererror') {
        console.log('Requested JSON parse failed.');
    } else if (thrownError === 'timeout') {
        console.log("服务器连接超时.");
        return;
    } else if (jqxhr.status === 0) {
        console.log("网络故障,请检查您的网络");
        return;
    } else {
        console.log('Uncaught Error.n' + jqxhr.responseText);
    }
    var status = jqxhr.status;
    if (status == 599) { //未登录或需要重新登录
        window.location.href = "/login?r=" + encodeURIComponent(window.location.href);
    }
});
function nav(uri) {
    window.location.href = uri;
}
function toHome() {
    nav('/');
}
function getFormValue(id){
    var paramObj = {};
    $.each($('#'+id).serializeArray(), function(_, kv) {
        paramObj[kv.name] = kv.value;
    });
    return paramObj;
}
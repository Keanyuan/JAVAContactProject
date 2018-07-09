var jq = $.noConflict();
jq(function () {
    // $("p").click(function(){
    //     //     $(this).hide();
    //     // });
    jq("p").click(function () {
        jq(this).hide();
    });
});
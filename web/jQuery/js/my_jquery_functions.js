$(function () {
    $("p").click(function(){
        $(this).hide();
    });
    $(".add-click").click(function () {
        // $("p").hide();
        $(".test").hide();
        $("#test").hide();
    });

    $(".dblclick").dblclick(function () {
        $(this).hide();
    });

    // $(".mouse").mouseenter(function () {
    //     alert("You entered h2! 移动在上边");
    // });

    // $(".mouse").mouseleave(function () {
    //     alert("Bye! You now leave h2! 离开");
    // });
    // $(".mouse").mousedown(function () {
    //     alert("Bye! You now down h2! 按下并点击");
    // });

    // $(".mouse").mouseup(function () {
    //     alert("Bye! You now mouse up h2! 松开并点击");
    // });

    // $(".mouse").hover(function () {
    //     alert("You entered h2! 鼠标悬停");
    // }, function () {
    //     alert("Bye! You now leave h2! 离开");
    // });

    $(".cl-input").focus(function () {
        $(this).css("background-color", "#ff6c92")
    });

    $(".cl-input").blur(function(){
        $(this).css("background-color","#ffffff");
    });

    $(".hide").click(function () {
        $("p").hide(500);
    });

    $(".show").click(function () {
        $("p").show(500);
    });

    $(".toggle").click(function () {
        $("p").toggle(500);
    });

    //淡入
    $(".fadeIn").click(function () {
        $("#div1").fadeIn();
        $("#div2").fadeIn("slow");
        $("#div3").fadeIn(3000);
    });

    //淡出
    $(".fadeOut").click(function () {
        $("#div1").fadeOut();
        $("#div2").fadeOut("slow");
        $("#div3").fadeOut(2000);
    });

    // 点击淡入/淡出
    $(".fadeToggle").click(function () {
        $("#div1").fadeToggle();
        $("#div2").fadeToggle("slow");
        $("#div3").fadeToggle(2000);
    });
    //改变颜色
    $(".fadeTo").click(function () {
        $("#div1").fadeTo("slow",0.15);
        $("#div2").fadeTo("slow",0.4);
        $("#div3").fadeTo("slow",0.7);
    });

    //下滑
    $("#flip").click(function(){
        $("#panel1").slideDown();
        $("#panel2").slideDown("slow");
        $("#panel3").slideDown(1000);
    });

    //下滑
    $("#flip1").click(function(){
        $("#panel1").slideUp();
        $("#panel2").slideUp("slow");
        $("#panel3").slideUp(1000);
    });

    //切换上滑下滑
    $("#flip2").click(function(){
        $("#panel1").slideToggle();
        $("#panel2").slideToggle("slow");
        $("#panel3").slideToggle(1000);
    });

    //淡入
    $(".animate").click(function () {
        var div = $("#div4")
        div.animate({
            left: '+=100px',
            top: '-=100px',
            opacity:'0.5',
            height:'+=20px',
            width:'+=20px'
        },1000);
        div.animate({
            left: '-=100px',
            top: '+=100px',
            opacity:'1',
            height:'-=20px',
            width:'-=20px'
        },1000);
    });
    //toggle-animate
    $(".toggle-animate").click(function () {
        $("#div5").animate({
            height:'toggle'
        },1000);
    });
    //animate-operation
    $(".animate-operation").click(function () {
        var div = $("#div6")
        div.animate({height:'300px',opacity:'0.4'},"slow").animate({width:'300px',opacity:'0.8'},"slow");
        div.animate({height:'100px',opacity:'0.4'},"slow");
        div.animate({width:'100px',opacity:'0.8'},"slow");
        div.animate({left:'100px'},"slow");
        div.animate({fontSize:'2em'},"slow").animate({fontSize:'1em'},"slow");
        div.animate({left:'-=50px'},3000,function () {
            div.animate({left:'-=50px'});
        });
    });
    //animate-operation-stop
    $(".animate-operation-stop").click(function () {
        $("#div6").stop();
    });

    $(".content-attribute-text").click(function () {
        // alert("Text: " + $("#test").text());
        $("#content-att-html").text("hello word")

    });
    $(".content-attribute-html").click(function () {
        alert("Text: " + $("#test").html());
    });
    $(".content-attribute-val").click(function () {
        // alert("值为: " + $("#test1").val());
        $("#content-att-html").val("hello word")
    });
    $(".content-attribute-attr").click(function () {
        // alert("属性值为: " + $("#runoob").attr({
        //     "href" : "//www.baidu.com",
        //     "title" : "jQuery 教程"
        // }));
        $("#runoob").attr("href", function (i, origValue) {
            return origValue + "/jQuery";
        });
    });
    $(".content-attribute-html1").click(function () {
        $("#content-att-html").html("<b>hello word</b>")
    });
    $(".content-attribute-html4").click(function () {
        $("#test4").html(function (i, origText) {
            return "旧文本： " + origText +  " 新文本： hello world （index： " + i + ")";
        });
    });
    $(".content-attribute-html5").click(function () {
        $("#test5").html(function (i, origText) {
            return "旧 HTML ： " + origText + " 新 html: Hello <b>world!</b> (index: " + i + ")";
        })
    });

    $(".append").click(function () {
        $("#test6").append("这是我的结尾")
    });

    $(".prepend").click(function () {
        $("#test6").prepend("这是我的开头")
    });


    $(".after").click(function () {
        var txt1="<b>I </b>";                    // 使用 HTML 创建元素
        var txt2=$("<i></i>").text("love ");     // 使用 jQuery 创建元素
        var txt3=document.createElement("big");  // 使用 DOM 创建元素
        txt3.innerHTML="jQuery!";
        $("#test6").after(txt1, txt2, txt3);
    });
    $(".before").click(function () {
        var txt1="<br><b>I </b>";                    // 使用 HTML 创建元素
        var txt2=$("<i></i>").text("love ");     // 使用 jQuery 创建元素
        var txt3=document.createElement("big");  // 使用 DOM 创建元素
        txt3.innerHTML="jQuery!";
        $("#test6").before(txt1, txt2, txt3);
    });

    $(".remove").click(function () {
        $("#div7").remove();
    });
    $(".remove1").click(function () {
        $("p").remove(".test7");
    });

    $(".empty").click(function () {
        $("#div7").empty();
    });

    //addClass
    $(".addClass").click(function () {
        $("div").addClass("important blue");
    });
    //removeClass
    $(".removeClass").click(function () {
        $("div").removeClass("important");
    });

    //toggleClass
    $(".toggleClass").click(function () {
        $("div").toggleClass("important");
    });
    // setcss
    $(".setcss").click(function () {
        alert("背景颜色 = " + $("#div7").css("background-color"));
    });
    // setcss1
    $(".setcss1").click(function () {
        $("#div8").css({"background-color": "chocolate", "border": "solid 1px #c3c3c3"});
    });

    //  get size
    $(".div-size").click(function () {
        var txt = "";
        txt += "width(): " + $("#div8").width() + "px<br>";
        txt += "height(): " + $("#div8").height() + "px<br>";
        txt += "包括内边距: <br>";
        txt += "innerWidth(): " + $("#div8").innerWidth() + "px<br>";
        txt += "innerHeight(): " + $("#div8").innerHeight() + "px<br>";
        txt += "包括内边距和边框: <br>";
        txt += "outerWidth(): " + $("#div8").outerWidth() + "px<br>";
        txt += "outerHeight(): " + $("#div8").outerHeight() + "px<br>";
        txt += "包括内边距、边框和外边距: <br>";
        txt += "outerWidth(true): " + $("#div8").outerWidth(true) + "px<br>";
        txt += "outerHeight(true): " + $("#div8").outerHeight(true) + "px<br>";
        $("#div8").html(txt);
    });

    //parent
    $(".parent").click(function () {
        $("span").parent().css({
            "color" : "red",
            "border" : "2px solid red"
        });
    });
    //parents
    $(".parents").click(function () {
        $("span").parents().css({
            "color" : "#ff26c9",
            "border" : "2px solid #ff26c9"
        });
    });
    //parentsUntil
    $(".parentsUntil").click(function () {
        $("span").parentsUntil("div").css({
            "color" : "green",
            "border" : "2px solid green"
        });
    });

    //children
    $(".children").click(function () {
        $("div").children().css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //find
    $(".find").click(function () {
        $("div").find("span").css(
            {
                "color" : "blue",
                "border" : "2px solid blue"
            }
        );
    });
    //siblings
    $(".siblings").click(function () {
        $("h2").siblings("p").css(
            {
                "color" : "blue",
                "border" : "2px solid blue"
            }
        );
    });
    //next
    $(".next").click(function () {
        $("h2").next().css(
            {
                "color" : "blue",
                "border" : "2px solid blue"
            }
        );
    });
    //nextAll
    $(".nextAll").click(function () {
        $("h2").nextAll().css(
            {
                "color" : "blue",
                "border" : "2px solid blue"
            }
        );
    });
    //nextUntil
    $(".nextUntil").click(function () {
        $("h2").nextUntil("h6").css(
            {
                "color" : "blue",
                "border" : "2px solid blue"
            }
        );
    });
    //prev
    $(".prev").click(function () {
        $("h2").prev().css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //prevAll
    $(".prevAll").click(function () {
        $("h2").prevAll().css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //prevUntil
    $(".prevUntil").click(function () {
        $("h2").prevUntil("h6").css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //first
    $(".first").click(function () {
        $("div span").first().css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //last
    $(".last").click(function () {
        $("div span").last().css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //eq
    $(".eq").click(function () {
        $("p").eq(2).css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //filter
    $(".filter").click(function () {
        $("p").filter(".paina").css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //not
    $(".not").click(function () {
        $("p").not(".paina").css(
            {
                "color" : "orange",
                "border" : "2px solid orange"
            }
        );
    });
    //ajax1
    $(".cls-ajax").click(function () {
        //http://httpbin.org/get
        $("#div9").load("./static/test.html",function (responseTxt,statusTxt,xhr) {
            $("#div10").append("<hr>responseTxt: " + responseTxt);
            $("#div10").append("<br>statusTxt: " + statusTxt);
            if (statusTxt == "success")
                $("#div10").append("<br>xhr: " + "External content loaded successfully!");
            if (statusTxt == "error")
                $("#div10").append("<br>xhr: " + xhr.statusText);
        });
    });

    //cls-ajax-get
    $(".cls-ajax-get").click(function () {
        // $.get("http://httpbin.org/get", function (data, status) {
        //     alert("数据： " + data + "\n状态： " + status)
        // });
        var url = "http://mobile.ximalaya.com/mobile/discovery/v2/rankingList/group"
        var param = {
            "device": "iPhone",
            "channel": "ios-b1",
            "includeActivity": "true",
            "includeSpecial": "true",
            "scale": "2",
            "version": "5.4.21",
            "channel": "ios-b1"
        }
        $.get(url, param, function (data, status) {
            if (status == "success")
                $("#div11").append("<br>data: " + JSON.stringify(data));
            if (status == "error")
                alert("Error: request error")

        });

    });

    //cls-ajax-post
    $(".cls-ajax-post").click(function () {
        // $.post("https://m.w3cschool.cn/statics/demosource/demo_test_post.php",{
        //     name:"百度",
        //     url:"http://www.baidu.com"
        // },function (data,status) {
        //     if (status == "success")
        //         $("#div11").append("<br>data: " + JSON.stringify(data));
        //     if (status == "error")
        //         alert("Error: request error")
        // });
        $.post("http://example.com",{
            "foo": "bar",
            "baz": [1,2,3]
        },function (data,status) {
            if (status == "success")
                $("#div11").append("<br>data: " + JSON.stringify(data));
            if (status == "error")
                alert("Error: request error")
        });
    });
    //cls-ajax-ajax
    $(".cls-ajax-ajax").click(function () {
        var params = {
            "foo": "bar",
            "baz": [1,2,3]
        }
        $.ajax({
            url: "http://example.com",
            type: "POST",
            data: JSON.stringify(params),
            dataType: "json",
            success: function (data) {
                console.info("success.");
                if (data["status"] == "ok"){
                    alert("Settings is Ok. The Machine is rebooting.");
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.info("error.");
                if (xhr.status == 200) {

                    alert(ajaxOptions);
                }
                else {
                    alert(xhr.status);
                    alert(thrownError);
                }
            }
        });
    });
});

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
});

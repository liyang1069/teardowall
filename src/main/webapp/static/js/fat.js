    var txtObj = document.getElementById("alertSpan");

    //回调函数，用于获取用户当前选择的文字
    function show(str){
        txtObj.innerHTML = str;
    }

    var params = {
        "XOffset":0,            //提示框位置横向偏移量,单位px
        "YOffset":0,            //提示框位置纵向偏移量,单位px
        "width":204,            //提示框宽度，单位px
        "fontColor":"#f70",     //提示框文字颜色
        "fontColorHI":"#FFF",   //提示框高亮选择时文字颜色
        "fontSize":"12px",      //文字大小
        "fontFamily":"宋体",     //文字字体
        "borderColor":"gray",   //提示框的边框颜色
        "bgcolorHI":"#03c",     //提示框高亮选择的颜色
        "sugSubmit":true        //在选择提示词条是是否提交表单
    };

    // BaiduSuggestion.bind("baidu_input",params,show);

    function checkHttps () {
        BaiduHttps.useHttps();    
    };

    function baidu_with_https (formname) {
        var data = BaiduHttps.useHttps();
        if (data.s === 0) {
            return true;
        }
        else {
            formname.action = 'https://www.baidu.com/baidu' + '?ssl_s=1&ssl_c' + data.ssl_code;
            return true;
        }
    };

    function google_with_https(formname) {
        var url = "https://www.google.com.hk/?gws_rd=cr,ssl#newwindow=1&safe=strict&q=";
        if ($("#google_div form .search").val() != '') {
            formname.action = url + $("#google_div form .search").val();
            return true;
        }
    }

    $("#google_btn").click(function(){
        $("#google_div").removeClass("hide");
        $("#baidu_div").addClass("hide");
        $("#bing_div").addClass("hide");
    });

    $("#baidu_btn").click(function(){
        $("#google_div").addClass("hide");
        $("#baidu_div").removeClass("hide");
        $("#bing_div").addClass("hide");
    });

    $("#bing_btn").click(function(){
        $("#google_div").addClass("hide");
        $("#baidu_div").addClass("hide");
        $("#bing_div").removeClass("hide");
    });

    var common_array = ['common-weibo','common-zhihu','common-163','common-12306','common-facebook','common-twitter','common-github','common-tumblr'];
    var press_array = ['press-weibo','press-zhihu','press-163','press-12306','press-facebook','press-twitter','press-github','press-tumblr'];
    $(".common-svg").mouseover(function(){
        for(var i = 0; i < common_array.length; i++) {
            if ($(this).hasClass(common_array[i])) {
                $(this).removeClass(common_array[i]);
                $(this).addClass(press_array[i]);
                break;
            }
        }
    });

    $(".common-svg").mouseout(function(){
        for(var i = 0; i < press_array.length; i++) {
            if ($(this).hasClass(press_array[i])) {
                $(this).removeClass(press_array[i]);
                $(this).addClass(common_array[i]);
                break;
            }
        }
    });

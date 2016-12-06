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

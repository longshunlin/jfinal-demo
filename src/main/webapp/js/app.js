var app = function () {
    return {
        //设置cookie
        setCookie: function (key, value) {
            document.cookie = key + "=" + escape(value) + ';path=/;';
        },
        //设置cookie,带时间
        setCookieByExpires: function (key, val, time) {
            var date = new Date(); //获取当前时间
            var expiresDays = time;  //将date设置为n天以后的时间
            date.setTime(date.getTime() + expiresDays * 60 * 1000); //格式化为cookie识别的时间
            document.cookie = key + "=" + val + ";path=/;expires=" + date.toGMTString() + ";";  //设置cookie
        },
        //获取cookie值
        getCookie: function (key) {
            var a = document.cookie.match(new RegExp("(^| )" + key + "=([^;]*)(;|$)"));
            if (a != null) {
                return unescape(a[2])
            }
            return null
        },
        //判断cookie是否存在
        checkcookie: function (key) {
            var isok = false;
            var strCookie = document.cookie;
            var arrCookie = strCookie.split(";");
            for (var m = 0; m < arrCookie.length; m++) {
                var ac = arrCookie[m];
                if (ac.indexOf(key + '=') != -1) {
                    isok = true;
                    break;
                }
            }
            return isok;
        },
        //删除cookie
        delCookie: function (a) {
            var c = new Date();
            c.setTime(c.getTime() - 1);
            var b = this.getCookie(a);
            if (b != null) {
                document.cookie = a + "=" + b + ";path=/;expires=" + c.toGMTString() + ";";
            }
        },
        // 加密
        encrypt: function (str) {
            var s = '', charCode;
            for (var i = 0, len = str.length; i < len; i++) {
                charCode = str.charCodeAt(i);
                if (charCode >= 33 && charCode <= 79) s += String.fromCharCode(charCode + 47);
                else if (charCode >= 80 && charCode <= 126) s += String.fromCharCode(charCode - 47);
                else if ((charCode >= 0 && charCode <= 32) || (charCode >= 127 && charCode <= 133)) s += String.fromCharCode(charCode) + ',';
                else s += String.fromCharCode(charCode - 5) + '@';
            }
            return s;
        },
        //解密
        decrypt: function (str) {
            var i, charCode, s = '', preIndex;
            for (i = 0; i < str.length; i++) {
                charCode = str.charCodeAt(i);
                if (charCode >= 33 && charCode <= 79) s = s + String.fromCharCode(charCode + 47);
                else if (charCode >= 80 && charCode <= 126) s = s + String.fromCharCode(charCode - 47);
                else {
                    preIndex = i + 1;
                    if (str.substr(preIndex, 1) == "@") s = s + String.fromCharCode(charCode + 5)
                    else s = s + str.substr(i, 1);
                    i++;
                }
            }
            return s;
        },
        getUrlParms: function () {
            var args = new Object();
            var query = location.search.substring(1);//获取查询串
            var pairs = query.split("&");//在逗号处断开
            for (var i = 0; i < pairs.length; i++) {
                var pos = pairs[i].indexOf('=');//查找name=value
                if (pos == -1) continue;//如果没有找到就跳过
                var argname = pairs[i].substring(0, pos);//提取name
                var value = pairs[i].substring(pos + 1);//提取value
                args[argname] = decodeURI(value);//存为属性
            }
            return args;
        },
        getUrlParmString: function (str) {
            var args = new Object();
            var query = str.substring(1);//获取查询串
            var pairs = query.split("&");//在逗号处断开
            for (var i = 0; i < pairs.length; i++) {
                var pos = pairs[i].indexOf('=');//查找name=value
                if (pos == -1) continue;//如果没有找到就跳过
                var argname = pairs[i].substring(0, pos);//提取name
                var value = pairs[i].substring(pos + 1);//提取value
                args[argname] = decodeURI(value);//存为属性
            }
            return args;
        },
        toQueryString: function (obj) {
            var ret = [];
            for (var key in obj) {
                key = decodeURIComponent(key);
                var values = obj[key];
                if (values && values.constructor == Array) {//数组
                    var queryValues = [];
                    for (var i = 0, len = values.length, value; i < len; i++) {
                        value = values[i];
                        if (this.ToQueryPair(key, values)) {
                            queryValues.push(this.ToQueryPair(key, value));
                        }
                    }
                    ret = ret.concat(queryValues);
                } else { //字符串
                    if (this.ToQueryPair(key, values)) {
                        ret.push(this.ToQueryPair(key, values));
                    }
                }
            }
            return ret.join('&');
        },
        toQueryPair: function (key, value) {
            if (typeof value == 'undefined') {
                return key;
            } else if (key === 'platform' || key === 'version') {

                return;
            }
            return key + '=' + decodeURIComponent(value === null ? '' : String(value));
        },
        // 关键字保存数组
        setArraylocalStorage: function (key, value) {
            var array = this.getLocalStorage(key) ? this.getLocalStorage(key).split(',') : [];
            if (!this.Contains(array, value)) {
                if (array.length > 4) {
                    array.pop();
                    array.unshift(value);
                }
                else {
                    array.push(value)
                }
            } else {
                return;
            }
            localStorage.setItem(key, array);
        },
        // 关键字保存数组
        setLocalStorage: function (key, value) {
            localStorage.setItem(key, value);
        },
        //查询不存在的key时，有的浏览器返回undefined，这里统一返回null
        getLocalStorage: function (key) {
            var v = localStorage.getItem(key);
            return v === undefined ? null : v;
        },
        removeLocalStorage: function (key) {
            localStorage.removeItem(key);
        },
        setSessionStorage: function (key, value) {
            sessionStorage.setItem(key, value)
        },
        //查询不存在的key时，有的浏览器返回undefined，这里统一返回null
        getSessionStorage: function (key) {
            var v = sessionStorage.getItem(key);
            return v === undefined ? null : v;
        },
        removeSessionStorage: function (key) {
            sessionStorage.removeItem(key);
        },
        //判断数组是否存在
        contains: function (arr, obj) {
            var i = arr.length;
            while (i--) {
                if (arr[i] === obj) {
                    return true;
                }
            }
            return false;
        },
        center: function () {
            if (this.getCookie('H_PS_645EC')) {
                window.location.href = 'PersonalCenter.html'
            } else {
                window.location.href = 'Login.html'
            }
        },
        //判断是移动端，还是pc端
        checkversion: function () {
            if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
                return true
            } else {
                return false
            }
        },
        //判断是ios，还是android
        checksystem: function () {
            var ua = navigator.userAgent.toLowerCase();
            if (/iphone|ipad|ipod/.test(ua)) {
                return true;
            } else if (/android/.test(ua)) {
                return false;
            }
        },
        //判断是否是微信浏览器
        checkwx: function () {
            var ua = navigator.userAgent.toLowerCase();
            if (ua.match(/MicroMessenger/i) == 'micromessenger') {
                return true;
            } else {
                return false;
            }
        },
        //关注微信公众设置操作方法
        weixinop: function () {
            var endTime = (new Date().getTime() - parseInt(app.getCookie('startTime'))) / 1000;
            if ((endTime >= 60 || app.getCookie('IsBuy')) && !app.getCookie('IsClick')) {
                clearInterval(weixintimer);
                this.alertpublic();
            }
        },
        isLogin: function () {
            return this.getLocalStorage("userId");
        },
        /**
         * 检查用户是否已经登陆
         * @param needRole 需要的权限 0为不限 1为sv 2为sv主管  3tm 4供应商
         */
        checkLogin: function (suId,url) {
            // 如果是微信，且在微信公众号打开链接 则跳到授权页面
            // if (this.checkwx()) {
            //     if (!this.checkcookie("userId")) {
            //         window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + $appid + "&redirect_uri=http://" + $domain + "/login/wxLogin.html&response_type=code&scope=snsapi_userinfo&agentid=" + $agentid + "&state=" + suId + "#wechat_redirect";
            //     } else {
            //         if( url && url != "") {
            //             window.location.href = url;
            //         }
            //     }
            //  } else {
            //      window.location.href = "/wx/error102.html";
            //  }
        },
        getLocationAddress: function (lng, lat, targetId) {
            $.get("/apply/getLocationAddress.action?latitude=" + lat + "&longitude=" + lng, function (data) {
                if (data != "false") {
                    $("#" + targetId).html(data);
                }
            });
        },
        getQQLBSKey : function () {
            return "XXXBZ-2HTKX-4V54S-Z7JEP-D542F-ZLFKS";
        },
        getBMSApplyTypeHtml : function (type,cost,makeName,costRemark) {
            var applyType = '<font style="color: #0BB20C;font-weight: bold;">品牌显现</font>申请<font style="font-weight: bold;">' + makeName + '</font>制作';
            if(type == 1) {
                applyType = '<font style="color: #0BB20C;font-weight: bold;">广告费</font>申请，金额<font style="font-weight: bold;">' + cost + "</font>元";
                if(costRemark.length > 0) {
                    applyType += '，备注信息【<font style="font-weight: bold;">' + costRemark +'</font>】';
                }
            }
            return applyType;
        },
        msg : function (msg) {
            var index = layer.open({content: msg ,skin: 'msg',time: 2 });
            return index;
        },
        loading : function ( content ) {
            var index;
            if(content && content != '') {
                index = layer.open({type: 2 , shadeClose : false , content: content});
            } else {
                index = layer.open({type: 2 , shadeClose : false});
            }
            return index;
        },
        confirm : function ( content , callback ) {
            layer.open({content: content , btn: ['确定', '取消'] , yes: function(index){
                    layer.close(index);
                    if ( callback && typeof callback != 'undefined') {
                        callback();
                    }
                }
            });
        }
    }
}();

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};





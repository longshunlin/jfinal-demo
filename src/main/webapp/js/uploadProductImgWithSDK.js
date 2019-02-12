var isMaxSize = function (file) {
    if (file.size > 10 * 1024 * 1024) {
        layer.alert('产品图片限制上传最大为10M', {
            title: '提示框',
            icon: 0,
        });
        return true;
    }
    return false;
};

var getFileSuffix = function (fileName) {
    var index1 = fileName.lastIndexOf(".");
    var index2 = fileName.length;
    return fileName.substring(index1 + 1, index2);//后缀名
};

var token = null;
var domain = null;
var config = null;
var putExtra = {
    fname: "",
    params: {},
    mimeType: null
};
var bindUpload = function (inputId , btnUploadId , uploadCmpClass , inputUrlId , progressId) {
    if(token == null || domain == null || config == null) {
        return false;
    }
    $("#" + inputId).unbind("change").bind("change", function () {
        var file = this.files[0];
        var btn = $('#'+btnUploadId);
        // eslint-disable-next-line
        var finishedAttr = [];
        // eslint-disable-next-line
        var compareChunks = [];
        var observable;
        var timestamp = new Date().getTime();
        if (file) {
            if (isMaxSize(file)) {
                return;
            }
            $('#' + uploadCmpClass).show();
            var key = timestamp + "." + getFileSuffix(file.name);
            putExtra.params["x:name"] = key.split(".")[0];
            // 设置next,error,complete对应的操作，分别处理相应的进度信息，错误信息，以及完成后的操作
            var error = function (err) {
                btn.text("继续上传");
                console.log(err);
                layer.alert('上传出错', {
                    title: '提示框',
                    icon: 0,
                });
            };
            //完成时的处理
            var complete = function (res) {
                $('#' + uploadCmpClass).hide();
                $('#'+inputUrlId).val(domain + "/" + res.key);
                $('#'+inputUrlId + 'Img').attr("src",$('#'+inputUrlId).val()).show();
                //弹出提示
                layer.msg('图片上传成功。', {
                    icon: 1,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                });
            };
            var updateProgress = function (width) {
                $('#'+progressId).html( width + '%').css({ width : width + '%'});
            };
            var next = function (response) {
                var chunks = response.chunks || [];
                var total = response.total;
                // updateProgress(0);
                updateProgress(total.percent.toFixed(2));
                compareChunks = chunks;
            };

            var subObject = {
                next: next,
                error: error,
                complete: complete
            };
            var subscription;
            // 调用sdk上传接口获得相应的observable，控制上传和暂停
            observable = qiniu.upload(file, key, token, putExtra, config);
            var startUpload = function () {
                btn.text("暂停上传");
                btn.attr("data-status" , "true");
                subscription = observable.subscribe(subObject);
                updateProgress(0);
            };
            var stopUpload = function () {
                btn.text("继续上传");
                btn.attr("data-status" , "false");
                subscription.unsubscribe();
            };
            btn.unbind("click").click(function () {
                var status = $(this).attr("data-status");
                if(status == "false") {
                    startUpload();
                } else {
                    stopUpload();
                }
            });
            startUpload();
        }
    })
};

/**
 * 获取token，并初始化插件
 */
$.ajax({
    url: "/admin/getQiniuToken", success: function (res) {
        token = res.token;
        domain = res.domain;
        config = {
            useCdnDomain: true,
            disableStatisticsReport: false,
            retryCount: 6,
            region: qiniu.region.z2  //默认为华南区
        };
        // 绑定默认
        bindUpload('productFacePic','btnFaceUpload','uploadFace','productFace','faceProgress');
        bindUpload('headPicPic','btnHeadUpload','uploadHead','headPic','headProgress');
        $("#itemList").find(".pic").each(function () {
            var introPicPic = $(this).find('input[name="introPicPic"]').attr("id");
            var introPic = $(this).find('input[name="introPic"]').attr("id");
            var introProgress = $(this).find(".progress-bar").attr("id");
            var btnIntroUpload = $(this).find('.btn-danger').attr("id");
            var proUpload = $(this).find('.proUpload').attr("id");

            bindUpload(introPicPic, btnIntroUpload, proUpload, introPic, introProgress);
        });


    }
});

/**
 * 扩展startWith方法
 * @param str
 * @return
 */
String.prototype.startWith=function(str){
    if(str==null||str==""||this.length==0||str.length>this.length)
        return false;
    if(this.substr(0,str.length)==str)
        return true;
    else
        return false;
    return true;
};

/**
 * 扩展contains方法
 * @param item
 * @return
 */
Array.prototype.contains = function(item){
    return RegExp("\\b"+item+"\\b").test(this);
};

/**
 * IE不支持indexOf方法，为IE添加indexOf的方法
 */
Array.prototype.indexOf = function(val){
    var value = this;
    for(var i =0; i < value.length; i++){
        if(value[i] == val) return i;
    }
    return -1;
};

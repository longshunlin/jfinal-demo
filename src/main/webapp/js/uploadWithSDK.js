var isMaxSize = function (file) {
    if (file.size > 1024 * 1024 * 1024) {
        swal("系统限制最大上传文件1024M！", "", "error");
        return true;
    }
    return false;
};

var getFileSuffix = function (fileName) {
    var index1 = fileName.lastIndexOf(".");
    var index2 = fileName.length;
    return fileName.substring(index1 + 1, index2);//后缀名
};

var uploadWithSDK = function (token, putExtra, config, domain) {
    $("#bigFile").unbind("change").bind("change", function () {
        var file = this.files[0];
        var btn = $('#btnUpload');
        // eslint-disable-next-line
        var finishedAttr = [];
        // eslint-disable-next-line
        var compareChunks = [];
        var observable;
        var timestamp = new Date().getTime();
        if (file) {
            $('.upload-cmp').show();
            var key = timestamp + "." + getFileSuffix(file.name);
            if (isMaxSize(file)) {
                return;
            }
            putExtra.params["x:name"] = key.split(".")[0];
            // 设置next,error,complete对应的操作，分别处理相应的进度信息，错误信息，以及完成后的操作
            var error = function (err) {
                btn.text("继续上传");
                console.log(err);
                swal("上传出错！", "", "error");
            };
            //完成时的处理
            var complete = function (res) {
                $('.upload-cmp').hide();
                $('#adUrl').val(domain + "/" + res.key);
            };
            var updateProgress = function (width) {
                $('#upvideoProgress').html( width + '%').css({ width : width + '%'});
            };
            var next = function (response) {
                var chunks = response.chunks || [];
                var total = response.total;
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
            btn.click(function () {
                var status = $(this).attr("data-status");
                if(status == "false") {
                    $(this).text("暂停上传");
                    $(this).attr("data-status" , "true");
                    subscription = observable.subscribe(subObject);
                } else {
                    $(this).text("继续上传");
                    $(this).attr("data-status" , "false");
                    subscription.unsubscribe();
                }
            });
        }
    })
};
/**
 * 获取token，并初始化插件
 */
$.ajax({
    url: "/admin/getQiniuToken", success: function (res) {
        var token = res.token;
        var domain = res.domain;
        var config = {
            useCdnDomain: true,
            disableStatisticsReport: false,
            retryCount: 6,
            region: qiniu.region.z2  //默认为华南区
        };
        var putExtra = {
            fname: "",
            params: {},
            mimeType: null
        };
        uploadWithSDK(token, putExtra, config, domain);
    }
});

$('#upload-file').fileupload({
    formData: {path: path},
//    acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
    maxFileSize: 5000000, // 5 MB
    url: '/admin/handle-upload-file/',
    dataType: 'json',
    done: function (e, resp) {
        if (resp.result.returnCode == 1) {
            $.ambiance({
                type: "success",
                message: "upload success",
                fade: true,
                timeout: 3
            });
            setTimeout(function () {
                window.location.reload();
            }, 1500)
        } else {
            showWarning(resp.result.returnMessage);
        }

    },
    progressall: function (e, data) {

    }
}).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
function deleteFile(path) {
    $.ajax({
        url: "/admin/handle-delete-file/",
        type: 'POST',
        dataType: 'json',
        data: {
            path: path,
        },
        success: function (resp) {
            if (resp.returnCode == 1) {
                $.ambiance({
                    type: "success",
                    message: "delete success",
                    fade: true,
                    timeout: 2
                });
                setTimeout(function () {
                    window.location.reload();
                }, 1500)
            } else {
                showWarning(resp.returnMessage);
            }
        },
    });
}
function createFolder() {
    var foldername = $("#create-folder-name").val();
    if (!foldername) {
        showWarning("input folder name");
        return;
    }
    $.ajax({
        url: "/admin/handle-create-folder/",
        type: 'POST',
        dataType: 'json',
        data: {
            path: path,
            foldername: foldername,
        },
        success: function (resp) {
            if (resp.returnCode == 1) {
                $.ambiance({
                    type: "success",
                    message: "create success",
                    fade: true,
                    timeout: 2
                });
                setTimeout(function () {
                    window.location.reload();
                }, 1500)
            } else {
                showWarning(resp.returnMessage);
            }
        },
    });
}
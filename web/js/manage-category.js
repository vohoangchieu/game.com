$(function() {

    try {
        tinyMCE.init({
            // General options
            mode: "textareas",
            theme: "advanced",
            plugins: "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave,visualblocks",
            // Theme options
            theme_advanced_buttons1: "|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
            theme_advanced_buttons2: "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
            theme_advanced_buttons3: "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
            theme_advanced_buttons4: "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft,visualblocks",
            theme_advanced_toolbar_location: "top",
            theme_advanced_toolbar_align: "left",
            theme_advanced_statusbar_location: "bottom",
            theme_advanced_resizing: true,
            // Example content CSS (should be your site CSS)
            content_css: "css/content.css",
            // Drop lists for link/image/media/template dialogs
            template_external_list_url: "lists/template_list.js",
            external_link_list_url: "lists/link_list.js",
            external_image_list_url: "/ajax/am/get-list-image.js",
            media_external_list_url: "lists/media_list.js",
            // Style formats
            style_formats: [
                {title: 'Bold text', inline: 'b'},
                {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
                {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
                {title: 'Example 1', inline: 'span', classes: 'example1'},
                {title: 'Example 2', inline: 'span', classes: 'example2'},
                {title: 'Table styles'},
                {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
            ],
//            document_base_url: "/static/image/product/",
            convert_urls: false,
            height: 450,
            width: 730
        });

    } catch (e) {
//        console.log(e);
    }

//    $("#price").keydown(allowNumber);
//    $("#warranty").keydown(allowNumber);
//    $("#add-product").click(handleAdd);

})
//function handleAdd() {
//    if (validateInput() == false) {
//        return;
//    }
//    var id = $("#id").val();
//    var category = $("#category").val();
//    var alilas = $("#alilas").val();
//    var name = $("#name").val();
//    var price = $("#price").val();
//    var warranty = $("#warranty").val();
//    var meta_description = $("#meta-description").val();
//    var tag = $("#tag").val();
//    var show_in_home = $("#show_in_home:checked").length;
//    var show_in_slide = $("#show_in_slide:checked").length;
//    var enable = $("#enable:checked").length;
//    var description = tinyMCE.activeEditor.getContent();
//    $.ajax({
//        url: "/ajax/am/add-product",
//        type: 'POST',
//        dataType: 'json',
//        data: {
//            id: id,
//            alilas: alilas,
//            name: name,
//            price: price,
//            warranty: warranty,
//            tag: tag,
//            show_in_home: show_in_home,
//            show_in_slide: show_in_slide,
//            enable: enable,
//            category:category,
////            image:image,
//            description: description,
//            meta_description: meta_description,
//        },
//        beforeSend: function() {
//        },
//        success: function(data) {
////            hideLoading();
////            console.log(data);
//            if (data.code == 0) {
//                var link = '<a style="color:#fff" href="' + data.msg + '" target="_blank">' + $("#name").val() + "</a>";
//                $.ambiance({
//                    type: "success",
//                    message: "Xử lý thành công. Link sản phẩm: " + link,
//                    fade: true,
//                    timeout: 2
//                });
//                setTimeout(function(){window.location=data.msg},2000);
//            } else {
//                showWarning(data.msg);
//            }
//        },
//        error: function(e) {
//            showWarning("Có lỗi xảy ra vui lòng thử lại sau");
//        }
//    });
//}
//function validateInput() {
//    if ($("#name").val().trim() == "") {
//        $("#error").html("Nhập tên sản phẩm");
//        return false;
//    }
//    if ($("#price").val().trim() == '') {
//        $("#error").html("Nhập giá sản phẩm");
//        return false;
//    }
//    if ($("#warranty").val().trim() == '') {
//        $("#error").html("Nhập thời gian bảo hành");
//        return false;
//    }
//    if ($("#category").val().trim() == '') {
//        $("#error").html("Chọn danh mục sản phẩm");
//        return false;
//    }
//    if($("#thumb").attr("src")=="#"){
//        $("#error").html("Chọn hình ảnh sản phẩm");
//        return false;
//    }
//    $("#error").html("");
//    return true;
//}
//function allowNumber(e) {
//    var key = e.charCode || e.keyCode || 0;
//    // allow backspace, tab, delete, enter, arrows, numbers and keypad numbers ONLY
//    // home, end, period, and numpad decimal
//    return (
//            key == 8 ||
//            key == 9 ||
//            key == 13 ||
//            key == 46 ||
////                key == 110 ||
////                key == 190 ||
//            (key >= 35 && key <= 40) ||
//            (key >= 48 && key <= 57) ||
//            (key >= 96 && key <= 105));
//}

$(function() {
    $("#category").change(function() {
        var categoryid = $("#category").val();
        for (var i = 0; i < list_category.length; i++) {
            if (list_category[i]["id"] == categoryid) {
                console.log(list_category[i]);
            }
        }
    })
})
$(function() {
    'use strict';
    var url = '/ajax/am/add-product-image-handler/';
    $('#image-file').fileupload({
        formData: {id: $("#id").val()},
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5000000, // 5 MB
        url: url,
        dataType: 'json',
        done: function(e, data) {
            $.each(data.result.files, function(index, file) {
                if (file.success) {
                    //var link = '<a style="color:#fff" href="' + $("#producturl").val() + '" target="_blank">' + $("#name").val() + "</a>";
                    $("#thumb").attr("src", file.url + "?" + (new Date()).getMilliseconds());
                    $.ambiance({
                        type: "success",
                        message: "Xử lý thành công",
                        fade: true,
                        timeout: 3
                    });
                } else {
                    showWarning("Xử lý thất bại");
                }

            });

        },
        progressall: function(e, data) {
            var width = 700;//$("#progress").width();
            var progress = parseInt(data.loaded / data.total * 100, 10) / 100;
            $('#progress .progress-bar').css(
                    'width',
                    progress * width + 'px'
                    );
        }
    }).prop('disabled', !$.support.fileInput)
            .parent().addClass($.support.fileInput ? undefined : 'disabled');
});
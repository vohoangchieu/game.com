$(function () {

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
            convert_urls: false,
            height: 450,
            width: 730
        });

    } catch (e) {
//        console.log(e);
    }

    $("#order_weight").keydown(allowNumber);
    $("#add-game").click(handleAdd);

})
function handleAdd() {

    var id = $("#id").val();
    var url = $("#url").val();
    var name = $("#name").val();
    var name_vn = $("#name_vn").val();
    var short_desc = $("#short_desc").val();
    var order_weight = $("#order_weight").val();
    var link_youtube = $("#link_youtube").val();
    var is_promote = $("#is_promote:checked").length;
    var is_fearture = $("#is_fearture:checked").length;
    var is_active = $("#is_active:checked").length;
    var category = getCategoryVal();
    var long_desc = tinyMCE.activeEditor.getContent();
    $.ajax({
        url: "/admin/ajax/add-game",
        type: 'POST',
        dataType: 'json',
        data: {
            id: id,
            url: url,
            name: name,
            name_vn: name_vn,
            short_desc: short_desc,
            order_weight: order_weight,
            link_youtube: link_youtube,
            is_promote: is_promote,
            is_fearture: is_fearture,
            is_active: is_active,
            category: category,
            long_desc: long_desc,
        },
        beforeSend: function () {
        },
        success: function (resp) {
            if (resp.returnCode == 1) {
                if (id > 0) {
                    $.ambiance({
                        type: "success",
                        message: "update success",
                        fade: true,
                        timeout: 2
                    });
                } else {
                    $.ambiance({
                        type: "success",
                        message: "insert success",
                        fade: true,
                        timeout: 2
                    });
                    setTimeout(function () {
                        window.location = "/admin/add-game?id=" + resp.data;
                    }, 2000);
                }
            } else {
                showWarning(resp.msg);
            }
        },
        error: function (e) {
            showWarning("error");
        }
    });
}

function allowNumber(e) {
    var key = e.charCode || e.keyCode || 0;
    // allow backspace, tab, delete, enter, arrows, numbers and keypad numbers ONLY
    // home, end, period, and numpad decimal
    return (
            key == 8 ||
            key == 9 ||
            key == 13 ||
            key == 46 ||
//                key == 110 ||
//                key == 190 ||
            (key >= 35 && key <= 40) ||
            (key >= 48 && key <= 57) ||
            (key >= 96 && key <= 105));
}


$(function () {
    'use strict';
    $('#image-file').fileupload({
        formData: {id: $("#id").val()},
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5000000, // 5 MB
        url: '/admin/handle-upload-game-thumb/',
        dataType: 'json',
        done: function (e, data) {
            if (data.result.returnCode == 1) {
                $("#thumb").attr("src", data.result.data + "?" + (new Date()).getMilliseconds());
                $.ambiance({
                    type: "success",
                    message: "upload success",
                    fade: true,
                    timeout: 3
                });
            } else {
                showWarning("upload error");
            }

        },
        progressall: function (e, data) {
            var width = 700;//$("#progress").width();
            var progress = parseInt(data.loaded / data.total * 100, 10) / 100;
            $('#progress .progress-bar').css(
                    'width',
                    progress * width + 'px'
                    );
        }
    }).prop('disabled', !$.support.fileInput)
            .parent().addClass($.support.fileInput ? undefined : 'disabled');
    $('#nes-file').fileupload({
        formData: {id: $("#id").val()},
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 5000000, // 5 MB
        url: '/admin/handle-upload-game-nes/',
        dataType: 'json',
        done: function (e, data) {
            if (data.result.returnCode == 1) {
//                $("#nes").attr("href", data.result.data + "?" + (new Date()).getMilliseconds());
                $.ambiance({
                    type: "success",
                    message: "upload success",
                    fade: true,
                    timeout: 3
                });
            } else {
                showWarning("upload error");
            }

        },
        progressall: function (e, data) {
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

function getCategoryVal() {
    var ret = "";
    $(".category").each(function () {
        if ($(this).is(":checked")) {
            console.log($(this).val());
            ret += $(this).val() + ",";
        }
    })
    return ret;
}
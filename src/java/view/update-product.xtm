<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0105)/may-khoan-pin-bosch/may-khoan-van-vit-dung-pin-gsr-1440-li-professional.html -->
<html xmlns="http://www.w3.org/1999/xhtml"><head>
        <?php
        include $APPLICATION_PATH. '/view/header.php';
        ?>
        <script src="/static/js/jquery-1.7.2.min.js"></script>
        <link rel="stylesheet" href="/static/css/jquery.ambiance.css"/>
        <script src="/static/js/common.js"></script>

        <script src="/static/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
        <script src="/static/js/update-product.js"></script>

    </head>
    <body>
        <script src="/static/js/jquery.ambiance.js"></script>
        <form name="form1" method="post" action="#" id="form1">



            <script src="/static/js/ScriptToolTip.js" type="text/javascript"></script>

            <ul class="uav">
                <?php include $APPLICATION_PATH. '/view/top-banner.php'; ?>
            </ul>
            <ul class="umh">

                <?php
                include $APPLICATION_PATH. '/view/top-nav.php';
                ?>

            </ul>

            <div class="divl">




                <div class="g y">
                    <?php
                    include $APPLICATION_PATH. '/view/support.php';
                    ?>
                </div>


                <div class="g">
                    <div class="dtl">&nbsp;</div>
                </div>




                <!--<div class="v"><a href="/" target="_blank"><img src="./detail_files/100.jpg" alt="hỗ trợ trực tuyến"></a></div>-->
            </div>
            <div class="divr">
                <!--<div class="dlink"><a href="/">Trang chủ</a> &gt; <a href="/san-pham-bosch.html">Sản phẩm Bosch</a> &gt; <a href="/may-khoan-pin-bosch.html">Máy khoan pin Bosch</a></div>-->



                <div class="h2dt">Thông tin sản phẩm</div>

                <div style="width: 100%;display: block;margin: 0 5px 0 5px;">
                    <style>
                        .wrap-input{
                            width: 349px;
                            float: left;
                            display: inline;
                            padding: 5px;
                            border: 1px solid #cfcfcf;
                            margin: 5px;
                        }
                        .label{
                            width: 80px;
                            float: left;
                        }
                        .value{
                            width: 250px;
                            float: right;
                        }

                        input[type=text]{
                            width: 235px;
                            padding: 5px;

                        }
                        input[type=checkbox]{
                            float: right;
                        }
                    </style>
                    <input type="hidden" id="id" value="<?php echo $product_info['id'] ?>"/>
                    <input type="hidden" id="alilas" value="<?php echo $product_info['alilas'] ?>"/>
                    <!--<input type="hidden" id="category" value="<?php echo $product_info['category'] ?>"/>-->
                    <input type="hidden" id="producturl" value="<?php echo "/p/{$product_info['category']}/{$product_info['id']}"?>"/>
                    <div class="wrap-input" style="   width: 720px;">
                        <div class="label" style="padding-top: 6px">Tên sp</div>
                        <div class="value" style="width: 620px"><input type="text" id="name" 
                                                                       value="<?php echo $product_info['name'] ?>" style="width: 605px"
                                                                       /></div>

                    </div>
                     <div class="wrap-input" style="   width: 720px;">
                        <div class="label" style="padding-top: 6px">Tiêu đề website</div>
                        <div class="value" style="width: 620px"><input type="text" id="title" 
                                                                       value="<?php echo $product_info['title'] ?>" style="width: 605px"
                                                                       /></div>

                    </div>
                    <div class="wrap-input" style="   width: 720px;">
                        <div class="label"  style="padding-top: 6px">Mô tả ngắn</div>
                        <div class="value" style="width: 620px"><input type="text" id="meta-description" 
                                                                       value="<?php echo $product_info['meta_description'] ?>" style="width: 605px"
                                                                       /></div>

                    </div>
                    <div class="wrap-input">
                        <div class="label"  style="padding-top: 6px">Giá</div>
                        <div class="value"><input type="text" id="price" 
                                                  value="<?php echo $product_info['price'] ?>"
                                                  /></div>

                    </div>
                    <div class="wrap-input">
                        <div class="label"  style="padding-top: 6px">Bảo hành</div>
                        <div class="value"><input type="text" id="warranty" 
                                                  value="<?php echo $product_info['warranty'] ?>"
                                                  /></div>

                    </div>
                    <div class="wrap-input" style="display: none">
                        <div class="label"  style="padding-top: 6px">Tag</div>
                        <div class="value"><input type="text" id="tag" 
                                                  value="<?php echo $product_info['tag'] ?>"
                                                  /></div>

                    </div>
                    <div class="wrap-input" style="width: 349px;height: 29px">
                                                    <div class="label">Danh mục</div>
                                                    <div class="value">
                                                        <select id="category" style="width: 250px;padding: 5px">
                                                        <?php
                                                        foreach ($list_category as $category) {
                                                            if($product_info['category']==$category['id']){
                                                                $select='selected';
                                                            }else{
                                                                 $select=''; 
                                                            }
                                                            echo "<option value='{$category['id']}' $select>{$category['name']}</option>";
                                                        }
                                                        ?>
                                                         </select>
                                                    </div>
                    </div>
                    <div class="wrap-input">
                        <div class="label" style="width: 300px">Hiển thị ở trang chủ</div>
                        <input type="checkbox" name="vehicle" value="" id="show_in_home"
                        <?php
                        if ($product_info['show_in_home']) {
                            echo "checked='checked'";
                        }
                        ?>
                               ></div>


                    <div class="wrap-input">
                        <div class="label" style="width: 300px">Sản phẩm bán chạy</div>
                        <input type="checkbox" name="vehicle" value="" id="show_in_slide"
                        <?php
                        if ($product_info['show_in_slide']) {
                            echo "checked='checked'";
                        }
                        ?>/></div>
                    <div class="wrap-input">
                        <div class="label" style="width: 300px">Hiện sản phẩm</div>
                        <input type="checkbox" name="vehicle" value="" id="enable"
                        <?php
                        if ($product_info['enable']) {
                            echo "checked='checked'";
                        }
                        ?>/></div>

                </div>


                <div style="clear:both"></div>

                <div style="text-align:justify;line-height:150%; padding:10px;clear:both">



                    <div class="editor" style="display: block;width: 100%;height: 480px">
                        <textarea id="description"><?php echo $product_info['description']; ?></textarea>
                    </div>
                    <div>
                        <!--<div style="padding-top: 10px;width: 400px;float: left;color: red;" id="error">ten sp khong hop le</div>-->
                        <input type="button" id="update-product" class="post" value="Cập nhật sản phẩm" style="padding: 5px;
                               margin: 5px;
                               margin-right: 0;
                               float: right;"/>
                         <input type="button" id="delete-product" class="post" value="Xóa sản phẩm" style="padding: 5px;
                               margin: 5px;
                               margin-right: 0;
                               float: right;color: red"/>
                    </div>
                    <div style="clear:both"></div>
                </div>
                <div class="cl"></div>
                <!--image-->
                <!--<script src="/static/js/jquery-2.1.1.min.js"></script>-->
                <script src="/static/js/fileupload/vendor/jquery.ui.widget.js"></script>
                <script src="/static/js/fileupload/jquery.iframe-transport.js"></script>
                <script src="/static/js/fileupload/jquery.fileupload.js"></script>
                <div style="width: 100%;display: block;margin: 0 5px 0 5px;">
                    <div class="wrap-input">
                        <div class="label" style="width: 300px">Cập nhật hình ảnh</div>
                        <input type="file" name="image" value="" id="image-file"/></div>
<!--                    <input type="button" id="update-image" class="post" value="Cập nhật hình ảnh" 
                           style="padding: 5px 9px;margin: 5px;margin-right: 15px;float: right;"/>-->
                    <div style="clear:both"></div>
                    <div id="progress" class="progress">
        <div class="progress-bar progress-bar-success" style="width: 700px;
height: 5px;
background: green;
margin: auto;"></div>
    </div>
                    <div style="width: 100%;display: block;margin: 10px;margin-left: auto;margin-right: auto">
                        <img id="thumb" src="<?php echo  $product_info['product_image']  ?>" style="display: block;margin-left: auto;margin-right: auto"/>
                    </div>
                </div>

            </div>
            <div class="cl"></div>


            <ul class="ulft">
                <?php
                include $APPLICATION_PATH. '/view/company.php';
                ?>
            </ul>
            <div class="cl"></div>
            <div class="dhead">
                <?php
                include $APPLICATION_PATH. '/view/top-head.php';
                ?>
            </div>

        </form>
        <?php
        include $APPLICATION_PATH. '/view/side-ads.php';
        ?>

    </body></html>
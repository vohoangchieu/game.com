<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0105)/may-khoan-pin-bosch/may-khoan-van-vit-dung-pin-gsr-1440-li-professional.html -->
<html xmlns="http://www.w3.org/1999/xhtml"><head>
        <?php
        include $APPLICATION_PATH . '/view/header.php';
        ?>
        <script src="/static/js/jquery-1.7.2.min.js"></script>
        <link rel="stylesheet" href="/static/css/jquery.ambiance.css"/>
        <script src="/static/js/common.js"></script>

        <script src="/static/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
        <script src="/static/js/manage-category.js"></script>

    </head>
    <body>
        <script src="/static/js/jquery.ambiance.js"></script>
        <form name="form1" method="post" action="#" id="form1">



            <script src="/static/js/ScriptToolTip.js" type="text/javascript"></script>

            <ul class="uav">
                <?php include $APPLICATION_PATH . '/view/top-banner.php'; ?>
            </ul>
            <ul class="umh">

                <?php
                include $APPLICATION_PATH . '/view/top-nav.php';
                ?>

            </ul>

            <div class="divl">




                <div class="g y">
                    <?php
                    include $APPLICATION_PATH . '/view/support.php';
                    ?>
                </div>


                <div class="g">
                    <div class="dtl">&nbsp;</div>
                </div>
            </div>
            <div class="divr">

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
                        input[type=button]{
                            padding: 5px;
                            margin: 0 5px 0 5px;
                            padding: 5px 15px 5px 15px;
                            /*width: 70px;*/
                        }
                        input[type=checkbox]{
                            float: right;
                        }
                    </style>

                    <div class="wrap-input">
                        <div class="label">Danh mục</div>
                        <div class="value"><input type="text" id="price" 
                                                  value=""
                                                  /></div>

                    </div>
                    <div class="wrap-input" >
                       <input type="button"  value="Them" style="width: 340px"/>

                    </div>

                    <div style="clear:both;height: 10px"></div>
                   <div class="wrap-input" style="width: 349px;height: 29px">
                                                    <div class="label">Danh mục</div>
                                                    <div class="value"><select id="category" style="width: 250px;padding: 5px">
                                                        <option value='' ></option>
                                                        <?php
                                                        foreach ($list_category as $category) {
                                                            echo "<option value='{$category['id']}' >{$category['name']}</option>";
                                                        }
                                                        ?>
                                                         <!--<option value='1' >1</option>-->
                                                         </select>
                                                    </div>
                    </div>
                    <div class="wrap-input" >
                       <input type="button"  value="Them" style="width: 163px"/>
                       <input type="button"  value="Them" style="width: 163px"/>
                    </div>
                    <div class="wrap-input">
                        <div class="label" style="padding-top: 6px">Tên sp</div>
                        <div class="value" ><input type="text" id="update_name" /></div>

                    </div>
                    <div style="clear:both;"></div>
                    <div style="text-align:justify;line-height:150%; padding:5px;clear:both">



                    <div class="editor" style="display: block;width: 100%;height: 480px">
                        <textarea id="update_description"></textarea>
                    </div>
                    <div style="clear:both"></div>
                </div>
                </div>


                <div style="clear:both"></div>


                <div class="cl"></div>
                <!--image-->
                <!--<script src="/static/js/jquery-2.1.1.min.js"></script>-->
                <script src="/static/js/fileupload/vendor/jquery.ui.widget.js"></script>
                <script src="/static/js/fileupload/jquery.iframe-transport.js"></script>
                <script src="/static/js/fileupload/jquery.fileupload.js"></script>


            </div>
            <div class="cl"></div>


            <ul class="ulft">
                <?php
                include $APPLICATION_PATH . '/view/company.php';
                ?>
            </ul>
            <div class="cl"></div>
            <div class="dhead">
                <?php
                include $APPLICATION_PATH . '/view/top-head.php';
                ?>
            </div>

        </form>
        <?php
        include $APPLICATION_PATH . '/view/side-ads.php';
        ?>
        <script>
        var list_category=<?php 
        echo json_encode($list_category);
        ?>;
        </script>
    </body></html>
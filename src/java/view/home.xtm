<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0029)/ -->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <?php
        include $APPLICATION_PATH. '/view/header.php';
        ?>
    </head>
    <body>
        <form name="form1" method="post" action="#" id="form1">




            <script src="/static/js/ScriptToolTip.js" type="text/javascript"></script>
            <h1 class="t">Dụng cụ cầm tay Bosch chính hãng - Máy khoan Bosch</h1>
            <ul class="uav">
                <?php
                include $APPLICATION_PATH. '/view/top-banner.php';
                ?>
            </ul>
            <ul class="umh">

                <?php
                include $APPLICATION_PATH. '/view/top-nav.php';
                ?>

            </ul>


            <script language="javascript" type="text/javascript" src="/static/js/fader.js"></script>

            <div class="dmian">


                <script type="text/javascript" src="/static/js/jquery_008.js"></script>
                <script type="text/javascript" src="/static/js/jquery_003.js"></script>
                <ul class="m"><li class="l"><h3>Sản phẩm <strong>bán chạy</strong></h3></li></ul>
                <div class="divslp">
                    <div class="l"><a href="javascript:void(0);" id="slide-adv-back"></a></div>
                    <div class="skinimg">
                        <ul id="ul-box-adv">

                            <?php
                            foreach ($list_home_slide_product as $product) {
                                ?>
                                <li>
                                    <a class="m" href="<?php echo $product["product_url"] ?>" onmouseover="mtln('<div class =\'bdtl\'><img src =\'<?php echo $product["product_image"] ?>\'></div>', 360)" onmouseout="mtlh()">
                                        <img src="<?php echo $product["product_thumb"] ?>" alt="<?php echo $product["name"] ?>"></a>
                                    <strong><a href="<?php echo $product["product_url"] ?>"><?php echo $product["name"] ?></a></strong>
                                    <div>Giá bán: <b><?php echo ($product["price"]) ?></b></div>
                                </li>
                                <?php
                            }
                            ?>
                        </ul>
                    </div>
                    <div class="r"><a href="javascript:void(0);" id="slide-adv-next"></a></div>
                    <script type="text/javascript" src="/static/js/jquery_f.js"></script>
                </div>


                <div class="av9">
                    <a href="#" target="_blank">
                        <img src="/static/image/website/tuvancuakeo.jpg" />
                            <img src="/static/image/website/tuvancuakeo2.jpg" />
                    </a></div>
                <ul class="m">
                    <li class="l"><h3><a href="#">Danh mục</a></h3></li>
                    <li class="r">
                          <?php
                        $i = 0;
                        foreach ($list_category as $category) {
                            if ($category['type'] == "0") {
                                echo "<b>|</b><a href=\"{$category['url']}\">{$category['name']}</a>";
                                if($i++>5){
                                    break;
                                }
                            }
                        }
                        ?>
                    </li>
                </ul>
                <ul class="p">


                    <?php
                    foreach ($list_home_product_bosch as $product) {
                        ?>
                        <li>
                            <a class="m" href="<?php echo $product["product_url"] ?>" onmouseover="mtln('<div class = \'bdtl\'><img src =\'<?php echo $product["product_thumb"] ?>\'> </div>', 360)" onmouseout="mtlh()">
                                <img src="<?php echo $product["product_thumb"] ?>" alt="<?php echo $product["category"] . "-" . $product["name"]; ?>"></a>
                            <strong><a href="<?php echo $product["product_url"] ?>"><?php echo $product["name"] ?></a></strong>
                            <span><?php echo $product["short_desc"] ?></span>
                            <div>Giá bán: <b><?php echo ($product["price"]) ?></b></div>
                        </li>
                        <?php
                    }
                    ?>

                </ul>
                <div class="cl"></div>

           

            </div>

            <ul class="ulnf">

                <?php
                include $APPLICATION_PATH. '/view/news.php';
                ?>

            </ul>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0105)/may-khoan-pin-bosch/may-khoan-van-vit-dung-pin-gsr-1440-li-professional.html -->
<html xmlns="http://www.w3.org/1999/xhtml"><head>
        <?php
        include $APPLICATION_PATH. '/view/header.php';
        ?>
    </head>
    <body>
        <script type="text/javascript" 
        src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

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

                <div class="m">
                    <?php
                    include $APPLICATION_PATH. '/view/left-nav.php';
                    ?>
                </div>


                <div class="g y">
                    <?php
                    include $APPLICATION_PATH. '/view/support.php';
                    ?>
                </div>

      <div class="g y">
       <?php
           include $APPLICATION_PATH. '/view/price-catalog.php';
        ?>
    </div>
<!--                <div class="g">
                    <div class="dtl">&nbsp;</div>
                </div>-->


                <div class="g p">
                    <?php
                    include $APPLICATION_PATH. '/view/best-sell-left.php';
                    ?>
                </div>

             </div>
            <div class="divr">
                <div class="dlink"><a href="/">Trang chủ</a> &gt; <a href="/ban-do-cua-hang-khoan-bosch">Bản đồ đường đến cửa hàng</a></div>


                <div id="map_container" style="height: 600px"></div>
                <script>
                    document.onready = function() {
                        var iconshop = new google.maps.MarkerImage("/static/image/website/shop.jpg",
                                        new google.maps.Size(250, 90),
                                        new google.maps.Point(0, 0),
                                        new google.maps.Point(0, 0));
                        var infoWindow = new google.maps.InfoWindow({
                            content: "<img src='/static/image/website/shop.jpg'>"
                        });
                        //                    $("#map_container").height($(window).width()-100);
                        var address = '<?php echo $address ?>';
                        var geocoder, map;
                        geocoder = new google.maps.Geocoder();
                        geocoder.geocode({'address': address}, function(results, status) {
                            if (status == google.maps.GeocoderStatus.OK) {
                                
                                var myOptions = {
                                    zoom: 16,
                                    center: results[0].geometry.location,
                                    mapTypeId: google.maps.MapTypeId.ROADMAP
                                }
                                map = new google.maps.Map(document.getElementById("map_container"), myOptions);
                                var marker = new google.maps.Marker({
                                    map: map,
                                    position: results[0].geometry.location,
                                    title: address,
//                                    icon: iconshop
                                });
                                //infoWindow.open(marker.get('map'), marker);
//                                google.maps.event.addListener(marker, 'click', function() {
//                                    infoWindow.open(marker.get('map'), marker);
//                                })
                            }
                        });

                    }
                </script>

                <div class="cl"></div>

            </div>
            <div class="cl"></div>

            <ul class="ulnf">

                <?php
                include $APPLICATION_PATH. '/view/news.php';
                ?>

            </ul>
            <div class="cl"></div>
<!--            <div id="dswf">
                <?php
                include $APPLICATION_PATH. '/view/recent-view.php';
                ?>
            </div>-->
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
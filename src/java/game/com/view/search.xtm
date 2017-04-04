<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0049)/may-khoan-bosch.html -->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <?php
    include $APPLICATION_PATH. '/view/header.php';
    ?>
    </head>
<body>
<form name="form1" method="post" action="#" id="form1">


<script src="/static/js/ScriptToolTip.js" type="text/javascript"></script>

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

<div class="divl">
<!--    
    <div class="m">
          
        <?php
//            include $APPLICATION_PATH. '/view/left-nav.php';
        ?>
    </div>-->
    
    
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
    
<!--    <div class="g">
        <div class="dtl"><br></div>
    </div>-->
    
    
    <div class="g p">
       <?php
               include $APPLICATION_PATH. '/view/best-sell-left.php';
       ?>
    </div>
    
<!--    <div class="v">
        <a href="/" target="_blank"><img src="./category_files/100.jpg" alt="hỗ trợ trực tuyến">
        </a>
    </div>-->
</div>
<div class="divr">
    <div class="dlink"><a href="/">Trang chủ</a> &gt; <a href="/search?keyword=<?php echo $_REQUEST['keyword']?>">Kết quả tìm kiếm cho từ khóa '<?php echo htmlspecialchars ( $_REQUEST['keyword'])?>'</a></div>
    
    <!--<h1 class="htl">May Khoan Bosch, Máy Khoan Bê Tông Cầm Tay Giá Rẻ tại Hà Nội</h1>-->


    
    <ul id="upro" class="upro">

<?php
       foreach ($list_search_product as $product) {
    

?>
   <li>
            <a class="m" href="<?php echo $product['product_url']?>" 
               onmouseover="mtln('<div class=\'bdtl\'><img src=\'<?php echo $product['product_image']?>\'></div>',360)" onmouseout="mtlh()">
                <img src="<?php echo $product['product_thumb']?>" alt="<?php echo $product['name']?>"></a>
            <strong><a href="<?php echo $product['product_url']?>"><?php echo $product['name']?></a></strong>
            <span><?php echo $product['short_desc']?></span>
            <div>Giá bán: <b><?php echo ($product['price'])?> </b></div>
        </li>
        <?php
        }
        ?>
    </ul>


</div>
<div class="cl"></div>

<ul class="ulnf">
    
    <?php
           include $APPLICATION_PATH. '/view/news.php';
    ?>
    
</ul>
<div class="cl"></div>
<!--<div id="dswf">
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
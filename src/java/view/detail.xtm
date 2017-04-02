<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <?php
    include $APPLICATION_PATH. '/view/header.php';
    ?>
        <meta property="og:image" content="<?php echo $HOME.$product_info['product_thumb']?>"/>
    </head>
<body>
<form name="form1" method="post" action="#" id="form1">



<script src="/static/js/ScriptToolTip.js" type="text/javascript"></script>

<ul class="uav">
    <?php        include $APPLICATION_PATH. '/view/top-banner.php';?>
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

    
    
    <div class="g p">
     <?php
             include $APPLICATION_PATH. '/view/best-sell-left.php';
     ?>
    </div>
    
</div>
<div class="divr">
    <div class="dlink"><a href="/">Trang chủ</a>  &gt; <a href="/<?php echo $category_info['alilas']?>"><?php echo $category_info['name']?></a></div>

    <div class="pdl">
        <a class="a" href="<?php echo $product_info['product_image']?>" rel="lightbox[roundtrip]" title="<?php echo $product_info['name']?>">
            <img id="thumb" src="<?php echo $product_info['product_thumb']?>" alt="<?php echo $product_info['name']?>"></a>     
    </div>
    <div class="pdr">
        <h1><?php echo $product_info['name']?></h1>
        <div class="rr">
            <strong><a href="/<?php echo $ggmap_link?>" target="_blank">Sơ đồ chỉ đường</a></strong>
            <span><a href="/<?php echo $ggmap_link?>" target="_blank"></a></span>
        </div>
                <div style="padding-top:10px;"><b>Giá bán:</b> <span style="color:Red;font-weight:bold;"><?php echo ($product_info['price'])?></span></div>
        
        <div style="padding-top:10px;<?php if ($product_info['warranty']==0) {echo "display:none";}?>"><b>Bảo hành:</b> <?php echo ($product_info['warranty'])?> tháng</div>
        
        <div style="padding-top:10px;padding-bottom:11px;"><b>Phone:</b> <span style="color:Red;font-weight:bold;"><?php echo $mobile_phone?></span></div>
        <div class="dshl">
            <div style="padding-top:10px;">
                <a rel="nofollow" href="http://www.facebook.com/share.php?u=<?php echo $HOME. $product_info['product_url']?>&title=<?php echo $product_info['name']?>&picture=<?php echo "{$HOME}{$product['product_image']}"?>" target="socialbookmark"><img title="<?php echo $product_info['name']?>" src="/static/image/website/facebook.gif" alt="<?php echo $product_info['name']?>"></a>
            </div>
        </div>
        <div class="cl"></div>
        <div class="km">&nbsp;</div>
    </div>
    <div class="cl"></div>
    <div class="h2dt">Thông tin sản phẩm</div>
    <div style="text-align:justify;line-height:150%; padding:10px;">
       <?php echo $product_info['description']?>
    </div>
    <div class="cl"></div>
    <div><fb:comments href="<?php echo $product_info['product_url']?>" num_posts="8" colorscheme="light" width="754"></fb:comments></div>
    <div id="dpage"><span id="pd_ctl00_lpg"><b>Trang:</b> <a id="a1" href="javascript:delt('#ispro','324','041800','1')">1</a><a id="a2" href="javascript:delt('#ispro','324','041800','2')">2</a><a id="a3" href="javascript:delt('#ispro','324','041800','3')">3</a></span></div>
    <div class="h2dt">Sản phẩm cùng loại <a style="color: red" href="/c/<?php echo $category_info['id']?>"><?php echo $category_info['name']?></a></div>
    <div class="cl"></div>

    
    <div id="ispro">
<ul class="upro">
    
   <?php
       foreach ($list_category_product as $product) {
    

?>
   <li>
            <a class="m" href="<?php echo $product['product_url']?>" 
               onmouseover="mtln('<div class=\'bdtl\'><img src=\'<?php echo $product['product_image']?>\'></div>',360)" onmouseout="mtlh()">
                <img src="<?php echo $product['product_thumb']?>" alt="<?php echo $product['name']?>"></a>
            <strong><a href="<?php echo $product['product_url']?>"><?php echo $product['name']?></a></strong>
            <span><?php echo $product['short_desc']?></span>
            <div>Giá bán: <b><?php echo $product["price"]?> </b></div>
        </li>
        <?php
        }
        ?>
    
</ul>
        <div class="cl"></div>
</div>

    
    <div class="cl"></div>
    
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
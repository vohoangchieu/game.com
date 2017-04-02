

<li class="g n">
    <a class="a1" href="/" title="<?php echo $title ?>">Trang Chủ</a>
    <div></div>
</li>
<?php
$count_category = count($list_category);
for ($i = 0; $i < 5; $i++) {


    $category = $list_category[$i];
    echo "<li class=\"g n\">
                <a class=\"a1\" href=\"{$category['url']}\" title=\"{$category['name']}\">{$category['name']}</a>
         </li>";
}

?>
<li class="g n"><!--class="g s"-->
    <a class="a1" href="/bang-gia-cua-cuon-dai-loan-cua-keo-cong-nghe-dai-loan" title="BẢNG GIÁ CỬA CUỐN ĐÀI LOAN, CỬA KÉO CÔNG NGHỆ ĐÀI LOAN">Bảng giá</a>
</li>

<li class="g n"><!--class="g s"-->
    <a class="a1" href="#" title="Danh mục khác">Danh Mục Khác</a>
    <div>
        <?php
        for ($i = 5; $i < $count_category; $i++) {
            $category = $list_category[$i];
             echo "<a class=\"a2\" href=\"{$category['url']}\">{$category['name']}</a>";
        }
        ?>

    </div>
</li>



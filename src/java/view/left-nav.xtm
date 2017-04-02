 <strong class="t">Danh mục</strong>
<?php

//    echo "<h3><a href=\"#\">Danh mục</a></h3>";
    foreach ($list_category as $category) {
        if (isset($category_id) && ($category_id == $category['id'])) {
            echo "<div class=\"f s\"><a href=\"{$category['url']}\">{$category['name']}</a></div>";
        } else {
            echo "<div class=\"f\"><a href=\"{$category['url']}\">{$category['name']}</a></div>";
        }
    }
?>

var zone_id =2;

var time_delay=5;
if(zone_id==2){
time_delay=5;
}
jQuery(document).ready(function() {
jQuery("#ul-box-adv").jcarousel({
auto: time_delay, //thoi gian auto
scroll: 4,  //so anh cuon
buttonNextHTML: null,
buttonPrevHTML: null,
wrap: 'circular',
initCallback: mycarousel_initCallback
});
});
function mycarousel_initCallback(carousel) {

jQuery('#slide-adv-next').bind('click', function() {
carousel.next();
return false;
});

jQuery('#slide-adv-back').bind('click', function() {
carousel.prev();
return false;
});
}
;

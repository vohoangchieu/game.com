window.onresize = ShowAdDiv;
var topY=32;
function FloatTopDiv()
{

    var myWidth = 0, myHeight = 0;
    if (typeof (window.innerWidth) == 'number') {
        //Non-IE
        myWidth = window.innerWidth;
        myHeight = window.innerHeight;

        startLX = (myWidth / 2) - 642, startLY = topY;//150;
        startRX = (myWidth / 2) + 500, startRY = topY;//150;

//    } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
//        //IE 6+ in 'standards compliant mode'
//        myWidth = document.documentElement.clientWidth;
//        myHeight = document.documentElement.clientHeight;
//
//        startLX = (myWidth / 2) - 632, startLY = 250;
//        startRX = (myWidth / 2) + 507, startRY = 250;
//    } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
//        //IE 4 compatible
//        myWidth = document.body.clientWidth;
//        myHeight = document.body.clientHeight;
//
//        startLX = (myWidth / 2) - 632, startLY = 150;
//        startRX = (myWidth / 2) + 507, startRY = 150;
    }
    var d = document;
    function ml(id)
    {
        var el = d.getElementById ? d.getElementById(id) : d.all ? d.all[id] : d.layers[id];
        el.sP = function(x, y) {
            this.style.left = x + 'px';
            this.style.top = topY+ 'px';//y + 'px';
        };
        el.x = startRX;
        el.y = startRY;

        return el;
    }
    function m2(id)
    {
        var e2 = d.getElementById ? d.getElementById(id) : d.all ? d.all[id] : d.layers[id];
        e2.sP = function(x, y) {
            this.style.left = x + 'px';
            ;
            this.style.top = y + 'px';
        };
        e2.x = startLX;
        e2.y = startLY;
        return e2;
    }
    window.stayTopLeft = function()
    {


        var scrOfX = 0, scrOfY = 0;
        if (typeof (window.pageYOffset) == 'number') {
            //Netscape compliant
            scrOfY = window.pageYOffset;
            scrOfX = window.pageXOffset;
        } else if (document.body && (document.body.scrollLeft || document.body.scrollTop)) {
            //DOM compliant
            scrOfY = document.body.scrollTop;
            scrOfX = document.body.scrollLeft;
        } else if (document.documentElement && (document.documentElement.scrollLeft || document.documentElement.scrollTop)) {
            //IE6 standards compliant mode
            scrOfY = document.documentElement.scrollTop;
        }
        pY = scrOfY;
        //   alert(scrOfY);

        startLY = (myHeight / 2) - topY;// 156;
        startRY = (myHeight / 2) - topY;//156;

        ftlObj.y += (pY + startRY - ftlObj.y) / 16;
        ftlObj.sP(ftlObj.x, ftlObj.y);
        //ftlObj2.y += (pY + startLY - ftlObj2.y) / 16;
        //ftlObj2.sP(ftlObj2.x, ftlObj2.y);
        setTimeout("stayTopLeft()", 200);

    }

    ftlObj = ml("divAdRight");

    //stayTopLeft();
    //ftlObj2 = m2("divAdLeft");
    stayTopLeft();
}

function ShowAdDiv()
{
    var objAdDivRight = document.getElementById("divAdRight");


    // var objAdDivLeft = document.getElementById("divAdLeft");       
    objAdDivRight.style.display = "block";
    // objAdDivLeft.style.display = "block";
    FloatTopDiv();

}
try {
    ShowAdDiv();
} catch (e) {
    console.log(e);
}
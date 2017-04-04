var is_ie/*@cc_on = {
  // quirksmode : (document.compatMode=="BackCompat"),
  version : parseFloat(navigator.appVersion.match(/MSIE (.+?);/)[1])
}@*/;

function opacity(id, opacStart, opacEnd, millisec) {
	//speed for each frame
	var speed = Math.round(millisec / 100);
	var timer = 0;
	//determine the direction for the blending, if start and end are the same nothing happens
	if(opacStart > opacEnd) {
		for(i = opacStart; i >= opacEnd; i--) {
			setTimeout("changeOpac(" + i + ",'" + id + "')",(timer * speed));
			timer++;
		}
	} else if(opacStart < opacEnd) {
		for(i = opacStart; i <= opacEnd; i++)
			{
			setTimeout("changeOpac(" + i + ",'" + id + "')",(timer * speed));
			timer++;
		}
	}
}

//change the opacity for different browsers
function changeOpac(opacity, id) {
	var object = document.getElementById(id).style; 
	object.opacity = (opacity / 100);
	object.MozOpacity = (opacity / 100);
	object.KhtmlOpacity = (opacity / 100);
	object.filter = "alpha(opacity=" + opacity + ")";
}

function blendimage(divid, imageid, imagefile, millisec) {
	var speed = Math.round(millisec / 100);
	var timer = 0;
	
	//set the current image as background
	document.getElementById(divid).style.backgroundImage = "url(" + document.getElementById(imageid).src + ")";
	
	//make image transparent
	changeOpac(0, imageid);
	
	//make new image
	document.getElementById(imageid).src = imagefile;

	//fade in image
	for(i = 0; i <= 100; i++) {
		setTimeout("changeOpac(" + i + ",'" + imageid + "')",(timer * speed));
		timer++;
	}
}

function currentOpac(id, opacEnd, millisec) {
	//standard opacity is 100
	var currentOpac = 100;
	
	//if the element has an opacity set, get it
	if(document.getElementById(id).style.opacity < 100) {
		currentOpac = document.getElementById(id).style.opacity * 100;
	}

	//call for the function that changes the opacity
	opacity(id, currentOpac, opacEnd, millisec)
}


function picture1(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture1_done()',7000);
}

function picture1_done(){
	picture1_doneload('picture1');
}

function picture1_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture1_next()',750);
}

function picture1_next(id) {
        document.getElementById('picture1').style.display = "none";
	if (document.getElementById('picture2')) {
		picture2('picture2');
	}
	else {
		picture1('picture1');
	}
}


function picture2(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture2_done()',7000);
}

function picture2_done(){
	picture2_doneload('picture2');
}

function picture2_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture2_next()',750);
}

function picture2_next(id) {
        document.getElementById('picture2').style.display = "none";
	if (document.getElementById('picture3')) {
		picture3('picture3');
	}
	else {
		picture1('picture1');
	}
}


function picture3(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture3_done()',7000);
}

function picture3_done(){
	picture3_doneload('picture3');
}

function picture3_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture3_next()',750);
}

function picture3_next(id) {
        document.getElementById('picture3').style.display = "none";
	if (document.getElementById('picture4')) {
		picture4('picture4');
	}
	else {
		picture1('picture1');
	}
}


function picture4(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture4_done()',7000);
}

function picture4_done(){
	picture4_doneload('picture4');
}

function picture4_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture4_next()',750);
}

function picture4_next(id) {
        document.getElementById('picture4').style.display = "none";
	if (document.getElementById('picture5')) {
		picture5('picture5');
	}
	else {
		picture1('picture1');
	}
}


function picture5(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture5_done()',7000);
}

function picture5_done(){
	picture5_doneload('picture5');
}

function picture5_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture5_next()',750);
}

function picture5_next(id) {
        document.getElementById('picture5').style.display = "none";
	if (document.getElementById('picture6')) {
		picture6('picture6');
	}
	else {
		picture1('picture1');
	}
}


function picture6(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture6_done()',7000);
}

function picture6_done(){
	picture6_doneload('picture6');
}

function picture6_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture6_next()',750);
}

function picture6_next(id) {
        document.getElementById('picture6').style.display = "none";
	if (document.getElementById('picture7')) {
		picture7('picture7');
	}
	else {
		picture1('picture1');
	}
}


function picture7(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture7_done()',7000);
}

function picture7_done(){
	picture7_doneload('picture7');
}

function picture7_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture7_next()',750);
}

function picture7_next(id) {
        document.getElementById('picture7').style.display = "none";
	if (document.getElementById('picture8')) {
		picture8('picture8');
	}
	else {
		picture1('picture1');
	}
}


function picture8(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture8_done()',7000);
}

function picture8_done(){
	picture8_doneload('picture8');
}

function picture8_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture8_next()',750);
}

function picture8_next(id) {
        document.getElementById('picture8').style.display = "none";
	if (document.getElementById('picture9')) {
		picture9('picture9');
	}
	else {
		picture1('picture1');
	}
}


function picture9(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture9_done()',7000);
}

function picture9_done(){
	picture9_doneload('picture9');
}

function picture9_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture9_next()',750);
}

function picture9_next(id) {
        document.getElementById('picture9').style.display = "none";
	if (document.getElementById('picture10')) {
		picture10('picture10');
	}
	else {
		picture1('picture1');
	}
}


function picture10(id) {
        document.getElementById(id).style.display = "block";
	opacity(id, 0, 100, 750);
        window.setTimeout('picture10_done()',7000);
}

function picture10_done(){
	picture10_doneload('picture10');
}

function picture10_doneload(id) {
	opacity(id, 100, 0, 750);
        window.setTimeout('picture10_next()',750);
}

function picture10_next(id) {
    document.getElementById('picture10').style.display = "none";
	picture1('picture1');
}

picture1('picture1');

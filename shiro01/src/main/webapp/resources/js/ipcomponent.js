/**
 * 
 * @param title 弹出层标题
 * @param ipAddress ip地址
 * @param ipAuto 范围
 * @param ipType 类型
 * @return 数组
 * @author 狄江
 */
//全局变量
var i=0;
var vad = true;
function validIp(fn){
	/*****IP地址****/ 
	var exp1=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	/*****IP掩码****/
	var exp2=/^(254|252|248|240|224|192|128|0)\.0\.0\.0$|^(255\.(254|252|248|240|224|192|128|0)\.0\.0)$|^(255\.255\.(254|252|248|240|224|192|128|0)\.0)$|^(255\.255\.255\.(254|252|248|240|224|192|128|0))$/;
	/*****网关******/
	var exp3=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/ 
	/*****正整数****/
	var exp4=/^[0-9]*[1-9][0-9]*$/;
}
//将IP地址添加到DIV层
function appendIps(){ 
 		var ccc = $("#ccc").val();
 	    var ipTypes = $("#ipTypes").val();
 	    var ipTypesText=$("#ipTypes").find("option:selected").text();
 	    var ipText1 = $("#ipText1").val();
 	    //判断是否存在将要添加的IP
 	    if(ccc==""){
 	    	$.mePrompt("请输入您要填写的IP地址");
 	       return;
 	    }	
 	    if(vad==false){
 	    	//$.mePrompt("请填写符合规则的IP地址");
 	    	return;
 	    }
 	    //获取当前弹出层页面中已经添加的IP
 	    var allowIps = document.getElementById("allowIps");//获取父页面对象
 	    var param = $("span[name='isp']");
 	    var param_ips = "";
 	    for(var k=0;k<param.length;k++){
 	       var spanid = param[k].id;
 	       if(param_ips==""){
 	          param_ips = $("#"+param[k].id).text();
 	       }else{
 	          param_ips = param_ips+","+$("#"+param[k].id).text();
 	       }
 	    }
 	    //判断IP类型,根据不同的IP类型，添加不同的IP
 	    if(ipTypes=="2"){
 	        var bolen = isAllowsAdd(ccc,param_ips);
 	        if(bolen==true){
 	        	i++;
 	    	    $("#Ips").append("" +
 	    	    		"<div id=Ips"+i+" style='position:relative; height:20px; background-color:#CCCCCC;'>&nbsp;&nbsp;&nbsp;" +
 	    	    		"<span name=isp id=span"+i+" style='border:0;width:120px;height:15px;bottom:0;left:5%;display:block;position:absolute;margin-left:10px;'>"+ccc+"</span>&nbsp;&nbsp;&nbsp;&nbsp;" +
 	    	    		"<span><a id=a"+i+" href='#' onClick=upDivIp(this)>" +
 	    	    		"<img src='../images/01.png' style='border:0;width:10px;height:15px;bottom:0;left:50%;display:block;position:absolute;margin-left:80px;'></a>&nbsp;&nbsp;" +
 	    	    		"<a id=a"+i+" href='#' onclick=downDivIp(this)>" +
 	    	    		"<img src='../images/02.png' style='border:0;width:10px;height:15px;bottom:0;left:50%;display:block;position:absolute;margin-left:100px;margin-bottom:-3px;'></a>&nbsp;&nbsp;" +
 	    	    		"<a id=a"+i+" href='#' onclick=remove1(this)>" +
 	    	    		"<img src='../images/03.png' style='border:0;width:10px;height:15px;bottom:0;left:50%;display:block;position:absolute;margin-left:120px;'></a></span></div>");

 	        }
 	    }else{
 	         if($("#ipText1").val()==""){
 	        	$.mePrompt("请填写范围");
 	             return;
 	         }
 	         if(ccc!=""&&ipText1!=""){
 	            var ccc_ = ccc.split("."); 
 	            if(ipTypes=="0"){
 	            	 if(parseInt(ccc_[ccc_.length-1])>parseInt(ipText1)){
 	            		$.mePrompt("请正确填写范围！");
 		            	return;
 	           		 }
 	            }
 	         }	 
 	         var bolen = isAllowsAdd((ccc+ipTypesText+ipText1),param_ips);
 	         if(bolen==true){
 		         i++;
 		        $("#Ips").append("" +
 	    	    		"<div id=Ips"+i+" style='position:relative; height:20px; background-color:#CCCCCC;'>&nbsp;&nbsp;&nbsp;" +
 	    	    		"<span name=isp id=span"+i+" style='border:0;width:120px;height:15px;bottom:0;left:5%;display:block;position:absolute;margin-left:10px;'>"+ccc+ipTypesText+ipText1+"</span>&nbsp;&nbsp;&nbsp;&nbsp;" +
 	    	    		"<span><a id=a"+i+" href='#' onClick=upDivIp(this)>" +
 	    	    		"<img src='../images/01.png' style='border:0;width:10px;height:15px;bottom:0;left:50%;display:block;position:absolute;margin-left:80px;'></a>&nbsp;&nbsp;" +
 	    	    		"<a id=a"+i+" href='#' onclick=downDivIp(this)>" +
 	    	    		"<img src='../images/02.png' style='border:0;width:10px;height:15px;bottom:0;left:50%;display:block;position:absolute;margin-left:100px;margin-bottom:-3px;'></a>&nbsp;&nbsp;" +
 	    	    		"<a id=a"+i+" href='#' onclick=remove1(this)>" +
 	    	    		"<img src='../images/03.png' style='border:0;width:10px;height:15px;bottom:0;left:50%;display:block;position:absolute;margin-left:120px;'></a></span></div>");

 		    	// $("#Ips").append("<div id=Ips"+i+"><span name=isp id=span"+i+">"+ccc+ipTypesText+ipText1+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><a id=a"+i+" href='#' onclick=remove1(this)>删除</a>&nbsp;&nbsp;<a id=a"+i+" href='#' onClick=upDivIp(this)>上移</a>&nbsp;&nbsp;<a id=a"+i+" href='#' onclick=downDivIp(this)>下移</a></span></div>");
 	    	 }
 	    }
 }

function isAllowsAdd(thisIp,divIp){
	   //循环当前弹出层中已添加的IP,与将要添加的IP进行比对，查看是否重复
	    var ipss = divIp.split(",");
	    if(ipss.length>0&&ipss!=""){
	    	for(var s=0;s<ipss.length;s++){
	    		if(ipss[s]==thisIp){
	    			$.mePrompt("该值已存在");
	    		   return;
	    		}
	    	}
	    }
	    return true;
}
//判断所填写的IP地址的类型
function ipType(){
   $("#ipText1").val("");
   var iptype = $("#ipTypes").val();
   if(iptype=="2"){
   		document.getElementById("ipText1").disabled=true;
   }else{
	    document.getElementById("ipText1").disabled=false;
   }
}
//删除IP地址
function remove1(obj){
     var id = document.getElementById(obj.id);//获取当前标签的id
     var my = document.getElementById(id.parentNode.parentNode.id);//获取当前标签所在行的id
     var ip = document.getElementById("Ips");//获取当前行的父级对象
     ip.removeChild(my); //移除父级对象下的div标签
	 i--;
	// $("#button5").attr("disabled",false);
}
//获取标签位置以及个数
function ConcelBorder(obj){
 $(obj).parent().parent().parent().find(".li3").each(function() {
    $(this).css("border","0px");
 });
}
//上移
function upDivIp(obj){
	 var onthis = $(obj).parent().parent();
	 var getup = $(obj).parent().parent().prev();
	 if(getup.html()!=null && getup.find("span").length==2){
	  ConcelBorder(obj); 
	  $(getup).before(onthis);
	  $(onthis).css("border","solid 1px #acf");
	  $(getup).css("border","0px");
	 }
}
//下移
function downDivIp(obj){
	  var onthis = $(obj).parent().parent();
	  var getdown = $(obj).parent().parent().next();
	  if(getdown.html()!=null&&getdown.find("span").length==2){
	  ConcelBorder(obj); 
	  $(getdown).after(onthis);
	  $(onthis).css("border","solid 1px #acf");
	  $(getdown).css("border","0px");
     }
}
//允许访问范围---将子页面的IP添加到父页面
function assIpsToParent(){ 
	var allowIps = document.getElementById("allowIps");//获取父页面对象
    var param = $("span[name='isp']");
    var param_ips = "";
    for(var k=0;k<param.length;k++){
       var spanid = param[k].id;
       if(param_ips==""){
          param_ips = $("#"+param[k].id).text();
       }else{
          param_ips = param_ips+","+$("#"+param[k].id).text();
       }
    }
    $("#allowIps").val(param_ips);
    clearIps();
}
//清空div弹出的IP
function clearIps(){
  $("#Ips").empty();
  $("#ccc").val("");
  $("#ipTypes").attr("value",'0');
  $("#ipText1").val("");
}

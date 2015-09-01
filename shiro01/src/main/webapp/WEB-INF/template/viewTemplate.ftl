<#-- 
         此宏为页面的模板宏 ,作用如下：
   1.统一向页面输出meta头元素及JQuery等通用元素标签
   2.自定义向页面输出js组件引用标签及CSS引用标签
     (目前支持：upload、data(My97DatePicker)、common-----可扩充其他，默认输出easyui的相关js和CSS)
   3.向页面输出导航信息
   4.判定页面只读权限并作出相应控制
  -->
<#macro viewTemplate template=[]>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" >
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="this is my page"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <script type="text/javascript" src="../script/jquery-1.9.0/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../script/jquery-1.9.0/themes/default/easyui.css">  
    <link rel="stylesheet" type="text/css" href="../script/jquery-1.9.0/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/default.css">  
    <script type="text/javascript" src="../script/jquery-1.9.0/jquery.easyui.min.js"></script>   
	<script type="text/javascript" src="../script/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	
        <#list template as item>
		        <#if item="date">
  					<script language="javascript" type="text/javascript" src="../script/My97DatePicker/WdatePicker.js"></script>
				<#elseif item="common">
	                <script type="text/javascript" src="../js/common.js"></script> 
			    <#elseif item="upload">
			    	<script type="text/javascript" src="../js/upload.js"></script>
			    	<script type="text/javascript" src="../script/ajaxupload.js"></script>
			    	<script type="text/javascript" src="../js/ipcomponent.js"></script>
			    <#elseif item="jqplot">
			    	<script type="text/javascript" src="../script/jquery.jqplot.min.js"></script>
					<script type="text/javascript" src="../script/jqplot.pieRenderer.js"></script>
					<script type="text/javascript" src="../script/excanvas.js"></script>
					<link rel="stylesheet" type="text/css" href="../css/jquery.jqplot.min.css" />
				</#if>
        </#list>
        
     
    <script type="text/javascript">
		    //以下暂时预留
          $(function(){
               
	          //此方法用来判断页面是否只读并加以控制
	          //权限为只读的页面只需在 html元素的属上添加ifopt
	          //目前只支持<button><a><input> <select>(可扩展)
             
		    });
		    
		    function ifopt(){
		    
		    	 var limited ="${limited!}";
             
		    	if(limited=="1"){
		    	 $("[ifopt]").each(function(){
		    	        if($(this).prop("tagName")=="A"){
		    	        	if('undefined'!= typeof($(this).attr("class")) && $(this).attr("class").indexOf("easyui-linkbutton")>=0){
			    	        	$(this).linkbutton('disable');
		    	        	}else{
			    	        	$(this).attr("onclick", "");
			    	        	$(this).attr("href", "#");
			    	        	$(this).attr("style","color:#c0c0c0");
		    	        	} 
		    	        }else if($(this).prop("tagName")=="BUTTON"){
	    	             	$(this).attr("disabled",true);
		    	        }else if($(this).prop("tagName")=="INPUT"){
	    	               if('undefined'!= typeof($(this).attr("class")) && $(this).attr("class").indexOf("easyui-")>=0){
		    	        		$(this).combo('disable');
		    	        	}else{
		    	                $(this).attr("onclick", "");
		    	                $(this).attr("disabled",true);
		    	                $(this).attr("readonly",true);
		    	        	}
		    	        }else if($(this).prop("tagName")=="SELECT"){
	    	                $(this).attr("onclick", "");
	    	                $(this).attr("disabled",true);
	    	                $(this).attr("readonly",true);
		    	        }
		    	    });
		    	}
		    }
    </script>
    
</#macro>
	
    $.ajaxSetup({
	  global: true,
	  complete:function(event,request){
    	/*
		  var info = eval("(" + event.responseText + ")");
		  if(info.loginPage){
			 
			  //alert('登陆时间过长，请重新登陆');
			  window.open(info.loginPage+'/longin.html?p=timeout','_top');
			  
		  }
		  */
	  }

	}); 
    //将easyui message控件中的按钮改为中文
    $.extend($.messager.defaults,{  
        ok:"确定",  
        cancel:"取消"  
    });  
    //封装的easyui message控件，直接使用jquery调用
    jQuery.extend(jQuery, {
    	//错误信息弹出框
	    	meAlert:function(message){
    	       $.messager.alert('错误',message,'error');
	    	},
	    //提示信息弹出框
	    	mePrompt:function(message){
        	       $.messager.alert('提示',message,'info');
	       },
	 
	       //二次确认弹出框
    	meConfirm:function(message,fn){
	    	   if(fn){
	    		   $.messager.confirm('操作提示', message, fn);
	    	   }
	       },
    	//自动消失弹出框
    	meAuto:function(message){
    		 $.messager.show({
                 title:'提示',
                 msg:message,
                 showType:'fade',
                 timeout: 1000,
                 style:{
                     right:'',
                     bottom:''
                 }
             });
    	}
    }); 
	
	

	
	$.fn.serializeObject = function() { 
			var o = {}; 
			var a = this.serializeArray(); 
			$.each(a, function() { 
			if (o[this.name]) { 
			if (!o[this.name].push) { 
			o[this.name] = [ o[this.name] ]; 
			} 
			o[this.name].push(this.value || ''); 
			} else { 
			o[this.name] = this.value || ''; 
			} 
			}); 
			return o; 
		}
	
	
	/**
	 * 根据业务方传递字典类型返回需要的字典值
	 * @param dictionaryType
	 * @return
	 */
	function findDictionary(dictionaryType){
		var dictionMap="";
		$.ajax({
			   type: "POST",
			   async:false,
			   url: "../offLineApply/unloadApplyAction_findDictionary",
			   data: "dictionaryType="+dictionaryType,
			   success: function(data){
				dictionMap= data.map;
			   }
			});
		
		return dictionMap;
	}
	
	/**@author 赵庆伟
	 * 为combo类型的控件赋值字典项
	 * @param itemType 字典项中的type
	 * @param elementId comboID,控件ID
	 * @return
	 */
	function setComboDictionary(itemType,elementId,param){
		var dataMap = findDictionary(itemType);
		var data = new Array();
		if(param!=null){
			data.push({"id":"","text":"全部"});
		}
		$.each(dataMap,function(key,value){
			data.push({"id":key,"text": value});
		});
		$('#'+elementId).combobox({
			data:data,
			valueField:'id',
            textField:'text'
		});
	}

	
	//通用异常信息封装
	function processAjaxResult(data,successCallback,failCallback){
		if(data.rst != 0){
			if(failCallback)
			{
				failCallback(data.errMsg);
			}else{
				$.meAlert(data.errMsg);  
			}
         }else{
        	 if(successCallback)
        	 {
        		 successCallback();
        	 }
         }
	}
	
	
	
	//通用下载方法
	function downLoadFile(fileName,downType){
		
		  window.location.href ="downLoadFile?fileName="+ encodeURI(encodeURI(fileName))+"&downLoadType="+encodeURI(encodeURI(downType));
		  
	}
	
	//特殊下载方法
	function downLoadFileSpecial(fileName,pathCode){
		
		  window.location.href ="downLoadFile?fileName="+ encodeURI(encodeURI(fileName))+"&pathCode="+encodeURI(encodeURI(pathCode));
		  
	}
	
	//统一替换时间字符串中存在的"T"
	function replaceT(StringTime){
		return StringTime.replace("T"," ");
	}
	function datagridResize(domstr,widthOffset,heightOffset)
	{
		var width = $(window).width() + widthOffset;
				var height = $(window).height() + heightOffset;
				$(domstr).datagrid('resize', {
					width:width,
					height:height
		});
	}
	
	/**
	 * @author 赵庆伟
	 * 判断对象是否为空，或者null
	 * @param obj
	 * @return
	 */
	function isNull(obj){
		if(null == obj || ''==obj || 'undefined'==typeof(obj)){
			return false;
		}
		return true;
	}
	

	/**
	 *  @author 赵庆伟
	 * 获取指定name的checkbox的选中项的value,以“,”隔开。
	 */
	function getChecked(checkboxName){
		var str = '';
		var boxes = document.getElementsByName(checkboxName);  
	    for(var i=0; i<boxes.length; i++){
	    	if(boxes[i].checked){
	    		str += (boxes[i].value+',');
	    	}
	    }
		return str;
	}
	
	/**
	 * 获取浏览器类型
	 * @return
	 */
	function getOs(){ 
	   if(navigator.userAgent.indexOf("MSIE")>0) { 
	        return "IE"; 
	   } 
	   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
	        return "Firefox"; 
	   } 
	   if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
	        return "Safari"; 
	   }  
	   if(isCamino=navigator.userAgent.indexOf("Camino")>0){ 
	        return "Camino"; 
	   } 
	   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){ 
	        return "Gecko"; 
	   } 
	} 
	
	
	/**
	 * @author 赵庆伟
	 * 日期处理函数
	 * @param format
	 * @return
	 */
	Date.prototype.format = function(format){
		if( 'yyyy-MM-dd hh:mm:ss' == format){
			var o = { 
					"M+" : this.getMonth()+1, //month 
					"d+" : this.getDate(), //day 
					"h+" : this.getHours(), //hour 
					"m+" : this.getMinutes(), //minute 
					"s+" : this.getSeconds(), //second 
					"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
					"S" : this.getMilliseconds() //millisecond 
					}
					
					if(/(y+)/.test(format)) { 
						format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
					} 

					for(var k in o) { 
						if(new RegExp("("+ k +")").test(format)) { 
							format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
						}
					}
					
			return format;
		

		}else if(false){
			
		}else{
			
		}
	}
	/**
	 * 处理日期格式
	 */
	function doDate(value,style){
		if(isNull(value)){
			
		}else{
			return "";
		}
		value = value.replace("T"," ");

		var date= new Date(Date.parse(value.replace(/-/g,   "/")));
		return date.format("yyyy-MM-dd hh:mm:ss"); 
	}
	/**  
	 * 扩展两个方法  
	 */  
	$.extend($.fn.datagrid.methods, {   
	    /** 
	     * 开打提示功能  
	     * @param {} jq  
	     * @param {} params 提示消息框的样式  
	     * @return {}  
	     */  
	    doCellTip : function(jq, params) {   
	        function showTip(data, td, e) {   
	            if ($(td).text() == "")   
	                return;   
	        	var size = textSize("",$(td).text());
	            if(parseInt($(td).width())>parseInt(size.width))return;
	            data.tooltip.text($(td).text()).css({   
	                        top : (e.pageY + 10) + 'px',   
	                        left : (e.pageX + 20) + 'px',   
	                        'z-index' : $.fn.window.defaults.zIndex,   
	                        display : 'block'   
	            });   
	        };   
	        return jq.each(function() {   
	            var grid = $(this);   
	            var options = $(this).data('datagrid');   
	            if (!options.tooltip) {   
	                var panel = grid.datagrid('getPanel').panel('panel');   
	                var defaultCls = {   
	                    'border' : '1px solid #333',   
	                    'padding' : '1px',   
	                    'color' : '#333',   
	                    'background' : '#f7f5d1',   
	                    'position' : 'absolute',   
	                    'max-width' : '200px',   
	                    'border-radius' : '4px',   
	                    '-moz-border-radius' : '4px',   
	                    '-webkit-border-radius' : '4px',   
	                    'display' : 'none'   
	                }   
	                var tooltip = $("<div id='celltip' style='white-space:normal; word-break:break-all;'></div>").appendTo('body');   
	                tooltip.css($.extend({}, defaultCls, params.cls));   
	                options.tooltip = tooltip;  
	                //------------
	               
	            /*  var factContentWidth = options.factContent.width();      
	                params.content = $(this).text();      
                    if (params.onlyShowInterrupt) {      
                        if (factContentWidth > $(this).width()) {      
                            showTip(params, this, e, grid);      
                        }      
                    } else {      
                       showTip(params, this, e, grid);      
                    }    */
	                //------------
	                panel.find('.datagrid-body').each(function() {   
	                    var delegateEle = $(this).find('> div.datagrid-body-inner').length   
	                            ? $(this).find('> div.datagrid-body-inner')[0]   
	                            : this;   
	                    $(delegateEle).undelegate('td', 'mouseover').undelegate(   
	                            'td', 'mouseout').undelegate('td', 'mousemove')   
	                            .delegate('td', {   
	                                'mouseover' : function(e) {   
	                                    if (params.delay) {   
	                                        if (options.tipDelayTime)   
	                                            clearTimeout(options.tipDelayTime);   
	                                        var that = this;   
	                                        options.tipDelayTime = setTimeout(   
	                                                function() {   
	                                                    showTip(options, that, e);   
	                                                }, params.delay);   
	                                    } else {  
	     
	                                        showTip(options, this, e);   
	                                    }   
	  
	                                },   
	                                'mouseout' : function(e) {   
	                                    if (options.tipDelayTime)   
	                                        clearTimeout(options.tipDelayTime);   
	                                    options.tooltip.css({   
	                                                'display' : 'none'   
	                                            });   
	                                },   
	                                'mousemove' : function(e) {   
	                                    var that = this;   
	                                    if (options.tipDelayTime) {   
	                                        clearTimeout(options.tipDelayTime);   
	                                        options.tipDelayTime = setTimeout(   
	                                                function() {   
	                                                    showTip(options, that, e);   
	                                                }, params.delay);   
	                                    } else {   
	                                    	//alert("文本宽度："+tooltip.width());
	                                    	//alert("表格宽度："+$(this).width()); 
	                      
	                                        	showTip(options, that, e);  
	          
	                                    }   
	                                }   
	                            });   
	                });   
	  
	            }   
	  
	        });   
	    },   
	    /** 
	     * 关闭消息提示功能  
	     * @param {} jq  
	     * @return {}  
	     */  
	    cancelCellTip : function(jq) {   
	        return jq.each(function() {   
	                    var data = $(this).data('datagrid');   
	                    if (data.tooltip) {   
	                        data.tooltip.remove();   
	                        data.tooltip = null;   
	                        var panel = $(this).datagrid('getPanel').panel('panel');   
	                        panel.find('.datagrid-body').undelegate('td',   
	                                'mouseover').undelegate('td', 'mouseout')   
	                                .undelegate('td', 'mousemove')   
	                    }   
	                    if (data.tipDelayTime) {   
	                        clearTimeout(data.tipDelayTime);   
	                        data.tipDelayTime = null;   
	                    }   
	                });   
	    }   
	});  
	function textSize(fontSize, text) {
	    var span = document.createElement("span");
	    var result = {};
	    result.width = span.offsetWidth;
	    result.height = span.offsetWidth; 
	    span.style.visibility = "hidden";
	    document.body.appendChild(span);
	    if (typeof span.textContent != "undefined")
	        span.textContent = text;
	    else span.innerText = text;
	    result.width = span.offsetWidth - result.width;
	    result.height = span.offsetHeight - result.height;
	    span.parentNode.removeChild(span);
	    return result;
	}
	function showTopTip(id){//鼠标悬浮提示
		$('#'+id).datagrid('cancelCellTip').datagrid('doCellTip',   
		    {   
		        onlyShowInterrupt:false,   
		        position:'bottom',   
		        tipStyler:{'backgroundColor':'#ffffcc', borderColor:'#ffffcc', maxWidth:'100px', boxShadow:'1px 1px 3px #ffffcc'},   
		        contentStyler:{backgroundColor:'#ffffcc', paddingLeft:'5px'}   
		    });
	}
	
	
	/**
	 * 实现替换字符串
	 * @param s1
	 * @param s2
	 * @return
	 */
	String.prototype.replaceAll = function(s1,s2) { 
	    return this.replace(new RegExp(s1,"gm"),s2);
	}
	
	
	/**
	 * 用 '\\' 代替 '\'
	 */
	String.prototype.replacePath = function(path) { 
		var param = new RegExp("\\\\","gm")
		return path.replace(param,"\\\\");
	}
	/**
	 * 修改linkbutton按钮背景图片
	 * @param {Object} id
	 */
	function linkbuttonImg(id){
		$(id).css('background-image','url("/scmconsole/script/jquery-1.9.0/themes/default/images/linkbutton_bg1.png")');
		$(id).find('span.l-btn-left').css('background-image','url("/scmconsole/script/jquery-1.9.0/themes/default/images/linkbutton_bg1.png")');
		$(id).css('color','white').mouseover(function(){
			$(this).css('color','red');
		}).mouseout(function(){
			$(this).css('color','white');
		});
	}
	
	/**
	 * 修改弹出框head背景图片
	 * @param {Object} id
	 */
	function  titleImg1(id){
		
		$(id).find('.panel-title').css('background','url("/scmconsole/script/jquery-1.9.0/themes/default/images/head_04.png")');
		$(id).find('.panel-title').css('color','white');
		$(id).find('.panel-title').css('padding','2px 5px 3px 5px');
	}
	/**
	 * 修改弹出框head背景图片
	 * @param {Object} id
	 */
	function  bodyImg1(id){
		$(id).find('.panel-body').css('background','url("/scmconsole/script/jquery-1.9.0/themes/default/images/btn.png")');
	}
	
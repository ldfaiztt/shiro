/**
 * 
 * @param title 上传弹出层标题
 * @param fileNum 限制单次上传文件数量
 * @param model   上传模式，0-如出现重复文件，直接覆盖 1-如出现重复文件，在其上传文件结尾添加一个随机标识（年月日时分秒）2-固定文件名模式
 * @param path    业务标识（一个properties类型的文件，已键值对的形式存储业务标识 例子：1 - c:\.. 2 - d:\..）
 * @param exsitFile  回调函数，将上传文件名回显到父级页面
 * @param isExsitFile[]数组，当打开上传弹出层的同时，验证父页面中的文件在业务标识位置是否存在
 * @param fileFormat 带文件格式的正则表达式
 * @param fileName 固定文件模式所需要的文件名
 * @param backFunction 点击返回时的回调函数
 * @return 上传组件
 * @author 狄江
 */
function fileUpload(title,fileNums,model,uploadpath,exsitFile,fileFormat,fileName,backFunction){
	if(title){
		this.title =title;
	}else{
		this.title="自定义组件";
	}
	if(fileNums){
		this.fileNums=fileNums;
	}else{
		this.fileNums=3;
	}
	if(model){
		this.model = model;
	}else{
		this.model = 0;
	}
	if(fileName){
		this.fileName = fileName;
	}else{
		this.fileName = "默认文件名";
	}
	this.uploadpath = uploadpath;
	this.exsitFile = exsitFile;
	this.dialogTemplate = "<div title='"+title+"' class='easyui-dialog' closed='true' style='background:url(../images/btn.png) repeat;'><li class='example'><div class='wrapper'><div class='button1' style='text-align:left;'>选择文件</div></div><ol class='files'></ol></li><div class='exitFile' style='text-align:left;'></div></div>";

	this.isFileExist = false; //变量，上传文件时判断要上传的文件是否存在
		  //初始化上传弹出层
	this.dialog = $(this.dialogTemplate).dialog({
		modal:true,
		bgiframe:true,
        height: 380,
		width: 450,
		position: ['right','bottom'],
		buttons: [{     
				text:"确定",
				iconCls:"icon-ok",
				handler:function(){
			    	var close_dialog = $(this).parent().parent();
			    	$(close_dialog).dialog( "close" );
				    exsitFile(callback_fileNames(close_dialog));//回调函数，将所上传文件显示到用户自己定义的文本框中
				}
			},
			{     
				text:"返回",
				iconCls:"icon-cancel",
				handler:function(){
					var close_dialog = $(this).parent().parent();
					$(close_dialog).dialog( "close" );
					if(!backFunction || typeof(backFunction) == 'undefined' || backFunction == undefined){
						
					}else{
						backFunction();
					}
				}
			}]
		 });
	    this.dialog.parent().children().children('.ui-dialog-titlebar-close').hide();//隐藏右上角的"X"
		//初始化ajaxupload
	    var exitFileList = this.dialog.find(".exitFile");
	    var button = this.dialog.find(".example .wrapper .button1");
	    var interval;
	    this.ajaxupload = new AjaxUpload(button,{
				action: '../jsonuploadAction_upload.action?uploadCode='+uploadpath+"&model="+model+"&fixedFileName="+fileName, 
				name: 'myfile',
				onSubmit : function(file, ext){	
		
			    	var filesize = exitFileList.children().length; 
					var max = filesize;
					var sax = fileNums-1;
					if(max > sax){
				    	$.meAuto("已达到上传限制数量!");
				    	return false;
				    }
	    	
		    		var fomat = /^(jpg|png|jpeg|gif|doc|docx|xml)$/;
		    		if(!fileFormat)fileFormat = fomat;
				    if (ext && fileFormat.test(ext)){
				         $.ajax({
				         		type : "POST",
				         		url : "../jsonuploadAction_findAllHasUploadFiles.action?uploadCode="+uploadpath,
				         		data : {myfileFileName:file},
				         		async: false,
				         		dataType: "json",
				         		success : function(data){ 
				         			if(data.hasFile==true){
				         				//fileMode(model,file,uploadpath,this.dialogTemplate);
				         		        
						             	isFileExist = true;
				             		}else{
				             			isFileExist = false;
				             		}
						 		}
						 });  
				    }else{
		            	  $.meAuto("文件格式不正确!");
		                  return false;              
				    }
				     
				    button.text('Uploading');
					//this.disable();
					interval = window.setInterval(function(){
						var text = button.text();
						if (text.length < 13){
							button.text(text + '.');					
						} else {
							button.text('Uploading');				
						}
					}, 200);
				},
				onComplete: function(file,response){ 
					var json = eval('('+$(response+" pre").html()+')');
					//button.text('继续添加');		
					window.clearInterval(interval);
					//this.enable();
					
					//判断页面是否存在重复文件名
					var samFileName = "";
					for(var i=0;i<exitFileList.children().length;i++){
						 if(json.myfileFileName==$(exitFileList.children()[i]).find("span").first().text()){
							// alert($(exitFileList.children()[i]).html());
							 $(exitFileList.children()[i]).remove();
						 }
						 
					}
	
					exitFileList.append("<div class='file'><span name='uploadfileName'>"+json.myfileFileName+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>"+json.fileSize+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><a href='#' onclick='deleteFile(this,\""+uploadpath+"\",\""+fileNums+"\")'>删除</a></span></div>");
					var filesize = exitFileList.children().length; 
					var max = filesize;
					var sax = fileNums-1;
					if(max > sax){
				    	//this.disable();
				    	button.text('已达到上传限制数量');
				    	//button.attr('disabled',"true");
				    	return false;
				    }	
				}
		});
	
	    //打开弹出层
        this.open = function(_fileNames){
        	if(_fileNames!="" && _fileNames!=null){
        		this.dialog.dialog('open');
        		this.fileExist(_fileNames);
        	}else{
        		exitFileList.empty();
        		button.text('选择文件');
        		this.dialog.dialog( "open" );
        	}
        }
        
        //去后台判断文件是否存在
        this.fileExist = function(isExsitFile){
            if(isExsitFile!=""){
            	$.post("../jsonuploadAction_fileExists",{myfileFileName:isExsitFile,uploadCode:uploadpath},function(data){
                     if(data.notFindFileNames!=null){
                     		$.meAuto(data.notFindFileNames+"文件已不存在!");
                     		button.text('上传');
                     }
                     exitFileList.empty();
                     if(data.findFileNames!=null){ 
                        var findFileName =  data.findFileNames.split("⊙");
                        var fileSize = data.fileSize.split("⊙"); 
                        for(var s=0;s<findFileName.length;s++){
                        	exitFileList.append("<div class='file' ><span  name='uploadfileName'>"+findFileName[s]+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span>"+fileSize[s]+"</span>&nbsp;&nbsp;&nbsp;&nbsp;<span><a href='#'  onclick=deleteFile(this,\'"+uploadpath+"\',\""+fileNums+"\")>删除</a></span></div>");
                        }
                        var filesize = exitFileList.children().length; 
    					var max = filesize;
    					var sax = fileNums-1;
    					if(max > sax){
    				    	button.text('已达到上传限制数量');	
    				    	return false;
    				    }	
                     }
                });
            }else{
                exitFileList.empty();
            	button.text('上传');	
            }
        }

}
//根据不同的模式，进行覆盖或重命名处理
function fileMode(model,file,uploadpath,obj){	
	//删除重名文件 
	alert(model);
	if(model==0){
		alert($(obj).find(".exitFile").find("span").first().text());
	}
	/*if(model==0){
		$.post("jsonuploadAction_deleteUploadFile",{myfileFileName:file,uploadCode:uploadpath},function(data){   
	    	var json = eval("(" + data + ")");
		   		  if(json.deleteFiles == true){
		   			//判断页面已上传列表中是否存在与即将上传的文件相同的名字
		   			var exitFileList = $(obj).find(".exitFile");
		   			alert($(obj).html());
    		   		 var deleteFileNames = new Array();
    		   		 for(var i=0;i<exitFileList.children().length;i++){
    		   			 alert($(exitFileList.children()[i]).find("span").first().text());
    		   			 deleteFileNames[i]=$(exitFileList.children()[i]).find("span").first().text();
    		   		 }
    		         return;
		   			  
		   			 var param = $("span[name='uploadfileName']");
		   		     if(param.length>0){
		   		     	for(var m=0;m<param.length;m++){
		   		     		if($("#"+param[m].id).text()==file){
		   		     			var parent = document.getElementById(param[m].id);
		   		     			var file_id = document.getElementById("exitFile");//获取当前行的父级对象
		   		     			var my = document.getElementById(parent.parentNode.id);//获取文件所在行的对象
		   		     			file_id.removeChild(my); //移除父级对象下的div标签
		   		     		}
		   		     	}
		   		     }
		   		  }else{
		   		  	  alert("删除文件失败");
		   		  }   
	     });
	}*/
}

//获取需要回显的文件名
function callback_fileNames(dialog){
	 var exitFileList = $(dialog).find(".exitFile");
	 var deleteFileNames = new Array();
	 for(var i=0;i<exitFileList.children().length;i++){
		 deleteFileNames[i]=$(exitFileList.children()[i]).find("span").first().text();
	 }
    return deleteFileNames;
}

//删除上传的文件
function deleteFile(obj,uploadpath,fileNums){
	 var deleteFileName = $(obj).parent().parent().find("span[name='uploadfileName']").text();
   $.post("../jsonuploadAction_deleteUploadFile",{myfileFileName:deleteFileName,uploadCode:uploadpath},function(data){   
   	var json = eval("(" + data + ")");
	   		  if(json.deleteFiles == true){
	   			if(fileNums=="1"){
	   				$(obj).parent().parent().parent().parent().find(" .button1").text('上传');
	   			}else{
	   				$(obj).parent().parent().parent().parent().find(" .button1").text('继续添加');
	   			}
	   			$(obj).parent().parent().remove();
	   		  }else{
	   		  	$.meAuto("删除文件失败");
	   		  }  
    });
}



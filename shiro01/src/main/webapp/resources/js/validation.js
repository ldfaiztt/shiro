$().ready(function(){
		//自定义验证方法
		$.extend($.fn.validatebox.defaults.rules, {  
			ajaxValidator:{ 
		        validator:function(value,param){
						var postdata = {};
	                    postdata[param[1]]=value;
	                    var result=$.ajax({
	                        url:param[0],
	                        dataType:"json",
	                        data:postdata,
	                        async:false,
	                        cache:false,
	                        type:"post"
	                    }).responseText;
						var result1=eval("("+result+")");
	                    return result1.tsts;
	                },
	                message:"这个字段已经被使用"
			  },
			    phone:{  //手机号验证
			        validator: function(value){  
					   //var  mobile = "^(13|14|15|18)[0-9]{9,13}$";	//手机(最小输入11位最大可输入15位)
				         var  tel="^[0-9]{1,15}$";//纯数字联系电话可以任意输入最小1位最大15位
					   var regExp = new RegExp(mobile);
						return regExp.test(value);  
			        },  
			        message: '请输入正确的手机号'  
			    },
			 tell:{  //电话验证
		        validator: function(value){  
				   //var  tel = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";	//有“-”符号的电话号码的函数(包括验证国内区号,国际区号,分机号)
				   //var  tel = "^(([0\\+]\\d{2,3})?(0\\d{2,3}))?(\\d{7,8})((\\d{3,}))?$";	//纯数字电话号码的函数(包括验证国内区号,国际区号,分机号)
			    	 var  tel="^[0-9]{1,15}$";//纯数字联系电话可以任意输入最小1位最大15位
				   var regExp = new RegExp(tel);
				   return regExp.test(value);
		        },  
		        message: '请输入正确的电话号'  
		      } , 
		      
		      tephone:{  //电话和手机同时验证
			        validator: function(value){  
					   //var  tel =/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$|(^(13[0-9]|14[0-9]|15[0|1|2|3|5|6|7|8|9]|18[3|6|7|8|9])\d{8}$)/;//此验证是带“——”符号的电话号码的函数(包括验证国内区号,分机号)
					   //var  tel =/^(0[0-9]{2,3})?([2-9][0-9]{6,7})+([0-9]{1,4})?$|(^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8,12}$)/;//此验证是纯数字的电话号码手机号是15位的函数(包括验证国内区号,分机号)
		    	  		 var  tel="^[0-9]{1,15}$";//纯数字联系电话可以任意输入最小1位最大15位
					   var regExp = new RegExp(tel);
					   return regExp.test(value);
			        },  
			        message: '请输入正确的联系电话'  
			      } , 
			minLength:{  //最小长度
		        validator: function(value, param){  
		            return value.length >= param[0];  
		        },  
		        message: '请输入至少 {0} 字符'  
		     },
			maxLength:{  //最大长度
		        validator: function(value, param){  
		            return value.length <= param[0];  
		        },  
		        message: '请输入至多 {0} 字符'  
			  },
			  
		zh_cn:{
			validator:function(value){
				var chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";				//仅中文
				var regExp = new RegExp(chinese);
				return regExp.test(value);
			},
			message: '请输入正确的中文'  
		},
		numbers:{
			validator:function(value){
				 var  num = "^([+-]?)\\d*\\.?\\d+$";			//数字
				var regExp = new RegExp(num);
				return regExp.test(value);
			},
			message: '请输入正确的数字' 
		},
		Email:{
			validator:function(value){
				var  email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;//Email
				var regExp = new RegExp(email);
				return regExp.test(value);
			},
			message: '电子邮件格式不正确!' 
		},
		
		check_space:{
			validator:function(value){
				var falg = true;
				var space = "^[ ]+$";
				var regExp = new RegExp(space);
				if(regExp.test(value)){
					falg = false;
				}
				return falg;
		    },
		    message: '输入的字符不能全部为空格' 
		},
		zzs:{
			validator:function(value){
				var num  = "^[1-9]\\d*$";					//正整数
				var regExp = new RegExp(num);
				return regExp.test(value);
			},
			message: '请输入正确的数字' 
		},
		numbersIp:{
			validator:function(value){
			 	
				 var  validTypes = $("#ipTypes").val();
				 var  num ="^(([1-9])|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))$";//1-255	正整数
				 var  num1 ="^([0-1]\d|(2[4-9])|3[0-2])$";//24-32的正整数
				 if(validTypes == "0"){
					 var regExp = new RegExp(num);
					 return regExp.test(value);
//					 rules.numbersIp.message = rules.numbersIp1.message;
//					 return false;
				 }else{
					 var regExp = new RegExp(num1);
					 return regExp.test(value);
//					 rules.numbersIp.message = rules.numbersIp2.message;
//					 return false;
				 }
			},
			
			message: '请输入正确的ip范围' 
			
		},
		numbersIp1:{
			validator:function(value){
			 	var  num ="^(([1-9])|([1-9][0-9])|(1[0-9][0-9])|(2[0-4][0-9])|(25[0-5]))$";//1-255	正整数
				var regExp = new RegExp(num);
				return regExp.test(value);
			},
			message: '请输入1-255'  
		},
		numbersIp2:{
			validator:function(value){
				//var  num ="^([1-2]\d|3[0-1]|[1-9])$";//1-31的正整数
				var  num1 ="^([0-1]\d|(2[4-9])|3[0-2])$";//24-32的正整数
				var regExp = new RegExp(num);
				return regExp.test(value);
			},
			message: '请输入28-32' 
		},
		equals: {  
	        validator: function(value,param){  
	            return value == $(param[0]).val();  
	        },  
	        message: '两次输入密码不一样'  
	    },
		C_adrss:{  
	        validator: function(value){
				var ip4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";	//ip地址
		        var regExp = new RegExp(ip4);
				return regExp.test(value); 
	        },  
	        message: 'IP格式不正确'  
	    },
		date:{  
	        validator: function(value){  
			var date = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
	        var regExp = new RegExp(date);
			return regExp.test(value); 
	        },  
	        message: '日期格式不正确'  
	    },
		test:{  
	        validator: function(value){  
			var ip4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";	//ip地址
	        var regExp = new RegExp(ip4);
			return regExp.test(value); 
	        },  
	        message: 'IP格式不正确'  
	    },
	    account:{  
	       validator: function(value){  
	    	    var account1 = '^\\w+$';
	    	    var regExp = new RegExp(account1);
				return regExp.test(value); 
	        },  
	        message: '由字母、数字、下划线等组成'   
	    },
	    	minLength : {
	    		validator : function (value, param) {
	    			var rules = $.fn.validatebox.defaults.rules;
	    			rules.minLength.message = 'Please enter at least {0} characters.';
	    			if(!rules.email.validator(value)){
	    				rules.minLength.message = rules.email.message;
	    				return false;
	    			}
	    			if(!rules.length.validator(value,param)){
	    				rules.minLength.message = rules.length.message;
	    				return false;
	    			}
	    			return value.length >= param[0];
	    		},
	    		message : ''
	    	},

	    
	    password:{  
	        validator: function(value){  
	    	 var postdata = {param:value};
            //  postdata[param[1]]=value;
              var result=$.ajax({
                  url:"authorize/jsonUserAction_isHasPassword",
                  dataType:"json",
                  data:postdata,
                  async:false,
                  cache:false,
                  type:"post"
              }).responseText;
				var result1=eval("("+result+")");
				//alert(typeof(result1.status));
				if(result1.status == "false"){
					return false;
				}else{
					return true;
				}
             // return result1.status;
	        },  
	        message: '原始密码错误'  
	    },
	    equalTo: {
	        validator:function(value,param){
	    	    if($("#newPassword").val()==$("#newPassword1").val()){
	    	    	return true;
	    	    }else{
	    	    	return false;
	    	    }
	        },
	        message:'字段不匹配'
	    },
	    newPassword:{  
	        validator: function(value){  
		    	//var pattern = /(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,20}/;
		    	var pattern = /^[a-zA-Z][A-Za-z0-9]+$/;
				var pattern1 = /[A-Z]/;
				var pattern2 = /[a-z]/;
				var pattern3 = /[0-9]/;
				if (pattern.test(value))
				{ 
				  if(pattern1.exec(value) && pattern2.exec(value) && pattern3.exec(value) && value.length>=8 && value.length<=20){
						return true;
				  }else{
						return false;
				  }
				}else{
				    return false;
				}
	        },  
	        message: '密码长度8-20位且必须包含大写字母、小写字母和数字！'  
	    }
	    ,validRoleName:{
	    	validator: function(value){  
	    	var pp = true;
	    	var postdata = {};
            var result=$.ajax({
                url:"authorize/json_list",
                dataType:"json",
                data:postdata,
                async:false,
                cache:false,
                type:"post"
            }).responseText;
            var result1=eval("("+result+")");
              if(value == $("#hidden_name").val()){
            	  pp = true;
              }else{
            	  for(var i=0;i<result1.rows.length;i++){
                	  if(value==result1.rows[i].name){
                		  pp = false;
                		  return;
                	  }
                  }
              }
              return pp;
	        },  
	        message: '该角色名称已存在！'  
	    }
	    ,validScmRoleName:{
	    	validator: function(value){  
	    	var pp = true;
	    	var postdata = {};
            var result=$.ajax({
                url:"authorize/scm_list",
                dataType:"json",
                data:postdata,
                async:false,
                cache:false,
                type:"post"
            }).responseText;
            var result1=eval("("+result+")");
              if(value == $("#hidden_name").val()){
            	  pp = true;
              }else{
            	  for(var i=0;i<result1.rows.length;i++){
                	  if(value==result1.rows[i].name){
                		  pp = false;
                		  return;
                	  }
                  }
              }
              return pp;
	        },  
	        message: '该角色名称已存在！'  
	    }
	    ,validBranchName:{
	    	validator: function(value){  
	    	var pp = true;
	    	var postdata = {};
            var result=$.ajax({
                url:"branchAction_allList",
                dataType:"json",
                data:postdata,
                async:false,
                cache:false,
                type:"post"
            }).responseText;
            var result1=eval("("+result+")");
            if(value == $("#hidden_name").val()){
          	  pp = true;
            }else{
              for(var i=0;i<result1.branchs.length;i++){
            	  if(value==result1.branchs[i].name){
            		  pp = false;
            		  return;
            	  }
              }
            }
              return pp;
	        },  
	        message: '该部门名称已存在！'  
	    },
	     validGroupName:{
	    	validator: function(value){  
	    	var pp = true;
	    	var postdata = {};
	    	 var result=$.ajax({
	                url:"jsonStrategyTemplate_validGroupName",
	                dataType:"json",
	                data:{'strategyGroup.name':value},
	                async:false,
	                cache:false,
	                type:"post"
	            }).responseText;
	    	 var data=eval("("+result+")");
	    	 if(value == $("#hidden_name").val()){
	          	  pp = true;
	            }else{
	            	if(data.strategyGroup != null){
	    			pp = false;
	            }
	    	 }
           
              return pp;
	        },  
	        message: '该分组名称已存在！'  
	    },
	    validStrategyName:{
	    	validator: function(value){  
	    	var pp = true;
	    	 var result=$.ajax({
	                url:"jsonStrategyTemplate_validStrategyName",
	                dataType:"json",
	                data:{'strategyTemplate.name':value},
	                async:false,
	                cache:false,
	                type:"post"
	            }).responseText;
	    	 var data=eval("("+result+")");
	    	 if(value == $("#hidden_strategyname").val()){
	          	  pp = true;
	            }else{
	            	if(data.strategyTemplate != null){
	    			pp = false;
	            }
	    	 }
           
              return pp;
	        },  
	        message: '该模板名称已存在！'  
	    }
	    
	}); 
	$('input.easyui-validatebox').validatebox({
			tipOptions: {	// the options to create tooltip
				showEvent: 'mouseenter',
				hideEvent: 'mouseleave',
				showDelay: 0,
				hideDelay: 0,
				zIndex: '',
				onShow: function(){
					if (!$(this).hasClass('validatebox-invalid')){//验证通过
						if ($(this).tooltip('options').prompt){
								$(this).tooltip('update', $(this).tooltip('options').prompt);
						} else {
							$(this).tooltip('tip').hide();
						}
					} else {                                       //未验证通过（警告提示样式--validatebox-invalid）
						$(this).tooltip('tip').css({
							color: '#000',
							borderColor: '#CC9933',
							backgroundColor: '#FFFFCC'
//							width:'200px'    //设置宽度
						});
					}
				},
				onHide: function(){
					if (!$(this).tooltip('options').prompt){
						$(this).tooltip('destroy');
					}
				}
			}
		}).tooltip({
			position: 'right',//left  right
			content: function(){
				var opts = $(this).validatebox('options');
				return opts.prompt;
			},
			onShow: function(){
				if($(this).tooltip('tip').text()){
					$(this).tooltip('tip').css({
						color: '#000',
						borderColor: '#CC9933',
						backgroundColor: '#FFFFCC'
//					width:'200px'    //璁剧疆瀹藉害
					});
				}else{
					$(this).tooltip('tip').hide();
				}
			}
		});
			
})

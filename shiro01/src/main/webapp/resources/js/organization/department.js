var formUrl;

function list() {

	$(window).resize(function() {
		datagridResize('#tt', -5, -50);
	});
	$('#tt').datagrid({
		width : 'auto', // 宽度自适
		height : 'auto', // 此处不能自适应，否则将会把分页插件顶到最下边，显示不出来
		url : 'department_list',
		columns : [ [
				{field : 'id',title : 'id',hidden : true},
				{field : 'name',title : '单位名称',width : 30,align : "center",sortable : true},
				{field : 'des',title : '单位描述',width : 100,align : "center",sortable : true},
				{field : 'person',title : '联系人',width : 100,align : "center",sortable : true},
				{field : 'phone',title : '联系电话',width : 100,align : "center",sortable : true},
				{field : 'act',title : '操作',width : 40,align : "center",formatter : function(value, row, rowIndex) {
						return '<a ifopt href="javascript:void(0);" onclick ="openAddDialog(\'edit\',\''
								+ row.id + '\');">编辑</a>'
						// + '&nbsp;<a
						// href="javascript:void(0);"
						// onclick="removeDepartment(\''+row.id+'\');">删除</a>';
					}
				} ] ],
		nowrap : true,
		striped : true,
		border : true,
		collapsible : false,// 是否可折叠的
		fit : false,// 自动大小
		// sortName: 'code',
		// sortOrder: 'desc',
		pagination : 'true',
		remoteSort : true,
		idField : 'id',
		singleSelect : true,// 是否单选
		rownumbers : true,
		onLoadSuccess : function(data) {
			processAjaxResult(data);
			topTip('tt');
			ifopt();
		}
	});
	// 设置分页控件
	var p = $('#tt').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15 ],// 可以设置每页记录条数的列表
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});

	datagridResize('#tt', -5, -50);
	$('#addDialog').dialog({
		modal : true
	});
	$('#distDialog').dialog({
		modal : true
	})

}
// 鼠标悬浮
function topTip(id) {
	showTopTip(id);
}

// 查询条件
function FindData() {
	$('#tt').datagrid('load', {
		name : $('#name1').val()
	});
}
function reset() {
	$("#name1").val("");
	refresh();
}

// 添加单位和编辑单位弹出框
function openAddDialog(type, id) {
	if (type == "add") {

		$('#addDialog').dialog('open').dialog('setTitle', '创建单位');
		$('#fm').form('clear');
		formUrl = "save";
	} else {
		$('#addDialog').dialog('open').dialog('setTitle', '编辑单位信息');
		$(".easyui-validatebox").removeClass('validatebox-invalid');

		formUrl = "update";
		$.post("department_findById", {
			id : id
		}, function(data) {
			processAjaxResult(data, function() {
				$("#name").val(data.department.name);
				$("#des").val(data.department.des);
				$("#id").val(data.department.id);
				$("#person").val(data.department.person);
				$("#phone").val(data.department.phone)
			});

		});
	}
}

// 添加保存单位
function saveDepartment() {
	var url;
	if (formUrl == "save") {
		url = "department_add"
	} else if (formUrl == "update") {
		url = "department_update";
	}
	$('#fm').form('submit', {
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(data) {
			var result = eval("(" + data + ")");
			processAjaxResult(result, function() {
				refresh();
				$('#addDialog').dialog('close');
			});

		}
	});
}

// 删除部门
function removeDepartment(id) {
	$.meConfirm('您确定要删除这个单位吗?', function(r) {
		if (r) {
			$.post('department_delete', {
				id : id
			}, function(data) {
				processAjaxResult(data, function() {
					refresh();
				});
			}, 'json');
		}
	});
}
// 刷新方法
function refresh() {
	$('#tt').datagrid('reload');
}

/**
 * 部门初始化
 */
$(function() {
	list();
});
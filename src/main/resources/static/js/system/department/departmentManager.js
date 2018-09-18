/**
 * 部门管理
 */
$(function(){
	
	//根据窗口尺寸调整表格
	$(window).resize(function() {
		$('#mytable').bootstrapTable('resetView');
	})
	
	//操作栏事件			
	window.operateEvents = {
		'click .editItem' : function(e, value, row, index) {
			$("#id").val(row.id);
			$("#departmentName").val(row.departmentName);
			$("#departmentCode").val(row.departmentCode);
			
			$("#modelTitle").html("编辑部门");
			$('#editModal').modal(); //显示编辑弹窗
		},
		'click .removeItem' : function(e, value, row, index) {
			swal({
				title : "您确定要删除这条信息吗",
				text : "删除后将无法恢复，请谨慎操作！",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "删除",
				cancelButtonText : "取消",
				closeOnConfirm : true
			}, function() {
				deleteItems(row.id);
			});
		}
	};
	
	//初始化列表
	initTable();
	
	//初始化表格
	function initTable() {
		$("#mytable").bootstrapTable('destroy');
		$('#mytable').bootstrapTable({
			method : 'post',
			contentType : "application/x-www-form-urlencoded",
			url : "/department/getDepartmentsList",
			queryParams : queryParams, //请求服务器时所传的参数
			striped : true, //是否显示行间隔色
			dataField : "rows", //数据列表字段
			sidePagination : 'server', //指定服务器端分页
			pagination : true, //是否分页
			pageNumber : 1, //初始化加载第一页
			pageSize : 10, //单页记录数
			pageList : [ 5, 10, 20, 30 ],//分页步进值
			showColumns : true,
			clickToSelect : true,//是否启用点击选中行
			uniqueId : "id",
			toolbarAlign : 'left',//工具栏对齐方式
			buttonsAlign : 'right',//按钮对齐方式
			toolbar : '#toolbar',//指定工作栏
			columns : [
					{
						checkbox : true, //复选框
						width : 25,
						align : 'center',
						valign : 'middle'
					},
					{
						align : 'center',
						title : '部门名称',
						field : 'departmentName'
					},
					{
						align : 'center',
						title : '部门编码',
						field : 'departmentCode'
					},
					{
						align : 'center',
						title : '创建日期',
						field : 'createTime'
					},
					{
						title : '操作',
						align : 'center',
						events : operateEvents,
						formatter : operateFormatter //列数据格式化
					} ],
			locale : 'zh-CN', //中文支持,
			responseHandler : function(res) {
				//在ajax获取到数据，渲染表格之前，修改数据源
				return res;
			}
		});
	}
	
	//操作栏格式化，三个参数，value代表该列的值
	function operateFormatter(value, row, index) {
		return [
				'<a class="editItem" href="javascript:void(0)" title="编辑">',
				'<i class="fa fa-pencil-square-o"></i>',
				'</a>  ',
				'<a class="removeItem" href="javascript:void(0)" title="删除">',
				'<i class="glyphicon glyphicon-remove"></i>',
				'</a>' ].join('');
	}
	
	//请求服务数据时所传参数
	function queryParams(params) {
		var formData = $("#queryForm").serializeArray();//把form里面的数据序列化成数组
		formData.forEach(function(e) {
			params[e.name] = e.value;
		});
		return params;
	}
	
	//查询按钮事件
	$('#search_btn').click(function() {
		initTable();
	})
	
	//删除数据
	function deleteItems(ids) {
		$.post("/department/deleteDepartments", {
			ids : ids
		}, function(data) {
			if(data && data.state == 1) {
				//刷新列表
				initTable();
			}
		});
	}
	
	//新增按钮事件
	$('#btn_add').click(function() {
		$("#modelTitle").html("新增部门");
		$('#editModal').modal();
	});
	
	//表格顶部的删除按钮
	$('#btn_delete').click(function() {
		var dataArr = $('#mytable').bootstrapTable('getSelections');
		if (dataArr.length > 0) {
			swal({
				title : "您确定要删除选中的信息吗",
				text : "删除后将无法恢复，请谨慎操作！",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "删除",
				cancelButtonText : "取消",
				closeOnConfirm : true
			}, function() {
				var ids = "";
				for (var i = 0; i < dataArr.length; i++) {
					if (ids == "") {
						ids = dataArr[i].id;
					} else {
						ids += "," + dataArr[i].id;
					}
				}
				
				deleteItems(ids) 
			});
		} else {
			swal("请选择要删除的信息", "", "warning");
		}
	});
	
	//保存按钮事件
	$('#saveBtn').click(function() {
		//点击保存时触发表单验证
		$('#editForm').bootstrapValidator('validate');
	    //如果表单验证正确，则请求后台保存用户
		if($("#editForm").data('bootstrapValidator').isValid()){
    	   $.post("/department/saveDepartment", $('#editForm').serialize(), function(data) {
    		   if(data && data.state == 1) { //后台返回添加成功
					//关闭弹窗
					$('#editModal').modal('hide');
					
					//刷新列表
					initTable();
				}
			})
		}
	});
	
	//弹窗关闭监听
	$("#editModal").on("hide.bs.modal",function(){
		//清空表单信息
		$("#editForm").find('input[type=text],select,input[type=hidden]').each(function() {
			$(this).val('');
		});
		
		//hide方法后调用，清除表单验证提示
		$('#editForm').data('bootstrapValidator').resetForm(true);
	});
	
	//添加表单验证
	$('#editForm').bootstrapValidator({
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			departmentName: {
				validators: {
					notEmpty: {
                       message: '部门名称不能为空'
					}
				}
			},
			departmentCode: {
				validators: {
					notEmpty: {
                       message: '部门编码不能为空'
					}
				}
			}
		}
	});

});
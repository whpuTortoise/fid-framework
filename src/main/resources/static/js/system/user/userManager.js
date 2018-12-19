/**
 * 用户管理
 */
$(function(){
	
	//根据窗口尺寸调整表格
	$(window).resize(function() {
		$('#mytable').bootstrapTable('resetView');
	})



	//初始化机构树
   initTreeview();
  
   
   
   //初始化机构类型
   function initDepartmentType(){
   	$.get("/departmentType/getAllDepartmentType", function(data){
   		if(data && data.state == 1) {
   			departmentTypes = data.datas;
   			var htmlStr = "";
   			for(var i=0; i<departmentTypes.length; i++){
   				htmlStr += "<option value='"+departmentTypes[i].id+"'>"+departmentTypes[i].typeName+"</option>";
   			}
   			
   			$("#departmentTypeId").html(htmlStr);
   			
   		}
   	});
   }
   
   //构建树结构
	function buildDomTree(departments) {
       var data = [];

       function walk(nodes, data) {
       	if (!nodes) { return; }
       	$.each(nodes, function (id, node) {
       		var obj = {
       			text: node.departmentName,
       			icon: "fa fa-bank",
       			nodev: node
	            };
	            if (node.children && node.children.length > 0) {
	            	obj.nodes = [];
	            	walk(node.children, obj.nodes);
	            }
	            data.push(obj);
       	});
       }

       walk(departments, data);
       return data;
	}
   
	 //初始化机构树
    function initTreeview(){
//    	$('#event_output').html('无');
//    	$('#event_code').html('无');
//    	$('#event_type').html('无');
    	$.post("/department/getDepartmentTree", {}, function(data) {
    		if(data && data.state == 1) {
    			var departments = data.datas;
    			
    			$("#departmentTree").treeview({
    	            color: "#428bca",
    	            data: buildDomTree(departments),
    	            onNodeSelected: function(event, node) {
    	                $('#event_output').html(node.text);
    	                $('#event_code').html(node.nodev.departmentCode);
    	                for(var i=0; i<departmentTypes.length; i++){
    	    				if(node.nodev.departmentTypeId==departmentTypes[i].id){
    	    					 $('#event_type').html(departmentTypes[i].typeName);
    	    					 break;
    	    				}
    	    			}
    	               
    	              },
    	              onNodeUnselected: function (event, node) {
//    	            	  $('#event_output').html('无');
//    	            	  $('#event_code').html('无');
//    	            	  $('#event_type').html('无');
    	              }
    	        });
    			initDepartment();
    			initRole();
    		}
    	})
    }

	//初始化部门
	function initDepartment(){
		$.get("/department/getAllDepartment", function(data){
			if(data && data.state == 1) {
				var lists = data.datas;
				var htmlStr = "";
				for(var i=0; i<lists.length; i++){
					if(i == 0){
						htmlStr = "<option value='"+lists[i].id+"' selected='selected'>"+lists[i].departmentName+"</option>";
					}else{
						htmlStr += "<option value='"+lists[i].id+"'>"+lists[i].departmentName+"</option>";
					}
				}

				$("#departmentId").html(htmlStr);

			}
		});
	}

	//初始化角色
	function initRole(){
		$.get("/role/getAllRole", function(data){
			if(data && data.state == 1) {
				var roles = data.datas;
				var htmlStr = "";
				for(var i=0; i<roles.length; i++){
						htmlStr += "<label class='checkbox-inline'><input type='checkbox' value='"+roles[i].id+"' >"+roles[i].roleName+"</label>";
				}

				$("#checkRole").html(htmlStr);

			}
		});
	}
   
   
   /**
    * 获取选中的节点
    */
   function getSelectNode(){
   	var arr = $('#departmentTree').treeview('getSelected');
   	if(arr.length > 0){
   		return arr[0].nodev;
   	}else{
   		return null;
   	}
   }
   
	
	
	
	
	//操作栏事件			
	window.operateEvents = {
		'click .editItem' : function(e, value, row, index) {
			$("#id").val(row.id);
			$("#username").val(row.username);
			$("#realName").val(row.realName);
			$("#age").val(row.age);
			$("#tel").val(row.tel);
			
			$("#modelTitle").html("编辑用户");
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
			url : "/user/getUserList",
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
						title : '用户名',
						field : 'username'
					},
					{
						align : 'center',
						title : '姓名',
						field : 'realName'
					},
					{
						align : 'center',
						title : '年龄',
						field : 'age'
					},
					{
						align : 'center',
						title : '电话',
						field : 'tel'
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
		$.post("/user/deleteUsers", {
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
		$("#modelTitle").html("新增用户");
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
    	   $.post("/user/saveUser", $('#editForm').serialize(), function(data) {
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
			departmentId: {
				validators: {
					notEmpty: {
						message: '归属机构不能为空'
					}

				}
			},
			username: {
				validators: {
					notEmpty: {
                       message: '用户名不能为空'
					},
					stringLength:{
						min:5,
						max:15,
						message:'用户名为5-10位'
					}
				}
			},
			realName: {
				validators: {
					notEmpty: {
						message: '姓名不能为空'
					},
					stringLength:{
						min:2,
						max:10,
						message:'姓名为2-10位'
					}
				}
			},
			tel: {
				validators: {
					notEmpty: {
						message: '手机号不能为空'
					},
					stringLength: {
						min: 11,
						max: 11,
						message: '手机号必须为11位'
					},
					regexp: {
						regexp: /^1(3|4|5|6|7|8)\d{9}$/,
						message: '请填写正确的手机号'
					}
				}
			},
			birthday: {
				validators: {
					notEmpty: {
						message: '出生日期不能为空'
					}

				}
			}
			/*Email: {
				validators: {
					notEmpty:{
						message:'邮箱不能为空'
					},
					regexp: {
						regexp:/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ,
						message: '无效的邮箱'
					}
				}
			}, */
		}
	});

});
/**
 * 角色管理
 */
$(function(){
	
	//根据窗口尺寸调整表格
	$(window).resize(function() {
		$('#mytable').bootstrapTable('resetView');
	})
	
	var treeview = null;
	var roleId = null;
	var moveUserId = null;
	//操作栏事件			
	window.operateEvents = {
		'click .editItem' : function(e, value, row, index) {
			$("#id").val(row.id);
			$("#roleName").val(row.roleName);
			$("#roleCode").val(row.roleCode);
			$("#description").val(row.description);
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
		},
		'click .authorityItem' : function(e, value, row, index) {
			roleId = row.id
			$("#roleShowName").val(row.roleName);
			initTreeview();
			$('#authorityModal').modal(); //显示编辑弹窗
			
		},
		'click .userItem' : function(e, value, row, index) {
			roleId = row.id
			$('#showRoleName').text(row.roleName);
			initUserTable();
			//$("#roleShowName").val(row.roleName);
			$('#userModal').modal(); //显示编辑弹窗

		},
		'click .moveItem' : function(e, value, row, index) {
			moveUserId = row.id
			$.post("/role/deleteUserRoleByEntity",{'roleId':roleId,'userId':moveUserId}, function(data) {
				if(data && data.state == 1) {
					//刷新列表
					initUserTable();
				}
			});
			//initUserTable();
			//$("#roleShowName").val(row.roleName);
			//$('#userModal').modal(); //显示编辑弹窗

		}
	};

	
	//初始化列表
	initTable();
	initDepartmentTreeview();
	initReadyTable();
	//初始化表格
	function initTable() {
		$("#mytable").bootstrapTable('destroy');
		$('#mytable').bootstrapTable({
			method : 'post',
			contentType : "application/x-www-form-urlencoded",
			url : "/role/getRoleList",
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
						title : '角色名称',
						field : 'roleName'
					},
					{
						align : 'center',
						title : '角色编码',
						field : 'roleCode'
					},
					{
						align : 'center',
						title : '角色描述',
						field : 'description'
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
				'<button type="button" class="btn btn-primary btn-sm">编辑</button>',
				'</a>  ',
				'<a class="removeItem" href="javascript:void(0)" title="删除">',
				'<button type="button" class="btn btn-danger btn-sm">删除</button>',
				'</a>' ,
				'<a class="authorityItem" href="javascript:void(0)" title="权限设置">',
				'<button type="button" class="btn btn-success btn-sm">权限设置</button>',
				'</a>' ,
				'<a class="userItem" href="javascript:void(0)" title="分配用户">',
				'<button type="button" class="btn btn-warning btn-sm">分配用户</button>',
				'</a>' 
				].join('');
	}
	
	//请求服务数据时所传参数
	function queryParams(params) {
		var formData = $("#queryForm").serializeArray();//把form里面的数据序列化成数组
		formData.forEach(function(e) {
			params[e.name] = e.value;
		});
		return params;
	}

	//初始化分配用户表格
	function initUserTable() {
		$("#usertable").bootstrapTable('destroy');
		$('#usertable').bootstrapTable({
			method : 'post',
			contentType : "application/x-www-form-urlencoded",
			url : "/user/getUserListByRoleId",
			queryParams : queryUserParams, //请求服务器时所传的参数
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
			toolbar : '#usertoolbar',//指定工作栏
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
					title : '出生日期',
					field : 'birthday'
				},
				{
					align : 'center',
					title : '电话',
					field : 'tel'
				},
				{
					align : 'center',
					title : '部门',
					field : 'departmentName'
				},
				{
					title : '操作',
					align : 'center',
					events : operateEvents,
					formatter : operateUserFormatter //列数据格式化
				} ],
			locale : 'zh-CN', //中文支持,
			responseHandler : function(res) {
				//在ajax获取到数据，渲染表格之前，修改数据源
				return res;
			}
		});
	}

	//操作栏格式化，三个参数，value代表该列的值
	function operateUserFormatter(value, row, index) {
		return [
			'<a class="moveItem" href="javascript:void(0)" title="移除">',
			'移除',
			'</a>' ].join('');
	}

	//请求服务数据时所传参数
	function queryUserParams(params) {

		params["roleId"] = roleId;

		return params;
	}


	
	//查询按钮事件
	$('#search_btn').click(function() {
		initTable();
	})
	
	//删除数据
	function deleteItems(ids) {
		$.post("/role/deleteRoles", {
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
		$("#modelTitle").html("新增角色");
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


	//新增按钮事件
	$('#user_btn_add').click(function() {
		initSelectTable();
		$('#addUserModal').modal();
	});

	//初始化机构树
	function initDepartmentTreeview(){

		$.post("/department/getDepartmentTree", {}, function(data) {
			if(data && data.state == 1) {
				var departments = data.datas;

				$("#departmentTree").treeview({
					color: "#428bca",
					data: buildDepartmentDomTree(departments),
					onNodeSelected: function(event, node) {
						initReadyTable();
					},
					onNodeUnselected: function (event, node) {
						initReadyTable();
					}
				});
			}
		})
	}

	//构建树结构
	function buildDepartmentDomTree(departments) {
		var data = [];

		function walk(nodes, data) {
			if (!nodes) { return; }
			$.each(nodes, function (id, node) {
				var obj = {
					text: node.departmentName,
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

	//初始化分配用户表格
	function initReadyTable() {
		$("#readytable").bootstrapTable('destroy');
		$('#readytable').bootstrapTable({
			method : 'post',
			contentType : "application/x-www-form-urlencoded",
			url : "/user/getShowUserList",
			queryParams : queryReadyParams, //请求服务器时所传的参数
			striped : true, //是否显示行间隔色
			dataField : "rows", //数据列表字段
			sidePagination : 'server', //指定服务器端分页
			pagination : true, //是否分页
			pageNumber : 1, //初始化加载第一页
			pageSize : 10, //单页记录数
			pageList : [ 5, 10, 20, 30 ],//分页步进值
			clickToSelect : true,//是否启用点击选中行
			uniqueId : "id",
			buttonsAlign : 'right',//按钮对齐方式
			onClickRow:function(row){
				var selectTableItemArr = $("#selecttable").bootstrapTable('getData');
				var selectUserId = row.id;
				var isHave = false;
				for (var x = 0; x < selectTableItemArr.length; x++) {
					if (selectUserId==selectTableItemArr[x].id){
						isHave = true;
						break;
					}
				}
				if(!isHave){
					$('#selecttable').bootstrapTable('append', row);
					//$('#selecttable').bootstrapTable('refreshOptions', {sidePagination: "client"});
				}
			},
			columns : [

				{
					align : 'center',
					title : '姓名',
					field : 'realName'
				}
				],
			locale : 'zh-CN', //中文支持,
			responseHandler : function(res) {
				//在ajax获取到数据，渲染表格之前，修改数据源
				return res;
			}
		});
	}


	//请求服务数据时所传参数
	function queryReadyParams(params) {


		var node = getSelectNode();
		if (node!=null) {
			params["searchDepartmentId"] = node.id;
		}

		return params;
	}




	//初始化分配用户表格
	function initSelectTable() {

		$("#selecttable").bootstrapTable('destroy');
		$('#selecttable').bootstrapTable({
			method : 'post',
			contentType : "application/x-www-form-urlencoded",
			url : "/user/getUserListByRoleId",
			queryParams : queryUserParams, //请求服务器时所传的参数
			striped : true, //是否显示行间隔色
			dataField : "rows", //数据列表字段
			sidePagination : 'server', //指定服务器端分页
			pagination : true, //是否分页
			pageNumber : 1, //初始化加载第一页
			pageSize : 10, //单页记录数
			pageList : [ 5, 10, 20, 30 ],//分页步进值
			clickToSelect : true,//是否启用点击选中行
			uniqueId : "id",
			toolbarAlign : 'left',//工具栏对齐方式
			buttonsAlign : 'right',//按钮对齐方式
			onClickRow:function(row){
				var selectTableItemArr = $("#selecttable").bootstrapTable('getData');
				$('#selecttable').bootstrapTable('remove',{field:"id", values:[row.id]});
			},
			columns : [
				{
					align : 'center',
					field : 'id',
					visible: false
				},
				{
					align : 'center',
					title : '姓名',
					field : 'realName'
				}
			],
			locale : 'zh-CN', //中文支持,
			responseHandler : function(res) {
				//在ajax获取到数据，渲染表格之前，修改数据源
				return res;
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
			roleName: {
				validators: {
					notEmpty: {
                       message: '角色名称不能为空'
					}
				}
			},
			description:{
				validators:{
					notEmpty:{
						message:'角色描述不能为空'
					}
				}
			}
		}
	});
	
	
    
    

    
	//保存按钮事件
	$('#saveRoleBtn').click(function() {
		
			//点击保存时触发表单验证
			$('#editForm').bootstrapValidator('validate');
		    //如果表单验证正确，则请求后台保存用户
			if($("#editForm").data('bootstrapValidator').isValid()){
	    	   $.post("/role/saveRole", $('#editForm').serialize(), function(data) {
	    		   if(data && data.state == 1) { //后台返回添加成功
						//关闭弹窗
						$('#editModal').modal('hide');
						
						//刷新列表
						initTable();
					}
				})
			}
	
		
	});
	
	    //构建树结构
		function buildDomTree(menus) {
	        var data = [];

	        function walk(nodes, data) {
	        	if (!nodes) { return; }
	        	$.each(nodes, function (id, node) {
	        		var obj = {
	        			id: node.id,
	        			text: node.menuName,
	        			icon: node.menuIcon
		            };
		            if (node.children && node.children.length > 0) {
		            	obj.nodes = [];
		            	walk(node.children, obj.nodes);
		            }
		            data.push(obj);
	        	});
	        }

	        walk(menus, data);
	        return data;
		}
	    
	    //初始化菜单树
	    function initTreeview(){
	    	$.post("/menu/getMenuTree", {}, function(data) {
	    		if(data && data.state == 1) {
	    			var menus = data.datas;
	    			
	    			treeview = $("#menuTree").treeview({
	    		    	showIcon: false,
	    		    	showCheckbox: true,
	    		        color: "#428bca",
	    		        highlightSelected: false,
	    		        expandIcon: "glyphicon glyphicon-chevron-right",
	    		        collapseIcon: "glyphicon glyphicon-chevron-down",
	    		        nodeIcon: "glyphicon glyphicon-bookmark",
	    		        data: buildDomTree(menus),
	    		        state: {
	    		        	checked: true
	    		        }
	    		    });
	    			
	    			initChecked();
	    		}
	    	})
	    }
	    
	    
	    //初始化选中的菜单
	    function initChecked(){
	    	$('#menuTree li').removeClass("node-checked");
	    	$('#menuTree .check-icon').removeClass("glyphicon-check");
	    	$('#menuTree .check-icon').removeClass("glyphicon-unchecked").addClass("glyphicon-unchecked");
	    	
	    	$.post("/authority/getAuthority", {'roleId': roleId}, function(data) {
	    		if(data && data.state == 1) {
	    			var menus = data.datas;
	    			//选中
	    			for(var i = 0; i< menus.length; i++){
	    				$("#"+menus[i].menuId).removeClass("node-checked").addClass("node-checked");
	    				$("#"+menus[i].menuId+" .check-icon").removeClass("glyphicon-unchecked").addClass("glyphicon-check");
	    			}
	    		}
	    	})
	    }
	    
	    
	    

	    
		//保存按钮事件
		$('#saveAuthorityBtn').click(function() {
			
			var menuIds = "";
			var nodesEle = $('#menuTree').find(".node-checked");
			for(var i=0; i<nodesEle.length; i++){
				if(menuIds == ""){
					menuIds = nodesEle.eq(i).attr("id");
				}else{
					menuIds += ","+nodesEle.eq(i).attr("id");
				}
			}

			
			$.post("/authority/saveAuthority", {'roleId': roleId, 'menuIds': menuIds}, function(data) {
			   if(data && data.state == 1) { //后台返回添加成功
				   swal("保存成功", "", "success");
				}
			})
		});

	//保存按钮事件
	$('#saveUserRoleBtn').click(function() {

		var userIds = "";
		var selectUserArr = $("#selecttable").bootstrapTable('getData');
		for(var i=0; i<selectUserArr.length; i++){
			if(userIds == ""){
				userIds = selectUserArr[i].id;
			}else{
				userIds += ","+selectUserArr[i].id;
			}
		}


		$.post("/role/saveUserRoleEntities", {'roleId': roleId, 'userIds': userIds}, function(data) {
			if(data && data.state == 1) { //后台返回添加成功
			//	swal("保存成功", "", "success");
				//关闭弹窗
				$('#addUserModal').modal('hide');

				//刷新列表
				initUserTable();
			}
		})

	});

});
/**
 * 菜单管理
 */
$(function() {
    
	//初始化菜单树
    initTreeview();
    
    //构建树结构
	function buildDomTree(menus) {
        var data = [];

        function walk(nodes, data) {
        	if (!nodes) { return; }
        	$.each(nodes, function (id, node) {
        		var obj = {
        			text: node.menuName,
        			icon: node.menuIcon,
        			nodev: node
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
    	$('#event_output').html('无');
    	$.post("/menu/getMenuTree", {}, function(data) {
    		if(data && data.state == 1) {
    			var menus = data.datas;
    			
    			$("#menuTree").treeview({
    	            color: "#428bca",
    	            data: buildDomTree(menus),
    	            onNodeSelected: function(event, node) {
    	                $('#event_output').html(node.text);
    	              },
    	              onNodeUnselected: function (event, node) {
    	            	  $('#event_output').html('无');
    	              }
    	        });
    		}
    	})
    }
    
    /**
     * 获取选中的节点
     */
    function getSelectNode(){
    	var arr = $('#menuTree').treeview('getSelected');
    	if(arr.length > 0){
    		return arr[0].nodev;
    	}else{
    		return null;
    	}
    }
    
    //新增按钮事件
	$('#btn_add').click(function() {
		$("#modelTitle").html("新增菜单");
		$(".pmenuDiv").show();
		
		var node = getSelectNode();
		if(node == null){ //新增根节点
			$("#pid").val(0);
			$("#level").val(1);
			$("#pmenuName").val('无');
			$('#editModal').modal();
		}else{ //新增子节点
			if(node.level >= 3){ //达到最大层级，无法新建
				swal("最大只支持三级节点", "", "error");
			}else{
				$("#pid").val(node.id);
				$("#level").val(node.level+1);
				$("#pmenuName").val(node.menuName);
				$('#editModal').modal();
			}
		}
	});
	
	//编辑按钮事件
	$('#btn_update').click(function() {
		var node = getSelectNode();
		if(node == null){ 
			swal("请选择要编辑的节点", "", "warning");
		}else{ 
			$("#pid").val(node.pid);
			$("#level").val(node.level);
			$("#id").val(node.id);
			$("#menuName").val(node.menuName);
			$("#menuCode").val(node.menuCode);
			$("#menuIcon").val(node.menuIcon);
			$("#targetUrl").val(node.targetUrl);
			
			$(".pmenuDiv").hide();
			$("#modelTitle").html("编辑菜单");
			$('#editModal').modal();
		}
	});
	
	
	//删除按钮
	$('#btn_delete').click(function() {
		var node = getSelectNode();
		if(node != null){ 
			swal({
				title : "您确定要删除选中的节点及其子节点吗",
				text : "删除后将无法恢复，请谨慎操作！",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "删除",
				cancelButtonText : "取消",
				closeOnConfirm : true
			}, function() {
				$.post("/menu/deleteMenus", {
					id : node.id
				}, function(data) {
					if(data && data.state == 1) {
						//刷新列表
						initTreeview();
					}
				});
			});
		} else {
			swal("请选择要删除的节点", "", "warning");
		}
	});
	
	//保存按钮事件
	$('#saveBtn').click(function() {
		//点击保存时触发表单验证
		$('#editForm').bootstrapValidator('validate');
	    //如果表单验证正确，则请求后台保存用户
		if($("#editForm").data('bootstrapValidator').isValid()){
    	   $.post("/menu/saveMenu", $('#editForm').serialize(), function(data) {
    		   if(data && data.state == 1) { //后台返回添加成功
					//关闭弹窗
					$('#editModal').modal('hide');
					
					//刷新列表
					initTreeview();
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
			menuName:{
				validators:{
					notEmpty:{
						message:'菜单名称不能为空'
					}
				}
			}
		}
	});
   
});
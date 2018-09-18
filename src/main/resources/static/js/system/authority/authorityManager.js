/**
 * 权限管理
 */
$(function() {
    var treeview = null;
	
	initTreeview();
    
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
    			
    			initRole();
    		}
    	})
    }
    
    //初始化角色
    function initRole(){
    	$.get("/role/getAllRole", function(data){
    		if(data && data.state == 1) {
    			var roles = data.datas;
    			var htmlStr = "";
    			for(var i=0; i<roles.length; i++){
    				if(i == 0){
    					htmlStr = "<option value='"+roles[i].id+"' selected='selected'>"+roles[i].roleName+"</option>";
    				}else{
    					htmlStr += "<option value='"+roles[i].id+"'>"+roles[i].roleName+"</option>";
    				}
    			}
    			
    			$("#roleId").html(htmlStr);
    			
    			initChecked();
    		}
    	});
    }
    
    //初始化选中的菜单
    function initChecked(){
//    	$('#menuTree').treeview('uncheckAll', { silent: true });//先把全部选中取消掉  
    	$('#menuTree li').removeClass("node-checked");
    	$('#menuTree .check-icon').removeClass("glyphicon-check");
    	$('#menuTree .check-icon').removeClass("glyphicon-unchecked").addClass("glyphicon-unchecked");
    	var roleId = $("#roleId").val();
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
    
    
    
    $("#roleId").change(function() {
    	initChecked();
    });
    
	//保存按钮事件
	$('#saveBtn').click(function() {
//		var arr = $('#menuTree').treeview('getChecked');
		
		var menuIds = "";
		var nodesEle = $('#menuTree').find(".node-checked");
		for(var i=0; i<nodesEle.length; i++){
			if(menuIds == ""){
				menuIds = nodesEle.eq(i).attr("id");
			}else{
				menuIds += ","+nodesEle.eq(i).attr("id");
			}
		}

		var roleId = $("#roleId").val();
		$.post("/authority/saveAuthority", {'roleId': roleId, 'menuIds': menuIds}, function(data) {
		   if(data && data.state == 1) { //后台返回添加成功
			   swal("保存成功", "", "success");
			}
		})
	});
	
	
	
   
});



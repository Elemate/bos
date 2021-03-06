<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd(){
	   /* var data = $("#grid").datagrid("getSelected")
		$("#staffAddForm").form("load",data)*/
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
	    //获得所有被选中的取派员
        var rows = $("#grid").datagrid("getSelections");
        if (rows.length!=0){

            //进行删除前操作确认
            $.messager.confirm("删除确认","你确定要删除吗",function (v) {
				if (v){
				    //确认删除
                    //保存id
                    var arrays = new Array();
                    for (var i=0; i<rows.length;i++){
                        //获得的是json对象
						var data = rows[i];
                        arrays.push(data.id);
                    }
                    var ids = arrays.join(",");
                    location.href="${pageContext.request.contextPath}/staffAction_delete?ids="+ids;
				}
            })


        } else {
            //没有选中记录,弹出提示
            $.messager.alert("提示","请选中删除人员!","warning")
        }

	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		<shiro:hasPermission name="staff-delete">
        id : 'button-delete',
        text : '作废',
        iconCls : 'icon-cancel',
        handler : doDelete
		</shiro:hasPermission>
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [2,20,
			],
			pagination : true,
			toolbar : toolbar,
            url : "staffAction_pageQuery",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
        $('#addStaffWindow').window({
            title: '添加取派员',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
        });

        // 修改取派员窗口
        $('#editStaffWindow').window({
            title: '添加取派员',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
        });
	});

	function doDblClickRow(rowIndex, rowData){
	    //打开更新窗口
		$("#editStaffWindow").window("open");

		$("#staffeditForm").form("load",rowData);
	}



	//保存新增用户信息
	$(function () {
		$("#save").click(function () {
            var v = $("#staffAddForm").form("validate");
            if (v){
                $("#staffAddForm").submit();
            }
        })

      /*  //自定义验证手机号码
        $.extend($.fn.validatebox.defaults.rules, {

            telephone: {

                validator: function(value){

                    var reg = /^[1][3,5,7,8][0-9]{9}$/;
                    return reg.test(value);

                },

                message:"手机号码格式有误"

            }

        });*/
    });

</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">

	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="staffAddForm" action="staffAction_add.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input data-options="validType:'telephone'"  type="text" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>

	<divclass="easyui-window" title="对收派员进行修改" id="editStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="staffeditForm" action="staffAction_edit.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<script type="text/javascript">
                            $(function () {
                                $("#edit").click(function () {
                                    var v = $("#staffeditForm").form("validate");
                                    if(v){
                                        $("#staffeditForm").submit();
                                    }
                                });

                                //自定义验证手机号码
                                $.extend($.fn.validatebox.defaults.rules, {

                                    telephone: {

                                        	validator: function(value){
                                        	    debugger
                                            var reg = /^[1][3,5,7,8][0-9]{9}$/;
                                            return reg.test(value);
                                        },
									message: '手机号码格式有误'
                                    }
                                });
                            })
						</script>
						<td><input data-options="validType:'telephone'" type="text" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="haspda" value="1" />
							是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<%--<body>
<shiro:hasPermission name="staff-delete">
	<div style="width: 100px; height: 200px; background-color: red"></div>
</shiro:hasPermission>
</body>--%>
</html>	
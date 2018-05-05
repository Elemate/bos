<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	$(function(){

        function doEdit() {
            //获得选中的行
            var rows = $("#grid").datagrid("getSelections");

			var rowJson = rows[0];
			rowJson["parentFunction.id"] = rowJson.pId;
            if(rows.length == 1){
                $('#editFunctionWindow').window("open")
				console.log(rowJson)
                $("#editFunctionWindow").form("load",rowJson);
			}
        }

        //双击修改
		function  dbClickRow(rowIndex, rowData) {
			$("#editFunctionWindow").window("open");
            rowData["parentFunction.id"] = rowData.pId;
			$("#editFunctionWindow").form("load",rowData);
        }

        // 编辑权限窗口
        $('#editFunctionWindow').window({
            title: '添加取派员',
            width: 550,
            modal: true,
            shadow: true,
            closed: true,
            height: 500,
            resizable:false
        });

		$("#grid").datagrid({
			toolbar : [
				{
					id : 'add',
					text : '添加权限',
					iconCls : 'icon-add',
					handler : function(){
						location.href='${pageContext.request.contextPath}/page_admin_function_add.action';
					}
				} ,
				{
				    id : "edit",
					text: "编辑",
					iconCls: 'icon-edit',
					handler: doEdit
				}
			],
			url : 'functionAction_pageQuery',
			pagination:true,
			fit:true,
            onDblClickRow:dbClickRow,
			columns : [[
			  {
				  field : 'id',
				  title : '编号',
				  width : 200,
				  checkbox:true
			  },
			  {
				  field : 'name',
				  title : '名称',
				  width : 200
			  },  
			  {
				  field : 'description',
				  title : '描述',
				  width : 200
			  },  
			  {
				  field : 'generatemenu',
				  title : '是否生成菜单',
				  width : 200,
                  formatter : function(data,row, index){
                      if(data=="0"){
                          return "否"
                      }else{
                          return "是";
                      }
                  }
			  },  
			  {
				  field : 'zindex',
				  title : '优先级',
				  width : 200
			  },  
			  {
				  field : 'page',
				  title : '路径',
				  width : 200
			  }
			]]
		});
	});
</script>	
</head>
<body class="easyui-layout">
<div data-options="region:'center'">
	<table id="grid"></table>
</div>
<div id="editFunctionWindow">
	<form id="editFunctionForm" method="post" action="functionAction_edit">
		<table class="table-edit" width="90%" align="center">
			<tr class="title">
				<td colspan="2">功能权限信息</td>
			</tr>
			<tr>
				<td width="100">关键字</td>
				<td>
					<input type="text" name="code" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td>名称</td>
				<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<td>访问路径</td>
				<td><input type="text" name="page"  /></td>
			</tr>
			<tr>
				<td>是否生成菜单</td>
				<td>
					<select name="generatemenu" class="easyui-combobox">
						<option value="0">不生成</option>
						<option value="1">生成</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>优先级</td>
				<td>
					<input type="text" name="zindex" class="easyui-numberbox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td>父功能点</td>
				<td>
					<input name="parentFunction.id" class="easyui-combotree" data-options="url:'functionAction_listAjax'"/>
				</td>
			</tr>
			<tr>
				<td>描述</td>
				<td>
					<textarea name="description" rows="4" cols="50"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
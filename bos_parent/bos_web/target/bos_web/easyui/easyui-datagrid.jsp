<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>布局页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <%--方式一,将静态html渲染成为datagrid样式--%>
    <%--<table class="easyui-datagrid">
        <thead>
            <tr>
                <th data-options="field:'id'">编号</th>
                <th data-options="field:'name'">姓名</th>
                <th data-options="field:'age'">年龄</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>01</td>
                <td>素贞</td>
                <td>22</td>
            </tr>
        </tbody>
    </table>--%>

    <%--方式二,发送ajax请求获取json数据创建datagrid--%>
    <%-- <table data-options="url:'${pageContext.request.contextPath}/json/datagrid_data.json'" class="easyui-datagrid">
         <thead>
         <tr>
             <th data-options="field:'id'">编号</th>
             <th data-options="field:'name'">姓名</th>
             <th data-options="field:'age'">年龄</th>
         </tr>
         </thead>
     </table>--%>

    <%--使用easyui提供的api创建--%>
    <script type="text/javascript">
        $(function () {
            $("#mytable").datagrid({
                url:'${pageContext.request.contextPath}/json/datagrid_data.json',
                columns:[[
                    {field:"id", title:"编号",checkbox:true, width:200},
                    {field:"name", title:"姓名", width:100},
                    {field:"age", title:"年龄", width:100},
                ]],
                rownumbers:true,
                singleSelect:true,
                pagination:true,
                toolbar:[
                    {text:"增加",iconCls:"icon-add",
                        handler:function () {
                            alert(123)
                        }},
                    {text:"删除",iconCls:"icon-delete"},
                    {text:"修改",iconCls:"icon-edit"},
                    {text:"查询",iconCls:"icon-search"}
                ]

            });

            $("#btn").click(function () {
                $("#mytable").datagrid("load",{
                    id:"1",
                    name:"1333"
                })
            })
        })
    </script>
</head>
    <body class="easyui-layout" style="visibility:hidden;">
        <div region="center">
            <table id="mytable" style="display: block"></table>
        </div>


    </body>
</html>

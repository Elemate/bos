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

    <%--使用easyui提供的api创建--%>
    <script type="text/javascript">



        $(function () {

            var index = -1;     //定义一个全局当前编辑行数
            $("#mytable").datagrid({
                url:'${pageContext.request.contextPath}/json/datagrid_data.json',
                columns:[[
                    {field:"id", title:"编号",checkbox:true, width:200},
                    {field:"name", title:"姓名", width:100, editor:{
                            type:'numberbox',
                            option:{}
                        }},

                    {field:"age", title:"年龄", width:100}
                ]],
                rownumbers:true,
                singleSelect:true,
                pagination:true,
                toolbar:[
                    {text:"增加",iconCls:"icon-add",
                        handler:function () {
                            $("#mytable").datagrid("insertRow",{
                                index:0,
                                row:{}
                            })
                            $("#mytable").datagrid("beginEdit",0)
                            index = 0;
                        }},
                    {text:"删除",iconCls:"icon-delete",
                        handler:function () {
                            var rows = $("#mytable").datagrid("getSelections");
                            if (rows.length ==1){
                                index = $("#mytable").datagrid("getRowIndex", rows[0]);
                            }

                            $("#mytable").datagrid("deleteRow",index);
                        }
                    },
                    {text:"修改",iconCls:"icon-edit",
                        handler:function(){

                            var rows = $("#mytable").datagrid("getSelections");
                            if (rows.length ==1){
                                index = $("#mytable").datagrid("getRowIndex", rows[0]);
                            }
                            $("#mytable").datagrid("beginEdit",index);
                        }
                        },
                    {text:"保存",iconCls:"icon-save",
                        handler:function () {

                            var rows = $("#mytable").datagrid("getSelections");
                            if (rows.length ==1){
                                index = $("#mytable").datagrid("getRowIndex", rows[0]);
                            }

                            $("#mytable").datagrid("endEdit",index);
                        }
                    }
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
    <body class="easyui-layout">
        <div region="center">
            <table id="mytable" style="display: block"></table>
        </div>

    </body>
</html>

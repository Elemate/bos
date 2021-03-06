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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>

</head>
<body class="easyui-layout">


    <div data-options="region:'north',title:'North Title'" style="height:100px;"></div>

    <div data-options="region:'south',title:'South Title',split:true" style="height:50px;"></div>

    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>

    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
        <div class="easyui-accordion" data-options="fit:true">
            <div data-options="iconCls:'icon-cut'" title="面板一">
                <a id="btn" class="easyui-linkbutton">添加一个选项版</a>
                <script type="text/javascript">
                    /* 加载完页面然后调用这个方法*/
                  /*  $(function () {
                        /!*从面板中动态添加选项卡*!/
                        $("#btn").click(function () {
                            //查看当前选项卡是否打开
                            var e = $("#tabs").tabs("exists","new tab");
                            if(e){
                                //点击重新回到选中当前选项卡
                                $('#tabs').tabs('select','new tab');

                            } else {
                                $('#tabs').tabs('add',
                                    {
                                        title: 'new tab'
                                    });
                            }
                        })

                    });*/
                </script>
            </div>

            <div data-options="iconCls:'icon-cut'" title="面板二">
                <%--展示ztree效果,使用标准json数据构造ztree--%>
                <ul id="ztree1" class="ztree"></ul>
                <script type="text/javascript">
                    /*  var setting = {

                     };

                    var zTreeNodes = [
                         {"name":"节点一","children":[
                                 {"name":"子节点一"},
                                 {"name":"子节点二"},
                                 {"name":"子节点三"}
                             ]},
                         {"name":"节点二"},
                         {"name":"节点三"}
                         ];
                     $(function () {
                         $.fn.zTree.init($("#ztree1"), setting, zTreeNodes);
                     });*/

                </script>
            </div>

            <div data-options="iconCls:'icon-cut'" title="面板三">
                <%--展示ztree效果,使用简单json数据构造ztree--%>
                <ul id="ztree2" class="ztree"></ul>
                <script type="text/javascript">
                  /*  var setting = {
                        data: {
                            simpleData: {
                                enable: true,
                                idKey: "id",
                                pIdKey: "pId",
                                rootPId: 0
                            }
                        }
                    };

                    var zTreeNodes = [
                        {"id":1, "pId":0, "name":"节点一"},
                        {"id":11, "pId":1, "name":"节点二"},
                        {"id":12, "pId":1, "name":"节点三"},
                        {"id":111, "pId":11, "name":"

                        88节点四"}
                    ];
                    $(function () {
                        $.fn.zTree.init($("#ztree2"), setting, zTreeNodes);
                    });*/
                </script>
            </div>

            <div data-options="" title="面板四">
                <ul id="ztree3" class="ztree"></ul>
                <script type="text/javascript">

                    /*使用ajax获得json数据*/
                      $(function () {
                        var setting3 = {
                            data: {
                                simpleData: {
                                    enable: true,   //使用简单json数据构造zTree节点
                                    idKey: "id",
                                    pIdKey: "pId",
                                    rootPId: 0
                                }
                            },

                            callback: {
                                onClick: function zTreeOnClick(event, treeId, treeNode) {
                                   if(treeNode.pId!=0) {
                                       //判断选项卡是否存在
                                       var exists = $("#tabs").tabs("exists", treeNode.name)

                                       if (exists) {
                                           $("#tabs").tabs("select", treeNode.name);
                                       } else {
                                           $("#tabs").tabs("add", {
                                               title: treeNode.name,
                                               closable: true,
                                               content: '<iframe frameborder="0" width="100%" height="100%" src="${pageContext.request.contextPath}/"+treeNode.page></iframe>'
                                           })
                                       }
                                   }
                                }

                            }

                        };

                        $.post(
                            "${pageContext.request.contextPath}/json/menu.json",
                            {},
                            function (data) {

                                $.fn.zTree.init($("#ztree3"), setting3, data);

                            },
                            "json"
                        );
                    });


                </script>
            </div>
        </div>
    </div>

    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
        <div id="tabs" class="easyui-tabs" data-options="fit:true">
            <div data-options="iconCls:'icon-cut',closable:true" title="选项卡一">11111</div>
            <div data-options="iconCls:'icon-cut',closable:true" title="选项卡二">2222</div>
            <div data-options="iconCls:'icon-cut',closable:true" title="选项卡三">3333</div>
        </div>

    </div>



</body>
</html>

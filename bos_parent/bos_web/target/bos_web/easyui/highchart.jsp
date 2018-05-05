<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>布局页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/highchart/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/highchart/modules/exporting.js"></script>
    
    <script type="text/javascript">
      $(function () {
          $('#test').highcharts({
              chart: {
                  plotBackgroundColor: null,
                  plotBorderWidth: null,
                  plotShadow: false
              },
              title: {
                  text: '2014 某网站各浏览器浏览量占比'
              },
              tooltip: {
                  headerFormat: '{series.name}<br>',
                  pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
              },
              plotOptions: {
                  pie: {
                      allowPointSelect: true,
                      cursor: 'pointer',
                      dataLabels: {
                          enabled: true,
                          format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                          style: {
                              color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                          }
                      }
                  }
              },
              series: [{
                  type: 'pie',
                  name: '浏览器访问量占比',
                  data: [
                      ['Firefox',   45.0],
                      ['IE',       26.8],
                      {
                          name: 'Chrome',
                          y: 12.8,
                          sliced: true,
                          selected: true
                      },
                      ['Safari',    8.5],
                      ['Opera',     6.2],
                      ['其他',   0.7]
                  ]
              }]
          });
      })

    </script>
</head>
<body>
    <div id="test" style="min-width:400px;height:400px">
    </div>
</body>
</html>
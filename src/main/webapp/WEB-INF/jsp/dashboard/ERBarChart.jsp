
<html>
  <head>
    <title>Histogram</title>
            <link rel="stylesheet" href="css/jqplot/jquery.jqplot.min.css" type="text/css" />
            <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
            <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
            <!--[if lt IE 9]><script language="javascript" type="text/javascript" src="javascript/jqplot/excanvas.min.js"></script><![endif]-->
            <script language="javascript" type="text/javascript" src="javascript/jqplot/jquery.min.js"></script>
            <script language="javascript" type="text/javascript" src="javascript/jqplot/jquery.jqplot.min.js"></script>
            <script language="javascript" type="text/javascript" src="javascript/jqplot/excanvas.min.js"></script>
            <script type="text/javascript" src="javascript/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
            <script language="javascript" type="text/javascript" src="javascript/jqplot/plugins/jqplot.barRenderer.min.js"></script>
            <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
            <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
            <script type="text/javascript"> 
	 function drawBC(){   
                    var s1 = [0,0,0,0,20,36,17, 5, 2,1];
                    var ticks = ['10', '20', '30', '40','50','60','70','80','90','100'];
                    
                    $.jqplot('chart1', [s1], {
                        // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
                        animate: false,
                                                animateReplot: true,
                        seriesDefaults:{
                            renderer:$.jqplot.BarRenderer,
                            pointLabels: { show: true }
                        },
                        axes: {
                            xaxis: {
                                renderer: $.jqplot.CategoryAxisRenderer,
                                ticks: ticks
                            }
                        },
                        highlighter: { show: false }
                    });
                    
                     $('#chart1').bind('jqplotDataClick', 
                        function (ev, seriesIndex, pointIndex, data) {
                            $('#info1').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
                        }
                    );
                } 
        </script>
   </head>
   <body onload="drawBC()">
      <jsp:include page="../common/Header.jsp"></jsp:include> 
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        
   <div id="chart1" style="margin-top: 12%; margin-left: 20%;width: 50%; height: 60%; position: relative;"></div>
    <div style="color:black"><span>Moused Over: </span><span id="info1">Nothing</span></div>
   </body>
</html>
	
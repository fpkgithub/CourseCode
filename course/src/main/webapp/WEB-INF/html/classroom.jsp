<!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MOVE</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/superfish.css" rel="stylesheet" type="text/css" />

<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

 <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />

<!-- ICONS -->
    <link rel="shortcut icon" href="img/favicon.png"/>

 <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>


<script type="text/javascript">
    
    $(function(){

        $("#show").on("click",function(){

            var obj={type:"POST",url:"http://localhost:3000/1"};

            $.ajax(obj).done(function(res){
                alert(1);
                createTable(res);
            });
            
            
        });
        
        function createTable(res){

            var arrayweek = new Array();
            arrayweek[0]="星期一"
            arrayweek[1]="星期二"
            arrayweek[2]="星期三"
            arrayweek[3]="星期四"
            arrayweek[4]="星期五"
            

            for(var i=0;i<res.length;i++){
                var course=res[i];
                $("<tr>").append($("<td>").text(arrayweek[i]))
                .append($("<td>").text(course.cone))
                .append($("<td>").text(course.ctwo))
                .append($("<td>").text(course.cthree))
                .append($("<td>").text(course.cfour))
                .append($("<td>").text(course.cfive))
                .appendTo($("#List"));
            }
        }
        
    });
    
    

</script>

</head>

<body class="bg6">
    <header class="header_wrapper"> <!--header_wrapper Start-->
  <div class="header"> <!--header Start-->
  <div class="logo_style1">
            <img src="img/logo.gif" alt="" />
        </div>
        <div class="logo_style2">
            <img src="assets/images/site/h_banner_sys.gif" alt="" />
        </div>
        <div class="navigation fltright"> <!--navigation Start-->
        <ul class="sf-menu">
        <li><a href="index.html">Home</a></li>
        <li><a href="teacher.html">教师课表</a></li>
        <li> <a href="class.html">班级课表</a></li>
        <li><a href="classroom.html">教室课表</a></li>
        <li><a href="random.html">任选课表</a></li>
      </ul>
        </div> <!--navigation End-->
        

    </div> <!--header End-->        
</header> <!--header_wrapper End-->

    <section class="container_middle"> <!--container_top Start-->
        <Section class="container_top"> <!--container_bottom Start-->
            <section class="container_bottom"> <!--container_middle Start-->
                <section class="cotent_bolck1"> <!--cotent_bolck1 Start-->
                    
                        
                        
                            <form action="#" method="post">
                               
                                        
                                        <ul >
                                            <li style="margin-left: 30px; list-style: none;float: left;">
                                                 学年学期&nbsp;&nbsp;
                                                 <select name='Sel_XNXQ'  style="WIDTH:180px;height: 30px;">
                                                 <option value='20161'>2016-2017学年第二学期</option><option value='20160'>2016-2017学年第一学期</option><option value='20151'>2015-2016学年第二学期</option><option value='20150'>2015-2016学年第一学期</option><option value='20141'>2014-2015学年第二学期</option><option value='20140'>2013   学年第一学期</option><option value='20131'>2013-2014学年第二学期</option><option value='20130'>2013-2014学年第一学期</option><option value='20121'>2012-2013学年第二学期</option><option value='20120'>2012-2013学年第一学期</option><option value='20111'>2011-2012学年第二学期</option><option value='20050'>2005-2006学年第一学期</option></select>
                                            </li>
                                            <li style=" margin-left: 30px; list-style: none;float: left;">
                                            教学楼&nbsp;&nbsp;
                                            <select name='Sel_XNXQ'  style="WIDTH:180px;height: 30px;">
                                                <option value='20161'>八号楼</option> </select>
                                            </li> 
                                            <li style=" margin-left: 30px; list-style: none;float: left;">
                                                校区&nbsp;&nbsp;
                                                 <select name='Sel_XNXQ'  style="WIDTH:180px;height: 30px;">
                                                 <option value='20161'>北校区</option></select></li>
                                            <li style=" margin-left: 30px; list-style: none;float: left;">
                                            教学楼&nbsp;&nbsp;<select name='Sel_XNXQ'  style="WIDTH:180px;height: 30px;">
                                                <option value='20161'>八号楼</option>
                                            </li> 
                                   
                                            <li style=" margin-left: 3px; list-style: none;float: left;">
                                            <input type="radio" name="type" value="">格式一
                                                &nbsp;&nbsp;
                                               
                                                <input type="radio" name="type" value="">&nbsp;格式二
                                            </li>
                                            <li style="margin-left: 30px; list-style: none;float: left;">
                                                验证码&ensp;<input type="text" name="txt_yzm" id="txt_yzm" maxlength="4" style="width:40px">
                                    <img id="imgCode" src="img/logo.gif" onclick="changeValidateCode(this)" alt="单击可更换图片！" style="CURSOR: pointer;width:70px;height:24px">

                                            </li>

                                            <li style="width: 5px; margin-left: 30px; list-style: none;float: left;">
                                                <input id="show" name="检索" style="width: 50px;" value="检索" type="button"/>
                                            </li>

                                            </ul>
                                        
                                           
                                           
                               
                                 <!--form_botton_box Start-->
                                    
                               

                            </form>
                        
                    
                    <!--block_left End-->
                    

                   
                          
                                <table class="table table-hover  thead_style table-striped" id="List">
                                    <thead>
                                        <tr>
                                        <th style="width: 150px;">
                                        <h3>课程表</h3>
                                        </th>
                                        </tr>

                                        <tr>
                                        <th></th>
                                        <th colspan="2">上午</th>
                                        <th colspan="2">下午</th>
                                        <th>晚上</th>
                                       
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th>一</th>
                                        <th>二</th>
                                        <th>三</th>
                                        <th>四</th>
                                        <th>五</th>
                                       
                                    </tr>
                                    </thead>  
                                      <tr>
                                        <th>星期一</th>
                                        <th>一</th>
                                        <th>二</th>
                                        <th>三</th>
                                        <th>四</th>
                                        <th>五</th>
                                       
                                    </tr>

                                    

                                </table>
                           

                    <div class="clear"></div>
              </section> <!--cotent_bolck1 End-->
            </section> <!--container_middle End-->      
        </section> <!--container_bottom End-->
    </section> <!--container_top End-->
    
    </section> <!--container_top End-->
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>教室课表</title>
<%@include file="common/head.jsp"%>

<script type="text/javascript">
	//下拉列表选择的课程
	var semester_select;
	var campus_select;
	var building_select;
	var room_select;
	function selected() {
		semester_select = $('#semester_select option:selected').val();
		campus_select = $('#campus_select option:selected').val();
		building_select = $('#building_select option:selected').val();
		room_select = $('#room_select option:selected').val();
	}
	
	//获取教学楼
	function selectedBuilding() {
		campus_select = $('#campus_select option:selected').val();
		var obj = {
				type : "GET",
				url : "/room/BuildingInfo" + "/" 
						+ campus_select 
			};
		
		$.ajax(obj).done(function(res) {
			if(res.length == 0){
				alert("此校区没有教学楼");
				return;
			}
			else{
				
				$('#building_select').empty();
				$('#room_select').empty();
				var Building = res['Building'];
				for(var i = 0; i < Building.length; i++ ){
					var bu = Building[i];
				 	$("#building_select").append('<option value='+bu['bnum']+'>'+bu['bname']+'</option>')
				}
			}
			
		});
		
	}
	
	//获取教室
	function selectedClassRoom() {
		building_select = $('#building_select option:selected').val();
		var obj2 = {
				type : "GET",
				url : "/room/Classroom" + "/" 
						+ building_select 
			};
		$.ajax(obj2).done(function(res) {
			$('#room_select').empty();
			if(res.length == 0){
				alert("此教学楼没有教室");
				return;
			}
			else{
			
				var Classroom = res['Classroom'];
				
				for(var i = 0; i < Classroom.length; i++ ){
					var cl = Classroom[i];
				 	$("#room_select").append('<option value='+cl['crnum']+'>'+cl['crname']+'</option>')
				}
			}
			
		});
	}

	//获取教室课表信息
	function getCourse() {
		var code = $('#code').val();
		if( campus_select == null ){
			alert("请选择校区");
			return;
		}	
		
		if( room_select == null ){
			alert("请选择教室");
			return;
		}
		if(code==null){
			alert("请填写验证码");
			return;
		}

		var obj = {
			type : "POST",
			url : "/room/ClassCourse" + "/" + semester_select + "/"
					+ campus_select + "/" + building_select +"/"+ room_select + "/"+code
		};

		$.ajax(obj).done(function(res) {
			if (res.length == 0) {
				$('#List').empty();
				alert("此教室没有课程/验证码错误！");
			} else {
				//情况table数据
				$('#List').empty();
				createTable(res);
			}
		});

	}

	function createTable(res) {

		var arrayweek = new Array();
		arrayweek[0] = "星期一"
		arrayweek[1] = "星期二"
		arrayweek[2] = "星期三"
		arrayweek[3] = "星期四"
		arrayweek[4] = "星期五"
		var TeacherCourse = res['Classroom'];

		for (var i = 0; i < TeacherCourse.length; i++) {

			var course = TeacherCourse[i];

			$("<tr>").append($("<td>").text(arrayweek[i])).append(
					$("<td  width='20%'>").text(course['ccone'])).append(
					$("<td  width='20%' >").text(course['cctwo'])).append(
					$("<td width='20%'>").text(course['ccthree'])).append(
					$("<td width='20%'>").text(course['ccfour'])).append(
					$("<td width='20%'>").text(course['ccfive'])).appendTo($("#List"));
		}
	}
</script>

</head>
<body>
	<!-- 页面显示不放呢 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>教室课表</h2>
			</div>

			<div class="panel-body">
				<table class="table table-hover">
					<tbody>
						<tr style="height: 70sp">
							<td>学年学期</td>
							<td><select id="semester_select" onchange="selected()"
								style="WIDTH: 200px; height: 30px;">
									<c:forEach items="${listSemester}" var="ls">
										<option value='${ls.sid}'>${ls.sname}</option>
									</c:forEach>
							</select></td>
							<td>校区：</td>
							<td><select id="campus_select"
								style="WIDTH: 180px; height: 30px;" onchange="selectedBuilding()">
									<option value=''>请选择校区</option>
									<c:forEach items="${listCampus}" var="tea">
										<option value='${tea.canum}'>${tea.caname}</option>
									</c:forEach>
							</select></td>
							<td>教学楼：</td>
							<td><select id="building_select"
								style="WIDTH: 180px; height: 30px;" onchange="selectedClassRoom()">
									<option value="">请选择教学楼</option> 
									
							</select></td>
						</tr>
						<tr style="height: 70sp">
							<td>教室：</td>
							<td><select id="room_select"
								style="WIDTH: 180px; height: 30px;" onchange="selected()">
										<option value=''>请选择教室</option>
							</select></td>
							<td><input type="radio" checked="true" name="type" value="">格式一</td>
							<td><input type="radio" name="type" value="">&nbsp;格式二
							</td>
							<td>验证码：</td>
							<td><input id="code" type="text" name="txt_yzm" id="txt_yzm"
								maxlength="4" style="width: 100px"> <img id="imgCode"
								src="/room/getRoomImage" onclick="changeValidateCode(this)"
								alt="单击可更换图片！"
								style="CURSOR: pointer; width: 100px; height: 24px"></td>
							<td><input id="show" name="检索" style="width: 180px;"
								value="检索" type="button" onclick="getCourse()" /></td>

						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-body ">

				<table class="table table-hover  thead_style table-striped"
					style="text-align: center;">
					<tr style="width: 100%; text-align: center;">
						<th style="width: 5%;">星期</th>
						<th style="width: 19%; text-align: center;">一/二</th>
						<th style="width: 19%; text-align: center;">三/四</th>
						<th style="width: 19%; text-align: center;">五/六</th>
						<th style="width: 19%; text-align: center;">七/八</th>
						<th style="width: 19%; text-align: center;">九/十</th>
					</tr>

				</table>

				<table id="List"
					class="table table-hover  thead_style table-striped">
					<tr width=100%>
						<th width=100%></th>
						<th width=100%></th>
						<th width=100%></th>
						<th width=100%></th>
						<th width=100%></th>
					</tr>

				</table>
			</div>

		</div>
	</div>
</body>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
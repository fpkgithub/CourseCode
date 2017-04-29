<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>任选课表</title>
<%@include file="common/head.jsp"%>

<script type="text/javascript">
	//下拉列表选择的课程
	var semester_select;
	var campus_select;
	function selected() {
		semester_select = $('#semester_select option:selected').val();
		campus_select = $('#campus_select option:selected').val();
	}

	//获取教师信息
	function getCourse() {
		var code = $('#code').val();
		if (semester_select == null && campus_select == null) {
			alert("请选择学期和校区");
			return;
		}

		var obj = {
			type : "POST",
			url : "/any/anyCourse" + "/" + semester_select + "/"
					+ campus_select + "/" + code
		};


		
		//alert("url:"+obj.url);
		$.ajax(obj).done(function(res) {
			if (res.length == 0) {
				alert("此校区没有课程/验证码错误！");
			} else {
				//alert("123");
				//情况table数据
				$('#List').empty();
				createTable(res);
			}
		});

	}

	function createTable(res) {

		if(res.length == 0){
			alert("此校区没有课程");
			return;
		}
		else{
		
		var CampusCourse = res['AnyCourse'];
		
		for (var i = 0; i < CampusCourse.length; i++) {

			var course = CampusCourse[i];

			$("<tr>").append(
					$("<td  width='5%'>").text(i+1)).append(
					$("<td  width='9.5%' >").text(course['coursename'])).append(
					$("<td width='9.5%'>").text(course['score'])).append(
					$("<td width='9.5%'>").text(course['cteacher'])).append(
					$("<td width='9.5%'>").text(course['cno'])).append(
					$("<td width='9.5%'>").text(course['cnum'])).append(
					$("<td width='9.5%'>").text(course['cweek'])).append(
					$("<td width='9.5%'>").text(course['ctime'])).append(
					$("<td width='9.5%'>").text(course['caddress']))
					.appendTo($("#List"));
		}
		}
	}
</script>

</head>
<body>
	<!-- 页面显示不放呢 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>任选课表</h2>
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
							<td> &nbsp;&nbsp;</td>
							<td></td>
							
							<td>
							校区：
							<select id="campus_select"
								style="WIDTH: 180px; height: 30px;" onchange="selected()">
									<option value=''>请选择校区</option>
									<c:forEach items="${listCampus}" var="tea">
										<option value='${tea.canum }'>${tea.caname}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr style="height: 70sp">
							<td><input type="radio" checked="true" name="type" value="">格式一</td>
							<td><input type="radio" name="type" value="">&nbsp;格式二
							</td>
							<td>验证码：</td>
							<td><input id="code" type="text" name="txt_yzm" id="txt_yzm"
								maxlength="4" style="width: 100px"> <img id="imgCode"
								src="/any/getAnyImgage" onclick="changeValidateCode(this)"
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
						<th style="width: 5%;">序号</th>
						<th style="width: 9.5%; text-align: center;">课程</th>
						<th style="width: 9.5%; text-align: center;">学分</th>
						<th style="width: 9.5%; text-align: center;">任课教师</th>
						<th style="width: 9.5%; text-align: center;">上课班号</th>
						<th style="width: 9.5%; text-align: center;">上课人数</th>
						<th style="width: 9.5%; text-align: center;">周次</th>
						<th style="width: 9.5%; text-align: center;">时间</th>
						<th style="width: 9.5%; text-align: center;">上课地点</th>
					</tr>

				</table>

				<table id="List"
					class="table table-hover  thead_style table-striped">
				

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
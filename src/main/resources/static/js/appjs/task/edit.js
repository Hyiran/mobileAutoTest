var taskId;
$().ready(function() {
	$("#part1").show();
	$("#part2").hide();
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/task/update",
		data : new FormData($('#signupForm')[0]),
		processData : false,
		contentType : false,
		async : false,
		beforeSend : function() {
			uploading = true;
		},
		error : function(request) {
			alert("修改失败，请联系管理员！");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg);
			}
		}
	});

}
$(validateRule());
function openDept(){
	layer.open({
		type : 2,
		title : "选择部门",
		closeBtn : 0,
		area : [ '300px', '450px' ],
		content : "/task/showDepts"
	})
}
function loadDept(deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
	$.ajax({
		type : "post",
		url : "/task/getSvnRepoPath",// 获得svn库的树形结构路径
		data : {
			'deptId' : deptId
		},
		success : function(data) {
			if(data.code != 0){
				parent.layer.alert(data.msg);
			} else {
				$("#svnPath").val(data.msg);
			}
		}
	});
}
function toStep12() {
	var taskName=$("#taskName").val();
	if(taskName !== null && taskName !== undefined && taskName !== '') {
		$("#part1").hide();
		$("#part2").show();
	} else {
		parent.layer.msg("测试任务名称不能为空！");
	}
}
function toStep21() {
	$("#part1").show();
	$("#part2").hide();
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	return $("#signupForm").validate({
		rules : {
			taskName : {
				required : true
			},
			deptName : {
				required : true
			},
			svnPath : {
				required : true
			},
			batchName : {
				required : true
			},
			batchSvnPath : {
				required : true
			},
			agree : "required"
		},
		messages : {
			taskName : {
				required : icon + "请输入测试任务名称"
			},
			deptName : {
				required : icon + "请选择所属产品机构"
			},
			svnPath : {
				required : icon + "请输入测试任务的svn路径"
			},
			batchName : {
				required : icon + "请选择所属批次"
			},
			batchSvnPath : {
				required : icon + "请输入SVN批次分支名称"
			},
		}
	})
}
var openBatch = function(){
	layer.open({
		type : 2,
		title : "选择批次",
		closeBtn : 0,
		area : [ '300px', '450px' ],
		content:"/sys/batch/showBatch"
	});
};
function loadBatch(batchId,batchName){
	$("#batchId").val(batchId);
	var deptId = $("#deptId").val();
	$("#batchName").val(batchName);
	$.ajax({
		type : "post",
		url : "/task/getBatchSvnPath",// 获得svn库的树形结构路径
		data : {
			'batchId' : batchId,
			'deptId' : deptId
		},
		success : function(data) {
			if(data.code != 0){
				parent.layer.alert(data.msg);
			} else {
				$("#batchSvnPath").val(data.msg);
			}
		}
	});
}
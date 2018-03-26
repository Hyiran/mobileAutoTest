$(document).ready(function() {
	$("#part1").show();
	$("#part2").hide();
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		url : "/task/save",
		type : "POST",
		cache : false,
		data : new FormData($('#formId')[0]),
		processData : false,
		contentType : false,
		dataType : "json",
		beforeSend : function() {
			uploading = true;
		},
		success : function (data) {
			if(data.code == 0) {
				parent.layer.msg("操作成功");
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
				parent.reLoad();
			} else {
				parent.layer.alert(data.msg);
			}
		},
		error : function (e) {
			parent.layer.alert("请求失败！");
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
function openDept(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/task/showDepts"
	})
}
function loadDept( deptId,deptName){
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
var openBatch = function(){
	layer.open({
		type:2,
		title:"选择批次",
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
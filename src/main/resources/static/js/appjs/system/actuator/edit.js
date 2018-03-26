// 以下为官方示例
$().ready(function() {
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
		url : "/actuator/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			ip : {
				required : true
			},
			deviceUri : {
				required : true
			},
			activeUri : {
				required : true
			},
			agree : "required"
		},
		messages : {

			ip : {
				required : icon + "请输入ip地址"
			},
			deviceUri : {
				required : icon + "请输入执行机获取执行设备接口"
			},
			activeUri : {
				required : icon + "请输入执行机执行接口"
			},
		}
	})
}
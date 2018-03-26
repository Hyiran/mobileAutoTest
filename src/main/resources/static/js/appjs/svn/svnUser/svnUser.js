var prefix = "/sys/user";
var prefixSvnUser = "/svn/svnUser";

$(function() {
	load();
});

function load() {
	$('#svnUserTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/list", // 服务器数据的加载地址
				showRefresh : true,
				showToggle : true,
				// showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				// search : true, // 是否显示搜索框
				showColumns : true, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						name : $('#searchName').val(),
						username : $('#searchUserName').val()
					};
				},
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				columns : [
/*					{
						checkbox : true
					},*/
					{
						field : 'userId', // 列字段名
						title : '序号', // 列标题
						align : 'center'
					},
					{
						field : 'name',
						title : '姓名',
						align : 'center'
					},
					{
						field : 'username',
						title : '用户名',
						align : 'center'
					},
					{
						field : 'status',
						title : '状态',
						align : 'center',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<span class="label label-danger">禁用</span>';
							} else if (value == '1') {
								return '<span class="label label-primary">正常</span>';
							}else if (value == '2') {
								return '<span class="label label-warning">待审批</span>';
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_add_h + '" href="#" title="添加SVN用户" mce_href="#" onclick="add(\''
								+ row.userId
								+ '\')"><i class="fa fa-user-plus "></i></a> ';			
							var d = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="重置SVN密码"  mce_href="#" onclick="resetPwd(\''
								+ row.userId
								+ '\')"><i class="fa fa-key"></i></a> ';
							var f = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除SVN用户" mce_href="#" onclick="remove(\''
								+ row.userId
								+ '\')"><i class="fa fa-remove"></i></a> ';
							return e + d + f;
						}
					} ]
			});
}

function reLoad() {
	$('#svnUserTable').bootstrapTable('refresh');
}


function query() {
	// iframe层
	var page = layer.open({
		type : 2,
		title : '查询SVN用户信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '410px' ],
		content : prefixSvnUser + '/querySVN'
	});
	layer.full(page); //默认全屏展示
}

function add(id) {
	layer.open({
		type : 2,
		title : '添加SVN用户',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/addSvnUser/' + id // iframe的url
	});
}

function remove(id) {
	layer.open({
		type : 2,
		title : '删除SVN用户',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/delSvnUser/' + id // iframe的url
	});
}

function resetPwd(id) {
	layer.open({
		type : 2,
		title : '重置SVN密码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/resetSvnUserPwd/' + id // iframe的url
	});
}


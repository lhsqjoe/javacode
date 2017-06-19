/*
 * 2012-4-10 by shaka
 */
(function($){
	/*拼接多选框组中被选中框的值，以逗号分割*/
	$.fn.connectValue=function(){
		var result = "";
		$(this).each(function(i) {
			if ($(this).prop("checked")) {
				result += $(this).val() + ",";
			}
		});
		if (result.length > 1) {
			result = result.substring(0, result.length - 1);
		}
		return result;
	}
	
	/*复选框全选。全选/不选checkbox调用方法，控制的数据项checkbox作为参数*/
	$.fn.checkAll=function(checkebox_item){
		var checkebox_checkAll = $(this);
		//全选或全不选
		checkebox_checkAll.click(function(){
			var checkFlag = $(this).prop("checked");
			checkebox_item.each(function(){
				$(this).prop("checked",checkFlag);
			});
		});
		//如果全部选中勾上全选框，全部选中状态时取消了其中一个则取消全选框的选中状态
		checkebox_item.each(function(){
			$(this).click(function(){
				if(checkebox_item.filter(":checked").length == checkebox_item.length){
					checkebox_checkAll.prop("checked",true);
				}else{
					checkebox_checkAll.prop("checked",false);
				}
			});
		});
	}
})(jQuery);


/* 只允许输入整数 */
$.fn.integer = function() {
	// 禁用输入法，屏蔽粘贴操作
	return this.css('ime-mode', 'disabled').bind('paste', function(e) {
		e.preventDefault();
	}).keypress(function(e) {
		/*
		 * 只能输入数字，backspace和tab
		 * keyCode:
		 * 48~57: 0~9
		 * 8: backspace
		 * 9: tab
		 */
		var keyCode = e.keyCode ? e.keyCode : e.charCode ? e.charCode : 0;
		if (keyCode && (keyCode < 48 || keyCode > 57) && keyCode != 8 && keyCode != 9) {
			e.preventDefault();
		}
	});
};

/* jQuery Validate 设置默认属性 */
$.validator.setDefaults({
	errorPlacement: function(error, element) {       
		error.appendTo(element.parent());       
	}
});

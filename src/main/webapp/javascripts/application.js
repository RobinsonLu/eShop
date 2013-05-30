/* ֻ������������ */
$.fn.integer = function() {
	// �������뷨������ճ������
	return this.css('ime-mode', 'disabled').bind('paste', function(e) {
		e.preventDefault();
	}).keypress(function(e) {
		/*
		 * ֻ���������֣�backspace��tab
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

/* jQuery Validate ����Ĭ������ */
$.validator.setDefaults({
	errorPlacement: function(error, element) {       
		error.appendTo(element.parent());       
	}
});

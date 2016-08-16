$(function(){
	$('.logo-menu-list li').mouseover(function(){
		var ths = $(this);
		var left = ths.position().left;
		var mid = ths.attr("menu-id");
		ths.mouseout(function(){
			$('#'+mid).hide();
		});
		$('#'+mid).mouseover(function(){
			$(this).show();
		}).mouseout(function() {
			$(this).hide();
		}).css("left", left).show();
	});
});
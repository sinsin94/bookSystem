/**
 * 
 */
$(document).ready(function(){
//	alert("hah");
	$(".logout_head").click(function(){	
		$.ajax({
			url:'../userAction.do',
			data:{
				"action":"logout",
				
				 
			},

			success:function(data) {
			    if(data.msg == 105){
			    	alert("退出成功");
			    	window.location.href="../login.jsp";
					}else{
						alert("退出失败");
					}
			         
			}
			         
			});
	});
})
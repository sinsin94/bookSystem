/**
 * 
 */
$(document).ready(function(){
	userShow();
	
//点击添加用户	
	
	$('.add_user').click(function(){
		
		$('.total_add').slideDown();
		$("table").addClass("opa");
	})
	$("table").not(".add_user_table").click(function(){
		
		$('.total_add').slideUp();
		$("table").removeClass("opa");
	})
	

	$('.submit').click(function(){
		
		var password=$('#password').val();
		var password2=$('#password2').val();
		if(password==''||password2==''){
			alert("密码不能为空");
			return false;
		}else if(password!=password2){
			alert("两次密码不同");
			return false;
		}else{
//			updata_user();
			

		    $.ajax({
		    	
		    	url:'../userAction.do',
				data:{
					"action":"update",			
					 id:$('#userid').val(),
					 userName:$('#username').val(),
					 password:$('#password').val(),
					 realName:$('#realname').val(),
				},
        
		        success:function(data) {
		            if(data.msg == 102){
		            	alert("更新成功");
		            	$('.total').slideUp();
		            	$("table").removeClass("opa");
		            	userShow();
						}else{
							alert("更新失败");
//							$("table").css("opacity","1");
						}

		                   
		        }
		            
		             
		    });
		    
			
		}
	});
	

		
$('.submit_add').click(function(){
	    var username=$('#username_add').val();
		var password=$('#password_add').val();
		var password2=$('#password2_add').val();
		var flag=1;
		
		$(".users_name").each(function(){
			if($(this).attr("data_name")==username){
				alert("已经存在该用户名");
				var flag=0;
				return false;
			}else{
				if(password==''||password2==''){
					alert("密码不能为空");
					var flag=0;
					return false;
				}else if(password!=password2){
					alert("两次密码不同");
					var flag=0;
					return false;
				}else{
//					updata_user();	
					flag=1;
				}							
			}
		});
	
		
		if(flag==1){
		
			$.ajax({
		    	
		    	url:'../userAction.do',
				data:{
					"action":"add",			
					 
					 userName:$('#username_add').val(),
					 password:$('#password_add').val(),
					 realName:$('#realname_add').val(),
				},
		           
		        
		        success:function(data) {
		            if(data.msg == 103){
		            	alert("添加成功");
		            	$('.total_add').slideUp();
		            	$("table").removeClass("opa");
		            	userShow();
						}else{
							alert("添加失败");
						}

		                   
		        }
		            
		             
		    });
		    
			
		}
	

	});

//添加分页

//var initPagination = function() {
//    var num_entries = $('#totalpage1').val();
//    // 创建分页
//    $("#pagination4").pagination(num_entries, {
//        num_edge_entries: 1, //边缘页数
//        num_display_entries: 4, //主体页数
//        // current_page:$('.current').text(),
//        callback: getFenxiao,
//        items_per_page: 1, //每页显示1项
//        prev_text: "上一页",
//        next_text: "下一页"
//    });
// }();
// 
// function getFenxiao(pageNum){
//	  var current = $('.current').text();
//	  if(current.indexOf('上')!=-1){
//	      current = current.substring(3);
//	  }else if(current.indexOf('下')!=-1){
//	      current = current.substring(0,1);
//	  }
//	  pageNum = current;
//	 
//	      }

//获取参数
//$.urlParam = function(name){
//    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
//    return results[1] || 0;
//}
//var name=decodeURIComponent($.urlParam('name'));
////alert(name);
//$("#head_name").text("欢迎  "+name);
//
});

function userShow(){

    $.ajax({
    	
    	url:'../userAction.do',
		data:{
			"action":"user",
			
		},
           
        
        success:function(data) {
        	$('.user_content').empty();
            if(data.msg == 104){
				
				if(data.users!=null&&data.users.length>0){
	            	var users = data.users;
					for(var i=0;i<users.length;i++){
	            	$('.user_content').append(
	            			"<tr>"+
	            			"<td data_id='"+users[i].id+"' width='150'>"+(i+1)+"</td>"+
	            			"<td class='users_name' data_name='"+users[i].username+"' width='300'>"+users[i].username+"</td>"+
	            			"<td data_realname='"+users[i].realname+"'width='300'>"+users[i].realname+"</td>"+
	            			"<td  class='notclick' width='230'><a data_id='"+users[i].id+"' class='delete' style='color:blue ;margin-left:10px;margin-right:18px'>删除</a>  <a class='change' data_id='"+users[i].id+"' data_realName='"+users[i].realname+"'  data_name='"+users[i].username+"' style='color:blue;margin-left:20px'>修改</a></td>"+
	            			"</tr>"
	            		);
					}
				}
			
				$(".user_content tr").each(function() {    // 遍历每一行
				    $(this).children('td:eq(0)').click(function(){$('.total').slideUp();$("table").removeClass("opa");});  // td:eq(0)选择器表示第一个单元格
				});
				$(".user_content tr").each(function() {    // 遍历每一行
				    $(this).children('td:eq(1)').click(function(){$('.total').slideUp();$("table").removeClass("opa");});  // td:eq(0)选择器表示第一个单元格
				});
				$(".user_content tr").each(function() {    // 遍历每一行
				    $(this).children('td:eq(2)').click(function(){$('.total').slideUp();$("table").removeClass("opa");});  // td:eq(0)选择器表示第一个单元格
				});
				
				
				$("table ").not('.user_content').click(function() {    // 遍历每一行
				    $('.total').slideUp();$("table").removeClass("opa");;
				    });  // td:eq(0)选择器表示第一个单元格

				
				
				
				//删除地址
				$('.delete').click(function(){
					var id=$(this).attr("data_id");
					deleted(id);
					
				});
				
				$('.change').click(function(){
					$('.total').slideDown();
					$("table").addClass("opa");
					
					var id=$(this).attr("data_id");
					var username=$(this).attr("data_name");
					var realName=$(this).attr("data_realName");
					$('#userid').val(id);
					$('#realname').val(realName);
					$('#username').val(username);
				});
				
//				
				
           	
               
            }
        }
            
             
    });
    
}

function deleted(id){
	

    $.ajax({
    	
    	url:'../userAction.do',
		data:{
			"action":"delete",
			
			 id:id,
		},
           
        
        success:function(data) {
            if(data.msg == 101){
            	alert("删除成功");
            	userShow();
				}else{
					alert("删除失败");
				}

                   
        }
            
             
    });
    
}

function updata_user(id,userName,password,realName){
	

    $.ajax({
    	
    	url:'../userAction.do',
		data:{
			"action":"update",			
			 id:id,
			 userName:username,
			 password:password,
			 realName:realName,
		},
           
        
        success:function(data) {
            if(data.msg == 102){
            	alert("更新成功");
            	userShow();
				}else{
					alert("更新失败");
				}

                   
        }
            
             
    });
    
}
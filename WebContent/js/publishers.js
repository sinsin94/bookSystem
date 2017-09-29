/**
 * 
 */

$(document).ready(function(){
	userShow();
//	
//	$(".total").css({ 
//        position: "fixed", 
//        left: ($(window).width() - $(".total").outerWidth())/2, 
//        top: ($(window).height() - $(".total").outerHeight())/2 
//    });    
	
	$('.add_authors').click(function(){

		$('.total_add').slideDown();
	})
	
		$("table").not(".add_publishers_table").click(function(){		
		$('.total_add').slideUp();
		$("table").removeClass("opa");
	})
	
	$('.submit').click(function(){
		$('.total').slideUp();
	    $.ajax({	    	
	    	url:'../publisherAction.do',
			data:{
				"action":"update",			
				 id:$('#publishers_id').val(),
				 name:$('#publishers_name').val(),				 			 
			},	          	        
	        success:function(data) {
	            if(data.msg == 122){
	            	alert("更新成功");
	            	$('.total').slideUp();
	            	userShow();
					}else{
						alert("更新失败");
					}	                   
	        }	           	             
	    });	   		
	});
	
	
$('.submit_add').click(function(){
	$('.total_add').slideUp();
	var publisher_name=$('#username_add').val()
	var flag=1;
	$(".publisher_n").each(function(){
		if($(this).attr("data_name")==publisher_name){
			
			alert("已经存在该出版社");
			flag=0;
			return false;
		}else if(publisher_name==""){
			
			alert("出版名字不能为空");
			flag=0;
			return false;
		}else{
			flag=1;
		}
		
	});

  if( flag==1){
    $.ajax({
    	
    	url:'../publisherAction.do',
		data:{
			"action":"add",			
			name:$('#username_add').val(),
			 
		},
           
        
        success:function(data) {
            if(data.msg == 123){
            	alert("添加成功");
            	$('.total_add').slideUp();
            	userShow();
				}else{
					alert("添加失败");
				}                   
        }           
    });
  }
});



});
//查询函数
function userShow(){
	$('.total').slideUp();
    $.ajax({
    	
    	url:'../publisherAction.do',
		data:{
			"action":"publisher",
			
		},
       
        success:function(data) {
        	$('.user_content').empty();
            if(data.msg == 120){
				$('.user_content').empty();
				if(data.publishers!=null&&data.publishers.length>0){
	            	var publishers = data.publishers;
					for(var i=0;i<publishers.length;i++){
	            	$('.user_content').append(
	            			"<tr>"+
	            			"<td data_id='"+publishers[i].id+"' width='200'>"+(i+1)+"</td>"+
	            			"<td class='publisher_n' data_name='"+publishers[i].name+"' width='550'>"+publishers[i].name+"</td>"+	
	            			"<td width='230'><a data_id='"+publishers[i].id+"' class='delete' style='color:blue ;margin-left:10px;margin-right:18px'>删除</a>  <a class='change' data_id='"+publishers[i].id+"' data_name='"+publishers[i].name+"'  style='color:blue;margin-left:20px'>修改</a></td>"+
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

				
				//删除地址
				$('.delete').click(function(){
					var id=$(this).attr("data_id");
					deleted(id);
					
				});
				
				$('.change').click(function(){
					$('.total').slideDown();
					var id=$(this).attr("data_id");					
					var name=$(this).attr("data_name");
					$('#publishers_id').val(id);
					$('#publishers_name').val(name);
					
				});
              
            }
        }
            
             
    });
    
}


//删除函数
function deleted(id){


$.ajax({

url:'../publisherAction.do',
data:{
	"action":"delete",
	
	 id:id,
},

success:function(data) {
    if(data.msg == 121){
    	alert("删除成功");
    	userShow();
		}else{
			alert("删除失败");
		}
         
}
         
});

}
//
//修改操作
function updata_publisher(id,name){


$.ajax({

url:'../authorAction.do',
data:{
	"action":"update",			
	 id:id,
	 name:name,

	
},
   

success:function(data) {
    if(data.msg == 122){
    	alert("更新成功");
    	userShow();
		}else{
			alert("更新失败");
			$('.total').slideUp();
		}

           
}
    
     
});

}
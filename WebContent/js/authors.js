
$(document).ready(function(){
	userShow();
	$('.add_authors').click(function(){

		$('.total_add').slideToggle();
		$("table").addClass("opa");
	})
	
	//点击添加用户	
	$("table").not(".add_authors_table").click(function(){		
		$('.total_add').slideUp();
		$("table").removeClass("opa");
	})
	
//点击修改用户
//	
//	$('.change').click(function(){
//		
//		$('.total_add').slideDown();
//		$("table").addClass("opa");
//		
//	})
	
	$('.submit').click(function(){
		$('.total').slideUp();
		$("table").removeClass("opa");
		
		
	    $.ajax({	    	
	    	url:'../authorAction.do',
			data:{
				"action":"update",			
				 id:$('#authors_id').val(),
				 firstName:$('#authors_firstname').val(),
				 lastName:$('#authors_lastname').val(),				 
			},	          	        
	        success:function(data) {
	            if(data.msg == 112){
	            	alert("更新成功");
	            	
	            	userShow();
					}else{
						alert("更新失败");
					}	                   
	        }	           	             
	    });	   		
	});
	
	
$('.submit_add').click(function(){
	$('.total_add').slideUp();
	$("table").removeClass("opa");
	firstName=$('#firstname_add').val();
	lastName=$('#lastname_add').val();
	var flag=1;
//	var flag1=0;
	$(".change").each(function(){
		if($(this).attr("data_firstname")==firstName&&$(this).attr("data_lastname")==lastName){
		  alert("已经存在该作者");	
		  flag=0;
		  return false;	
			
		}
		
	});
	
	if(flag==1){
    $.ajax({
    	
    	url:'../authorAction.do',
		data:{
			"action":"add",			
			 
			firstName:$('#firstname_add').val(),
			lastName:$('#lastname_add').val(),
			 
		},
           
        
        success:function(data) {
            if(data.msg == 113){
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

    $.ajax({
    	
    	url:'../authorAction.do',
		data:{
			"action":"author",
			
		},
       
        success:function(data) {
        	$('.user_content').empty();
            if(data.msg == 110){
				$('.user_content').empty();
				if(data.authors!=null&&data.authors.length>0){
	            	var authors = data.authors;
					for(var i=0;i<authors.length;i++){
	            	$('.user_content').append(
	            			"<tr>"+
	            			"<td data_id='"+authors[i].id+"' width='150'>"+(i+1)+"</td>"+
	            			"<td data_name='"+authors[i].firstName+"' width='300'>"+authors[i].firstname+"</td>"+
	            			"<td data_realname='"+authors[i].lastName+"'width='300'>"+authors[i].lastname+"</td>"+
	            			"<td width='230'><a data_id='"+authors[i].id+"' class='delete' style='color:blue ;margin-left:10px;margin-right:18px'>删除</a>  <a class='change' data_id='"+authors[i].id+"' data_firstname='"+authors[i].firstname+"'  data_lastname='"+authors[i].lastname+"' style='color:blue;margin-left:20px'>修改</a></td>"+
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
					var firstname=$(this).attr("data_firstname");
					var lastname=$(this).attr("data_lastname");
					$('#authors_id').val(id);
					$('#authors_firstname').val(firstname);
					$('#authors_lastname').val(lastname);
				});
              
            }
        }
            
             
    });
    
}


//删除函数
function deleted(id){


$.ajax({

url:'../authorAction.do',
data:{
	"action":"delete",
	
	 id:id,
},

success:function(data) {
    if(data.msg == 111){
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
function updata_author(id,firstName,lastname){


$.ajax({

url:'../authorAction.do',
data:{
	"action":"update",			
	 id:id,
	 firstname:firstName,
	 lastname:lastname,
	
},
   

success:function(data) {
    if(data.msg == 112){
    	alert("更新成功");
    	userShow();
		}else{
			alert("更新失败");
			
		}

           
}
    
     
});

}
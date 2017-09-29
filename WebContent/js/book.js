/**
 * 
 */


$(document).ready(function(){
	userShow();
	show_author();
	show_publishers();
	$('.add').click(function(){
		$('.total_add').slideToggle();
		$("table").addClass("opa");
	})	
	$('.submit').click(function(){
		$('.total').slideUp();
		$("table").removeClass("opa");		
	    $.ajax({	  
	    	traditional: true,
	    	url:'../bookAction.do',
			data:{
				"action":"update",			
			
				 isbn:$("#isbn").val(),
				 name:$("#book_name").val(),
				 version:$("#version").val(),
				 price :$("#price").val(),
				 publishTime:$("#year1").val(),
				 publisherId: $("#publisherId_update").val(),
				 authorID:[$("#authorID_update").val()],
				
				 
				 
			},	          	        
	        success:function(data) {
	            if(data.msg == 132){
	            	alert("更新成功");
	            	
	            	userShow();
					}else{
						alert("更新失败");
					}	                   
	        }	           	             
	    });	   		
	});
	
	
$('.submit1').click(function(){
	$('.total_add').slideUp();
	$("table").removeClass("opa");
	$(this).attr("");

    $.ajax({
    	traditional: true,
    	url:'../bookAction.do',
		data:{
			"action":"add",			
			isbn:$("#isbn1").val(),
			name:$("#book_name1").val(),
			publishTime:$("#year1").val(),
			version:$("#version1").val(),
			price:$('#price1').val(),
			publisherId:$("#publisherId_add").val(),
			publishername:$(".book_publisher").val(),
			authorID:[$("#authors_add_id").val()]
			
		},                
        success:function(data) {
            if(data.msg == 134){
            	alert("添加成功");
            	$('.total_add').slideUp();
            	userShow();
				}else{
					alert("添加失败");
				}                   
        }           
    });
	
});

//查询数据
$("#search_book").click(function(){
		
    $.ajax({	
    	url:'../bookAction.do',
		data:{
			"action":"search",			
			isbn:$("#book_isbn_seach").val(),
			name:$("#book_name_seach").val(),
		 
		},
           
        
        success:function(data) {
        	$('.book_table').remove();
            if(data.msg == 131){
            	
				if(data.books!=null&&data.books.length>0){
	            	var books = data.books;
	            	var publishTime = "";
					for(var i=0;i<books.length;i++){
					(books[i].publishTime=="") ? publishTime="无" : publishTime=books[i].publishTime;
	            	$('.content_book').append(	
	            	"<tr class='book_table' style='text-align:'center'  align='center' width='980' border='1'cellspacing='0' cellpadding='0'>"+
	            		"<td width='50'>"+(i+1)+"</td>"+
	            		"<td width='100'>"+books[i].isbn+"</td>"+
	            		"<td width='300'>"+books[i].name+"</td>"+
	            		"<td width='150'>"+books[i].publishername+"</td>"+
	            		"<td width='70'>"+books[i].price+"</td>"+
	            		"<td width='70'>"+books[i].version+"</td>"+
	            		"<td width='80'>"+publishTime+"</td>"+
	            		"<td width='230px'><a data_id='"+books[i].isbn+"' class='delete' style='color:blue ;margin-left:10px;margin-right:18px'>删除</a>  <a class='change' date-publisherTime='"+books[i].publisherTime+"'data_id='"+books[i].isbn+"' data_name='"+books[i].name+"' data_version='"+books[i].version+"' data_price='"+books[i].price+"' data_publisherId='"+books[i].publisherId+"' style='color:blue;margin-left:20px'>修改</a></td>"+
	            		"</tr>"
	
	            		);
					}
	
				}
				//删除地址
				$('.delete').click(function(){
					var id=$(this).attr("data_id");
					deleted(id);
					
				});


				$('.change').click(function(){
				
					$('.total').slideDown();
					$("table").addClass("opa");
    				
					var isbn=$(this).attr("data_id");
					var book_name=$(this).attr("data_name");
					var version=$(this).attr("data_version");
					var price=$(this).attr("data_price");
					var publisherId=$(this).attr("data_publisherId");
					var publisherTime=$(this).attr("data_publishTime");
					
					
					$('#isbn').val(isbn);
					$('#book_name').val(book_name);
					$('#version').val(version);
					$('#price').val(price);
					$('#year').val(publisherTime);
					$('#publisherId').val(publisherId);
					
				});
				}else{
					alert("查询失败");
				}                   
        }           
    });
	
});




});
//初始化
function userShow(){

    $.ajax({
    	
    	url:'../bookAction.do',
		data:{
			"action":"book",
			
		},
       
        success:function(data) {
        	$('.book_table').remove();
            if(data.msg == 130){
            	$('.book_table').empty();
				if(data.books!=null&&data.books.length>0){
	            	var books = data.books;
	            	var publishTime = "";
					for(var i=0;i<books.length;i++){
					(books[i].publishTime=="") ? publishTime="无" : publishTime=books[i].publishTime;
	            	$('.content_book').append(
	           	            			
	            		"<tr class='book_table' style='text-align:'center'  align='center' width='980' border='1'cellspacing='0' cellpadding='0'>"+
	            			"<td width='50'>"+(i+1)+"</td>"+
	            			"<td width='100'>"+books[i].isbn+"</td>"+
	            			"<td width='300'>"+books[i].name+"</td>"+
	            			"<td width='150'>"+books[i].publishername+"</td>"+
	            			"<td width='70'>"+books[i].price+"</td>"+
	            			"<td width='70'>"+books[i].version+"</td>"+
	            			"<td width='80' id='publishTime'>"+publishTime+"</td>"+
	            			"<td width='230px'><a data_isbn='"+books[i].isbn+"' class='delete' style='color:blue ;margin-left:10px;margin-right:18px'>删除</a>  <a class='change' date-publisherTime='"+books[i].publisherTime+"'  data_isbn='"+books[i].isbn+"' data_name='"+books[i].name+"' data_version='"+books[i].version+"' data_price='"+books[i].price+"' data_publisherId='"+books[i].publisherId+"'  data_publishername='"+books[i].publishername+"' data_publishTime='"+books[i].publishTime+"' style='color:blue;margin-left:20px'>修改</a></td>"+
	            		
	            		"</tr>"
	            		);
	            	

					}
				}			
				
				//删除地址
				$('.delete').click(function(){
					var id=$(this).attr("data_isbn");
					deleted(id);
					
				});


				$('.change').click(function(){
				
					$('.total').slideDown();
					$("table").addClass("opa");
    				
					var isbn=$(this).attr("data_isbn");
					var book_name=$(this).attr("data_name");
					var version=$(this).attr("data_version");
					var price=$(this).attr("data_price");
					var publisherId=$(this).attr("data_publisherId");
					var publisherTime=$(this).attr("data_publishTime");
					
					
					$('#isbn').val(isbn);
					$('#book_name').val(book_name);
					$('#version').val(version);
					$('#price').val(price);
					$('#year').val(publisherTime);
					$('#publisherId').val(publisherId);
					
				});
              
            }
        }
            
             
    });
    
}


//删除函数
function deleted(id){


$.ajax({

url:'../bookAction.do',
data:{
	"action":"delete",
	
	 isbn:id,
},

success:function(data) {
    if(data.msg == 133){
    	alert("删除成功");
    	userShow();
		}else{
			alert("删除失败");
		}
         
}
         
});

}
//


function show_author(){
	$.ajax({
	url:'../authorAction.do',
	data:{
		"action":"author",					
	},
	success:function(data) {
	    if(data.msg == 110){
	    	$(".book_authors_update").empty();
	    	$(".book_authors1").empty();
			if(data.authors!=null&&data.authors.length>0){
            	var authors = data.authors;
				for(var i=0;i<authors.length;i++){
	            	$('.book_authors_update').append(
	            		"<option class='book_authors1_update' authors_id='"+authors[i].id+"'>"+authors[i].firstname+" "+authors[i].lastname+"</option>"       
	            	);
				}
			
				for(var i=0;i<authors.length;i++){
	            	$('.book_authors1').append(
	            		"<option class='book_authors1_select' authors_id='"+authors[i].id+"'>"+authors[i].firstname+" "+authors[i].lastname+"</option>"       
	            	);
				}
			}	
			
			$('.book_authors1_select').click(function(){
				var authors_id=$(this).attr("authors_id");
				$("#authors_add_id").val(authors_id);
			
			})
			
//		   	$('.book_authors1_update').click(function(){
//		
//				var authors_id_update=$(this).attr("authors_id");
//				$("#authors_add_id").val(authors_id_update);
//			
//			})
			//修改作者ID
			$('.book_authors_update').change(function(){

				var authors_id_update=$(this).children('option:selected').attr("authors_id"); 

				$("#authorID_update").val(authors_id_update);
			});
			
	    	
		}          
	}
	    
	     
	});

	}


function show_publishers(){
	$.ajax({
	url:'../publisherAction.do',
	data:{
		"action":"publisher",				
	},
	success:function(data) {
		
	    if(data.msg == 120){
	    	$(".book_publisher").empty();
	    	$(".book_publisher_update").empty();
			if(data.publishers!=null&&data.publishers.length>0){
            	var publishers = data.publishers;
				for(var i=0;i<publishers.length;i++){
					$('.book_publisher').append(
            			"<option publisher_id='"+publishers[i].id+"' class='book_publisher_name_add'>"+publishers[i].name+"</option>"      
            		);
				}
				for(var i=0;i<publishers.length;i++){
					$('.book_publisher_update').append(
            			"<option publisher_id='"+publishers[i].id+"'>"+publishers[i].name+"</option>"      
            		);
				}
			}	
			
			$('.book_publisher').change(function(){
				var publisher_id=$(this).children('option:selected').attr("publisher_id"); 
//				alert(publisher_id);
				$("#publisherId_add").val(publisher_id);
			});
			
			
			$('.book_publisher_update').change(function(){
				var publisher_id_update=$(this).children('option:selected').attr("publisher_id"); 
//				alert(publisher_id_update);
				$("#publisherId_update").val(publisher_id_update);
			});
		}

		//book_publisher_update
           
	}
	    
	     
	});

	}
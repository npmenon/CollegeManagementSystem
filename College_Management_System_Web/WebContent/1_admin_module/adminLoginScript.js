/**
 * 	Module: Admin module
 */

$(function(){
		
		//alert("Hey");
		
		$( document ).tooltip();
		
		$("form").submit(function(evt){
			
				var target = $(evt.target);
				
				flag = true;
				
				if(target.attr('name') == "AdminSignInForm"){
					
					alert("Before submitting form");
					
					var username = target.find("input[name='username']");
					var password = target.find("input[name='password']");
				
					if(username.val() == null ||username.val().length == 0)
					{
						username.addClass('showError');					
						username.attr('title','username should be empty');					
						flag = false;
					}
					else{					
						username.removeClass('showError');					
						username.removeAttr('title');					
					}
					
					if(password.val() == null ||password.val().length == 0)
						{
							password.addClass('showError');
							password.attr('title','password should not be empty');
							flag = false;
						}
					else
						{
							password.removeClass('showError');
							password.removeAttr('title');
						}				
				}
				
				alert("Final value of flag: " + flag);
				if(flag == false)
					evt.preventDefault();
				
		});
});

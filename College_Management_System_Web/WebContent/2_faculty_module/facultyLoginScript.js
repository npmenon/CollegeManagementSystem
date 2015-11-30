/**
 * 	Module: faculty module
 */

$(function(){
		
		//alert("Hey");
		
		$( document ).tooltip();
		
		$("form").submit(function(evt){
			
				var target = $(evt.target);
				
				flag = true;
				
				if(target.attr('name') == "facultySignIn"){
					
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
				
				if(target.attr('name') == "facultySignUp"){
					
					alert("validating sign up");
					
					var username = target.find("input[name='username']");
					var oldpassword = target.find("input[name='oldpassword']");
					var newpassword = target.find("input[name='newpassword']");
					var confpassword = target.find("input[name='confpassword']");
					
					if(username.val() == null ||username.val().length == 0)
					{
						username.addClass('showError');					
						username.attr('title','username field should be empty');					
						flag = false;
					}
					else{					
						username.removeClass('showError');					
						username.removeAttr('title');					
					}
					
					if(oldpassword.val() == null ||oldpassword.val().length == 0)
						{
							oldpassword.addClass('showError');
							oldpassword.attr('title','old password field should not be empty');
							flag = false;
						}
					else
						{
							oldpassword.removeClass('showError');
							oldpassword.removeAttr('title');
						}
					
					if(newpassword.val() == null ||newpassword.val().length == 0)
					{
						newpassword.addClass('showError');
						newpassword.attr('title','new password field should not be empty');
						flag = false;
					}
					else
					{
						newpassword.removeClass('showError');
						newpassword.removeAttr('title');
					}
					
					
					if(confpassword.val() == null ||confpassword.val().length == 0)
					{
						confpassword.addClass('showError');
						confpassword.attr('title','Confirmation password field should not be empty');
						flag = false;
					}
					else
					{
						confpassword.removeClass('showError');
						confpassword.removeAttr('title');
					}
					
					if(newpassword.val() != confpassword.val())
					{
						confpassword.addClass('showError');
						confpassword.attr('title','passwords should match');
						flag = false;
					}
					else
					{
						confpassword.removeClass('showError');
						confpassword.removeAttr('title');
					}
				}
				
				alert("Final value of flag: " + flag);
				if(flag == false)
					evt.preventDefault();
				
		});
});

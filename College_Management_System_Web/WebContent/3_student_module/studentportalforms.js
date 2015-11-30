/*
 * STUDENT MODULE
 * 
 */
$(function(){
	
	$( ".date" ).datepicker();
	$( document ).tooltip();
	
	var hideForms = function(){
		$("div#formType form").each(function(){
			$(this).css('display','none');
		});
	};
	
	hideForms();
	//When user chooses any of the operations of student
	$("ol#indexElements li a").click(function(evt){
		
		evt.preventDefault();
		var target = $(evt.target);
		
		if(target.is("#viewProfile")){
			
			//alert("view profile");
			hideForms();
			$("div#formError").css('display','none');
			var formElement = $("form[name='viewProfileForm']");
			formElement.children().addClass("formborder");
			formElement.show();
			
		}		
		if(target.is("#updatePersonalDetails")){
			
			//alert("Modify details");
			hideForms();
			$("div#formError").css('display','none');
			$("#updatePersonalFormError").css('display','none');
			var formElement = $("form[name='updatePersonalDetailsForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
		
	});
	
	//validation of form
	$("form").submit(function(evt){
		
		flag = true;
		var target = $(evt.target);
		
		
		if(target.attr('name') == "updatePersonalDetailsForm" )
		{
			alert("validating update student personal profile det form");
			var firstName= target.find("input[name='firstName']"); 
			var lastName = target.find("input[name='lastName']");
			var emailId = target.find("input[name='emailId']");
			var contact = target.find("input[name='contact']");
			var joinDate = target.find("input[name='joinDate']");
			var dateOfBirth = target.find("input[name='dateOfBirth']");

			count = 0;
		
			
			if(firstName.val().length != 0)
			{
				count++;
				//alert("type of firstname.val() is " + typeof firstName.val());
				if(!(isNaN(firstName.val()))){
					//alert("firstname is not a string");
					firstName.addClass('showError');
					firstName.attr('title','Should be a String');
					flag = false;
				}
				else{
					firstName.removeClass('showError');
					firstName.removeAttr('title');
				}
					
			}
			if(lastName.val().length != 0)
			{
				count++;
				if(!(isNaN(lastName.val()))){
					//alert("gradInst is not a string");
					lastName.addClass('showError');
					lastName.attr('title','Should be a String');
					flag = false;
				}
				else{
					lastName.removeClass('showError');
					lastName.removeAttr('title');
				}
					
			}
			if(emailId.val().length != 0)
			{
				count++;		
				
				function validateForm(x) {
					var flag = true;
					var atpos = x.indexOf("@");
				    var dotpos = x.lastIndexOf(".");
				    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) 
				        flag = false;
				    
				    return flag;
				}
				
				if(!validateForm(emailId.val())){
					//alert("gradInst is not a string");
					emailId.addClass('showError');
					emailId.attr('title','should be a valid email');
					flag = false;
				}
				else{
					emailId.removeClass('showError');
					emailId.removeAttr('title');
				}
					
			}
			if(contact.val().length != 0)
			{
				count++;
				
				if(isNaN(contact.val()) || contact.val().length > 10){
					
					if(!isNaN(contact.val()))
						contact.attr('title','should a number');
					else
						contact.attr('title','should be 10 digits');					
					contact.addClass('showError');					
					flag = false;
				}
				else{
					emailId.removeClass('showError');
					emailId.removeAttr('title');
				}
			}
						
			if(joinDate.val().length != 0)
			{
				count++;
			}
			
			if(dateOfBirth.val().length !=0)
			{
				count++;
			}
			
			alert("checking count");
			if(count==0){
				alert("count is 0");
				$("#facFormError").show();
				flag = false;
			}
			else
				$("#facFormError").css('display','none');			
			
		}
		
		alert("Final value of flag: " + flag);
		if(flag==false)
			evt.preventDefault();
		
	});
	
});
/*
 * FACULTY MODULE
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
		if(target.is("#modifyDetails")){
			
			//alert("Modify details");
			hideForms();
			$("div#formError").css('display','none');
			$("#facFormError").css('display','none');
			var formElement = $("form[name='modifyPersDetForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
		if(target.is("#updateStudAcaDet")){
			
			//alert("Modify details");
			hideForms();
			$("div#formError").css('display','none');
			$("#studFormError").css('display','none');
			var formElement = $("form[name='updateStudCurrAcadDetForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
		
	});
	
	//Validation of form
	$("form").submit(function(evt){
		
		flag = true;
		var target = $(evt.target);
	
		//validating add user and delete user form
		if(target.attr('name') == "modifyPersDetForm" )
		{
			alert("validating update faculty prof det form");
			var firstName= target.find("input[name='firstName']"); 
			var lastName = target.find("input[name='lastName']");
			var emailId = target.find("input[name='emailId']");
			var contact = target.find("input[name='contact']");
			var hire_date = target.find("input[name='hire_date']");
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
			if(hire_date.val().length != 0)
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
		if(target.attr('name') == "updateStudCurrAcadDetForm" )
		{
			alert("validating update student current academic details form");
			var sem1Marks= target.find("input[name='sem1Marks']"); 
			var sem2Marks = target.find("input[name='sem2Marks']");
			var sem3Marks = target.find("input[name='sem3Marks']");
			var sem4Marks = target.find("input[name='sem4Marks']");
			var currentSem = target.find("input[name='currentSem']");
			
			count = 0;
			
			if(sem1Marks.val().length != 0)
			{
				count++;
				//alert("checking sem1 marks");
				if(isNaN(sem1Marks.val()) || sem1Marks.val() > 100){
					
					sem1Marks.addClass('showError');
					
					if(isNaN(sem1Marks.val()))
						sem1Marks.attr('title','Should be a valid number');
					else
						sem1Marks.attr('title','Should be less than or equal to 100');
					
					flag = false;
				}
				else{
					sem1Marks.removeClass('showError');
					sem1Marks.removeAttr('title');
				}
			}
			if(sem2Marks.val().length != 0)
			{
				count++;
				//alert("checking sem2 marks");
				if(isNaN(sem2Marks.val()) || sem2Marks.val() > 100){
					
					sem2Marks.addClass('showError');
					
					if(isNaN(sem2Marks.val()))
						sem2Marks.attr('title','Should be a valid number');
					else
						sem2Marks.attr('title','Should be less than or equal to 100');
					
					flag = false;
				}
				else{
					sem2Marks.removeClass('showError');
					sem2Marks.removeAttr('title');
				}
			}
			if(sem3Marks.val().length != 0)
			{
				count++;
				//alert("checking sem3 marks");
				if(isNaN(sem3Marks.val()) || sem3Marks.val() > 100){
					
					sem3Marks.addClass('showError');
					
					if(isNaN(sem3Marks.val()))
						sem3Marks.attr('title','Should be a valid number');
					else
						sem3Marks.attr('title','Should be less than or equal to 100');
					
					flag = false;
				}
				else{
					sem3Marks.removeClass('showError');
					sem3Marks.removeAttr('title');
				}
			}
			if(sem4Marks.val().length != 0)
			{
				count++;
				//alert("checking sem4 marks");
				if(isNaN(sem4Marks.val()) || sem4Marks.val() > 100){
					
					sem4Marks.addClass('showError');
					
					if(isNaN(sem4Marks.val()))
						sem4Marks.attr('title','Should be a valid number');
					else
						sem4Marks.attr('title','Should be less than or equal to 100');
					
					flag = false;
				}
				else{
					sem4Marks.removeClass('showError');
					sem4Marks.removeAttr('title');
				}
			}
			if(currentSem.val().length != 0)
			{
				count++;
				//alert("checking currentsem marks");
				if(isNaN(currentSem.val()) || currentSem.val() > 8){
					
					currentSem.addClass('showError');
					
					if(isNaN(currentSem.val()))
						currentSem.attr('title','Should be a valid number');
					else
						currentSem.attr('title','Should be less than or equal to 100');
					
					flag = false;
				}
				else{
					currentSem.removeClass('showError');
					currentSem.removeAttr('title');
				}
			}
		}
		
		alert("Final value of flag: " + flag);
		if(flag==false)
			evt.preventDefault();		
		
	});
});
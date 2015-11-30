/*
 * MODULE: ADMIN MODULE
 * 
 */
$(function(){
	
	var hideForms = function(){
		$("div#formType form").each(function(){
			$(this).css('display','none');
		});
	};
	
	hideForms();
	//When user chooses any of the operations of admin
	$("ol#indexElements li a").click(function(evt){
		
		evt.preventDefault();
		var target = $(evt.target);
		
		if(target.is("#addUser"))
		{
			//alert("Add user");
			hideForms();
			$("div#formError").css('display','none');
			var formElement = $("form[name='addUserForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
		if(target.is("#deleteUser"))
		{
			//alert("Delete User");
			hideForms();
			$("div#formError").css('display','none');
			var formElement = $("form[name='deleteUserForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
		if(target.is("#addDept"))
		{
			//alert("Add dept");
			hideForms();
			$("div#formError").css('display','none');
			var formElement = $("form[name='addDeptForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
		if(target.is("#updateFacProfDet"))
		{
			//alert("Update faculty professional details");
			hideForms();
			$("div#formError").css('display','none');
			$("#facFormError").css('display','none');
			var formElement = $("form[name='updateFacultyProfDetForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
		if(target.is("#updateStudPrevAcaDet"))
		{
			alert("update student previous academic details");
			hideForms();
			$("div#formError").css('display','none');
			$("#studFormError").css('display','none');
			var formElement = $("form[name='updateStudPrevAcadDetForm']");
			formElement.children().addClass("formborder");
			formElement.show();
		}
	});
	
	//Validation of form
	$("form").submit(function(evt){
		
		flag = true;
		var target = $(evt.target);
	
		//validating add user and delete user form
		if(target.attr('name') == "addUserForm" || target.attr('name') == "deleteUserForm")
		{
			alert("Validating add user form");
			var userId = target.find("input[name='userId']");
			if(userId.val() == "" || userId.val() == null){
				userId.addClass('showError');
				flag = false;
			}
			else
				userId.removeClass('showError');
		}
		//validating add department form
		if(target.attr('name') == "addDeptForm")
		{
			alert("Validating add dept form");
			var deptId = target.find("input[name='deptId']");
			var deptName = target.find("input[name='deptName']");
			if(deptId.val() == "" || deptId.val() == null){
				deptId.addClass('showError');
				flag = false;
			}
			else
				deptId.removeClass('showError');
			
			if(deptName.val() == "" || deptName.val() == null){
				deptName.addClass('showError');
				flag = false;
			}			
			else
				deptName.removeClass('showError');
		}
		//validating faculty professional details update form
		if(target.attr('name') == "updateFacultyProfDetForm")
		{
			alert("validating update faculty prof det form");
			var facultyId = target.find("input[name='facultyId']"); 
			var gradInst = target.find("input[name='gradInstit']");
			var gradMarks = target.find("input[name='gradMarks']");
			var postGradInst = target.find("input[name='postGradInstit']");
			var postGradMarks = target.find("input[name='postGradMarks']");
			var currentDesign = target.find("input[name='currentDesign']");

			count = 0;
			alert("checking faculty id");
			if(facultyId.val().length != 0){
				
				facultyId.removeClass('showError');	
				
				if(gradInst.val().length != 0)
				{
					count++;
					if(!(typeof gradInst.val() === "string")){
						//alert("gradInst is not a string");
						gradInst.addClass('showError');
						flag = false;
					}
					else
						gradInst.removeClass('showError');
				}
				if(gradMarks.val().length != 0)
				{
					count++;
					if(isNaN(parseFloat(gradMarks.val())) ||
							!(typeof parseFloat(gradMarks.val()) === "number")){
						
						gradMarks.addClass('showError');
						flag = false;
					}
					else
						gradMarks.removeClass('showError');
				}
				if(postGradInst.val().length != 0)
				{
					count++;
					if(!(typeof postGradInst.val() === "string")){
						postGradInst.addClass('showError');
						flag = false;
					}
					else
						postGradInst.removeClass('showError');
				
				}
				if(postGradMarks.val().length != 0)
				{
					count++;
					alert("post grad marks is not null");
					if(isNaN(parseFloat(postGradMarks.val())) ||
							!(typeof parseFloat(postGradMarks.val()) === "number")){
						
						postGradMarks.addClass('showError');
						flag = false;
					}
					else
						postGradMarks.removeClass('showError');
				}
				if(currentDesign.val().length != 0)
				{
					count++;
					alert("post grad marks is not null");
					if(!(typeof currentDesign.val() === "string")){
						currentDesign.addClass('showError');
						flag = false;
					}
					else
						currentDesign.removeClass('showError');
				}
			}
			else{
				alert("faculty id is null");
				facultyId.addClass('showError');
				flag = false;
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
		//validating student previous academic details form
		if(target.attr('name') == "updateStudPrevAcadDetForm")
		{
			alert("validating update student previous academic details form");
			var studentId = target.find("input[name='studentId']"); 
			var tenInstit = target.find("input[name='tenInstit']");
			var tenMarks = target.find("input[name='tenMarks']");
			var twelveInstit = target.find("input[name='twelveInstit']");
			var twelveMarks = target.find("input[name='twelveMarks']");
			var prevProjects = target.find("input[name='prevProjects']");

			count = 0;
			alert("checking student id");
			if(studentId.val().length != 0){
				
				studentId.removeClass('showError');	
				
				if(tenInstit.val().length != 0)
				{
					count++;
					if(!(typeof tenInstit.val() === "string")){
						//alert("gradInst is not a string");
						tenInstit.addClass('showError');
						flag = false;
					}
					else
						tenInstit.removeClass('showError');
				}
				if(tenMarks.val().length != 0)
				{
					count++;
					if(isNaN(parseFloat(tenMarks.val())) ||
							!(typeof parseFloat(tenMarks.val()) === "number")){
						
						tenMarks.addClass('showError');
						flag = false;
					}
					else
						tenMarks.removeClass('showError');
				}
				if(twelveInstit.val().length != 0)
				{
					count++;
					if(!(typeof twelveInstit.val() === "string")){
						twelveInstit.addClass('showError');
						flag = false;
					}
					else
						twelveInstit.removeClass('showError');
				
				}
				if(twelveMarks.val().length != 0)
				{
					count++;
					if(isNaN(parseFloat(twelveMarks.val())) ||
							!(typeof parseFloat(twelveMarks.val()) === "number")){
						
						twelveMarks.addClass('showError');
						flag = false;
					}
					else
						twelveMarks.removeClass('showError');
				}
				if(prevProjects.val().length != 0)
				{
					count++;
					if(!(typeof prevProjects.val() === "string")){
						prevProjects.addClass('showError');
						flag = false;
					}
					else
						prevProjects.removeClass('showError');
				}
			}
			else{
				alert("student id is null");
				studentId.addClass('showError');
				flag = false;
			}
			alert("checking count");
			if(count==0){
				alert("count is 0");
				$("#studFormError").show();
				flag = false;
			}
			else
				$("#studFormError").css('display','none');
		}
		
		alert("Final value of flag: " + flag);
		if(flag==false)
			evt.preventDefault();
		
	});
	
});
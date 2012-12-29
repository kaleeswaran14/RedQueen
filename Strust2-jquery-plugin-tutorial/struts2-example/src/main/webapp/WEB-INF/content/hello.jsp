<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>

<html>
<head>
	<title>My App</title>
	<sj:head  jqueryui="true" jquerytheme="start" />
	<sb:head/>
<%-- 	<script  --%>
<%--  		language="JavaScript"  --%>
<%--  		src="/struts2-jquery-showcase/struts/utils.js"  --%>
<%--  		type="text/javascript"> --%>
<%--  	</script> --%>
<%--  	<script  --%>
<%--  		language="JavaScript"  --%>
<%--  		src="/struts2-jquery-showcase/struts/xhtml/validation.js"  --%>
<%--  		type="text/javascript"> --%>
<%--  	</script> --%>
	
	<!-- This files are needed for AJAX Validation of XHTML Forms -->
     	<script language="JavaScript" src="${pageContext.request.contextPath}/struts/utils.js" type="text/javascript"></script>
        <script language="JavaScript" src="${pageContext.request.contextPath}/struts/xhtml/validation.js" type="text/javascript"></script>
        
	<style>
		body {
			font-family: Arial, sans-serif;
			font-size: 9pt;
		}
		
	</style>
	<script type="text/javascript">
		$.subscribe('beforeForm', function(event,data) {
		     var fData = event.originalEvent.formData;
			 alert('About to submit: \n\n' + fData[0].value + ' to target '+event.originalEvent.options.target+' with timeout '+event.originalEvent.options.timeout );
		     var form = event.originalEvent.form[0]; 
		     if (form.echo.value.length  < 2) { 
		         alert('Please enter a value with min 2 characters'); 
		         event.originalEvent.options.submit = false; 
     		} 
		     event.originalEvent.options.submit = false; 
    	});
    	$.subscribe('completeForm', function(event,data) {
   		 	alert('status: ' + event.originalEvent.status + '\n\nresponseText: \n' + event.originalEvent.request.responseText + 
     			'\n\nThe output div should have already been updated with the responseText.');
    	});
    	$.subscribe('errorStateForm', function(event,data) {
        	alert('status: ' + event.originalEvent.status + '\n\nrequest status: ' +event.originalEvent.request.status);
    	});
    </script>
</head>

<body>
	<h2>Please enter a Name by k</h2>

<!-- <div id="result">Strust2 Submit form bellow.</div> -->

<%--     <s:form id="formValidate" action="login" theme="xhtml"> --%>
<%--         <s:textfield  --%>
<%--                 id="loginuser"  --%>
<%--                 name="loginuser"  --%>
<%--                 label="User"  --%>
<%--                 required="true" --%>
<%--         /> --%>
<%--         <s:textfield  --%>
<%--                 id="loginpassword"  --%>
<%--                 name="loginpassword"  --%>
<%--                 label="Password (test)"  --%>
<%--                 required="true" --%>
<%--         /> --%>
<%--         <sj:submit  --%>
<%--                 targets="result"  --%>
<%--                 button="true"  --%>
<%--                 validate="true"  --%>
<%--                 value="Submit"  --%>
<%--                 indicator="indicator" --%>
<%--                 /> --%>
           
<%--     </s:form> --%>

<!-- 	<h2>Ajax form submit with loading icon</h2> -->

<%-- <s:form id="form" action="echo" theme="simple" cssClass="yform"> --%>
<!--         <fieldset> -->
<!--             <legend>AJAX Form</legend> -->
<!-- 	        <div class="type-text"> -->
<!-- 	            <label for="echo">Echo: </label> -->
<%-- 	            <s:textfield id="echo" name="echo" value="Hello World!!!"/> --%>
<!-- 	        </div> -->
<!-- 	        <div class="type-button"> -->
<%-- 	            <sj:submit  --%>
<%-- 	            	id="formSubmit1" --%>
<%-- 	            	targets="formResult"  --%>
<%-- 	            	value="AJAX Submit"  --%>
<%-- 	            	indicator="indicator" --%>
<%-- 	            	button="true" --%>
<%-- 	            	/> --%>
<%-- 				<s:url id="simpleecho" value="/simpleecho.action"/> --%>
<%-- 	            <sj:submit  --%>
<%-- 	            	id="formSubmit2" --%>
<%-- 	            	href="%{simpleecho}"  --%>
<%-- 	            	targets="formResult"  --%>
<%-- 	            	value="AJAX Submit 2"  --%>
<%-- 	            	indicator="indicator" --%>
<%-- 	            	button="true" --%>
<%-- 	            	/> --%>
<!-- 	        </div> -->
<!--         </fieldset> -->
<%--     </s:form> --%>
    
    
    <h2>button submit with events </h2>
        <s:form id="formevent" action="echo" theme="simple" cssClass="yform">
        <fieldset>
            <legend>AJAX Form</legend>
	        <div class="type-text">
	            <label for="echo">Echo: </label>
	            <s:textfield id="echo" name="echo" value="Hello World!!!"/>
	        </div>
	        <div class="type-button">
	            <sj:submit 
	            	targets="result" 
	            	value="AJAX Submit" 
	            	timeout="2500" 
	            	indicator="indicator" 
	            	onBeforeTopics="before" 
	            	onCompleteTopics="complete" 
	            	onErrorTopics="errorState" 
	            	button="true"
	            />
	        </div>
        </fieldset>
    </s:form>

    <img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/>    
    
    <s:form id="formeventerror" action="file-does-not-exist.html" theme="simple" cssClass="yform">
        <fieldset>
            <legend>AJAX Form with Error Result</legend>
            <div class="type-text">
                <label for="echo">Echo: </label>
                <s:textfield id="echo" name="echo" value="Hello World!!!"/>
            </div>
            <div class="type-button">
                <sj:submit 
                	targets="result" 
                	value="AJAX Submit with Error" 
                	timeout="2500" 
                	indicator="indicator" 
                	onBeforeTopics="before" 
                	onCompleteTopics="complete" 
                	onErrorTopics="errorState"
	            	button="true"
                />
            </div>
        </fieldset>
    </s:form>
    
<!--      <h2>Other for example </h2> -->
<%--     <s:form id="formValidate" action="login" theme="xhtml" target="_blank"> --%>
<%--      	<s:textfield  --%>
<%--      		id="loginuser"  --%>
<%--      		name="loginuser"  --%>
<%--      		label="User"  --%>
<%--      		required="true" --%>
<%--      	/> --%>
<%--      	<s:textfield  --%>
<%--      		id="loginpassword"  --%>
<%--      		name="loginpassword"  --%>
<%--      		label="Password (test)"  --%>
<%--      		required="true" --%>
<%--      	/> --%>
<%--     	<sj:submit  --%>
<%--     		button="true"  --%>
<%--     		validate="true"  --%>
<%--     		value="Submit"  --%>
<%--     		indicator="indicator" --%>
<%--     		/> --%>
<%--     	<sj:submit  --%>
<%--     		targets="result"  --%>
<%--     		button="true"  --%>
<%--     		validate="true"  --%>
<%--     		value="AJAX Submit"  --%>
<%--     		indicator="indicator" --%>
<%--     		/> --%>
<%--     	<sj:a --%>
<%--     		formIds="formValidate"  --%>
<%--     		targets="result"  --%>
<%--     		button="true"  --%>
<%--     		buttonIcon="ui-icon-gear" --%>
<%--     		validate="true"  --%>
<%--     		indicator="indicator" --%>
<%--     		>AJAX Submit as Link</sj:a> --%>
<%--     </s:form> --%>
    
<!--      <h2>Bootstrap integration </h2> -->
<%--         <s:form action="index" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal" --%>
<%--             label="A sample horizontal Form"> --%>
<%--         <s:textfield --%>
<%--                 label="Name" --%>
<%--                 name="name" --%>
<%--                 tooltip="Enter your Name here"/> --%>
 
<%--         <s:textfield --%>
<%--                 label="Textfield with Error" --%>
<%--                 name="error"/> --%>
 
<%--         <s:textarea --%>
<%--                 tooltip="Enter your Biography" --%>
<%--                 label="Biography" --%>
<%--                 name="bio" --%>
<%--                 cols="20" --%>
<%--                 rows="3"/> --%>
 
<%--         <s:select --%>
<%--                 tooltip="Choose Your Favourite Color" --%>
<%--                 label="Favorite Color" --%>
<%--                 list="{'Red', 'Blue', 'Green'}" --%>
<%--                 name="favouriteColor" --%>
<%--                 emptyOption="true" --%>
<%--                 headerKey="None" --%>
<%--                 headerValue="None"/> --%>
 
<%--         <s:checkboxlist --%>
<%--                 tooltip="Choose your Friends" --%>
<%--                 label="Friends" --%>
<%--                 list="{'Wes', 'Patrick', 'Jason', 'Jay', 'Toby', 'Rene'}" --%>
<%--                 name="friends"/> --%>
 
<%--         <s:radio --%>
<%--                 tooltip="Choose your Best Friend" --%>
<%--                 label="Best Friend" --%>
<%--                 list="{'Wes', 'Patrick', 'Jason', 'Jay', 'Toby', 'Rene'}" --%>
<%--                 name="bestFriend" --%>
<%--                 cssErrorClass="foo"/> --%>
 
<%--         <s:checkbox --%>
<%--                 tooltip="Confirmed that your are Over 18" --%>
<%--                 label="Age 18+" --%>
<%--                 name="legalAge"/> --%>
 
<%--         <s:submit cssClass="btn"/> --%>
<%--     </s:form> --%>
    <img id="indicator" src="images/icons/loading.gif" alt="Loading..." style="display:none"/> 
</body>
</html>
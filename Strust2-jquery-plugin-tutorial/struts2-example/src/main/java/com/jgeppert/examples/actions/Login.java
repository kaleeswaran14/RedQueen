package com.jgeppert.examples.actions;

import org.apache.commons.logging.*;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.*;
import com.opensymphony.xwork2.validator.annotations.*;

//@ParentPackage(value = "showcase")
@InterceptorRef("jsonValidationWorkflowStack")
@Validations(requiredStrings = {
    @RequiredStringValidator(fieldName = "loginuser", type = ValidatorType.FIELD, message = "Login User is required"),
	@RequiredStringValidator(fieldName = "loginpassword", type = ValidatorType.FIELD, message = "Password is required")
})
public class Login extends ActionSupport {

  private static final long serialVersionUID = 7968544374444173511L;
  private static final Log  log              = LogFactory.getLog(Login.class);

  private String            loginuser;
  private String            loginpassword;
  private String            echo;

  @Action(value = "/login", results = {
    @Result(location = "simpleecho.jsp", name = "success")
  })
  public String execute() throws Exception
  {
    echo = "Welcome " + loginuser;
    log.info(echo);

    return SUCCESS;
  }

  public String getEcho()
  {
    return echo;
  }

  public String getLoginuser()
  {
    return loginuser;
  }

  public void setLoginuser(String loginuser)
  {
    this.loginuser = loginuser;
  }

  public String getLoginpassword()
  {
    return loginpassword;
  }

  public void setLoginpassword(String loginpassword)
  {
    this.loginpassword = loginpassword;
  }
}

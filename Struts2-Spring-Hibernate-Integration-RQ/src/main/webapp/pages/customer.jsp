<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
</head>
 
<body>
<h1>Contact Manager - Struts 2 + Spring + Hibernate integration example</h1>

<s:actionerror/>

<h2>Add Customer</h2>
<s:property value="customer.name" />
<s:form action="addCustomerAction" >
  <s:textfield name="customer.name" label="Name" value="%{customer.name}" />
  <s:textarea name="customer.address" label="Address" value="" cols="50" rows="5" />
  <s:submit />
</s:form>

<s:if test="customerList.size() > 0">
<h2>All Customers</h2>
<table border="1px" cellpadding="8px">
	<tr>
		<th>Customer Id</th>
		<th>Name</th>
		<th>Address</th>
		<th>Created Date</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<s:iterator value="customerList" status="userStatus">
		<tr>
			<td><s:property value="customerId" /></td>
			<td><s:property value="name" /></td>
			<td><s:property value="address" /></td>
			<td><s:date name="createdDate" format="dd/MM/yyyy" /></td>
			<td><a href="editCustomerAction?customerId=<s:property value="customerId"/>">edit</a></td>
			<td><a href="deleteCustomerAction?customerId=<s:property value="customerId"/>">delete</a></td>
		</tr>
	</s:iterator>
</table>
</s:if>
<br/>
<br/>

</body>
</html>
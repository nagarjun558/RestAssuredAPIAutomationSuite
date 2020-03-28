package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.employeeapi.base.TestBase;
import com.employeeapi.utils.Listeners;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.Assert;

public class TC01_GET_All_Employees extends TestBase
{
	
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException
	{
		
		logger.info("*****  Started TC01_Get_All_Employees Data *****");
		
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		 httpRequest = RestAssured.given();
		 response= httpRequest.request(Method.GET,"/employees");
		 
		 Thread.sleep(5);
	}
	
	@Test
	public void checkBodyResponse()
	{
		logger.info("*****  Checking Response Body  *****");
		
		String responseBody = response.getBody().asString();
		logger.info("*** Response from API body ****"+responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("*****  Checking Status Code  *****");
	
		
		
		int statusCode= response.getStatusCode();
		logger.info("Status code====>"+statusCode);
		//Assert.assertEquals(statusCode, 200);
		
		
	}
	
	@Test
	public void checkResponseTime()
	{
		logger.info("*****  Checking Response Time  *****");
		long responseTime= response.getTime();
		logger.info("Response Time====>"+responseTime);
		
		if(responseTime>2000)
			logger.warn("Response time is taking more time");
		
		//Assert.assertTrue(responseTime>=2000);
	}
	
	@Test
	public void checkStatusLine()
	{
		logger.info("*****  Checking Status Line  *****");
		String statusLine = response.getStatusLine();
		logger.info("Status Line====>"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
	}
	
	@Test
	public void checkContentType()
	{
		logger.info("*****  Checking ContentType  *****");
		String contentType= response.header("Content-Type");
		logger.info("Content Type====>"+contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@Test
	public void checkServer()
	{
		logger.info("*****  Checking Server  *****");
		String server= response.header("Server");
		logger.info("server====>"+server);
		Assert.assertEquals(server, "nginx/1.16.0");
	}
	

	@Test
	void checkcontentEncoding()
	{
		logger.info("***********  Checking Content Encoding**********");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is==>" +contentEncoding); 
		Assert.assertEquals(contentEncoding, "gzip");
		
		
	}

	@Test
	void checkContentLenght()
	{
		logger.info("***********  Checking Content Lenght**********");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is==>" +contentLength); 
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
		
	}
		
	@Test
	void checkCookies()
	{
		logger.info("***********  Checking Cookies **********");

		String cookie = response.getCookie("PHPSESSID");
		//Assert.assertEquals(cookie,"1esuvsfslcmiee2bfrsgnijtg0");
		
	}
	
	@AfterClass
	public void tearDown()
	{
		
	}
	

}

package regressionScripts;


import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic.Excel;
import generic.Generic_Test;

import pom.Enter_TIme_Track_Page;
import pom.Login_Page;
import pom.ReportBugPage;

public class Regression extends Generic_Test {

	
	
	@Test()
	public void validLoginLogout()
	{
		test=extent.createTest("validLoginLogout");
		test.log(Status.INFO, "validLoginLogout test script started executing");
		String username = Excel.readData("regression", 1, 0);
		String password = Excel.readData("regression", 1, 1);
		String hp_title = Excel.readData("regression", 1, 3);
		Login_Page lp =new Login_Page(driver);
		lp.setUsername(username);
		test.pass("enterd valid username successfully");
		lp.setPassword(password);
		test.pass("enterd valid password successfully");
		lp.clickLogin();
		test.pass("clicked on login successfully");
		Enter_TIme_Track_Page ep=new Enter_TIme_Track_Page(driver);
		ep.verifyTitle(hp_title, 5);
		test.pass("verified home page title successfully");
		ep.clickLogout();
		test.pass("clicked on logout successfully");
		test.log(Status.INFO, "validLoginLogout test script ended");
		
	}
	
	@Test(enabled = false)
	public void InvalidLogin()
	{
		String username = Excel.readData("regression", 2, 0);
		String password = Excel.readData("regression", 2, 1);
		String lp_title = Excel.readData("regression", 2, 2);
		Login_Page lp=new Login_Page(driver);
		lp.setUsername(username);
		lp.setPassword(password);
		lp.clickLogin();
		lp.verifyErrorMessage();
		lp.verifyTitle(lp_title, 5);
	}
	
	@Test(enabled=false)
	public void verifyActitimeversion()
	{
		String username = Excel.readData("regression", 3, 0);
		String password = Excel.readData("regression", 3, 1);
		String eVersion = Excel.readData("regression", 3, 4);
		Login_Page lp =new Login_Page(driver);
		lp.setUsername(username);
		lp.setPassword(password);
		lp.clickLogin();
		Enter_TIme_Track_Page ep=new Enter_TIme_Track_Page(driver);
		ep.clickHelp();
		ep.clickAboutActitime();
		String aversion = ep.getActtitimeVersion();
		Assert.assertEquals(aversion, eVersion);
		ep.clickClose();
		ep.clickLogout();
	}
	
	@Test()
	public void sendBugReport()
	{
		test=extent.createTest("sendBugReport");
		test.log(Status.INFO, "sendBugReport test script started executing");
		String username = Excel.readData("regression", 4, 0);
		String password = Excel.readData("regression", 4, 1);
		String bugSummary = Excel.readData("regression", 4, 5);
		String fn = Excel.readData("regression", 4, 6);
		String ln = Excel.readData("regression", 4, 7);
		String email = Excel.readData("regression", 4, 8);
		String company = Excel.readData("regression", 4, 9);
		Login_Page lp =new Login_Page(driver);
		lp.setUsername(username);
		test.pass("enterd valid username successfully");
		lp.setPassword(password);
		test.pass("enterd valid password successfully");
		lp.clickLogin();
		test.pass("clicked on login successfully");
		Enter_TIme_Track_Page ep=new Enter_TIme_Track_Page(driver);
		ep.clickHelp();
		test.pass("clicked on help successfully");
		ep.clickReportBug();
		ep.verifyTab(2, 5);
		Set<String> all_window = driver.getWindowHandles();
		for(String win:all_window)
		{
			driver.switchTo().window(win);//write generic method in base page
		}
		Assert.fail();
		ReportBugPage rbp=new ReportBugPage(driver);
		rbp.enterBugDescription(bugSummary);
		test.pass("enterd report description successfully");
		rbp.enterFirstName(fn);
		test.pass("enterd first name successfully");
		rbp.enterLastName(ln);
		test.pass("enterd last name successfully");
		rbp.enterCompany(company);
		test.pass("enterd company name successfully");
		rbp.enterEmail(email);
		test.pass("enterd email successfully");
		rbp.clickSend();
		test.pass("clicked on send successfully");
		test.log(Status.INFO, "sendBugReport test script ended !!");
		
	}
}

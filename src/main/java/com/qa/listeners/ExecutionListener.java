package com.qa.listeners;

import static com.qa.config.ConfigurationManager.configuration;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.logging.Logger;

import org.testng.IExecutionListener;

import com.amazonaws.services.s3.AmazonS3;
import com.qa.reports.ExtentReport;
import com.qa.util.AwsS3Service;

public class ExecutionListener implements IExecutionListener {

	public String ReportName = null;
	private Logger logs = Logger.getLogger("logs");

	@Override
	public void onExecutionStart() {
		logs.info("<<<<<<<<<<<<<<<<<<<< FROST UI Automation Test Started >>>>>>>>>>>>>>>>>>>>");
		//System.out.println("************Execution Started ************");
	}

	@Override
	public void onExecutionFinish() {
		//System.out.println("************Execution Finished ************");
		storeInS3();
		deleteReportFromLocal();
		logs.info("<<<<<<<<<<<<<<<<<<<< FROST UI Automation Test Ended >>>>>>>>>>>>>>>>>>>>");
	}

	public void storeInS3() {
		String value =configuration().upload_test_reports();
		try {
			switch(value.toUpperCase())
			{

			case "YES":
				String FullPath = System.getProperty("user.dir") + "/TestReports/"+ExtentReport.fileName;
				AmazonS3 aws=AwsS3Service.createConnection(configuration().S3_AccessKey(), configuration().S3_SecretKey(), configuration().S3_SessionToken());
				AwsS3Service.uploadFileToBucket(configuration().S3_BucketName(), ExtentReport.fileName, FullPath, aws);
				break;
			case "NO":
				//System.out.println("This Execution Report is not stored in cloud. If you want to store in cloud, Select 'YES' for upload-test-reports parameter!!!");
				logs.info("This Execution Report is not stored in cloud. If you want to store in cloud, Select 'YES' for upload-test-reports parameter!!!");
				break;
			default:
				throw new IllegalStateException("Unexpected value for `upload-test-reports`: " + value);

			}
		}catch(Exception e) {
			//System.out.println("Exception Occured : \n" + e.getMessage());
			logs.info("Exception Occured : \n" + e.getMessage());

		}
	}


	public void deleteReportFromLocal() {
		String deleteReport =configuration().delete_local_report();
		String s3Storage = configuration().upload_test_reports();
		boolean deleted;

		switch(deleteReport.toUpperCase())
		{
		case "YES":
			if(!s3Storage.equalsIgnoreCase("YES")){
				//System.out.println("Local Execution Report can't be deleted when 'upload-test-reports' is not enabled!! ");
				logs.info("Local Execution Report can't be deleted when 'upload-test-reports' is not enabled!! ");
			}else {
				try
				{
					deleted = Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + "/TestReports/"+ExtentReport.fileName));
					if(deleted) {
						//System.out.println("Local Test Report Successfully Deleted!!");
						logs.info("Local Test Report Successfully Deleted!!");
					}else
					{
						//System.out.println("Oops!! Unable to Delete Local Test Report, Please check the configured path");
						logs.info("Oops!! Unable to Delete Local Test Report, Please check the configured path");
					}
				}
				catch(NoSuchFileException e)
				{
					//System.out.println("No such file/directory exists");
					logs.info("No such file/directory exists");
				}
				catch(DirectoryNotEmptyException e)
				{
					//System.out.println("Directory is not empty.");
					logs.info("Directory is not empty.");
				}
				catch(IOException e)
				{
					//System.out.println("Invalid permissions.");
					logs.info("Invalid permissions.");
				}
			}
			break;

		case "NO":
			break;
		default:
			throw new IllegalStateException("Unexpected value for `delete-local-report`: " + deleteReport);
		}

	}

}

package com.qa.util;

import java.io.File;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.IllegalBucketNameException;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AwsS3Service {
	private static BasicSessionCredentials creds;
	private static AmazonS3 amazonS3;


	public static AmazonS3 createConnection(String AccessKey, String SecretKey, String SessionToken) {
		try {
			creds = new BasicSessionCredentials(AccessKey, SecretKey, SessionToken);
			amazonS3 = AmazonS3Client.builder().withRegion("us-east-1")
					.withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		} catch (AmazonClientException ace) {

			System.out.println("The Amazon Client encountered an Error with network Connectivity");
			System.out.println("Error Message:" + ace.getMessage());
		}
		return amazonS3;

	}

	public static void createBucket(String Bucketname, AmazonS3 amazonS3) {
		try {
			amazonS3.createBucket(Bucketname);
			System.out.println("Bucket Created Successfully.");
		} catch (AmazonServiceException e) {

			System.out.println("This means that your request made it AWS S3 but got rejected");
			System.out.println("Error Message:" + e.getMessage());
			System.out.println("Error Message:" + e.getErrorCode());
			System.out.println("Error Message:" + e.getErrorType());
			System.out.println("Error Message:" + e.getRequestId());
		} catch (IllegalBucketNameException i) {
			System.out.println("Error Message: " + i.getMessage());
		}
	}

	public static void listBuckets(AmazonS3 amazonS3) {
		try {
			List<Bucket> buckets = amazonS3.listBuckets();
			System.out.println("Below are the Available Buckets");
			for (Bucket b : buckets) {
				System.out.println("* " + b.getName());
			}
		} catch (AmazonServiceException e) {

			System.out.println("This means that your request made it AWS S3 but got rejected");
			System.out.println("Error Message:" + e.getMessage());
			System.out.println("Error Message:" + e.getErrorCode());
			System.out.println("Error Message:" + e.getErrorType());
			System.out.println("Error Message:" + e.getRequestId());
		}

	}

	public static void listFilesInBucket(String bucketName, AmazonS3 amazonS3) {
		try {
			ObjectListing object = amazonS3.listObjects(bucketName);
			List<S3ObjectSummary> files = object.getObjectSummaries();
			System.out.println("Below are Available files in *" + bucketName + "* bucket");
			for (S3ObjectSummary summary : files) {
				System.out.println("* " + summary.getKey());
			}
		} catch (AmazonServiceException e) {

			System.out.println("This means that your request made it AWS S3 but got rejected");
			System.out.println("Error Message:" + e.getMessage());
			System.out.println("Error Message:" + e.getErrorCode());
			System.out.println("Error Message:" + e.getErrorType());
			System.out.println("Error Message:" + e.getRequestId());
		}
	}

	public static void uploadFileToBucket(String BucketName, String KeyName, String FilePath, AmazonS3 amazonS3) {
		try {
			amazonS3.putObject(BucketName, KeyName, new File(FilePath));
			System.out.println("File Uploaded Successfully");
		} catch (AmazonServiceException e) {

			System.out.println("This means that your request made it AWS S3 but got rejected");
			System.out.println("Error Message:" + e.getMessage());
			System.out.println("Error Message:" + e.getErrorCode());
			System.out.println("Error Message:" + e.getErrorType());
			System.out.println("Error Message:" + e.getRequestId());
		}
	}

	public static void uploadFileToBucket(String BucketName, String FolderName, String KeyName, String FilePath,
			AmazonS3 amazonS3) {
		try {
			amazonS3.putObject(BucketName, FolderName + "/" + KeyName, new File(FilePath));
			System.out.println("File Uploaded Successfully");
		} catch (AmazonServiceException e) {

			System.out.println("This means that your request made it AWS S3 but got rejected");
			System.out.println("Error Message:" + e.getMessage());
			System.out.println("Error Message:" + e.getErrorCode());
			System.out.println("Error Message:" + e.getErrorType());
			System.out.println("Error Message:" + e.getRequestId());
		}
	}

	public static void deleteFile(String BucketName, String KeyName, AmazonS3 amazonS3) {
		try {
			amazonS3.deleteObject(BucketName, KeyName);
			System.out.println("File *" + KeyName + "* Deleted Successfully!!");
		} catch (AmazonServiceException e) {

			System.out.println("This means that your request made it AWS S3 but got rejected");
			System.out.println("Error Message:" + e.getMessage());
			System.out.println("Error Message:" + e.getErrorCode());
			System.out.println("Error Message:" + e.getErrorType());
			System.out.println("Error Message:" + e.getRequestId());
		}

	}

	public static void deleteBucket(String BucketName, AmazonS3 amazonS3) {
		try {
			amazonS3.deleteBucket(BucketName);
			System.out.println("Bucket *" + BucketName + "* Deleted Successfully!!");
		} catch (AmazonServiceException e) {

			System.out.println("This means that your request made it AWS S3 but got rejected");
			System.out.println("Error Message:" + e.getMessage());
			System.out.println("Error Message:" + e.getErrorCode());
			System.out.println("Error Message:" + e.getErrorType());
			System.out.println("Error Message:" + e.getRequestId());
		}
	}


}

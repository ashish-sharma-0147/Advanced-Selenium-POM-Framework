package com.qa.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
	"system:properties",
	"classpath:general.properties",
	"classpath:local.properties",
	"classpath:grid.properties",
"classpath:aws.properties"})
public interface Configuration extends Config {

	@Key("RunMode")
	String RunMode();

	@Key("url")
	String AppUrl();

	@Key("upload-test-reports")
	String upload_test_reports();

	@Key("delete-local-report")
	String delete_local_report();

	@Key("username")
	String username();
	
	@Key("password")
	String password();
	
	@Key("RemoteURL")
	String GridURL();

	@Key("isHeadless")
	Boolean isHeadless();

	@Key("AWSAccessKey")
	String DeviceFarm_AccessKey();

	@Key("AWSSecretKey")
	String DeviceFarm_SecretKey();

	@Key("AWSSessionToken")
	String DeviceFarm_SessionToken();

	@Key("AWSProjectARN")
	String DeviceFarm_ProjectARN();

	@Key("S3AccessKey")
	String S3_AccessKey();

	@Key("S3SecretKey")
	String S3_SecretKey();

	@Key("S3SessionToken")
	String S3_SessionToken();

	@Key("S3BucketName")
	String S3_BucketName();
	
	@Key("organization")
	String MyOrg();
	
	@Key("team")
	String MyTeam();
	
	@Key("project")
	String MyProject();
}
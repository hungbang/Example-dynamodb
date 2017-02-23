import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AWS3Signer;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClientBuilder;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenResult;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.ScanFilter;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.s3.AmazonS3;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by HungBang on 2/23/2017.
 */
public class AmazonDynamoDBSample {

    public static final String role_arn = "arn:aws:iam::221271379236:role/Cognito_KodomiruSenseiUnauth_Role";
    public static final String RoleSessionName = "boto";
    public static final String tableName = "kodomiru-sensei-students-stg";
    public static final String topic = "0008900001";

    static AmazonDynamoDBClient dynamoDB;
    static String token;
    public static void init() throws Exception {
//        GetOpenIdTokenRequest tokenRequest = new GetOpenIdTokenRequest();
//        tokenRequest.setIdentityId(GetIDBasedOnIdentityPoolID.getID());
//        AmazonCognitoIdentityClient identityClient = (AmazonCognitoIdentityClient) AmazonCognitoIdentityClientBuilder
//                .standard()
//                .withRegion(Regions.AP_NORTHEAST_1)
//                .build();
//        GetOpenIdTokenResult tokenResp = identityClient.getOpenIdToken(tokenRequest);
        token = GetIDBasedOnIdentityPoolID.getTokenID();
    }

   public static void getDynamoDB(){
       dynamoDB = new AmazonDynamoDBClient(
               new AssumeRoleWithWebIdentity.Builder()
                       .roleArn(role_arn)
                       .roleSessionName(RoleSessionName)
                       .webIdentityToken(token)
                       .build()
                       .getSessionCredentials());
   }

    public static void main(String[] args) throws Exception {
        init();getDynamoDB();
        HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
        Condition condition = new Condition()
                .withAttributeValueList(new AttributeValue().addMEntry("S", new AttributeValue(topic)))
                .withComparisonOperator(ComparisonOperator.EQ);
        scanFilter.put("topic", condition);

        ScanRequest request = new ScanRequest().withScanFilter(scanFilter);
        ScanResult result = dynamoDB.scan(request);
        System.out.println("Result: " + result);

    }

}

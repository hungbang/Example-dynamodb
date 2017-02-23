import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AWS3Signer;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenResult;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.s3.AmazonS3;

import java.io.IOException;

/**
 * Created by HungBang on 2/23/2017.
 */
public class AmazonDynamoDBSample {

    public static final String account_id = "2212-7137-9236";
    public static final String identity_pool_id = "ap-northeast-1:479b37dd-f443-40f3-977b-57c51b03404e";
    public static final String role_arn = "arn:aws:iam::221271379236:role/Cognito_KodomiruSenseiUnauth_Role";
    public static final String aws_region = "ap-northeast-1";
    public static final String RoleSessionName = "boto";
    public static final String tableName = "kodomiru-sensei-students-stg";
    public static final String topic = "0008900001";

    static AmazonDynamoDBClient dynamoDB;
    static String token;
    public static void init() throws IOException {
//        AWSCredentials credentials = new PropertiesCredentials(AmazonDynamoDBSample.class.getResourceAsStream("AwsCredentials.properties"));
        GetOpenIdTokenRequest tokenRequest = new GetOpenIdTokenRequest();
        tokenRequest.setIdentityId(GetIDBasedOnIdentityPoolID.getID());
        GetOpenIdTokenResult tokenResp = GetIDBasedOnIdentityPoolID.identityClient.getOpenIdToken(tokenRequest);
        token = tokenResp.getToken();
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

    public static void main(String[] args) throws IOException {
        init();getDynamoDB();
        CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
                .withKeySchema(new KeySchemaElement().withAttributeName(topic).withKeyType(KeyType.))

    }

}

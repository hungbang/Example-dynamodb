import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClientBuilder;
import com.amazonaws.services.cognitoidentity.model.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by HungBang on 2/23/2017.
 */
public class GetIDBasedOnIdentityPoolID {
    public static final String identity_pool_id = "ap-northeast-1:479b37dd-f443-40f3-977b-57c51b03404e";
    public static final String account_id = "221271379236";
    public static final String aws_region = "ap-northeast-1";
    public static AmazonCognitoIdentity identityClient = new AmazonCognitoIdentityClient();

    public static String getTokenID(){
    AmazonCognitoIdentityClient client = new AmazonCognitoIdentityClient();
    client.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));
    GetOpenIdTokenForDeveloperIdentityRequest tokenRequest = new GetOpenIdTokenForDeveloperIdentityRequest();
    tokenRequest.withIdentityPoolId(identity_pool_id);
    GetOpenIdTokenForDeveloperIdentityResult result = client.getOpenIdTokenForDeveloperIdentity(tokenRequest);

    String token = result.getToken();
    return token;
}




}

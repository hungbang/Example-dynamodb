import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HungBang on 2/23/2017.
 */
public class GetIDBasedOnIdentityPoolID {
    public static final String identity_pool_id = "ap-northeast-1:479b37dd-f443-40f3-977b-57c51b03404e";
    public static final String account_id = "221271379236";

    public static AmazonCognitoIdentity identityClient = new AmazonCognitoIdentityClient();

    public static String getTokenID() {
        AmazonCognitoIdentityClient client = new AmazonCognitoIdentityClient();
        Map<String, String> logins = new HashMap<>();
        logins.put("KindergartenID", account_id);
        client.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));
        GetOpenIdTokenForDeveloperIdentityRequest tokenRequest = new GetOpenIdTokenForDeveloperIdentityRequest();
        tokenRequest.withIdentityPoolId(identity_pool_id);
        tokenRequest.setLogins(logins);
        GetOpenIdTokenForDeveloperIdentityResult result = client.getOpenIdTokenForDeveloperIdentity(tokenRequest);
        String token = result.getToken();
        return token;
    }

    public static String getTokenID1() {
        AmazonCognitoIdentity identityClient = new AmazonCognitoIdentityClient(new AnonymousAWSCredentials());
        identityClient.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));
        GetIdRequest idRequest = new GetIdRequest();

        idRequest.setAccountId(account_id);
        idRequest.setIdentityPoolId(identity_pool_id);
        GetIdResult idResp = identityClient.getId(idRequest);
        String identityId = idResp.getIdentityId();



        GetOpenIdTokenRequest tokenRequest = new GetOpenIdTokenRequest().withIdentityId(identityId);
        GetOpenIdTokenResult result = identityClient.getOpenIdToken(tokenRequest);
        String token = result.getToken();
        return token;
    }




}

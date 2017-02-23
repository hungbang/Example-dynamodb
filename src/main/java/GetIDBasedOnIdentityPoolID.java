import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.GetIdRequest;
import com.amazonaws.services.cognitoidentity.model.GetIdResult;

/**
 * Created by HungBang on 2/23/2017.
 */
public class GetIDBasedOnIdentityPoolID {
    public static final String identity_pool_id = "ap-northeast-1:479b37dd-f443-40f3-977b-57c51b03404e";
    public static final String account_id = "2212-7137-9236";
    public static AmazonCognitoIdentity identityClient = new AmazonCognitoIdentityClient(new AnonymousAWSCredentials());

    public static String getID(){
        GetIdRequest idRequest = new GetIdRequest();
        idRequest.setIdentityPoolId(identity_pool_id);
        idRequest.setAccountId(account_id);
        GetIdResult idResp = identityClient.getId(idRequest);
        String identityId = idResp.getIdentityId();
        System.out.println("==identityId==identityId==="+ identityId);
        return identityId;
    }


}

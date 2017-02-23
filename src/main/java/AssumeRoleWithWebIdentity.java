import com.amazonaws.auth.*;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityResult;
import com.amazonaws.services.securitytoken.model.Credentials;

/**
 * Created by HungBang on 2/23/2017.
 */
public class AssumeRoleWithWebIdentity {
    private final String roleArn;
    private final String webIdentityToken;
    private final String roleSessionName;

    static AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.defaultClient();

    public AssumeRoleWithWebIdentity(Builder builder) {
        this.roleArn = builder.roleArn;
        this.roleSessionName = builder.roleSessionName;
        this.webIdentityToken = builder.webIdentityToken;
    }

    public static class Builder{
        private String roleArn;
        private String webIdentityToken;
        private String roleSessionName;

        public Builder(){}

        public Builder roleArn(String val){
            roleArn = val;
            return this;
        }
        public Builder webIdentityToken(String val){
            webIdentityToken = val;return this;
        }
        public Builder roleSessionName(String val){
            roleSessionName = val;return this;
        }

        public AssumeRoleWithWebIdentity build(){
            return new AssumeRoleWithWebIdentity(this);
        }
    }

    public AWSCredentials getSessionCredentials(){
        AssumeRoleWithWebIdentityRequest stsReq = new AssumeRoleWithWebIdentityRequest();
        stsReq.setRoleArn(roleArn);
        stsReq.setWebIdentityToken(webIdentityToken);
        stsReq.setRoleSessionName(roleSessionName);
        AssumeRoleWithWebIdentityResult stsResp = stsClient.assumeRoleWithWebIdentity(stsReq);
        Credentials stsCredentials = stsResp.getCredentials();
        AWSCredentials credentials = new BasicSessionCredentials(stsCredentials.getAccessKeyId(),
                stsCredentials.getSecretAccessKey(),
                stsCredentials.getSessionToken());
        return credentials;
    }
}

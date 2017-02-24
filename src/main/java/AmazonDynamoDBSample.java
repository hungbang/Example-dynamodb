import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.*;

/**
 * Created by HungBang on 2/23/2017.
 */
public class AmazonDynamoDBSample {

    public static final String role_arn = "arn:aws:iam::221271379236:role/Cognito_KodomiruSenseiUnauth_Role";
    public static final String RoleSessionName = "boto";
    public static final String tableName = "kodomiru-sensei-students-stg";
    public static final String topic = "0008900001";
    public static final String aws_region = "ap-northeast-1";

    static AmazonDynamoDBClient dynamoDB;
    static String token;
    public static void init() throws Exception {
        token = GetIDBasedOnIdentityPoolID.getTokenID1();
    }

   public static void getDynamoDB(){
       dynamoDB = new AmazonDynamoDBClient(
               new AssumeRoleWithWebIdentity.Builder()
                       .roleArn(role_arn)
                       .roleSessionName(RoleSessionName)
                       .webIdentityToken(token)
                       .build()
                       .getSessionCredentials());
       dynamoDB.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));
   }

    public static void main(String[] args) throws Exception {
        init();getDynamoDB();
        Map<String, Condition> scanFilter = new HashMap<String, Condition>();
        Map<String, AttributeValue> attribute = new HashMap<String, AttributeValue>();
        attribute.put("S", new AttributeValue().withN(topic));
        Condition condition = new Condition()
                .withComparisonOperator(ComparisonOperator.EQ);
        condition.withAttributeValueList(new AttributeValue().withS(topic));
        scanFilter.put("topic", condition);

        ScanRequest request = new ScanRequest().withTableName(tableName).withScanFilter(scanFilter);
        ScanResult result = dynamoDB.scan(request);
        System.out.println("Result: " + result);

    }

}

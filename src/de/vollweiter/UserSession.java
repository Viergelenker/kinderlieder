package de.vollweiter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "USER_SESSION")
public class UserSession {

    private String userId;
    private long offset;
    private String token;
    private String previousToken;

    @DynamoDBHashKey(attributeName = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "OFFSET")
    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @DynamoDBAttribute(attributeName = "TOKEN")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @DynamoDBAttribute(attributeName = "PREVIOUS_TOKEN")
    public String getPreviousToken() {
        return previousToken;
    }

    public void setPreviousToken(String previousToken) {
        this.previousToken = previousToken;
    }
}

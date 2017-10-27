package de.vollweiter;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DynamoDbService {

    private Logger logger = LoggerFactory.getLogger(DynamoDbService.class);

    private AmazonDynamoDB database;
    private DynamoDBMapper mapper;
    private DynamoDB dynamoDB;

    private final String DATABASE_TABLE_NAME = "USER_SESSION";

    public void init() {

        database = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build();

        dynamoDB = new DynamoDB(database);
    }

    public void updateSession(String userId, long offset, String token, String previousToken, List<String> songQueue) throws Exception {

        logger.info("Updating user session...");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        UserSession item = new UserSession();
        item.setUserId(userId);
        item.setOffset(offset);
        item.setToken(token);
        item.setPreviousToken(previousToken);
        item.setSongQueue(songQueue);

        mapper.save(item);
    }

    public UserSession getUserSession(String userId) throws Exception {

        logger.info("Trying to get userSession values...");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        UserSession userSession = mapper.load(UserSession.class, userId);

        return userSession;
    }
}

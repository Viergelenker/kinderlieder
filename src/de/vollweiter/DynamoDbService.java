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

    public void updateSession(String userId, long offset, String token, String previousToken) throws Exception {

        logger.info("Updating user session...");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        UserSession item = new UserSession();
        item.setUserId(userId);
        item.setOffset(offset);
        item.setToken(token);
        item.setPreviousToken(previousToken);

        mapper.save(item);
    }

    public UserSession getUserSession(String userId) throws Exception {

        UserSession userSession = new UserSession();
        userSession.setUserId(userId);

        DynamoDBQueryExpression<UserSession> queryExpression = new DynamoDBQueryExpression<UserSession>()
                .withHashKeyValues(userSession);

        List<UserSession> itemList = mapper.query(UserSession.class, queryExpression);

        logger.info("Reading values...");

        for (int i = 0; i < itemList.size(); i++) {
            logger.info(itemList.get(i).getUserId());
            logger.info(String.valueOf(itemList.get(i).getOffset()));
            logger.info(itemList.get(i).getToken());
            logger.info(itemList.get(i).getPreviousToken());

        }

        userSession.setOffset(itemList.get(1).getOffset());
        userSession.setToken(itemList.get(2).getToken());
        userSession.setPreviousToken(itemList.get(3).getPreviousToken());
        return userSession;
    }
}

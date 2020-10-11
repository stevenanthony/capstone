package com.example.demo.entities.V2;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;
import java.io.Serializable;

@DynamoDBTable(tableName = "bill")

public class BillV2 {
    @DynamoDBHashKey  (attributeName = "id")
    public String getId()
    {
        return id;
    }
    @DynamoDBAutoGeneratedKey
    public String id;

    @DynamoDBAttribute (attributeName="user_id")
    public String getUserId()
    {
        return this.user_id;
    }
    public String user_id;

    @DynamoDBAttribute (attributeName="date")
    public Date getDate()
    {
        return this.date;
    }
    public Date date;

    @DynamoDBAttribute (attributeName="amount")
    public double getAmount()
    {
        return this.amount;
    }
    public double amount;

    @DynamoDBAttribute (attributeName="type")
    public String getType()
    {
        return this.type;
    }
    public String type;

    @DynamoDBAttribute (attributeName="number")
    public String getNumber()
    {
        return this.number;
    }
    public String number;

    @DynamoDBAttribute (attributeName="location")
    public String getLocation()
    {
        return this.location;
    }
    public String location;

    @DynamoDBAttribute (attributeName="month")
    public int getMonth()
    {
        return this.month;
    }
    public int month;

    @DynamoDBAttribute (attributeName="label")
    public int getLabel()
    {
        return this.label;
    }
    public int label;
}
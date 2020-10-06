package com.example.demo.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;


@DynamoDBTable(tableName = "user")
public class UserV2 implements Serializable {
    public UserV2(String id, String first_name, String last_name, int total_bill, int cluster_id) {
        this.userid = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.total_bill = total_bill;
        this.cluster_id = cluster_id;
    }


    @DynamoDBHashKey  (attributeName = "userid")
    public String getId()
    {
        return userid;
    }
    @DynamoDBAutoGeneratedKey
    public String userid;
    @DynamoDBAttribute (attributeName="first_name")
    public String getFirst_name()
    {
        return this.first_name;
    }
    public String first_name;
    @DynamoDBAttribute (attributeName="last_name")
    public String getLast_name() {
        return last_name;
    }
    public String last_name;
    @DynamoDBAttribute (attributeName="total_bill")
    public int getTotal_bill() {
        return total_bill;
    }
    public int total_bill;
    @DynamoDBAttribute (attributeName="cluster_id")
    public int getCluster_id() {
        return cluster_id;
    }
    public int cluster_id;



}
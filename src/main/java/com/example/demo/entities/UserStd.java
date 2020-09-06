package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_std")
public class UserStd{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "electricity")
    private double electricity;

    @Column(name = "internet")
    private double internet;

    @Column(name = "water")
    private double water;

    @Column(name = "gas")
    private double gas;

    @OneToOne (optional = false)
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    public UserStd()
    {

    }

    public UserStd (int id){
        this.electricity = 0;
        this.internet = 0;
        this.water = 0;
        this.gas = 0;
    }

    public void calculateStd(List<Double> amounts, String type)
    {
        DescriptiveStatistics stats = new DescriptiveStatistics();

        // Add the data from the array
        for (Double amount : amounts) {
            stats.addValue(amount);
        }
        //std
        double std = stats.getStandardDeviation();
        switch (type.toLowerCase())
        {
            case "phone and internet":
                setInternet(std);
                break;
            case "water":
                setWater(std);
                break;
            case "gas":
                setGas(std);
                break;
            case "electricity":
                setElectricity(std);
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getElectricity() {
        return electricity;
    }

    public void setElectricity(double electricity) {
        this.electricity = electricity;
    }

    public double getInternet() {
        return internet;
    }

    public void setInternet(double internet) {
        this.internet = internet;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getGas() {
        return gas;
    }

    public void setGas(double gas) {
        this.gas = gas;
    }

}

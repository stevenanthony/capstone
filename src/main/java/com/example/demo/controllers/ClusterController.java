package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.example.demo.entities.User;
import com.example.demo.entities.UserMean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.Cluster;
import com.example.demo.repositories.ClusterRepository;

@RestController
public class ClusterController {

    private final ClusterRepository clusterRepository;
    private final UserController userController;

    @Autowired
    public ClusterController(ClusterRepository clusterRepository, UserController userController) {
        this.clusterRepository = clusterRepository;
        this.userController = userController;
    }

    public void createCluster(User userA, List<User> users){
        List<Double> distances = new ArrayList<>();
        Cluster cluster = new Cluster();
        User userB;
        User userC;
        users.remove(userA);
        users.removeIf(user -> user.getCluster() != null);

        userA.setCluster(cluster);
        cluster.getUsers().add(userA);
        if (users.isEmpty()){
            add(cluster);
            cluster.calculateCluster();
            edit(cluster, cluster.getId());
            return;
        }

        for (User user : users) {
            if (!user.isNewUser()) {
                UserMean userMean = user.getUserMean();
                UserMean userMeanA = userA.getUserMean();
                double electricityDistance = Math.pow(userMean.getElectricity() - userMeanA.getElectricity(), 2);
                double waterDistance = Math.pow(userMean.getWater() - userMeanA.getWater(), 2);
                double gasDistance = Math.pow(userMean.getGas() - userMeanA.getGas(), 2);
                double internetDistance = Math.pow(userMean.getInternet() - userMeanA.getInternet(), 2);
                double distance = Math.sqrt(electricityDistance + waterDistance + gasDistance + internetDistance);
                distances.add(distance);
            }
        }

        int index1 = distances.indexOf(Collections.min(distances));
        userB = users.get(index1);
        userB.setCluster(cluster);
        cluster.getUsers().add(userB);
        distances.remove(index1);
        users.remove(index1);

        if (users.isEmpty()){
            add(cluster);
            cluster.calculateCluster();
            edit(cluster, cluster.getId());
            return;
        }

        int index2 = distances.indexOf(Collections.min(distances));
        userC = users.get(index2);
        userC.setCluster(cluster);
        cluster.getUsers().add(userC);

        add(cluster);
        cluster.calculateCluster();
        edit(cluster, cluster.getId());
    }

    @CrossOrigin
    @GetMapping("/clusters")
    public List<Cluster> clusters() {
        List<User> users = userController.users();
        users.removeIf(user -> user.getCluster() != null);
        if (!users.isEmpty()) {
            List<User> userList = new ArrayList<>(users);
            for (User user : users) {
                if (user.getCluster() == null) {
                    createCluster(user, userList);
                }
            }
        }
        return clusterRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/clusters/{id}")
    public ResponseEntity<Cluster> getClusterById(@PathVariable("id") final int id)
    {
        Cluster cluster = clusterRepository.findById(id).get();
        return ResponseEntity.ok().body(cluster);
    }

    @CrossOrigin
    @PostMapping("/clusters")
    public void add(@RequestBody Cluster cluster)
    {
        clusterRepository.save(cluster);
    }

    @CrossOrigin
    @PutMapping("/clusters/{id}")
    public void edit(@RequestBody Cluster cluster, @PathVariable("id") final int id)
    {
        Cluster existedCluster = clusterRepository.findById(id).get();
        existedCluster.setId(cluster.getId());
        existedCluster.setElectricityClusterMean(cluster.getElectricityClusterMean());
        existedCluster.setInternetClusterMean(cluster.getInternetClusterMean());
        existedCluster.setWaterClusterMean(cluster.getWaterClusterMean());
        existedCluster.setGasClusterMean(cluster.getGasClusterMean());
        existedCluster.setElectricityClusterStd(cluster.getElectricityClusterStd());
        existedCluster.setInternetClusterStd(cluster.getInternetClusterStd());
        existedCluster.setWaterClusterStd(cluster.getWaterClusterStd());
        existedCluster.setGasClusterStd(cluster.getGasClusterStd());
        clusterRepository.save(existedCluster);
    }

    @CrossOrigin
    @DeleteMapping("/clusters/{id}")
    public void delete (@PathVariable("id") final Integer id)
    {
        clusterRepository.deleteById(id);
    }

}

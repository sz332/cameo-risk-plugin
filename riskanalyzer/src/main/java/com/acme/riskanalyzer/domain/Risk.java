/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acme.riskanalyzer.domain;

/**
 * @author Zsolt
 */
public class Risk {

    private final String id;
    private final String modelId;
    private final int likelihood;
    private final int maxConsequence;

    private final String name;

    public Risk(String modelId, String id, String name, int likelihood, int maxConsequence) {
        this.modelId = modelId;
        this.id = id;
        this.name = name;
        this.likelihood = likelihood;
        this.maxConsequence = maxConsequence;
    }

    public String getId() {
        return id;
    }

    public String getModelId() {
        return modelId;
    }

    public int getLikelihood() {
        return likelihood;
    }

    public int getMaxConsequence() {
        return maxConsequence;
    }

    public String getName() {
        return name;
    }
}

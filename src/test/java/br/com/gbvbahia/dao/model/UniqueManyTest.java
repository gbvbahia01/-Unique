/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.dao.model;

import br.com.gbvbahia.unique.Unique;;

/**
 *
 * @author Guilherme
 */
@Unique(fields = {"one","three","four","six"})
public class UniqueManyTest {
    
    private Integer id;
    private Long one = 1L;
    private int two = 2;
    private Double three = 3.0;
    private float four = 4.0F;
    private Character five = '5';
    private String six = "6";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOne() {
        return one;
    }

    public void setOne(Long one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public Double getThree() {
        return three;
    }

    public void setThree(Double three) {
        this.three = three;
    }

    public float getFour() {
        return four;
    }

    public void setFour(float four) {
        this.four = four;
    }

    public Character getFive() {
        return five;
    }

    public void setFive(Character five) {
        this.five = five;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }
    
    
}

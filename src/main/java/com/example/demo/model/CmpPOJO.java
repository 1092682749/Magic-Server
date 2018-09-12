package com.example.demo.model;

public class CmpPOJO {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj.getClass() != getClass()){
            return false;
        }
        CmpPOJO cmpPOJO = (CmpPOJO)obj;
        return id.equals(cmpPOJO.getId());
    }
}

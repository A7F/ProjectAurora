package com.luke.projectaurora.entities;

/**
 *
 * @author Luca
 */
public enum LedLayout {
    ALL(0,"Individual"),
    MONO(1,"monochromatic"),
    SPLIT_VERTICAL(2,"split vertical"),
    SPLIT_SIDES(3,"3 sides");
    
    
    private final String layoutName;
    private final int layoutId;
    
    LedLayout(int id, String name){
        this.layoutId=id;
        this.layoutName=name;
    }
    
    public int getId(){
        return this.layoutId;
    }
    
    @Override
    public String toString(){
        return this.layoutName;
    }
}

package com.example.dungeon.model;

public class Door extends Item {
    public Door(String name) {
        super(name);
    }
    private String correctKey;
    public String getCorrectKey() {
        return correctKey;
    }
        public void setCorrectKey(String correctKey) {
        this.correctKey = correctKey;
    }
    public boolean isCorrectKey(String keyName){
        if (keyName.equals(correctKey))
            {System.out.println("Ключ подходит");
                return true;    
            } 
        else 
            {System.out.println("Ключ не подходит");
                return false;
            }
    };

    @Override
    public void apply(GameState ctx) {
        System.out.println("В центре двери замочная скважина");
    }

}
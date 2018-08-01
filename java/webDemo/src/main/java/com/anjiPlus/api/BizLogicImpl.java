package com.anjiPlus.api;

public class BizLogicImpl implements BizLogic {
    @Override
    public String save() {
        System.out.println("BizLogicImpl: Logic save");
        return "BizLogicImpl save";
    }
}

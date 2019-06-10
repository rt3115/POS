package functions;

public enum AccessLevel {

    NOTLOGGEDIN(0),
    NORMAL(1), //regular usage
    MANAGER(2), //manager level functions (no sale, changing items, adding items)
    OWNER(3), //owner level control (everything before plus, changing permissions, added Manager keys, can see the system log)
    ROOT(4); //root level, sees all debug (cant see or change Manager Keys or Owner Keys, only has some Manager levels controls, can see logs, and can see Debug Information)
    //ROOT can be logged in at the same time as OWNER

    private Integer level;

    AccessLevel(int level){
        this.level = level;
    }

    public boolean isGreateThan(AccessLevel other){
        return this.level <= other.level;
    }

}

package functions;

public enum AccessLevel {

    NORMAL, //regular usage
    MANAGER, //manager level functions (no sale, changing items, adding items)
    OWNER, //owner level control (everything before plus, changing permissions, added Manager keys, can see the system log)
    ROOT; //root level, sees all debug (cant see or change Manager Keys or Owner Keys, only has some Manager levels controls, can see logs, and can see Debug Information)
    //ROOT can be logged in at the same time as OWNER
}

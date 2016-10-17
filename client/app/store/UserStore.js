Ext.define("MyHealth.store.UserStore", {
    extend: "Ext.data.Store",
    
    model: "MyHealth.model.User",
    data: [
        {
            firstName: "Honza",
            lastName: "Lastovicka",
            email: "lastovicka@avast.com",
            birthday: new Date(1983, 11, 11)
        }
    ]    
});
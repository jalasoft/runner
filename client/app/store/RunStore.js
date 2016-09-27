Ext.define("MyHealth.store.RunStore", {
    extend: "Ext.data.Store",
    
    model: "MyHealth.model.Run",
    data: [
        {
            description: "Stitary, pahorek",
            distance: 6.45,
            date: new Date(2016, 9, 26)
        },
        {
            description: "Peklo",
            distance: 4.55,
            date: new Date(2-16, 8, 22)
        },
        {
            description: "Spalenka",
            distance: 7.02,
            date: new Date(2016, 8, 15)
        }
    ]
});
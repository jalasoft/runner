Ext.define("MyHealth.model.Run", {
   extend: "Ext.data.Model",

    fields: [
        { 
            name: "description",
            type: "string"
        },
        {
            name: "distance",
            type: "float"
        },
        {
            name: "date",
            type: "date"
        }
    ]
});
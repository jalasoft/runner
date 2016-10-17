Ext.define("MyHealth.model.User", {
   extend: "Ext.data.Model",

    fields: [
        {
            name: "firstName",
            type: "string"
        },
        {
            name: "lastName",
            type: "string"
        },
        {
            name: "email",
            type: "string"
        },
        {
            name: "birthday",
            type: "date"
        }
    ]
});
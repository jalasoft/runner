

Ext.define("MyHealth.view.main.UserTable", {
    extend: "Ext.grid.Panel",
    
    xtype: "users",
    store: new MyHealth.store.UserStore(),
    columns: [
        {
            text: "Jmeno",
            dataIndex: "firstName",
            flex: 1,
            align: "left"
        },
        {
            text: "Prijmeni",
            dataIndex: "lastName",
            flex: 1,
            align: "left"
        },
        {
            text: "E-mail",
            dataIndex: "email",
            flex: 1,
            align: "left"
        },
        {
            text: "Datum narozeni",
            dataIndex: "birthday",
            flex: 1,
            align: "left",
            renderer: function(value, record) {
                return value.getDate() + ". " + value.getMonth() + ". " + value.getFullYear()
            }
        }
    ]

});
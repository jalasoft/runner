Ext.define("MyHealth.view.main.MainView", {
   extend: "Ext.tab.Panel",

   items: [
       {
           title: "Users",
           xtype: "users"
       },
       {
           title: "Runs",
           xtype: "runs"
       }
   ]
});
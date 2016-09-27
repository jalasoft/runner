var myStore = new MyHealth.store.RunStore();

Ext.define("MyHealth.view.main.RunTable", {
   extend: "Ext.grid.Panel",

   store: myStore, 
   controller: "run",
   
   columns: [
      {
         text: "popis",
         flex: 1,
         align: "center",
         dataIndex: "description"
      },
      {
         text: "vzdalenost",
         width: "400px",
         align: "center",
         dataIndex: "distance"
      },
      {
         text: "datum",
         width: "800px",
         align: "center",
         dataIndex: "date"
      }
   ],
   title: "Behani",
   tbar: [
      {
         xtype: "button",
         text: "Pridej beh",
         handler: "onAddRun"
      }
   ]
});
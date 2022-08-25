insert into kitchen(id, name) values(1,'Thai');
insert into kitchen(id, name) values(2,'Brazilian');

insert into restaurant(id, name, shipping_tax, kitchen_id) VALUES(1,'Thai Gourmet',12.50,1);
insert into restaurant(id, name, shipping_tax, kitchen_id) VALUES(2,'Bunbonambo chef', 5.75,1);
insert into restaurant(id, name, shipping_tax, kitchen_id) VALUES(3,'Casa da feijoada', 5.75,2);

insert into state(id, name) VALUES(1,);
insert into state(id, name) VALUES();
insert into state(id, name) VALUES();

insert into City(id, name, state) values(1,'Santa Catarina', 'SC');
insert into City(id, name, state) values(2, 'Paraná', 'PR');
insert into City(id, name, state) values(3, 'Rio Grande do Sul', 'RS');
insert into City(id, name, state) values(4, 'Minas Gerais', 'MG');
insert into City(id, name, state) values(5, 'Ceará', 'CE');

insert into PaymentWay(id, description) values(1,'Card');
insert into PaymentWay(id, description) values(2,'Money');
insert into PaymentWay(id, description) values(3,'QRcode');

insert into permission(id, name, description) values(1, 'QUERY_KITCHENS', 'available to query kitchens');
insert into permission(id, name, description) values(2, 'EDIT_KITCHENS','available to edit kitchens');
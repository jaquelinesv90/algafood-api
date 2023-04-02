insert into kitchen(id, name) values(1,'Thai');
insert into kitchen(id, name) values(2,'Brazilian');

insert into state(id, name) VALUES(1,'Santa Catarina');
insert into state(id, name) VALUES(2, 'Paraná');
insert into state(id, name) VALUES(3, 'Rio Grande do Sul');

insert into City(id, name, state_id) values(1,'Chapecó', 1);
insert into City(id, name, state_id) values(2, 'Curitiba' ,2);
insert into City(id, name, state_id) values(3, 'Porto Alegre',3);
insert into City(id, name, state_id) values(4, 'Maringá', 2);
insert into City(id, name, state_id) values(5, 'Caxias', 3);

insert into restaurant(id, name, shipping_tax, kitchen_id, zipcode_address, register_date, update_date, logradouro_address,number_address, complement_address, neighborhood_address, city_id_address)VALUES(1,'Thai Gourmet',12.50,1, '140 00',utc_timestamp, utc_timestamp ,'320', 'second building', 'budejovicka','Praha',1 );
insert into restaurant(id, name, shipping_tax, kitchen_id, zipcode_address, register_date, update_date, logradouro_address,number_address, complement_address, neighborhood_address, city_id_address) VALUES(2,'Bunbonambo chef', 5.75,1, '140 00',utc_timestamp, utc_timestamp, '320', 'second building', 'budejovicka','Praha',1 );
insert into restaurant(id, name, shipping_tax, kitchen_id, zipcode_address, register_date, update_date, logradouro_address,number_address, complement_address, neighborhood_address, city_id_address) VALUES(3,'Casa da feijoada', 5.75,2, '140 00', utc_timestamp, utc_timestamp,'320', 'second building', 'budejovicka','Praha',1 );

insert into payment_way(id, description) values(1,'Card');
insert into payment_way(id, description) values(2,'Money');
insert into payment_way(id, description) values(3,'QRcode');

insert into permission(id, name, description) values(1, 'QUERY_KITCHENS', 'available to query kitchens');
insert into permission(id, name, description) values(2, 'EDIT_KITCHENS','available to edit kitchens');
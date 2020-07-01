CREATE TABLE order_data (
	id serial primary key,
	checkout_order_id integer not null,
	client_id integer not null,
	direction varchar(50) not null,
	total_amount decimal not null,
	created timestamp not null
);
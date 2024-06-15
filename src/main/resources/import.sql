-- -- Name: customers; Type: TABLE; Schema: public; Owner: postgres
-- --

-- create table customers (
--     createdAt timestamp(6) with time zone,
--     id uuid not null,
--     email varchar(255),
--     name varchar(255),
--     primary key (id)
-- );

-- create table orders (
--     value float4 not null,
--     createdAt timestamp(6) with time zone,
--     customer_id uuid,
--     id uuid not null,
--     item uuid,
--     shippingAddress varchar(255),
--     primary key (id)
-- );

-- alter table if exists orders 
--     add constraint FKpxtb8awmi0dk6smoh2vp1litg 
--     foreign key (customer_id) 
--     references customers;

-- -- alter table if exists customers_orders 
-- --     add constraint FKcgubpw142krbvsbpfumpss2wl 
-- --     foreign key (orders_id) 
-- --     references orders;

-- -- alter table if exists customers_orders 
-- --     add constraint FKuxkwti0so6b6i5xvr8i6aq0i 
-- --     foreign key (Customer_id) 
-- --     references customers;


-- -- ALTER TABLE public.customers OWNER TO postgres;

-- --
-- -- PostgreSQL database dump complete
-- --

-- create table customers (id uuid not null, email varchar(255), fullname varchar(255), primary key (id));
-- create table orders (id uuid not null, item uuid, price float4 not null, shippingAddress varchar(255), customer_id uuid, primary key (id));
-- alter table if exists orders add constraint FKpxtb8awmi0dk6smoh2vp1litg foreign key (customer_id) references customers;
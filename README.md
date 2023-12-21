# simple_bank_springboot
Database Structure

Table manager (Bank's managers table)
Column name	    Type	        Description
id	            bigint	        id key of row - unique, not null, primary key
first_name	    varchar(255)	manager's name
last_name	    varchar(255)	manager's surname
status	        varchar(255)	manager's status from enum ManagerStatus
created_at	    timestamp	    timestamp of row creation
updated_at	    timestamp	    timestamp of last update


Table client ( Bank's clients table )
Column name	    Type	        Description
id	            bigint      	id key of entity - unique, not null, PK
status	        VARCHAR(20)	    client's status from enum ClientStatus
tax_code	    varchar(16)	    client's TAX code unique
first_name	    varchar(50)	    client's name
last_name	    varchar(50)	    client's surname
email	        varchar(60)	    client's e-mail
address	        varchar(80)	    client's address
phone	        varchar(20)	    client's phone
created_at	    timestamp	    timestamp of entity creation
updated_at	    timestamp	    timestamp of last update
manager_id	    bigint      	manager`s id FK references managers(id)
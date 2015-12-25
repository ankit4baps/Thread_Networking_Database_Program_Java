# Thread_Networking_Database_Program_Java
Client Server Programming with Java
### Requirements:
Redo the previous Customer Lookup program as a Client/Server implementation. Essentially you are
breaking up the previous lab into two different programs – a server program and a client program.
Hand in the following

1) a Server which has the data on all the customers and can perform the necessary searches. The
server will retrieve the data from the database rather than storing the data in a data structure as was
done in Lab #2. Your Server should support multiple Clients at the same time. Try using a Thread Pool
to handle thread support.

2) a Client (the GUI) which requires services (customer lookups). Use the GUI provided with Lab #2 to
enter either a phone number or a customer name. Each lookup should create a new Socket connection
to the server, send the necessary data, get back the result, print the result, and close the Socket
connection.

3) Include the SQL statements required to create the database schema, create the database table, and
create the user.

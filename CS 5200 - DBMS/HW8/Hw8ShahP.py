import mysql.connector

# Replace these values with your MySQL server information
host = "localhost"
user = "root"
password = "aH24fkWu*}Q"
database = "musicshahp"

# Establish a connection to the MySQL server
connection = mysql.connector.connect(
    host=host,
    user=user,
    password=password,
    database=database
)

curor = connection.cursor()

query = "SELECT * FROM albums"

curor.execute(query)

results = curor.fetchall()
for row in results:
	print(row)

cursor.close()
connection.close()
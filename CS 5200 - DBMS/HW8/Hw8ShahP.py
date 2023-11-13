import mysql.connector


class DatabaseManager:
    def __init__(self, host, user, password, database):
        self.host = host
        self.user = user
        self.password = password
        self.database = database
        self.connection = None
        self.cursor = None

    def connect(self):
        self.connection = mysql.connector.connect(
            host=self.host,
            user=self.user,
            password=self.password,
            database=self.database
        )
        self.cursor = self.connection.cursor()

    def execute_query(self, query):
        self.cursor.execute(query)
        return self.cursor.fetchall()

    def close_connection(self):
        if self.cursor:
            self.cursor.close()
        if self.connection:
            self.connection.close()


def main():
    # Replace these values with your MySQL server information
    host = "localhost"
    user = input("Enter MySQL username: ")
    password = input("Enter MySQL password for " + user + " : ")
    database = "musicshahp"

    # Create an instance of the DatabaseManager
    db_manager = DatabaseManager(host, user, password, database)

    try:
        # Establish a connection to the MySQL server
        db_manager.connect()

        # Execute a query
        query = "SELECT * FROM albums"
        results = db_manager.execute_query(query)

        # Print the results
        for row in results:
            print(row)

    finally:
        # Close the connection
        db_manager.close_connection()

if __name__ == "__main__":
    main()

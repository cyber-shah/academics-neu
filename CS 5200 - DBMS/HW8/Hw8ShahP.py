import mysql.connector
from DatabaseManager import DatabaseManager
import Functions

def main():
    print("Hello and welcome to the music database!")

    # continue asking until it works
    while True:
        try: 
            host = "localhost"
            user = input("Enter MySQL username: ")
            password = input("Enter MySQL password for " + user + " : ")
            database = "musicshahp"

            # Create an instance of the DatabaseManager
            db_manager = DatabaseManager(host, user, password, database)
            # Establish a connection to the MySQL server
            db_manager.connect()
            break
        

        except Exception as e:
            print("Invalid userId or password")


    # 1. print all the genres
    genre_dict = Functions.printGenres(db_manager)

    # 2. validate input
    genreInput = Functions.validateInputs(genre_dict, "Please enter a GenreID : ")





    db_manager.close_connection()



if __name__ == "__main__":
    main()

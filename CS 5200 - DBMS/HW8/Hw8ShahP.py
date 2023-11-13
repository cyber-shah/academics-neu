import mysql.connector
import Functions

def main():
    print("Hello and welcome to the music database!")

    # 1. Prompt the user for the MySQL username and password. 
    # continue asking until it works
    while True:
        try: 
            host = "localhost"
            user = input("Enter MySQL username: ")
            password = input("Enter MySQL password for " + user + ": ")
            database = "musicshahp"

            connection = mysql.connector.connect(
                host=host,
                user=user,
                password=password,
                database=database
            )

            # 2. Use the user provided username and password values to connect 
            # to the Music database you modified in homework seven.
            cursor = connection.cursor()
            break
        
        except Exception as e:
            print("Invalid userId or password")


    # 4. Print all the genres
    cursor.execute("Select gid, genre_name from genres")
    results = cursor.fetchall()
    # store everything inside a dictionary
    genre_dict = {}
    for row in results:
        genre_id, genre_name = row
        genre_dict[genre_id] = genre_name
    # Print the results
    for genre_id, genre_name in genre_dict.items():
        print(f"Genre ID: {genre_id}, Name: {genre_name}")



    # 3. Prompt the user to enter a particular music genre. 
    # Store the result in a host language variable.
    while True:
        genreInput = input("Please enter a GenreID : ")
        try:
            genreInput = int(genreInput)
            if genreInput in genre_dict:
                break
            else:
                print("Please enter a valid ID.")
        # 5. Generate an error message to standard output and re-prompt the 
        # user for input, if the user provides invalid input.
        except ValueError:
            print("Please enter a valid integer.")
    # At this point, genreInput contains a valid genre ID
    print(f"You selected: {genreInput} - {genre_dict[genreInput]}")


    # 6. Use the genre  as an argument to the song_has_genre(genre_p) . 
    # Call the procedure.
    cursor.callproc("song_has_genre", [ str(genre_dict[genreInput]) ])
    
    # 7. print the results
    print("Songs with genre: " + genre_dict[genreInput])
    for result in cursor.stored_results():
        rows = result.fetchall()
        for row in rows:
            print(row)

    # 8. Once the records are written to standard output, 
    #close the connection to the database and end the application program.
    cursor.close()
    connection.close()



if __name__ == "__main__":
    main()

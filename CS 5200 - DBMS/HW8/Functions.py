import mysql.connector
from DatabaseManager import DatabaseManager

"""
Prints genres and returns a dictionary
"""
def printGenres(db_manager):

    results = db_manager.execute_query("Select gid, genre_name from genres")
    # store everything inside a dictionary
    genre_dict = {}
    for row in results:
        genre_id, genre_name = row
        genre_dict[genre_id] = genre_name

    # Print the results
    for genre_id, genre_name in genre_dict.items():
        print(f"Genre ID: {genre_id}, Name: {genre_name}")

    return genre_dict




def validateInputs(genre_dict):
    # Validate user input for integer
    while True:
        genreInput = input("Please enter a music genre ID: ")
        try:
            genreInput = int(genreInput)
            if genreInput in genre_dict:
                break  # Exit the loop if the input is a valid ID
            else:
                print("Please enter a valid ID.")
        except ValueError:
            print("Please enter a valid integer.")


    # At this point, genreInput contains a valid genre ID
    selected_genre_name = genre_dict[genreInput]
    print(f"You selected: {selected_genre_name}")

    return genreInput

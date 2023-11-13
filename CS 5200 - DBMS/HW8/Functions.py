import mysql.connector
from DatabaseManager import DatabaseManager

"""
Prints genres and returns a dictionary
"""
def printGenres(cursor):

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

    return genre_dict


def validateInputs(input_dict, ask_string):
    # Validate user input for integer
    while True:
        userInput = input(ask_string)
        try:
            userInput = int(userInput)
            if userInput in input_dict:
                break  # Exit the loop if the input is a valid ID
            else:
                print("Please enter a valid ID.")
        except ValueError:
            print("Please enter a valid integer.")


    # At this point, genreInput contains a valid genre ID
    print(f"You selected: {userInput} - {input_dict[userInput]}")

    return userInput

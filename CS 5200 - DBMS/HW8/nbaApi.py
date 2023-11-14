# Import the json module
import json

# Import the nba_api package
import nba_api

# Get the player career stats endpoint
from nba_api.stats.endpoints import playercareerstats

# Specify the player ID for Nikola Jokic
player_id = '203999'

# Create a PlayerCareerStats object
career = playercareerstats.PlayerCareerStats(player_id=player_id)

# Get the career stats as a dictionary
career_dict = career.get_dict()

# Save the dictionary to a JSON file
with open('career_stats.json', 'w') as f:
    json.dump(career_dict, f)

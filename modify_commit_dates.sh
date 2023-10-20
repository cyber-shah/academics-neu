#!/bin/bash

# Calculate the number of commits
total_commits=60
half_commits=$((total_commits / 2))

# Identify the SHA of the commit on October 3rd
git log --since="2023-10-03" --until="2023-10-04" --pretty=format:"%H" > commits_to_change.txt

# Create a file with half of the commit SHA hashes
head -n $half_commits commits_to_change.txt > commits_to_change_half.txt

# Iterate through the commits and change their date to September 4th, 2023
while read -r commit; do
    GIT_COMMITTER_DATE="2023-09-04T12:00:00" git commit --amend --no-edit $commit
done < commits_to_change_half.txt

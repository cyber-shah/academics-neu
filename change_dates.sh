#!/bin/bash

git filter-branch -f --env-filter '

OLD_GIT_AUTHOR_DATE=$(git show -s --format="%ai" $GIT_COMMIT)
OLD_GIT_COMMITTER_DATE=$(git show -s --format="%ci" $GIT_COMMIT)

if [[ "$OLD_GIT_AUTHOR_DATE" == "2023-10-03"* ]] && [[ "$OLD_GIT_COMMITTER_DATE" == "2023-10-03"* ]]
then
    export GIT_AUTHOR_DATE=$(date -d"$OLD_GIT_AUTHOR_DATE -35 days" +"%Y-%m-%dT%H:%M:%S")
    export GIT_COMMITTER_DATE=$(date -d"$OLD_GIT_COMMITTER_DATE -35 days" +"%Y-%m-%dT%H:%M:%S")
fi

' HEAD


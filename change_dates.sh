#!/bin/bash

git filter-branch -f --env-filter '

<<<<<<< HEAD
OLD_GIT_AUTHOR_DATE=$(git show -s --format="%ai" $GIT_COMMIT)
OLD_GIT_COMMITTER_DATE=$(git show -s --format="%ci" $GIT_COMMIT)

if [[ "$OLD_GIT_AUTHOR_DATE" == "2023-10-03"* ]] && [[ "$OLD_GIT_COMMITTER_DATE" == "2023-10-03"* ]]
then
    export GIT_AUTHOR_DATE=$(date -d"$OLD_GIT_AUTHOR_DATE -35 days" +"%Y-%m-%dT%H:%M:%S")
    export GIT_COMMITTER_DATE=$(date -d"$OLD_GIT_COMMITTER_DATE -35 days" +"%Y-%m-%dT%H:%M:%S")
fi

' HEAD
=======
n=$GIT_COMMIT
i=0

while [ $i -lt 22 ]
do
    if [ $n = $GIT_COMMIT ]
    then
        export GIT_AUTHOR_DATE="2023-08-23T00:00:00"
        export GIT_COMMITTER_DATE="2023-08-23T00:00:00"
    fi
    n=$(git rev-parse $n^)
    i=$((i+1))
done

' -- --all
>>>>>>> e5ad848fb3dc0bbdf523f9e4fbb8fc9c9794e3ba


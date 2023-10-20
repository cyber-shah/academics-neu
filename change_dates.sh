#!/bin/bash

git filter-branch -f --env-filter '

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


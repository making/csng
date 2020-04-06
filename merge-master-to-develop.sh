#!/bin/bash
set -ex

git checkout master
git pull origin master
git checkout develop
git merge master
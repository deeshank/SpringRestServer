# SpringRestServer
Developed for Scanbuy Code Challenge
------------------------------------

Author: Deepakshankar [ ds494@njit.edu/ shanky.cs@gmail.com ]

This Repo contains Java code which sets up a REST based web server running using Spring framework.
Also it communicates to the backend MYSQL Database via Hibernate ORM. This is a very minimalistic
web server which accepts and returns only JSON data. 

snapshots are added to snapshot folder.

#########################################################################################################################################################################

MYSQL initialization script is available which will create the  base table with sample data -

 >> books.sql


#########################################################################################################################################################################

CURL INPUTS -

curl -H "Content-Type: application/json" -X POST -d '{"barcode":100000,"title":"C Programming","author":"Dennis Ritchie", "noOfPgs":240}' http://localhost:8080/Challenge/rest/views/insert | pjson

curl -H "Content-Type: application/json" -X POST -d '{"barcode":100001,"title":"C++ Programming","author":"Bjarne Stroustroup", "noOfPgs":540}' http://localhost:8080/Challenge/rest/views/insert | pjson

curl -H "Content-Type: application/json" -X POST -d '{"barcode":100002,"title":"Java Programming","author":"James Gosling", "noOfPgs":1020}' http://localhost:8080/Challenge/rest/views/insert | pjson

curl -H "Content-Type: application/json" -X POST -d '{"barcode":100003,"title":"Python Programming","author":"Guido Von Rossum", "noOfPgs":530}' http://localhost:8080/Challenge/rest/views/insert | pjson

curl  http://localhost:8080/Challenge/rest/views/show | pjson

#########################################################################################################################################################################

pjson is the python library to prettify json output on terminal

Installation -

on Debian based systems -
	>> sudo pip install pjson



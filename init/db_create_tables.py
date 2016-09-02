from flask import Flask
import json
from flaskext.mysql import MySQL 


MS = MySQL() 

#connect to the DB
db1 = MS.connect("localhost","root","barry1")
cursor = db1.cursor()
sql = 'CREATE DATABASE IF NOT EXISTS how_lit_db'
cursor.execute(sql)


# create table
cursor.execute("SET sql_notes = 0; ")
cursor.execute("create table IF NOT EXISTS Artist Rankings (Artist varchar(70),Rank varchar(20));")
cursor.execute("SET sql_notes = 1; ")

#insert data
cursor.execute("insert into test (Artist,Rank) values('The Weeknd','100')")

# Commit changes in the database
db.commit()

# disconnect from server
db.close()

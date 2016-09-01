from flask import Flask
from flaskext.mysql import MySQL
import collections, json

mysql = MySQL()
app = Flask(__name__)
app.config['MYSQL_DATABASE_USER'] = 'root'
app.config['MYSQL_DATABASE_PASSWORD'] = 'barry1'
app.config['MYSQL_DATABASE_DB'] = 'how_lit_db'
app.config['MYSQL_DATABASE_HOST'] = 'localhost'
mysql.init_app(app)



@app.route("/")
def hello():
    return "Artist Rankings"


#list all artist!
@app.route("/getArtists")
def get_artist():
    cursor = mysql.connect().cursor()
    cursor.execute("SELECT artist_name,artist_lit_level FROM artist_tbl" )
    data = cursor.fetchall()
    all = []
    for row in data:
    	d = collections.OrderedDict() 
    	d['name'] = row[0]
    	d['level'] = row[1]
	all.append(d)
    return json.dumps(all)

@app.route("/updateLitLevel")
def update_lit_level():
    conn = mysql.connect()
    cursor = conn.cursor()
    cursor.execute("UPDATE artist_tbl SET artist_lit_level = artist_lit_level + 1")
    conn.commit()
    cursor.execute("SELECT artist_name,artist_lit_level FROM artist_tbl" )
    data = cursor.fetchall()
    return json.dumps(data)







if __name__ == "__main__":
    app.run(debug=True)

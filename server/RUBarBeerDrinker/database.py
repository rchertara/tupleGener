from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import create_engine
from sqlalchemy import sql
from RUBarBeerDrinker import config
import array

engine = create_engine(config.database_uri) #engine to execute the queries on

def get_bars():
    with engine.connect() as con: #connection
        rs = con.execute("SELECT barName, barLicense, State from barTable;")
        return [dict(row) for row in rs]
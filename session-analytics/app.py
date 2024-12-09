from flask import Flask
import os
import json
from models.game import Game

app = Flask(__name__)

@app.route('/', methods=['POST', 'GET'])
def hello_world():
    game = Game('test', 'test', 'ps')
    return json.dumps(vars(game))

if __name__ == "__main__":
    if os.environ.get("FLASK_ENV") == "development":
        app.run(debug=True)
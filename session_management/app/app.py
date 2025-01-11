from flask import Flask
from .controllers.game_controller import game_bp
from dotenv import load_dotenv  
import os

load_dotenv()  #

app = Flask(__name__)

app.register_blueprint(game_bp, url_prefix='/games')

if __name__ == "__main__":
    if os.environ.get("FLASK_ENV") == "development":
        app.run(debug=True, port=8080)
    app.run(port=8080)
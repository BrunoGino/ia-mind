from flask import Flask
from controllers.session_controller import session_blueprint
from dotenv import load_dotenv
import os

load_dotenv()

app = Flask(__name__)

app.register_blueprint(game_bp, url_prefix='/games')
app.register_blueprint(session_blueprint, url_prefix='/sessions')

if __name__ == "__main__":
    if os.environ.get("FLASK_ENV") == "development":
        app.run(debug=True)
from flask import Blueprint, request, jsonify
from ..models.game import Game

game_bp = Blueprint('game', __name__)

class GameController:
    @game_bp.route('/', methods=['POST'])
    def create_game():
        data = request.get_json()
        # ... logic to create a new game using data ...
        return jsonify({'message': 'Game created'}), 201
    
    @game_bp.route('/<int:game_id>', methods=['PUT'])
    def update_game(game_id):
        data = request.get_json()
        # ... logic to update the game with game_id using data ...
        return jsonify({'message': 'Game updated'}), 200

    @game_bp.route('/<int:game_id>', methods=['DELETE'])
    def delete_game(game_id):
        # ... logic to delete the game with game_id ...
        return jsonify({'message': 'Game deleted'}), 204
    
    @game_bp.route('/<int:game_id>', methods=['PATCH'])
    def partially_update_game(game_id):
        data = request.get_json()
        # ... logic to partially update the game with game_id using data ...
        return jsonify({'message': 'Game partially updated'}), 200

    @game_bp.route('/', methods=['GET'])
    def get_games():
        games = [
            Game('test', 'test', 'ps'),
            Game('test', 'test', 'xbox')
        ]
        return jsonify([game.__dict__ for game in games])
    
    @game_bp.route('/<int:game_id>', methods=['GET'])
    def get_game(game_id):
        game = Game('test', 'test', 'ps')
        return jsonify(game.__dict__)

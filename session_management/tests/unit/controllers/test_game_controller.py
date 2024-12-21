import pytest
from app.controllers.game_controller import GameController
from app.models.game import Game 


@pytest.fixture
def game_controller():
    return GameController()

def test_create_game(game_controller, mocker):
    mock_request = mocker.Mock(get_json=lambda: {'name': 'New Game', 'genre': 'Action', 'platform': 'PC'})
    mock_jsonify = mocker.patch('app.controllers.game_controller.jsonify')

    response = game_controller.create_game(request=mock_request)  

    mock_jsonify.assert_called_with({'message': 'Game created'})
    assert response[1] == 201

def test_update_game(game_controller, mocker):
    mock_request = mocker.Mock(get_json=lambda: {'name': 'Updated Game', 'genre': 'Adventure'})
    mock_jsonify = mocker.patch('app.controllers.game_controller.jsonify')

    response = game_controller.update_game(game_id=1, request=mock_request)

    mock_jsonify.assert_called_with({'message': 'Game updated'})
    assert response[1] == 200

def test_delete_game(game_controller, mocker):
    mock_jsonify = mocker.patch('app.controllers.game_controller.jsonify')

    response = game_controller.delete_game(game_id=1)

    mock_jsonify.assert_called_with({'message': 'Game deleted'})
    assert response[1] == 204

def test_partially_update_game(game_controller, mocker):
    mock_request = mocker.Mock(get_json=lambda: {'genre': 'RPG'})
    mock_jsonify = mocker.patch('app.controllers.game_controller.jsonify')

    response = game_controller.partially_update_game(game_id=1, request=mock_request)

    mock_jsonify.assert_called_with({'message': 'Game partially updated'})
    assert response[1] == 200

def test_get_games(game_controller):
    response = game_controller.get_games()
    assert response[1] == 200
    assert len(response[0].json) == 2

def test_get_game(game_controller):
    response = game_controller.get_game(game_id=1)
    assert response[1] == 200
    assert response[0].json['name'] == 'test'
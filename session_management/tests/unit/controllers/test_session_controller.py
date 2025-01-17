import json
from app.app import app  
from unittest.mock import patch

app.testing = True

def test_get_session():
    # Simula a função get_session
    with patch("app.models.session_model.get_session") as mock_get_session:
        mock_get_session.return_value = {
            "id": "123",
            "student": {
                "id": "stu1",
                "ra": "2021001",
                "fullName": "Maria Silva"
            },
            "institution": {
                "id": "inst1",
                "name": "Instituto ABC",
                "city": "São Paulo",
                "state": "SP",
                "country": "Brasil"
            },
            "dateTime": "2025-01-12T15:30:00",
            "professional": {
                "id": "prof1",
                "fullName": "Dr. João Souza"
            }
        }

        with app.test_client() as client:
            response = client.get("/sessions/123")
            assert response.status_code == 200
            data = json.loads(response.data)
            assert data["id"] == "123"
            assert data["student"]["fullName"] == "Maria Silva"

def test_create_session():
    # Simula a função create_session
    with patch("app.models.session_model.create_session") as mock_create_session:
        mock_create_session.return_value = "new_session_id"

        with app.test_client() as client:
            payload = {
                "student": {
                    "id": "stu1",
                    "ra": "2021001",
                    "fullName": "Maria Silva"
                },
                "institution": {
                    "id": "inst1",
                    "name": "Instituto ABC",
                    "city": "São Paulo",
                    "state": "SP",
                    "country": "Brasil"
                },
                "professional": {
                    "id": "prof1",
                    "fullName": "Dr. João Souza"
                }
            }
            response = client.post("/sessions", json=payload)
            assert response.status_code == 201
            data = json.loads(response.data)
            assert data["id"] == "new_session_id"

def test_delete_session():
    # Simula a função delete_session
    with patch("app.models.session_model.delete_session") as mock_delete_session:
        mock_delete_session.return_value = True

        with app.test_client() as client:
            response = client.delete("/sessions/123")
            assert response.status_code == 200
            data = json.loads(response.data)
            assert data["message"] == "Session deleted"

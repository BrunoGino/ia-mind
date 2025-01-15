import boto3
import pytest
import json
from app.app import app
from moto import mock_dynamodb2

@pytest.fixture
def dynamodb_setup():
    
    with mock_dynamodb2():
        dynamodb = boto3.resource('dynamodb', region_name='us-east-1')

        
        table = dynamodb.create_table(
            TableName='iamind-sessions',
            KeySchema=[
                {'AttributeName': 'id', 'KeyType': 'HASH'}
            ],
            AttributeDefinitions=[
                {'AttributeName': 'id', 'AttributeType': 'S'}
            ],
            ProvisionedThroughput={
                'ReadCapacityUnits': 5,
                'WriteCapacityUnits': 5
            }
        )

        
        table.put_item(
            Item={
                'id': '123',
                'student': json.dumps({
                    'id': 'stu1',
                    'ra': '2021001',
                    'fullName': 'Maria Silva'
                }),
                'institution': json.dumps({
                    'id': 'inst1',
                    'name': 'Instituto ABC',
                    'city': 'São Paulo',
                    'state': 'SP',
                    'country': 'Brasil'
                }),
                'dateTime': '2025-01-12T15:30:00',
                'professional': json.dumps({
                    'id': 'prof1',
                    'fullName': 'Dr. João Souza'
                })
            }
        )

        yield table

@pytest.fixture
def client():
    app.testing = True
    with app.test_client() as client:
        yield client

def test_get_session(dynamodb_setup, client):
    response = client.get('/sessions/123')
    assert response.status_code == 200
    data = json.loads(response.data)
    assert data['id'] == '123'
    assert data['student']['fullName'] == 'Maria Silva'

def test_delete_session(dynamodb_setup, client):
    response = client.delete('/sessions/123')
    assert response.status_code == 200
    data = json.loads(response.data)
    assert data['message'] == 'Session deleted'


    dynamodb = boto3.resource('dynamodb', region_name='us-east-1')
    table = dynamodb.Table('Sessions')
    result = table.get_item(Key={'id': '123'})
    assert 'Item' not in result

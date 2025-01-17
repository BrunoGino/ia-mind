from flask import Blueprint, request, jsonify
import boto3
from uuid import uuid4
from datetime import datetime

# Configuração do blueprint
session_blueprint = Blueprint("sessions", __name__)

# Configuração do DynamoDB
dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('iamind-sessions')  # Nome da tabela no DynamoDB que o bruno passou

# GET /sessions/<id>
@session_blueprint.route('/sessions/<string:id>', methods=['GET'])
def get_session(id):
    try:
        response = table.get_item(Key={'id': id})
        if 'Item' in response:
            return jsonify(response['Item']), 200
        else:
            return jsonify({"error": "Session not found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# POST /sessions
@session_blueprint.route('/sessions', methods=['POST'])
def create_session():
    try:
        data = request.get_json()
        session_id = str(uuid4())
        data['id'] = session_id
        data['dateTime'] = datetime.utcnow().isoformat()  # Timestamp atual
        table.put_item(Item=data)
        return jsonify(data), 201
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# DELETE /sessions/<id>
@session_blueprint.route('/sessions/<string:id>', methods=['DELETE'])
def delete_session(id):
    try:
        response = table.delete_item(Key={'id': id})
        if response.get('ResponseMetadata', {}).get('HTTPStatusCode') == 200:
            return jsonify({"message": "Session deleted successfully"}), 200
        else:
            return jsonify({"error": "Session not found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# PUT /sessions/<id>
@session_blueprint.route('/sessions/<string:id>', methods=['PUT'])
def update_session(id):
    try:
        data = request.get_json()
        # UpdateExpression e valores dinâmicos
        update_expression = "SET "
        expression_values = {}
        for key, value in data.items():
            update_expression += f"#{key} = :{key}, "
            expression_values[f":{key}"] = value
        update_expression = update_expression.rstrip(", ")  # Remove vírgula extra

        expression_attribute_names = {f"#{key}": key for key in data.keys()}

        response = table.update_item(
            Key={'id': id},
            UpdateExpression=update_expression,
            ExpressionAttributeNames=expression_attribute_names,
            ExpressionAttributeValues=expression_values,
            ReturnValues="ALL_NEW"
        )
        return jsonify(response.get("Attributes")), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500

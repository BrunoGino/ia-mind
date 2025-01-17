import boto3
from botocore.exceptions import ClientError
import uuid
from datetime import datetime


class SessionModel:
    def __init__(self, table_name="iamind-sessions"):
        # Inicializa o recurso DynamoDB e define a tabela
        dynamodb = boto3.resource("dynamodb", region_name="us-east-1")
        self.table = dynamodb.Table(table_name)

    def create_session(self, student, institution, professional):
        """
        Cria um registro de sessão no DynamoDB.

        :param student: Um dicionário com os dados do aluno.
        :param institution: Um dicionário com os dados da instituição.
        :param professional: Um dicionário com os dados do profissional.
        :return: O registro criado ou uma mensagem de erro.
        """
        session_id = str(uuid.uuid4())
        date_time = datetime.utcnow().isoformat()

        item = {
            "id": session_id,
            "student": student,
            "institution": institution,
            "professional": professional,
            "dateTime": date_time,
        }

        try:
            self.table.put_item(Item=item)
            return item
        except ClientError as e:
            print(f"Erro ao criar sessão: {e.response['Error']['Message']}")
            return None

    def get_session(self, session_id):
        """
        Busca uma sessão pelo ID no DynamoDB.

        :param session_id: O ID da sessão a ser buscada.
        :return: Os dados da sessão ou uma mensagem de erro.
        """
        try:
            response = self.table.get_item(Key={"id": session_id})
            return response.get("Item", None)
        except ClientError as e:
            print(f"Erro ao buscar sessão: {e.response['Error']['Message']}")
            return None

    def delete_session(self, session_id):
        """
        Exclui uma sessão pelo ID no DynamoDB.

        :param session_id: O ID da sessão a ser excluída.
        :return: True se a exclusão foi bem-sucedida, False caso contrário.
        """
        try:
            self.table.delete_item(Key={"id": session_id})
            return True
        except ClientError as e:
            print(f"Erro ao excluir sessão: {e.response['Error']['Message']}")
            return False

    def update_session(self, session_id, updates):
        """
        Atualiza um registro de sessão no DynamoDB.

        :param session_id: O ID da sessão a ser atualizada.
        :param updates: Um dicionário contendo os campos a serem atualizados.
        :return: O registro atualizado ou uma mensagem de erro.
        """
        try:
            # Construindo a expressão de atualização
            update_expression = "SET "
            expression_values = {}
            expression_attribute_names = {}

            for key, value in updates.items():
                update_expression += f"#{key} = :{key}, "
                expression_values[f":{key}"] = value
                expression_attribute_names[f"#{key}"] = key

            update_expression = update_expression.rstrip(", ")  # Remove a vírgula extra

            # Atualizando o item
            response = self.table.update_item(
                Key={"id": session_id},
                UpdateExpression=update_expression,
                ExpressionAttributeNames=expression_attribute_names,
                ExpressionAttributeValues=expression_values,
                ReturnValues="ALL_NEW",
            )
            return response.get("Attributes", None)
        except ClientError as e:
            print(f"Erro ao atualizar sessão: {e.response['Error']['Message']}")
            return None
